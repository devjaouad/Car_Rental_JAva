/*
 *Developer: MOUAOU JAOUAD
 *Date: 12/01/2015
 */
package Frames;

import BusinessClasses.UserAccountList;
import java.awt.Color;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public final class MainMenu extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    public static UserAccountList objUserAccountList = new UserAccountList();

    public MainMenu() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            initComponents();
            this.setLocationRelativeTo(null);
            this.LaodMainLogo();
            UIDefaults defaults = UIManager.getLookAndFeelDefaults();
            if (defaults.get("Table.alternateRowColor") == null) {
                defaults.put("Table.alternateRowColor", new Color(206, 250, 249));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    Login dg = new Login(new javax.swing.JFrame(), true);

    public void Activate() {

    }

    public void DisableMenuBtns() {
        this.UserAccountManagementBtn.setEnabled(false);
        this.EmployeeManagementBtn.setEnabled(false);
        this.CarRentalBtn.setEnabled(false);
        this.CarReturnBtn.setEnabled(false);
        this.AboutBtn.setEnabled(false);
        this.CustomerBtn.setEnabled(false);
        this.ExitBtn.setEnabled(false);

    }

    public void EnableMenuBtns() {
        this.UserAccountManagementBtn.setEnabled(true);
        this.EmployeeManagementBtn.setEnabled(true);
        this.CarRentalBtn.setEnabled(true);
        this.CarReturnBtn.setEnabled(true);
        this.AboutBtn.setEnabled(true);
        this.CustomerBtn.setEnabled(true);
        this.ExitBtn.setEnabled(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainMenuPanel = new javax.swing.JPanel();
        OtherPanel = new javax.swing.JPanel();
        AboutBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ExitBtn = new javax.swing.JButton();
        POSPanel = new javax.swing.JPanel();
        CarRentalBtn = new javax.swing.JButton();
        CarReturnBtn = new javax.swing.JButton();
        CustomerBtn = new javax.swing.JButton();
        BackEndPanel = new javax.swing.JPanel();
        UserAccountManagementBtn = new javax.swing.JButton();
        EmployeeManagementBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        LoggesAsTxt = new javax.swing.JTextField();
        MainDesktopPanel = new javax.swing.JPanel();
        MainDesktopPane = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Main Menu - Car Rental v1.0");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        MainMenuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        MainMenuPanel.setFocusable(false);

        OtherPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Others's"));

        AboutBtn.setText("About");
        AboutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("<html><u>Copyright © 2015 M.Jaouad</u></html>");

        ExitBtn.setForeground(new java.awt.Color(255, 51, 51));
        ExitBtn.setText("Log Out");
        ExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OtherPanelLayout = new javax.swing.GroupLayout(OtherPanel);
        OtherPanel.setLayout(OtherPanelLayout);
        OtherPanelLayout.setHorizontalGroup(
            OtherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OtherPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OtherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ExitBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(AboutBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(OtherPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OtherPanelLayout.setVerticalGroup(
            OtherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OtherPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AboutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        POSPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Retail Point of Sales"));

        CarRentalBtn.setText("Car Rental");
        CarRentalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CarRentalBtnActionPerformed(evt);
            }
        });

        CarReturnBtn.setText("Car Return");
        CarReturnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CarReturnBtnActionPerformed(evt);
            }
        });

        CustomerBtn.setText("Customer Management");
        CustomerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout POSPanelLayout = new javax.swing.GroupLayout(POSPanel);
        POSPanel.setLayout(POSPanelLayout);
        POSPanelLayout.setHorizontalGroup(
            POSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(POSPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(POSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CarRentalBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CarReturnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CustomerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addContainerGap())
        );
        POSPanelLayout.setVerticalGroup(
            POSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(POSPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CarRentalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CarReturnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CustomerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        BackEndPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Back-End Management"));

        UserAccountManagementBtn.setText("User Account Management");
        UserAccountManagementBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserAccountManagementBtnActionPerformed(evt);
            }
        });

        EmployeeManagementBtn.setText("Employee Management");
        EmployeeManagementBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmployeeManagementBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BackEndPanelLayout = new javax.swing.GroupLayout(BackEndPanel);
        BackEndPanel.setLayout(BackEndPanelLayout);
        BackEndPanelLayout.setHorizontalGroup(
            BackEndPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackEndPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BackEndPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UserAccountManagementBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(EmployeeManagementBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        BackEndPanelLayout.setVerticalGroup(
            BackEndPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackEndPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UserAccountManagementBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmployeeManagementBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Logged in as:");

        LoggesAsTxt.setEditable(false);
        LoggesAsTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoggesAsTxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainMenuPanelLayout = new javax.swing.GroupLayout(MainMenuPanel);
        MainMenuPanel.setLayout(MainMenuPanelLayout);
        MainMenuPanelLayout.setHorizontalGroup(
            MainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OtherPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(POSPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, MainMenuPanelLayout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(LoggesAsTxt))
                        .addComponent(BackEndPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MainMenuPanelLayout.setVerticalGroup(
            MainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LoggesAsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(BackEndPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(POSPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OtherPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        MainDesktopPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainDesktopPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));

        MainDesktopPane.setBackground(new java.awt.Color(255, 255, 255));
        MainDesktopPane.setPreferredSize(new java.awt.Dimension(450, 350));

        javax.swing.GroupLayout MainDesktopPaneLayout = new javax.swing.GroupLayout(MainDesktopPane);
        MainDesktopPane.setLayout(MainDesktopPaneLayout);
        MainDesktopPaneLayout.setHorizontalGroup(
            MainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );
        MainDesktopPaneLayout.setVerticalGroup(
            MainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout MainDesktopPanelLayout = new javax.swing.GroupLayout(MainDesktopPanel);
        MainDesktopPanel.setLayout(MainDesktopPanelLayout);
        MainDesktopPanelLayout.setHorizontalGroup(
            MainDesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
            .addGroup(MainDesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MainDesktopPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(MainDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        MainDesktopPanelLayout.setVerticalGroup(
            MainDesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(MainDesktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MainDesktopPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(MainDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainDesktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MainDesktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MainMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UserAccountManagementBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserAccountManagementBtnActionPerformed
        try {

            UserAccountManagement useraccount = new UserAccountManagement();
            MainDesktopPane.add(useraccount);
            useraccount.setMaximum(true);
            useraccount.show();
            this.DisableMenuBtns();

        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_UserAccountManagementBtnActionPerformed

    private void EmployeeManagementBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmployeeManagementBtnActionPerformed
        try {

            EmployeeManagement emp = new EmployeeManagement();
            MainDesktopPane.add(emp);
            emp.setMaximum(true);
            emp.show();
            this.DisableMenuBtns();

        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EmployeeManagementBtnActionPerformed

    private void AboutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutBtnActionPerformed
        About dialog = new About(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_AboutBtnActionPerformed

    private void ExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBtnActionPerformed

        this.dispose();
        Login dialog = new Login(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_ExitBtnActionPerformed

    private void CarRentalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CarRentalBtnActionPerformed
        try {

            CarRental cr = new CarRental();
            MainDesktopPane.add(cr);
            cr.setMaximum(true);
            cr.show();
            this.DisableMenuBtns();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CarRentalBtnActionPerformed

    private void CarReturnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CarReturnBtnActionPerformed
        try {

            CarReturn crt = new CarReturn();
            MainDesktopPane.add(crt);
            crt.setMaximum(true);
            crt.show();
            this.DisableMenuBtns();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CarReturnBtnActionPerformed

    private void CustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerBtnActionPerformed
        try {

            CustomerManagement cm = new CustomerManagement();
            MainDesktopPane.add(cm);
            cm.setMaximum(true);
            cm.show();
            this.DisableMenuBtns();

        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CustomerBtnActionPerformed

    private void LoggesAsTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoggesAsTxtActionPerformed

    }//GEN-LAST:event_LoggesAsTxtActionPerformed
    public void LaodMainLogo() {
        try {

            Welcome wel = new Welcome();
            MainDesktopPane.add(wel);
            wel.setMaximum(true);
            wel.show();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AboutBtn;
    private javax.swing.JPanel BackEndPanel;
    private javax.swing.JButton CarRentalBtn;
    private javax.swing.JButton CarReturnBtn;
    private javax.swing.JButton CustomerBtn;
    private javax.swing.JButton EmployeeManagementBtn;
    private javax.swing.JButton ExitBtn;
    public javax.swing.JTextField LoggesAsTxt;
    private javax.swing.JDesktopPane MainDesktopPane;
    private javax.swing.JPanel MainDesktopPanel;
    private javax.swing.JPanel MainMenuPanel;
    private javax.swing.JPanel OtherPanel;
    private javax.swing.JPanel POSPanel;
    public javax.swing.JButton UserAccountManagementBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
