package DAO;

import DTO.Luong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class daoLuong {

    private static daoLuong instance;

    public static daoLuong getInstance() {
        if (instance == null) {
            instance = new daoLuong();
        }
        return instance;
    }

    public daoLuong() {
    }

    // ==========================
    // LẤY DANH SÁCH LƯƠNG
    // ==========================
    public ArrayList<Luong> getListLuong() {

        ArrayList<Luong> result = new ArrayList<>();
        String query = "SELECT * FROM luong";
        ArrayList<Object> arr = new ArrayList<>();

        try {
            DataProvider.getIntance().open();

            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);

            while (rs.next()) {
                result.add(new Luong(
                        rs.getInt("id_luong"),
                        rs.getInt("id_nv"),
                        rs.getInt("luong_co_ban"),
                        rs.getInt("phu_cap"),
                        rs.getInt("thang"),
                        rs.getInt("nam")
                ));
            }

            DataProvider.getIntance().close();

        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return result;
    }

    // ==========================
    // THÊM LƯƠNG
    // ==========================
    public boolean insertLuong(int id_nv, int luong_co_ban,
                               int phu_cap, int thang, int nam) {

        String query = "INSERT INTO luong(id_nv, luong_co_ban, phu_cap, thang, nam) VALUES(?,?,?,?,?)";

        ArrayList<Object> arr = new ArrayList<>();
        arr.add(id_nv);
        arr.add(luong_co_ban);
        arr.add(phu_cap);
        arr.add(thang);
        arr.add(nam);

        try {
            DataProvider.getIntance().open();

            int result = DataProvider.getIntance().excuteUpdate(query, arr);

            DataProvider.getIntance().close();

            return result > 0;

        } catch (Exception ex) {
            DataProvider.getIntance().displayError((SQLException) ex);
        }

        return false;
    }

    // ==========================
    // XÓA LƯƠNG
    // ==========================
    public boolean deleteLuong(int id_luong) {

        String query = "DELETE FROM luong WHERE id_luong = ?";
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(id_luong);

        try {
            DataProvider.getIntance().open();

            int result = DataProvider.getIntance().excuteUpdate(query, arr);

            DataProvider.getIntance().close();

            return result > 0;

        } catch (Exception ex) {
            DataProvider.getIntance().displayError((SQLException) ex);
        }

        return false;
    }

    // ==========================
    // CẬP NHẬT LƯƠNG
    // ==========================
    public boolean updateLuong(int id_luong, int id_nv,
                           int luong_co_ban, int phu_cap,
                           int thang, int nam) {

        String query = "UPDATE luong SET luong_co_ban=?, phu_cap=?, thang=?, nam=? WHERE id_luong=?";
        ArrayList<Object> arr = new ArrayList<>();

        arr.add(luong_co_ban);
        arr.add(phu_cap);
        arr.add(thang);
        arr.add(nam);
        arr.add(id_luong);

        try {
            DataProvider.getIntance().open();

            int result = DataProvider.getIntance().excuteUpdate(query, arr);

            DataProvider.getIntance().close();

            return result > 0;

        } catch (Exception ex) {
            DataProvider.getIntance().displayError((SQLException) ex);
        }

        return false;
    }
}