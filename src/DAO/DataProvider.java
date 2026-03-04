package DAO;

import java.sql.*;
import java.util.ArrayList;

public class DataProvider {

    private static String url =
            "jdbc:mysql://localhost:3306/QuanLyKhoSieuThi?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static String user = "root";
    private static String pass = "1234";

    private Connection conn = null;
    private static DataProvider intance;

    public static DataProvider getIntance() {
        if (intance == null) {
            intance = new DataProvider();
        }
        return intance;
    }

    public void open() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, user, pass);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void displayError(SQLException ex) { System.out.println(" Error Message:" + ex.getMessage()); System.out.println(" SQL State:" + ex.getSQLState()); System.out.println(" Error Code:" + ex.getErrorCode()); }

    public Connection getconn() {
        return conn;
    }

    // ==============================
    // SELECT
    // ==============================

    public ResultSet excuteQuery(String sql, ArrayList<Object> arr) {
        try {
            open();
            PreparedStatement stm = conn.prepareStatement(sql);

            if (arr != null) {
                for (int i = 0; i < arr.size(); i++) {
                    stm.setObject(i + 1, arr.get(i));
                }
            }

            return stm.executeQuery();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // ==============================
    // INSERT UPDATE DELETE
    // ==============================

    public int excuteUpdate(String sql, ArrayList<Object> arr) {
        try {
            open();
            PreparedStatement stm = conn.prepareStatement(sql);

            if (arr != null) {
                for (int i = 0; i < arr.size(); i++) {
                    stm.setObject(i + 1, arr.get(i));
                }
            }

            int result = stm.executeUpdate();
            close();   // 
            return result;

        } catch (SQLException ex) {
            ex.printStackTrace();
            close();
            return -1;
        }
    }

    public boolean excute(String sql, ArrayList<Object> arr) {
        try {
            open();
            PreparedStatement stm = conn.prepareStatement(sql);

            if (arr != null) {
                for (int i = 0; i < arr.size(); i++) {
                    stm.setObject(i + 1, arr.get(i));
                }
            }

            boolean result = stm.execute();
            close();   
            return result;

        } catch (SQLException ex) {
            ex.printStackTrace();
            close();
            return false;
        }
    }
    public void excuteQuery(String sql) {// danh cho cau lenh select 
        try {
            PreparedStatement stm;
            stm = conn.prepareStatement(sql);
            stm.executeUpdate();

        } catch (SQLException ex) {
            displayError(ex);
        }
    }
    
}