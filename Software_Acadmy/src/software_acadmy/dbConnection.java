package software_acadmy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {
    Connection connect = null;
    
    public dbConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:sqlite:C:\\Users\\HP\\Documents\\NetBeansProjects\\Software_Acadmy\\src\\SoftwareAcadmy.db";
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection(url);
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
