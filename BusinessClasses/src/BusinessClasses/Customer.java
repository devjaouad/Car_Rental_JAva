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
import java.util.ArrayList;

public final class Customer extends Person {

    private String idNumber;

    public String getIDNumber() {
        return idNumber;
    }

    public void setIDNumber(String newIDNo) {
        idNumber = newIDNo;
    }

    public Customer() {
        super();
        idNumber = "";

    }

    public Customer(String ID, String fname, String lname, String dob,
            String adrs, String tel, String e_mail, String jtitle) {

        super(ID, fname, lname, dob, adrs, tel, e_mail, jtitle);
        this.idNumber = ID;

    }

    @Override
    public void print() throws IOException {

        try {
            try (PrintWriter objOutStream = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter("Network_Printer.txt", true)))) {
                        objOutStream.println("--------------------------------------------------------------------------");
                        objOutStream.println("**************** Customer Information ****************");
                        objOutStream.println("--------------------------------------------------------------------------");

                        objOutStream.println("First Name: " + super.getFirstName());
                        objOutStream.println("Last Name: " + super.getLastName());
                        objOutStream.println("ID: " + this.getIDNumber());
                        objOutStream.println("Date Of Birth: " + super.getDateOfBirth());
                        objOutStream.println("Address: " + super.getAddress());
                        objOutStream.println("Phone: " + super.getPhone());
                        objOutStream.println("Age: " + super.getAge());
                        objOutStream.println("Email: " + super.getEmail());

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

        String sql = "SELECT SSNUMBER AS SSN, FIRSTNAME, LASTNAME, DOB,"
                + " ADDRESS, PHONE, EMAIL FROM CUSTOMER WHERE SSNUMBER = ? ";

        DBConnection con = new DBConnection();
        Connection connect = con.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);

        try {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.setIDNumber(rs.getString("SSN"));
                super.setFirstName(rs.getString("FIRSTNAME"));
                super.setLastName(rs.getString("LASTNAME"));
                super.setDateOfBirth(rs.getString("DOB"));
                super.setAddress(rs.getString("ADDRESS"));
                super.setPhone(rs.getString("PHONE"));
                super.setEmail(rs.getString("EMAIL"));

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

        String insertTableSQL = "INSERT INTO CUSTOMER"
                + "(SSNUMBER, FIRSTNAME, LASTNAME, DOB, ADDRESS, PHONE, EMAIL) VALUES"
                + "(?,?,?,?,?,?,?)";

        try {
            DBConnection con = new DBConnection();
            connect = con.getConnection();
            ps = connect.prepareStatement(insertTableSQL);

            ps.setString(1, this.getIDNumber());
            ps.setString(2, super.getFirstName());
            ps.setString(3, super.getLastName());
            ps.setString(4, super.getDateOfBirth());
            ps.setString(5, super.getAddress());
            ps.setString(6, super.getPhone());
            ps.setString(7, super.getEmail());

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

        String sql = "UPDATE CUSTOMER SET FIRSTNAME = ?, LASTNAME = ?, DOB = ?"
                + ", ADDRESS = ?, PHONE = ?, EMAIL = ? WHERE SSNUMBER = ?";

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
            ps.setString(7, key);

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
        String sql = "DELETE FROM CUSTOMER WHERE SSNUMBER = ?";

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

    public ArrayList<Customer> getAllCustomerRecords() throws SQLException {

        String sql = "SELECT SSNUMBER, FIRSTNAME, LASTNAME, DOB, ADDRESS, PHONE, EMAIL FROM CUSTOMER ORDER BY SSNUMBER";

        DBConnection con = new DBConnection();
        Connection connect = con.getConnection();
        Statement stm = null;
        ArrayList<Customer> records = new ArrayList<>();

        try {
            stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Customer cs = new Customer();

                cs.setIDNumber(rs.getString("SSNUMBER"));
                cs.setFirstName(rs.getString("FIRSTNAME"));
                cs.setLastName(rs.getString("LASTNAME"));
                cs.setDateOfBirth(rs.getString("DOB"));
                cs.setAddress(rs.getString("ADDRESS"));
                cs.setPhone(rs.getString("PHONE"));
                cs.setEmail(rs.getString("EMAIL"));

                records.add(cs);

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
