package com.example.ratatouille23.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private Connection connection;
    private static DatabaseConnection instance;
    private final String host = "localhost";
    private final int port = 5432;
    private final String user = "postgres";
    private final String pass = "aniellosomma1996";
    private String url;
    private boolean status = false;

    public DatabaseConnection()
    {
        this.connection = null;
        this.url = "jdbc:postgresql://localhost:5432/Ratatouille23";

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    System.out.println("connected: " + status);
                }catch(Exception e){
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                    System.out.println("connected: " + status);
                }
            }
        });

        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }

        //this.disconnect();
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (DatabaseConnection.instance == null) {
            DatabaseConnection.instance = new DatabaseConnection();
        }
        else if (DatabaseConnection.instance.getConnection().isClosed()) {
            DatabaseConnection.instance = new DatabaseConnection();
        }
        return DatabaseConnection.instance;
    }

}
