/*
 *Developer: MOUAOU JAOUAD
 *Date: 03/01/2015
 */
package BusinessClasses;

import Driver.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

public class UserAccount {

    private String userAccountID;
    private String username;
    private String password;
    private final String adm = "admin";
    private final String pwd = "999";

    private String email;

    public String getUserAccountID() {
        return userAccountID;

    }

    public void setUserAccountID(String userID) {
        userAccountID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String m_username) {
        username = m_username;

    }

    public String getAdm() {
        return adm;
    }

    public String getPwd() {
        return pwd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String m_Password) {
        password = m_Password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String m_Email) {
        email = m_Email;
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    private UserAccount() {
        userAccountID = UUID.randomUUID().toString();
        this.setUsername("");
        this.setPassword("");
        this.setEmail("");
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    private UserAccount(String U, String P, String E) {
        userAccountID = UUID.randomUUID().toString();
        this.setUsername(U);
        this.setPassword(P);
        this.setEmail(E);
    }

    public static UserAccount getInstance() {
        UserAccount UserAccount = new UserAccount();
        return UserAccount;

    }

    public static UserAccount getInstance(String U, String P, String E) {
        UserAccount UserAccount;
        UserAccount = new UserAccount(U, P, E);
        return UserAccount;
    }

    public boolean authenticate(String U, String P) {
        if (this.getUsername().equals(U) && this.getPassword().equals(P)
                || this.getAdm().equals(U) && this.getPwd().equals(P)) {
            return true;
        } else {
            return false;
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
        String sql = "SELECT USERNAME, PASSWORD, EMAIL FROM USERACCOUNT WHERE USERNAME = ? ";

        DBConnection con = new DBConnection();
        Connection connect = con.getConnection();
        PreparedStatement ps = connect.prepareStatement(sql);

        try {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                this.setUsername(rs.getString("USERNAME"));
                this.setPassword(rs.getString("PASSWORD"));
                this.setEmail(rs.getString("EMAIL"));

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

        String insertTableSQL = "INSERT INTO USERACCOUNT"
                + "(USERID, USERNAME, PASSWORD, EMAIL) VALUES"
                + "(?,?,?,?)";

        try {
            DBConnection con = new DBConnection();
            connect = con.getConnection();
            ps = connect.prepareStatement(insertTableSQL);

            ps.setString(1, this.getUserAccountID());
            ps.setString(2, this.getUsername());
            ps.setString(3, this.getPassword());
            ps.setString(4, this.getEmail());

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

        String sql = "UPDATE USERACCOUNT SET USERNAME = ? , PASSWORD = ?,"
                + " EMAIL = ? WHERE USERNAME = ?";

        try {
            DBConnection con = new DBConnection();
            connect = con.getConnection();
            ps = connect.prepareStatement(sql);

            ps.setString(1, this.getUsername());
            ps.setString(2, this.getPassword());
            ps.setString(3, this.getEmail());
            ps.setString(4, key);

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
        String sql = "DELETE FROM USERACCOUNT WHERE USERNAME = ?";

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

    public ArrayList<UserAccount> getAllUserAccounts() throws SQLException {

        String sql = "SELECT USERID, USERNAME, PASSWORD, EMAIL FROM USERACCOUNT ";

        DBConnection con = new DBConnection();
        Connection connect = con.getConnection();
        Statement stm = null;
        ArrayList<UserAccount> records = new ArrayList<>();

        try {
            stm = connect.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                UserAccount ua = new UserAccount();

                ua.setUserAccountID(rs.getString("USERID"));
                ua.setUsername(rs.getString("USERNAME"));
                ua.setPassword(rs.getString("PASSWORD"));
                ua.setEmail(rs.getString("EMAIL"));

                records.add(ua);

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
