import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleSocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        File folder = new File("D:\\Downloads\\demo\\readText");
        while (true){
            try {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".txt")) {
                            sendFile(socket, file);
                            deleteFile(file);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void sendFile(Socket socket, File file) {

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    fileReader.close();
                    reader.close();
                    break;
                }
                writer.println(line);
                Thread.sleep(10);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(File file) {
        if (file.delete()) {
            System.out.println("Xoa thanh cong: " + file.getName());
        } else {
            System.out.println("Xoa khong thanh cong: " + file.getName());
        }
    }
}