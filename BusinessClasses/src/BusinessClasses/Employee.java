/*
 *Developer: MOUAOU JAOUAD
 *Date: 03/01/2015
 */
package BusinessClasses;

import Driver.DBConnection;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;

public class Employee extends Person {

    private String jobTitle;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String newJobTl) {
        jobTitle = newJobTl;
    }

    @Override
    public void setDateOfBirth(String newBOD) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate bod = LocalDate.parse(newBOD, formatter);
        LocalDate today = LocalDate.now();
        int age = today.getYear() - bod.getYear();

        if (age >= 16) {

            super.setDateOfBirth(newBOD);
        } else {

            throw new UnsupportedOperationException("Policy Violation - Under age Employee!");
        }

    }

    public Employee() {
        super();
        jobTitle = "";
        Person.setCount(Person.getCount() + 1);

    }

    public Employee(String ssnum, String fname, String lname, String dob,
            String adrs, String tel, String e_mail, String jtitle) {
        super(ssnum, fname, lname, dob, adrs, tel, e_mail, jtitle);
        this.jobTitle = jtitle;
        Person.setCount(Person.getCount() + 1);

    }

    @Override
    public void print() throws IOException {

        try {
            try (PrintWriter objOutStream = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter("Network_Printer.txt", true)))) {
                        objOutStream.println("--------------------------------------------------------------------------");
                        objOutStream.println("**************** Employee Information ****************");
                        objOutStream.println("--------------------------------------------------------------------------");

                        objOutStream.println("First Name: " + super.getFirstName());
                        objOutStream.println("Last Name: " + super.getLastName());
                        objOutStream.println("Social Security: " + super.getSSNumber());
                        objOutStream.println("Date Of Birth: " + super.getDateOfBirth());
                        objOutStream.println("Address: " + super.getAddress());
                        objOutStream.println("Age: " + super.getAge());
                        objOutStream.println("Email: " + super.getEmail());
                        objOutStream.println("Job Title: " + jobTitle);

                        objOutStream.close();

                    }

        } catch (IOException e) {
            throw new IOException("Error in print() method: " + e.toString());
        }

    }

    public void load(String key) throws SQLException {

        Database_Load(key);
    }

    public void insert() throws SQLException {

        Database_Insert();
    }

    public void update(String key) throws SQLException {

        Database_Update(key);
    }

    public void delete(String key) throws SQLException {

        Database_Delete(key);
    }

    protected void Database_Load(String key) throws SQLException {

        String sql = "SELECT SSNUMBER AS SSN, FIRSTNAME, LASTNAME, DOB, ADDRESS,"
                + " PHONE, EMAIL,JOB_TITLE FROM EMPLOYEE WHERE SSNUMBER = ? ";

        DBConnection con = new DBConnection();
        Connection connect = con.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);

        try {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                super.setSSNumber(rs.getString("SSN"));
                super.setFirstName(rs.getString("FIRSTNAME"));
                super.setLastName(rs.getString("LASTNAME"));
                super.setDateOfBirth(rs.getString("DOB"));
                super.setAddress(rs.getString("ADDRESS"));
                super.setPhone(rs.getString("PHONE"));
                super.setEmail(rs.getString("EMAIL"));
                this.setJobTitle(rs.getString("JOB_TITLE"));

            }

        } catch (SQLException e) {
            throw new UnsupportedOperationException("An Error has been occured: " + e.toString());
        } catch (Exception e) {
            throw new UnsupportedOperationException("An Error has been occured: " + e.toString());
        } finally {
            connect.close();
            ps.close();
        }

    }

    protected int Database_Insert() throws SQLException {

        int affected = 0;

        Connection connect = null;
        PreparedStatement ps = null;
        //I worked with Prepared statment, which uses parameters that will take care of strings
        //and make the code more clean and readable
        String insertTableSQL = "INSERT INTO EMPLOYEE"
                + "(SSNUMBER, FIRSTNAME, LASTNAME, DOB, ADDRESS, PHONE, EMAIL, JOB_TITLE) VALUES"
                + "(?,?,?,?,?,?,?,?)";

        try {
            DBConnection con = new DBConnection();
            connect = con.getConnection();
            ps = connect.prepareStatement(insertTableSQL);

            ps.setString(1, this.getSSNumber());
            ps.setString(2, super.getFirstName());
            ps.setString(3, super.getLastName());
            ps.setString(4, super.getDateOfBirth());
            ps.setString(5, super.getAddress());
            ps.setString(6, super.getPhone());
            ps.setString(7, super.getEmail());
            ps.setString(8, this.getJobTitle());

            // execute insert SQL stetement
            affected = ps.executeUpdate();

        } catch (SQLException e) {

            throw new UnsupportedOperationException("An Error has been occured: " + e.toString());

        } catch (Exception e) {
            throw new UnsupportedOperationException("An Error has been occured: " + e.toString());
        } finally {

            if (ps != null) {
                ps.close();
            }

            if (connect != null) {
                connect.close();

            }
        }
        return affected;
    }

    protected int Database_Update(String key) throws SQLException {

        int affected = 0;

        Connection connect = null;
        PreparedStatement ps = null;

        String sql = "UPDATE EMPLOYEE SET FIRSTNAME = ?, LASTNAME = ?, DOB = ?"
                + ", ADDRESS = ?, PHONE = ?, EMAIL = ?, JOB_TITLE = ? WHERE SSNUMBER = ?";

        try {
            DBConnection con = new DBConnection();
            connect = con.getConnection();
            ps = connect.prepareStatement(sql);

            ps.setString(1, super.getFirstName());
            ps.setString(2, super.getLastName());
            ps.setString(3, super.getDateOfBirth());
            ps.setString(4, super.getAddress());
            ps.setString(5, super.getPhone());
            ps.setString(6, super.getEmail());
            ps.setString(7, this.getJobTitle());
            ps.setString(8, key);

            // execute insert SQL stetement
            affected = ps.executeUpdate();

        } catch (SQLException e) {
            throw new UnsupportedOperationException("An Error has been occured: " + e.toString());
        } catch (Exception e) {
            throw new UnsupportedOperationException("An Error has been occured: " + e.toString());
        } finally {

            if (ps != null) {
                ps.close();
            }

            if (connect != null) {
                connect.close();

            }

        }
        return affected;

    }

    protected int Database_Delete(String key) throws SQLException {

        int affected = 0;
        String sql = "DELETE FROM EMPLOYEE WHERE SSNUMBER = ?";

        DBConnection con = new DBConnection();
        Connection connect = con.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);

        try {
            ps.setString(1, key);

            ps.execute();

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                connect.close();
                ps.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }

        return affected;

    }

    public ArrayList<Employee> getAllEmployeeRecords() throws SQLException {

        String sql = "SELECT SSNUMBER, FIRSTNAME, LASTNAME, DOB, ADDRESS, PHONE, EMAIL, JOB_TITLE FROM EMPLOYEE ORDER BY SSNUMBER";

        DBConnection con = new DBConnection();
        Connection connect = con.getConnection();
        Statement stm = null;
        ArrayList<Employee> records = new ArrayList<>();

        try {
            stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Employee emp = new Employee();

                emp.setSSNumber(rs.getString("SSNUMBER"));
                emp.setFirstName(rs.getString("FIRSTNAME"));
                emp.setLastName(rs.getString("LASTNAME"));
                emp.setDateOfBirth(rs.getString("DOB"));
                emp.setAddress(rs.getString("ADDRESS"));
                emp.setPhone(rs.getString("PHONE"));
                emp.setEmail(rs.getString("EMAIL"));
                emp.setJobTitle(rs.getString("JOB_TITLE"));

                records.add(emp);

            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (stm != null) {
                stm.close();
            }

            if (connect != null) {
                connect.close();

            }
        }

        return records;
    }

}
