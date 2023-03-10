package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;

public class Kata5P1 {


    private static final String fileName = "email.txt";
    
    
    public static void main(String[] args) {
        selectAll();
        createNewTable();
        insert(fileName);
        
    }

    private static Connection connect() {
        String url = "jdbc:sqlite:Kata5.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void selectAll() {
        String sql = "SELECT * FROM PEOPLE";
        try ( Connection conn = connect();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getString("Name") + "\t"
                        + rs.getString("Apellidos") + "\t"
                        + rs.getString("Departamento") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createNewTable() {
        String url = "jdbc:sqlite:Kata5.db";
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                + " Id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " Mail text NOT NULL);";
        try ( Connection conn = DriverManager.getConnection(url);  
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void insert(String fileName) {
        String sql = "INSERT INTO EMAIL(Mail) VALUES(?)";
        List<String> mails = MailListReaderBD.read(fileName);
        try ( Connection conn = connect();  
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for(String list: mails){
            pstmt.setString(1, list);
            pstmt.executeUpdate();
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}

    