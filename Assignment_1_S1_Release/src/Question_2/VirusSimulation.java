/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question_2;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author chg
 */
public class VirusSimulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            String message = "<html><body style='width:250px;text-align:center;line-height:2;'>"
                    + "This is a Cellphone Virus Simulation.<br><br>"
                    + "Press \"ARROW UP\" to add cellphone and then<br><br>"
                    + "Press \"V\" to infect one phone randomly<br><br>";
                   
            JOptionPane.showMessageDialog(null, message, "Virus Simulation", JOptionPane.PLAIN_MESSAGE);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occured" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("An error occured: "+e.getMessage());
        }

        new Frame();
        
    }
    
}
