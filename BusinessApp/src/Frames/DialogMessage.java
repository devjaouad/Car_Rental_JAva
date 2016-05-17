/*
 *Developer: MOUAOU JAOUAD
 *Date: 12/01/2015
 */
package Frames;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DialogMessage {
   //This a custom dialog message
    final JDialog dialog = new JDialog();

    public void Message(String Message) {        
        dialog.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(dialog, Message);

    }

}
