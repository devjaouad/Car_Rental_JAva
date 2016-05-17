/*
 *Developer: MOUAOU JAOUAD
 *Date: 03/01/2015
 */
package MainScreen;

import Frames.Login;
 
public class BuisinessApp {
 
    public static void main(String[] args) {
   
        //This is the main class that will call the startup from "Login"
        Login dialog = new Login(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
}

    
}