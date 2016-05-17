/*
 *Developer: MOUAOU JAOUAD
 *Date: 12/01/2015
 */
package Frames;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import BusinessClasses.UserAccount;
import BusinessClasses.UserAccountList;
import java.awt.Component;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class UserAccountManagement extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;
    public static UserAccountList objUserAccountList = new UserAccountList();
    DialogMessage dm = new DialogMessage();
    MainMenu main = new MainMenu();

    public UserAccountManagement() {
        try {
            objUserAccountList.load();
            initComponents();
            this.setBorder(null);
            this.AllUserAccounts();
            BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
            bi.setNorthPane(null);
        } catch (Exception ex) {
            Logger.getLogger(UserAccountManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void AllUserAccounts() {
        try {
            UserAccount objUserAccount = UserAccount.getInstance();

            ArrayList<UserAccount> records = objUserAccount.getAllUserAccounts();

            Object[] tableColumnName = new Object[4];

            tableColumnName[0] = "USER ID";
            tableColumnName[1] = "USERNAME";
            tableColumnName[2] = "PASSWORD";
            tableColumnName[3] = "EMAIL";

            DefaultTableModel tbd = new DefaultTableModel();

            tbd.setColumnIdentifiers(tableColumnName);

            this.UserAccountTable.setModel(tbd);

            Object[] RowRec = new Object[11];

            for (int i = 0; i < records.size(); i++) {

                RowRec[0] = records.get(i).getUserAccountID();
                RowRec[1] = records.get(i).getUsername().toUpperCase();
                RowRec[2] = records.get(i).getPassword().toUpperCase();
                RowRec[3] = records.get(i).getEmail().toUpperCase();

                tbd.addRow(RowRec);

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            dm.Message("An error has occurred. Please try again");
        }
    }

    private void getUserAccountRecord() {

        try {

            UserAccount objUserAccount = UserAccount.getInstance();
            String username;

            username = this.SearchUsernameTxt.getText();

            objUserAccount.load(username);

            this.UsernameTxt.setText(objUserAccount.getUsername());            
            this.PasswordTxt.setText(objUserAccount.getPassword());
            this.EmailTxt.setText(objUserAccount.getEmail());

        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }
    }

    private void addUserAccountRecord() {

        try {

            String username, password, email;

            username = this.UsernameTxt.getText();
            password = this.PasswordTxt.getText();
            email = this.EmailTxt.getText();
            UserAccount NewUserAccount = UserAccount.getInstance();
            NewUserAccount.setUsername(username);
            NewUserAccount.setPassword(password);
            NewUserAccount.setEmail(email);
            NewUserAccount.insert();
            this.AllUserAccounts();

            this.ClearData();

        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }
    }

    private void addUserAccountRecordValues() {

        try {

            String username, password, email;
            boolean success;

            username = this.UsernameTxt.getText();
            password = this.PasswordTxt.getText();
            email = this.EmailTxt.getText();
            success = objUserAccountList.add(username, password, email);
            if (success) {
                dm.Message("User Account Added");
                this.ClearData();
            } else {
                dm.Message("No available space!");
            }
        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }
    }

    private void editUserAccountRecord() {

        try {

            String search, username, password, email;
            
            
            search = this.SearchUsernameTxt.getText();
            username = this.UsernameTxt.getText();
            password = this.PasswordTxt.getText();
            email = this.EmailTxt.getText();
            UserAccount NewUserAccount = UserAccount.getInstance();
            NewUserAccount.setUsername(username);
            NewUserAccount.setPassword(password);
            NewUserAccount.setEmail(email);

            NewUserAccount.update(search);
            this.AllUserAccounts();

        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }
    }

    private void editUserAccountRecordValues() {

        try {

            String Searchusrname, username, password, email;
            boolean success;

            Searchusrname = this.SearchUsernameTxt.getText();
            username = this.UsernameTxt.getText();
            password = this.PasswordTxt.getText();
            email = this.EmailTxt.getText();
            success = objUserAccountList.edit(username, password, email);
            if (success) {
                dm.Message("User Account Updated!");
                this.ClearData();
            } else {
                dm.Message("Cannot Update, please try again!");
            }
        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());

        }
    }

    private void removeUserAccountRecord() {

        try {

            String username;

            username = this.SearchUsernameTxt.getText();
            UserAccount ObjUserAccount = UserAccount.getInstance();
            ObjUserAccount.delete(username);
            this.AllUserAccounts();

        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }
    }

    private void changeUserName() {

        try {

            String username;
            String newUsername;
            boolean success;

            username = this.SearchUsernameTxt.getText();
            newUsername = this.UsernameTxt.getText();

            success = objUserAccountList.changeUsername(username, newUsername);

            if (success) {
                dm.Message("Username Changed!");
                this.ClearData();
            } else {
                dm.Message("Cannot Update, please try again!");
            }
        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }
    }

    private void changePassword() {

        try {

            String username;
            String password;
            boolean success;

            username = this.UsernameTxt.getText();
            password = this.PasswordTxt.getText();

            success = objUserAccountList.changePassword(username, password);

            if (success) {
                dm.Message("Password Changed!");
                this.ClearData();
            } else {
                dm.Message("Cannot Update, please try again!");
            }
        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }
    }

    private void changeEmail() {

        try {

            String username;
            String email;
            boolean success;

            username = this.UsernameTxt.getText();
            email = this.EmailTxt.getText();

            success = objUserAccountList.changeEmail(username, email);

            if (success) {
                dm.Message("Email Changed!");
                this.ClearData();
            } else {
                dm.Message("Cannot Update, please try again!");
            }
        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }
    }

    private void ClearData() {
        this.SearchUsernameTxt.setText("");
        this.UsernameTxt.setText("");       
        this.PasswordTxt.setText("");
        this.EmailTxt.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        UsernameTxt = new javax.swing.JTextField();
        PasswordTxt = new javax.swing.JTextField();
        EmailTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        SearchUsernameTxt = new javax.swing.JTextField();
        SearchBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        AddBtn = new javax.swing.JButton();
        EditBtn = new javax.swing.JButton();
        RemoveBtn = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        ExitNsaveBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        UserAccountTable = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("User Account Management"));

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        jLabel3.setText("E-mail:");

        PasswordTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordTxtActionPerformed(evt);
            }
        });

        jLabel5.setText("Search By username:");

        SearchUsernameTxt.setBackground(new java.awt.Color(102, 255, 255));

        SearchBtn.setText("Search");
        SearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EmailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UsernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SearchUsernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(SearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchUsernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(UsernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(PasswordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EmailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        AddBtn.setText("Add");
        AddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtnActionPerformed(evt);
            }
        });

        EditBtn.setText("Edit");
        EditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditBtnActionPerformed(evt);
            }
        });

        RemoveBtn.setText("Remove");
        RemoveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveBtnActionPerformed(evt);
            }
        });

        ClearBtn.setText("Clear/New");
        ClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RemoveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ClearBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(AddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RemoveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ExitNsaveBtn.setText("<< Back to Main Menu");
        ExitNsaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitNsaveBtnActionPerformed(evt);
            }
        });

        UserAccountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(UserAccountTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ExitNsaveBtn)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExitNsaveBtn)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PasswordTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordTxtActionPerformed

    private void SearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtnActionPerformed
        this.getUserAccountRecord();

    }//GEN-LAST:event_SearchBtnActionPerformed

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed
        this.addUserAccountRecord();
    }//GEN-LAST:event_AddBtnActionPerformed

    private void EditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditBtnActionPerformed
        this.editUserAccountRecord();
    }//GEN-LAST:event_EditBtnActionPerformed

    private void RemoveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveBtnActionPerformed
        this.removeUserAccountRecord();
        this.ClearData();
    }//GEN-LAST:event_RemoveBtnActionPerformed

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        this.ClearData();
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void ExitNsaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitNsaveBtnActionPerformed
        //To get access to MainMenu methodes
        Component source = (Component) evt.getSource();
        MainMenu main = (MainMenu) SwingUtilities.windowForComponent(source);
        try {
            objUserAccountList.save();
            objUserAccountList.clear();
            this.dispose();
            main.EnableMenuBtns();
        } catch (IOException ex) {
            Logger.getLogger(UserAccountManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ExitNsaveBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JButton EditBtn;
    private javax.swing.JTextField EmailTxt;
    private javax.swing.JButton ExitNsaveBtn;
    private javax.swing.JTextField PasswordTxt;
    private javax.swing.JButton RemoveBtn;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JTextField SearchUsernameTxt;
    private javax.swing.JTable UserAccountTable;
    private javax.swing.JTextField UsernameTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
