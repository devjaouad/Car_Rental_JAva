/*
 *Developer: MOUAOU JAOUAD
 *Date: 12/01/2015
 */
package Frames;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import BusinessClasses.Customer;
import BusinessClasses.EmployeeList;
import java.awt.Component;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class CustomerManagement extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;
    DialogMessage dm = new DialogMessage();
    public static EmployeeList objEmployeeList = new EmployeeList();

    public CustomerManagement() {

        initComponents();
        this.setBorder(null);
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);
        this.AllCostomers();

    }

    private void AllCostomers() {
        try {
            Customer objCustomer = new Customer();

            ArrayList<Customer> records = objCustomer.getAllCustomerRecords();

            Object[] tableColumnName = new Object[7];

            tableColumnName[0] = "SS.Number";
            tableColumnName[1] = "First Name";
            tableColumnName[2] = "Last Name";
            tableColumnName[3] = "Date of Birth";
            tableColumnName[4] = "Address";
            tableColumnName[5] = "PHONE";
            tableColumnName[6] = "Email";

            DefaultTableModel tbd = new DefaultTableModel();

            tbd.setColumnIdentifiers(tableColumnName);

            this.CustomerTable.setModel(tbd);

            Object[] RowRec = new Object[7];

            for (int i = 0; i < records.size(); i++) {

                RowRec[0] = records.get(i).getIDNumber();
                RowRec[1] = records.get(i).getFirstName().toUpperCase();
                RowRec[2] = records.get(i).getLastName().toUpperCase();
                RowRec[3] = records.get(i).getDateOfBirth();
                RowRec[4] = records.get(i).getAddress().toUpperCase();
                RowRec[5] = records.get(i).getPhone();
                RowRec[6] = records.get(i).getEmail();

                tbd.addRow(RowRec);

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            dm.Message("An error has occurred. Please try again");
        }
    }

    private void getCustomerRecord() {
        try {

            String idNumber;
            Customer objCustomer = new Customer();
            idNumber = this.SearchSsnumberTxt.getText();
            objCustomer.load(idNumber);

            this.SsnumberTxt.setText(objCustomer.getIDNumber());
            this.FirstnameTxt.setText(objCustomer.getFirstName().toUpperCase());
            this.LastnameTxt.setText(objCustomer.getLastName().toUpperCase());
            this.DobTxt.setText(objCustomer.getDateOfBirth());
            this.AddressTxt.setText(objCustomer.getAddress().toUpperCase());
            this.PhoneTxt.setText(objCustomer.getPhone());
            this.EmailTxt.setText(objCustomer.getEmail().toUpperCase());

        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());

        }

    }

    private void addCustomerRecord() {
        try {

            String IDNumber, FirstName, LastName, DateOfBirth, Address, Phone, Email;

            IDNumber = this.SsnumberTxt.getText();
            FirstName = this.FirstnameTxt.getText();
            LastName = this.LastnameTxt.getText();
            DateOfBirth = this.DobTxt.getText();
            Address = this.AddressTxt.getText();
            Phone = this.PhoneTxt.getText();
            Email = this.EmailTxt.getText();

            Customer objCustomer = new Customer();

            objCustomer.setIDNumber(IDNumber);
            objCustomer.setFirstName(FirstName);
            objCustomer.setLastName(LastName);
            objCustomer.setDateOfBirth(DateOfBirth);
            objCustomer.setAddress(Address);
            objCustomer.setPhone(Phone);
            objCustomer.setEmail(Email);

            objCustomer.insert();
            this.ClearData();

        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }

    }

    private void editCustomerRecord() {
        try {

            String IDNumber, FirstName, LastName, DateOfBirth, Address, Phone, Email;

            IDNumber = this.SsnumberTxt.getText();
            FirstName = this.FirstnameTxt.getText();
            LastName = this.LastnameTxt.getText();
            DateOfBirth = this.DobTxt.getText();
            Address = this.AddressTxt.getText();
            Phone = this.PhoneTxt.getText();
            Email = this.EmailTxt.getText();

            Customer objCustomer = new Customer();

            objCustomer.setFirstName(FirstName);
            objCustomer.setLastName(LastName);
            objCustomer.setDateOfBirth(DateOfBirth);
            objCustomer.setAddress(Address);
            objCustomer.setPhone(Phone);
            objCustomer.setEmail(Email);
            objCustomer.update(IDNumber);

            dm.Message("Your data has been updated");
            this.ClearData();

        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }

    }

    private void removeCustomerRecord() {
        try {

            String IDNumber;

            IDNumber = this.SsnumberTxt.getText();

            Customer objCustomer = new Customer();
            objCustomer.delete(IDNumber);

            dm.Message("Your data has been deleted!");
            this.ClearData();

        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }

    }

    private void printCustomerRecord() {
        try {

            String IDNumber, FirstName, LastName, DateOfBirth, Address, Phone, Email;

            IDNumber = this.SsnumberTxt.getText();
            FirstName = this.FirstnameTxt.getText();
            LastName = this.LastnameTxt.getText();
            DateOfBirth = this.DobTxt.getText();
            Address = this.AddressTxt.getText();
            Phone = this.PhoneTxt.getText();
            Email = this.EmailTxt.getText();

            Customer objCustomer = new Customer();

            objCustomer.setIDNumber(IDNumber);
            objCustomer.setFirstName(FirstName);
            objCustomer.setLastName(LastName);
            objCustomer.setDateOfBirth(DateOfBirth);
            objCustomer.setAddress(Address);
            objCustomer.setPhone(Phone);
            objCustomer.setEmail(Email);

            objCustomer.print();

            dm.Message("The Customer has been printed to the Network_Printer.txt File!");

        } catch (IOException e) {
            dm.Message("An error has been occured: " + e.toString());
        }
    }

    private void ClearData() {
        this.SearchSsnumberTxt.setText("");
        this.SsnumberTxt.setText("");
        this.FirstnameTxt.setText("");
        this.LastnameTxt.setText("");
        this.DobTxt.setText("");
        this.AddressTxt.setText("");
        this.PhoneTxt.setText("");
        this.EmailTxt.setText("");
        this.SsnumberTxt.setEditable(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        SsnumberTxt = new javax.swing.JTextField();
        FirstnameTxt = new javax.swing.JTextField();
        LastnameTxt = new javax.swing.JTextField();
        DobTxt = new javax.swing.JTextField();
        AddressTxt = new javax.swing.JTextField();
        PhoneTxt = new javax.swing.JTextField();
        EmailTxt = new javax.swing.JTextField();
        SearchSsnumberTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        SearchBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        EditBtn = new javax.swing.JButton();
        AddBtn = new javax.swing.JButton();
        RemoveBtn = new javax.swing.JButton();
        PrintBtn = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        CustomerTable = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        setBorder(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Management"));

        jLabel1.setText("SS Number:");

        jLabel2.setText("First Name:");

        jLabel4.setText("Last Name:");

        jLabel5.setText("Date of Birth:");

        jLabel6.setText("Address:");

        jLabel7.setText("Phone:");

        jLabel8.setText("E-mail:");

        SearchSsnumberTxt.setBackground(new java.awt.Color(102, 255, 255));
        SearchSsnumberTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchSsnumberTxtActionPerformed(evt);
            }
        });

        jLabel3.setText("Search By SS Number:");

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
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EmailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DobTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LastnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FirstnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SsnumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(SearchSsnumberTxt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchBtn)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SearchSsnumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(SsnumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(FirstnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(LastnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(DobTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(AddressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(PhoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(EmailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        EditBtn.setText("Edit");
        EditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditBtnActionPerformed(evt);
            }
        });

        AddBtn.setText("Add");
        AddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtnActionPerformed(evt);
            }
        });

        RemoveBtn.setText("Remove");
        RemoveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveBtnActionPerformed(evt);
            }
        });

        PrintBtn.setText("Print");
        PrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintBtnActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AddBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RemoveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EditBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PrintBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RemoveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PrintBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jButton1.setText("<< Back to Main Menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        CustomerTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(CustomerTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtnActionPerformed
        this.getCustomerRecord();
        this.SsnumberTxt.setEditable(false);
    }//GEN-LAST:event_SearchBtnActionPerformed

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed
        this.addCustomerRecord();
        this.AllCostomers();
    }//GEN-LAST:event_AddBtnActionPerformed

    private void EditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditBtnActionPerformed
        this.editCustomerRecord();
        this.AllCostomers();
    }//GEN-LAST:event_EditBtnActionPerformed

    private void RemoveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveBtnActionPerformed
        this.removeCustomerRecord();
        this.AllCostomers();
        this.ClearData();
    }//GEN-LAST:event_RemoveBtnActionPerformed

    private void PrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintBtnActionPerformed
        this.printCustomerRecord();
    }//GEN-LAST:event_PrintBtnActionPerformed

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        this.ClearData();
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Component source = (Component) evt.getSource();
        MainMenu main = (MainMenu) SwingUtilities.windowForComponent(source);
        try {
            objEmployeeList.save();
            objEmployeeList.clear();
            this.dispose();
            main.EnableMenuBtns();
        } catch (IOException ex) {
            Logger.getLogger(CustomerManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SearchSsnumberTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchSsnumberTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchSsnumberTxtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JTextField AddressTxt;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JTable CustomerTable;
    private javax.swing.JTextField DobTxt;
    private javax.swing.JButton EditBtn;
    private javax.swing.JTextField EmailTxt;
    private javax.swing.JTextField FirstnameTxt;
    private javax.swing.JTextField LastnameTxt;
    private javax.swing.JTextField PhoneTxt;
    private javax.swing.JButton PrintBtn;
    private javax.swing.JButton RemoveBtn;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JTextField SearchSsnumberTxt;
    private javax.swing.JTextField SsnumberTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
