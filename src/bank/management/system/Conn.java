package bank.management.system;
import java.sql.*;

/*
5 steps of a jdbc connection
Register the driver
Create connection
Create statement
Execute query
Close connection
 */
public class Conn {
    Connection c;
    Statement s;
    public Conn(){
        try {
            //Class.forName(com.mysql.cj.jdbc.Driver);
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem", "root","neeraj123");
            s = c.createStatement(); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
