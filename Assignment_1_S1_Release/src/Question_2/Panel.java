/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author chg
 */
public class Panel extends JPanel implements KeyListener, ComponentListener {

    private final LinkedList<Phone> phoneThreadWrapper; // store threads instead of phones
    RepairShop shop;
    ThreadCount count;
    private final int infectionRange = 30;
    int infected = 0;
    int healthy = 0;
    int repair = 0;
    boolean repairProcessActive = false;

    public Panel() {
        count = new ThreadCount(this);
        shop = new RepairShop();
        phoneThreadWrapper = new LinkedList<>(); // will store the phoneThread
        setLayout(null);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.addComponentListener(this);
        this.add(shop);

        Timer timer = new Timer(10, (ActionEvent e) -> {
            phone_virus_transmission();
            phoneStateCount();
            repaint();

        });
        timer.start();

    }

    public int getPhoneThreadSize() {
        return phoneThreadWrapper.size();
    }

    private void add_phone() {
        // create new phone object
        Phone phone = new Phone(shop, this);
        phone.setRange(this.getWidth(), this.getHeight());
        phone.randomizeStartPosition(this.getWidth(), this.getHeight());

        phoneThreadWrapper.add(phone);
        this.add(phone);
    }

    public void removePhone(Phone phone) {
        phoneThreadWrapper.remove(phone);
    }

    public void virus_phone() {
        if (this.infected == 0) {
            int index = (int) (Math.random() * phoneThreadWrapper.size());
            Phone A = phoneThreadWrapper.get(index);
            A.setInfected(true);
        }
    }

    public void goToRepair() {
        synchronized (shop) {
            if (this.repair == 0) {
                int index = -1;
                do {
                    index = (int) (Math.random() * phoneThreadWrapper.size());
                } while (!phoneThreadWrapper.get(index).infected);
                Phone phoneToRepair = phoneThreadWrapper.get(index);
                phoneToRepair.setRepair(true);
            }
        }
    }

    public void phoneStateCount() {
        this.healthy = 0;
        this.infected = 0;
        this.repair = 0;

        for (Phone p : phoneThreadWrapper) {
            if (p.healthy) {
                this.healthy++;
            }
            if (p.infected) {
                this.infected++;
            }
            if (p.inRepair) {
                this.repair++;
            }
        }
        if (this.infected > 2 && !this.repairProcessActive) {
            goToRepair();
        }
        count.updateThreadCount(this.healthy, this.infected, this.repair);
    }

    public void phone_virus_transmission() {
        for (Phone A : phoneThreadWrapper) {
            if (A.isInfected()) {
                for (Phone B : phoneThreadWrapper) {
                    if (A != B && !B.isInfected()) { // Skip if already infected
                        int distance = A.distanceBetweenPhone(B);
                        if (distance <= infectionRange) {
                            if (B.healthy) {
                                B.setInfected(true);
                                break; // other thread can check as well.
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            add_phone();
        }
        if (e.getKeyCode() == KeyEvent.VK_V) {
            virus_phone();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent ce) {
        if (count != null) {
            Dimension panelSize = this.getSize();
            count.setBounds(panelSize.width - count.getWidth(),
                    0, count.getWidth(), count.getHeight());
            this.add(count);
        }
    }

    @Override
    public void componentMoved(ComponentEvent ce) {

    }

    @Override
    public void componentShown(ComponentEvent ce) {

    }

    @Override
    public void componentHidden(ComponentEvent ce) {

    }
}
