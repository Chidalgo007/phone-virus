/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 *
 * @author chg
 */
public class RepairShop extends JLayeredPane {

    private final int width = 100;
    private final int height = 120;


    public RepairShop() {
        setLayout(new BorderLayout());
        setBounds(0, 0, width, height);
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.black, 2));

        JLabel label = new JLabel("Repair Shop");
        label.setForeground(Color.black);
        label.setBackground(Color.lightGray);
        label.setOpaque(true);
        label.setPreferredSize(new Dimension(this.getWidth(), 20));
        label.setHorizontalAlignment(JLabel.CENTER);

        this.add(label, BorderLayout.NORTH);
        setVisible(true);
    }
}
