/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginaplikasi;

/**
 *
 * @author Aziizah Oki Shofrina
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Koneksi {
    
    private Connection conn;
    private final String URL = "jdbc:mysql://localhost:3306/reservasi_ruangan";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    
    public Koneksi() {}
    
    public void openConn() {
        try {
            this.conn = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void closeConn() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void updateDB(String query) {
        try {
            Statement st = this.conn.createStatement();
            st.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public ArrayList<Customer> retrieveDB(String query) {
        ArrayList<Customer> arrSelected = new ArrayList<>();

        try {
            Statement st = this.conn.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Customer cust = new Customer();
                cust.setUsername(result.getString("username"));
                cust.setPassword(result.getString("password"));
                cust.setNamaLengkap(result.getString("nama_lengkap"));
                cust.setTelepon(result.getString("telepon"));

                arrSelected.add(cust);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return arrSelected;

    }
    

}
