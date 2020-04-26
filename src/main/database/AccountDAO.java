package database;

import java.sql.*;

// Database Access Object class pertaining to the account schema
public class AccountDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/schedulesharer";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";
    private Connection accountConn;

    // EFFECTS: constructs account Database Access Object and connects to database
    public AccountDAO() throws SQLException {
        accountConn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // REQUIRES: valid username in database
    // EFFECTS: retrieves studentID associated with username
    public String getStudentID(String username) {
        String studentID = null;
        String query = "SELECT * FROM student WHERE accountID = \"" + getAccountID(username) + "\";";
        Statement myStatement;
        ResultSet myResultSet = null;

        try {
            myStatement = accountConn.createStatement();
            myResultSet = myStatement.executeQuery(query);

            while (myResultSet.next()) {
                studentID = myResultSet.getString("studentID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentID;
    }

    // REQUIRES: valid username in database
    // EFFECTS: retrieves accountID associated with username
    public String getAccountID(String username) {
        String accountID = null;
        String query = "SELECT * FROM account WHERE username = \"" + username + "\";";
        Statement myStatement;
        ResultSet myResultSet = null;

        try {
            myStatement = accountConn.createStatement();
            myResultSet = myStatement.executeQuery(query);

            while (myResultSet.next()) {
                accountID = myResultSet.getString("accountID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountID;
    }

    // REQUIRES: username that does not already exist in database
    // EFFECTS: creates a new account in database
    public void registerUsernamePassword(String username, String password) throws SQLException {
        Statement myStatement = accountConn.createStatement();

        myStatement.executeUpdate("INSERT INTO account(username, password) VALUES(\""
                + username + "\", \"" + password + "\");");
    }
}

