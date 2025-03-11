import java.sql.*;
import java.util.*;

public class StudentOperations {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "sit123";

    // Method to insert student data
    public void insertStudent() {
        try (
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO students (PRN, Name, Branch, Batch, CGPA) VALUES (?, ?, ?, ?, ?)")
        ) {
            Scanner scan = new Scanner(System.in);

            // Clear the buffer after reading integer input
            System.out.println("Enter PRN:");
            int prn = scan.nextInt();
            scan.nextLine();  // To clear the buffer after nextInt()

            System.out.println("Enter Name:");
            String name = scan.nextLine();

            System.out.println("Enter Branch:");
            String branch = scan.nextLine();

            System.out.println("Enter Batch:");
            String batch = scan.nextLine();

            System.out.println("Enter CGPA:");
            float cgpa = scan.nextFloat();

            // Setting the parameters for the PreparedStatement
            stmt.setInt(1, prn);
            stmt.setString(2, name);
            stmt.setString(3, branch);
            stmt.setString(4, batch);
            stmt.setFloat(5, cgpa);

            // Execute the insert query
            int effectedRows = stmt.executeUpdate();
            if (effectedRows > 0) {
                System.out.println("Student data inserted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to view all students' data
    public void viewStudents() {
        try (
                Connection con = DriverManager.getConnection(URL, USER, PASSWORD); // Corrected Connection variable name
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM students")
        ) {
            System.out.println("_______________Student_Data______________");
            while (rs.next()) {
                System.out.println("PRN: " + rs.getInt(1) + " Name: " + rs.getString(2) + " Branch: " + rs.getString(3) + " Batch: " + rs.getString(4) + " CGPA: " + rs.getFloat(5));
            }
            System.out.println("****************************************************");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


