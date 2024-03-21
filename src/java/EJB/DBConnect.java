/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.ejb.Singleton;

/**
 *
 * @author student
 */
@Singleton
public class DBConnect {
    private static final String JDBC_URL = "jdbc:derby://localhost:1527/WCY21IG1S1_Jasiek_Nowicki";
    private Connection conn = null;

    public boolean Connect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            conn = DriverManager.getConnection(JDBC_URL, "root", "root");
            if (conn != null) {
                System.out.println("Połączono z bazą danych.");
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }
    
    public boolean Disconnect() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Połączenie z bazą danych zostało zamknięte.");
                return true;
            } catch (SQLException e) {
            }
        }
        return false;
    }
}
