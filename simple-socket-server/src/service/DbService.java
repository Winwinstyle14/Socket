package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbService {
    public static Connection getConnection() throws SQLException {
        // TODO khong fix cung ma day thong tin ket noi ra config
        String jdbcUrl = "jdbc:oracle:thin:@14.160.91.174:1621:orcl";
        String username = "smartw";
        String password = "smartw123";
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

}
