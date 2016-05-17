/*
 *Developer: MOUAOU JAOUAD
 *Date: 03/01/2015
 */
package Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection connection = null;

    public Connection getConnection() {
        try {

            //To connect to Mysql Database:
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/car_rental";
            String username = "root";
            String password = "";
            
            //To connect to derby Database using Using ClientDriver:
//            String driver = "org.apache.derby.jdbc.ClientDriver";
//            String url = "jdbc:derby://localhost:1527/car_rental";
//            String username = "root";
//            String password = "000";

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                System.out.println(e.toString());
            }

            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            if (connection != null) {
                System.out.println("Connection Success!");
            } else {
                System.out.println("Connection Fail!");
            }
        }
        return connection;
    }

}
