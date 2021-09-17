package software_acadmy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {
    Connection connect = null;
    
    public dbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareacadmy","root","Amanshu@09");
        System.out.println("connected");
    }

    public void execute_sql(String sql) throws SQLException {
        Statement statement;
        
        statement = connect.createStatement();
        statement.execute(sql);
        
    }

    public ResultSet run_select(String sql) {
        ResultSet RS = null;
        try {
            
            Statement statement;
            statement = connect.createStatement();
            return RS = statement.executeQuery(sql);
        } catch (SQLException ex) {
            return RS;
        }
        
    }
}
