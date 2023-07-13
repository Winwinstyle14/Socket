
import service.DbService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SimpleSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server started..... ");
        try {
            while (true) {
                Socket socket = serverSocket.accept();

                Connection conn = DbService.getConnection();
                clientRequest(socket,conn);

                socket.close();
                conn.close();

                System.out.println("Dữ liệu đã được chèn vào cơ sở dữ liệu thành công!");
                Thread.sleep(10);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clientRequest(Socket socket,Connection connection) {
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                insertData(connection,line);

            }
        } catch(IOException |SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void insertData(Connection conn,String line) throws SQLException {
            String sql = "INSERT INTO ALARM_HUAWEI (ALARM_TYPE, RAW_CARD, NE_TYPE, NE_ID, " +
                    "NV_PROBABLE_CAUSE, NV_PERCEIVED_SEVERITY, NE_NAME, MESSAGE, RAW_PORT, NV_ALARM_ID," +
                    " NV_EVENT_TIME, NV_MANAGED_OBJECT_INSTANCE, NV_IRPAGENT_ID, SEVERITY, ALARM_NAME) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            String[] values = line.split(";");
            pstmt.setString(1, values[0]);
            pstmt.setString(2, values[1]);
            pstmt.setString(3, values[2]);
            pstmt.setString(4, values[3]);
            pstmt.setString(5, values[4]);
            pstmt.setString(6, values[5]);
            pstmt.setString(7, values[6]);
            pstmt.setString(8, values[7]);
            pstmt.setString(9, values[8]);
            pstmt.setString(10, values[9]);
            pstmt.setString(11, values[10]);
            pstmt.setString(12, values[11]);
            pstmt.setString(13, values[12]);
            pstmt.setString(14, values[13]);
            pstmt.setString(15, values[14]);
            pstmt.executeUpdate();
            pstmt.close();
        }
}