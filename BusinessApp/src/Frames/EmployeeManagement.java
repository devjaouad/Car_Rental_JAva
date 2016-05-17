/*
 *Developer: MOUAOU JAOUAD
 *Date: 12/01/2015
 */
package Frames;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import BusinessClasses.Employee;
import BusinessClasses.EmployeeList;
import java.awt.Component;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class EmployeeManagement extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;
    public static EmployeeList objEmployeeList = new EmployeeList();
    DialogMessage dm = new DialogMessage();
    MainMenu mm = new MainMenu();

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(operation); //To change body of generated methods, choose Tools | Templates.
    }

    public EmployeeManagement() {
        try {

            mm.DisableMenuBtns();
            initComponents();
            this.setBorder(null);
            BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
            bi.setNorthPane(null);
            this.AllEmployees();
            objEmployeeList.load();
        } catch (Exception ex) {
            Logger.getLogger(EmployeeManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void AllEmployees() {
        try {
            Employee objEmployee = new Employee();

            ArrayList<Employee> records = objEmployee.getAllEmployeeRecords();

            Object[] tableColumnName = new Object[8];

            tableColumnName[0] = "SS.Number";
            tableColumnName[1] = "First Name";
            tableColumnName[2] = "Last Name";
            tableColumnName[3] = "Date of Birth";
            tableColumnName[4] = "Address";
            tableColumnName[5] = "PHONE";
            tableColumnName[6] = "Email";
            tableColumnName[7] = "Job Title";

            DefaultTableModel tbd = new DefaultTableModel();

            tbd.setColumnIdentifiers(tableColumnName);

            this.EmployeeTable.setModel(tbd);

            Object[] RowRec = new Object[8];

            for (int i = 0; i < records.size(); i++) {

                RowRec[0] = records.get(i).getSSNumber();
                RowRec[1] = records.get(i).getFirstName().toUpperCase();
                RowRec[2] = records.get(i).getLastName().toUpperCase();
                RowRec[3] = records.get(i).getDateOfBirth();
                RowRec[4] = records.get(i).getAddress().toUpperCase();
                RowRec[5] = records.get(i).getPhone();
                RowRec[6] = records.get(i).getEmail();
                RowRec[7] = records.get(i).getJobTitle().toUpperCase();

                tbd.addRow(RowRec);

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            dm.Message("An error has occurred. Please try again");
        }
    }

    private void getEmployeeRecord() {
        try {

            String ssNumber;
            Employee objEmployee = new Employee();

            ssNumber = this.srSsnumberTxt.getText();

            objEmployee.load(ssNumber);

            this.FirstnameTxt.setText(objEmployee.getFirstName());
            this.LastnameTxt.setText(objEmployee.getLastName());
            this.SsnumberTxt.setText(objEmployee.getSSNumber());
            this.DobTxt.setText(objEmployee.getDateOfBirth());
            this.AddressTxt.setText(objEmployee.getAddress());
            this.PhoneTxt.setText(objEmployee.getPhone());
            String age = String.valueOf(objEmployee.getAge());
            this.ageTxt.setText(age);
            this.EmailTxt.setText(objEmployee.getEmail());
            this.JobtitleTxt.setText(objEmployee.getJobTitle());

        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }

    }

    private void addEmployeeRecord() {
        try {

            String SSNumber, FirstName, LastName, DateOfBirth, Address, Phone, Email, JobTitle;

            SSNumber = this.SsnumberTxt.getText();
            FirstName = this.FirstnameTxt.getText();
            LastName = this.LastnameTxt.getText();
            DateOfBirth = this.DobTxt.getText();
            Address = this.AddressTxt.getText();
            Phone = this.PhoneTxt.getText();
            Email = this.EmailTxt.getText();
            JobTitle = this.JobtitleTxt.getText();

            Employee NewEmployee = new Employee();

            NewEmployee.setSSNumber(SSNumber);
            NewEmployee.setFirstName(FirstName);
            NewEmployee.setLastName(LastName);
            NewEmployee.setDateOfBirth(DateOfBirth);
            NewEmployee.setAddress(Address);
            NewEmployee.setPhone(Phone);
            NewEmployee.setEmail(Email);
            NewEmployee.setJobTitle(JobTitle);

            NewEmployee.insert();
            this.AllEmployees();
            this.ClearData();

        } catch (SQLException e) {
            dm.Message("An error has been occured: " + e.toString());
        }

    }

    private void editEmployeeRecord() {
        try {

            String SearchSSNumber, SSNumber, FirstName, LastName, DateOfBirth, Address, Phone, Email, JobTitle;

            SearchSSNumber = this.srSsnumberTxt.getText();
            SSNumber = this.SsnumberTxt.getText();
            FirstName = this.FirstnameTxt.getText();
            LastName = this.LastnameTxt.getText();
            DateOfBirth = this.DobTxt.getText();
            Address = this.AddressTxt.getText();
            Phone = this.PhoneTxt.getText();
            Email = this.EmailTxt.getText();
            JobTitle = this.JobtitleTxt.getText();

            Employee NewEmployee = new Employee();

            NewEmployee.setFirstName(FirstName);
            NewEmployee.setLastName(LastName);
            NewEmployee.setDateOfBirth(DateOfBirth);
            NewEmployee.setAddress(Address);
            NewEmployee.setPhone(Phone);
            NewEmployee.setEmail(Email);
            NewEmployee.setJobTitle(JobTitle);

            NewEmployee.update(SSNumber);
            this.AllEmployees();
            this.ClearData();
        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }

    }

    private void removeEmployeeRecord() {
        try {

            String SSNumber;
            Employee objEmployee = new Employee();
            SSNumber = this.SsnumberTxt.getText();
            objEmployee.delete(SSNumber);
            this.AllEmployees();

        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }

    }

    private void addEmployeeRecordToFile() {
        try {

            String SSNumber, FirstName, LastName, DateOfBirth, Address, Phone, Email, JobTitle;

            SSNumber = this.SsnumberTxt.getText();
            FirstName = this.FirstnameTxt.getText();
            LastName = this.LastnameTxt.getText();
            DateOfBirth = this.DobTxt.getText();
            Address = this.AddressTxt.getText();
            Phone = this.PhoneTxt.getText();
            Email = this.EmailTxt.getText();
            JobTitle = this.JobtitleTxt.getText();

            Employee NewEmployee = new Employee();

            NewEmployee.setSSNumber(SSNumber);
            NewEmployee.setFirstName(FirstName);
            NewEmployee.setLastName(LastName);
            NewEmployee.setDateOfBirth(DateOfBirth);
            NewEmployee.setAddress(Address);
            NewEmployee.setPhone(Phone);
            NewEmployee.setEmail(Email);
            NewEmployee.setJobTitle(JobTitle);
            objEmployeeList.add(SSNumber, NewEmployee);
            objEmployeeList.save();

        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }

    }

    private void printEmployeeRecord() {
        try {

            String SSNumber;
            boolean success;

            SSNumber = this.SsnumberTxt.getText();
            success = objEmployeeList.print(SSNumber);
            if (success) {
                dm.Message("The Employee has been printed to the Network_Printer.txt File!");
            } else {
                dm.Message("Employee not found!");
            }
        } catch (Exception e) {
            dm.Message("An error has been occured: " + e.toString());
        }

    }

    private void printAllEmployeeRecords() {

        try {
            objEmployeeList.printAll();

            dm.Message("All Employees has been printed to the Network_Printer.txt File!");

        } catch (Exception e) {

            dm.Message("An error has been occured: " + e.toString());
        }

    }

    private void ClearData() {
        this.srSsnumberTxt.setText("");
        this.SsnumberTxt.setText("");
        this.FirstnameTxt.setText("");
        this.LastnameTxt.setText("");
        this.DobTxt.setText("");
        this.AddressTxt.setText("");
        this.PhoneTxt.setText("");
        this.ageTxt.setText("");
        this.EmailTxt.setText("");
        this.JobtitleTxt.setText("");
        this.SsnumberTxt.setEditable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
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
        JobtitleTxt = new javax.swing.JTextField();
        AgeLbl = new javax.swing.JLabel();
        ageTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        srSsnumberTxt = new javax.swing.JTextField();
        SearchBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        EditBtn = new javax.swing.JButton();
        AddBtn = new javax.swing.JButton();
        RemoveBtn = new javax.swing.JButton();
        PrintBtn = new javax.swing.JButton();
        PrintAllBtn = new javax.swing.JButton();
        ClearBtn = new javax.swing.JButton();
        SaveExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        EmployeeTable = new javax.swing.JTable();

        setBorder(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Employee Management"));

        jLabel1.setText("SS Number:");

        jLabel2.setText("First Name:");

        jLabel3.setText("Job Title");

        jLabel4.setText("Last Name:");

        jLabel5.setText("Date of Birth:");

        jLabel6.setText("Address:");

        jLabel7.setText("Phone:");

        jLabel8.setText("E-mail:");

        SsnumberTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SsnumberTxtActionPerformed(evt);
            }
        });

        AgeLbl.setText("Age");

        ageTxt.setEditable(false);

        jLabel9.setText("Search By SS Number:");

        srSsnumberTxt.setBackground(new java.awt.Color(102, 255, 255));
        srSsnumberTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                srSsnumberTxtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                srSsnumberTxtKeyTyped(evt);
            }
        });

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
                    .addComponent(AgeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SsnumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FirstnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LastnameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DobTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ageTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EmailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JobtitleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(srSsnumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SearchBtn)
                .addGap(10, 10, 10))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel9)
                .addGap(105, 105, 105))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(srSsnumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AgeLbl)
                    .addComponent(ageTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(EmailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(JobtitleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        PrintAllBtn.setText("Print All");
        PrintAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintAllBtnActionPerformed(evt);
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
                    .addComponent(PrintAllBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(PrintAllBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        SaveExit.setText("<< Back to Main Menu");
        SaveExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveExitActionPerformed(evt);
            }
        });

        EmployeeTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(EmployeeTable);

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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(SaveExit))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(SaveExit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtnActionPerformed
        this.getEmployeeRecord();
        this.SsnumberTxt.setEditable(false);
    }//GEN-LAST:event_SearchBtnActionPerformed

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed
        this.addEmployeeRecordToFile();
        this.addEmployeeRecord();

    }//GEN-LAST:event_AddBtnActionPerformed

    private void EditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditBtnActionPerformed
        this.editEmployeeRecord();
    }//GEN-LAST:event_EditBtnActionPerformed

    private void RemoveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveBtnActionPerformed
        this.removeEmployeeRecord();
        this.ClearData();
    }//GEN-LAST:event_RemoveBtnActionPerformed

    private void PrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintBtnActionPerformed
        this.printEmployeeRecord();
    }//GEN-LAST:event_PrintBtnActionPerformed

    private void PrintAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintAllBtnActionPerformed
        this.printAllEmployeeRecords();
    }//GEN-LAST:event_PrintAllBtnActionPerformed

    private void ClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtnActionPerformed
        this.ClearData();
    }//GEN-LAST:event_ClearBtnActionPerformed

    private void SaveExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveExitActionPerformed

        Component source = (Component) evt.getSource();
        MainMenu main = (MainMenu) SwingUtilities.windowForComponent(source);
        try {
            objEmployeeList.save();
            objEmployeeList.clear();
            this.dispose();
            main.EnableMenuBtns();

        } catch (IOException e) {
            dm.Message("An error has been occured: " + e.toString());
        }
    }//GEN-LAST:event_SaveExitActionPerformed

    private void SsnumberTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SsnumberTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SsnumberTxtActionPerformed

    private void srSsnumberTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srSsnumberTxtKeyPressed

    }//GEN-LAST:event_srSsnumberTxtKeyPressed

    private void srSsnumberTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_srSsnumberTxtKeyTyped

    }//GEN-LAST:event_srSsnumberTxtKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JTextField AddressTxt;
    private javax.swing.JLabel AgeLbl;
    private javax.swing.JButton ClearBtn;
    private javax.swing.JTextField DobTxt;
    private javax.swing.JButton EditBtn;
    private javax.swing.JTextField EmailTxt;
    private javax.swing.JTable EmployeeTable;
    private javax.swing.JTextField FirstnameTxt;
    private javax.swing.JTextField JobtitleTxt;
    private javax.swing.JTextField LastnameTxt;
    private javax.swing.JTextField PhoneTxt;
    private javax.swing.JButton PrintAllBtn;
    private javax.swing.JButton PrintBtn;
    private javax.swing.JButton RemoveBtn;
    private javax.swing.JButton SaveExit;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JTextField SsnumberTxt;
    private javax.swing.JTextField ageTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField srSsnumberTxt;
    // End of variables declaration//GEN-END:variables
}
