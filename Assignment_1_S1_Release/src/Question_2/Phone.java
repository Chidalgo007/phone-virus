/*
Question:   "Which Object(s) have you chosen for synchronize? why?
Answer:     I have chosen to synchronize the shop object, in the Panel class.
            I also used i couple og locks in setRepair and repairTimeDown method
Why:        because this is the sheared object that the thread are trying to access into, 
            when is call it from the panel class.
*/

package Question_2;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Phone extends JLabel implements Runnable {

    int x;
    int y;
    int vx;
    int vy;
    int delay = 10;
    int width;
    int height;
    boolean infected = false;
    boolean inRepair = false;

    boolean healthy = true;
    int life = 500;
    int time = 4;
    Timer repairTimer;
    Timer lifeCount;
    ImageIcon icon;
    ImageIcon iconGreen;
    ImageIcon iconYellow;
    ImageIcon iconRed;

    private RepairShop shop;
    private Panel panel;
    private Thread thread;
    private volatile boolean running = true;
    private final Lock lock = new ReentrantLock();

    public Phone(RepairShop shop, Panel panel) {

        this.panel = panel;
        this.shop = shop;
        assignVXandVYrandomValues();

        // ---- random phone movement -----------------------------
        Timer velocityTimer = new Timer(3000, (ActionEvent e) -> {
            assignVXandVYrandomValues();
        });
        velocityTimer.start();
        // --------------------------------------------------------
        iconGreen = new ImageIcon("green_phone.png");
        iconYellow = new ImageIcon("yellow_phone.png");
        iconRed = new ImageIcon("red_phone.png");
        icon = iconGreen;
        setIcon(icon);
        setSize(icon.getIconWidth(), icon.getIconHeight());

        // create and start thread
        thread = new Thread(this);
        thread.start();

    }

//--------------------------------------------------------------
    public void assignVXandVYrandomValues() {
        do {
            vx = (int) (Math.random() * 5) - 2;
            vy = (int) (Math.random() * 5) - 2;
        } while (vx == 0 || vy == 0);
    }

    public void setRange(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        while (running) {

            if (!this.inRepair) {

                x += vx;
                y += vy;

                // Check if the phone hits the panel boundaries
                if (x < 0 || x + getWidth() > panel.getWidth()) {
                    vx = -vx; // Reverse horizontal velocity
                }
                if (y < 0 || y + getHeight() > panel.getHeight()) {
                    vy = -vy; // Reverse vertical velocity
                }

                //-----------------------------------------------
                if (x + shop.getWidth() >= shop.getX() && x <= shop.getX() + shop.getWidth()
                        && y + shop.getHeight() >= shop.getY() && y <= shop.getY() + shop.getHeight()) {
                    if (x + shop.getWidth() >= shop.getX() || x <= shop.getX() + shop.getWidth()) {
                        vx = -vx;
                    }
                    if (y + 100 >= shop.getY() || y <= shop.getY() + shop.getHeight()) {
                        vy = -vy;
                    }
                }
                setLocation(x, y);
                //----------------------------------------------
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Phone.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void randomizeStartPosition(int Panelwidth, int Panelheight) {
        // randomize the initial position of the phone
        x = (int) (Math.random() * (Panelwidth - this.getWidth())); //new x;
        y = (int) (Math.random() * (Panelheight - this.getHeight()));//new y;
        if (x < shop.getWidth()) {
            x = (int) (x / 4 + shop.getWidth());
        }

    }

    public int distanceBetweenPhone(Phone phoneThread) {
        // Calculate distance from each corner of 'this' phone thread to 'phoneThread'
        int[] cornerDeltasX = {0, getWidth(), 0, getWidth()};
        int[] cornerDeltasY = {0, 0, getHeight(), getHeight()};

        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int deltaX = this.getX() + cornerDeltasX[i] - phoneThread.getX();
            int deltaY = this.getY() + cornerDeltasY[i] - phoneThread.getY();
            int distance = (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            minDistance = Math.min(minDistance, distance);
        }

        return minDistance;

    }

    public void setIconImg(ImageIcon color) {
        icon = color;
        setIcon(icon);
        repaint();
    }

    public void setInfected(Boolean infected) {
        this.healthy = false;
        this.inRepair = false;
        this.infected = infected;
        setIcon(iconRed);
        startLifeTimer();
        repaint();
    }

    public void setRepair(Boolean repair) {
        lock.lock();
        try {
            this.infected = false;
            this.healthy = false;
            this.inRepair = repair;
            panel.repairProcessActive = true;
            setIcon(iconYellow);
            if (lifeCount != null) {
                lifeCount.stop();
            }
            this.life = 500;
            shop.add(this);
            startRepairTimer();
            repaint();
        } finally {
            lock.unlock();
        }
    }

    private void startDelayBeforeRepair() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(() -> {
            if(panel.infected>0){
            panel.goToRepair(); // Start the repair timer after the delay
            }else {
                panel.repairProcessActive=false;
            }
        }, 2, TimeUnit.SECONDS); // 2-second delay
        executor.shutdown(); // Shutdown the executor after the task is completed
    }

    public void setHealthy(Boolean healthy) {
        this.inRepair = false;
        this.infected = false;
        this.healthy = healthy;
        setIcon(iconGreen);
        repaint();
    }

    public Boolean isInfected() {
        return this.infected;
    }

    public void startRepairTimer() {
        repairTimer = new Timer(1000, (ActionEvent e) -> {
            repairTimeDown();
        });
        repairTimer.start();
    }

    public void repairTimeDown() {
        if (time != 0) {
            time--;
        } else {
            this.setHealthy(true);
            lock.lock();
            try {
                panel.repair--;
            } finally {
                lock.unlock();
            }
            randomizeStartPosition(panel.getWidth(), panel.getHeight());
            panel.add(this);
            repairTimer.stop();
            startDelayBeforeRepair();
        }
    }

    public void startLifeTimer() {
        lifeCount = new Timer(20, (ActionEvent e) -> {
            lifeDown();
        });
        lifeCount.start();
    }

    public void lifeDown() {
        if (this.infected) {
            life--;
            if (life <= 0) {
                Container parent = this.getParent();
                stopThread();
                if (parent != null) {
                    parent.remove(this);
                    parent.revalidate();
                    parent.repaint();
                    panel.removePhone(this);
                }
            }
        }
    }

    private void stopThread() {
        running = false;
        try {
            thread.join(); // Wait for the thread to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
