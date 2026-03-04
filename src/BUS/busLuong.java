package BUS;

import DAO.daoLuong;
import DTO.Luong;
import java.util.ArrayList;

public class busLuong {

    private static busLuong instance;

    public static busLuong getInstance() {
        if (instance == null) {
            instance = new busLuong();
        }
        return instance;
    }

    public busLuong() {
    }

    // ================= LẤY DANH SÁCH =================
    public ArrayList<Luong> getListLuong() {
        return daoLuong.getInstance().getListLuong();
    }

    // ================= KIỂM TRA TRÙNG =================
    public boolean checkTrung(int id_nv, int thang, int nam) {

        ArrayList<Luong> list = getListLuong();

        for (Luong l : list) {
            if (l.getId_nv() == id_nv &&
                l.getThang() == thang &&
                l.getNam() == nam) {
                return true;
            }
        }
        return false;
    }

    // ================= THÊM =================
    public boolean insertLuong(int id_nv, int luong_co_ban,
                               int phu_cap, int thang, int nam) {

        if (luong_co_ban < 0 || phu_cap < 0) return false;
        if (thang < 1 || thang > 12) return false;
        if (nam < 2000) return false;

        return daoLuong.getInstance()
                .insertLuong(id_nv, luong_co_ban, phu_cap, thang, nam);
    }

    // ================= XÓA =================
    public boolean deleteLuong(int id_luong) {
        return daoLuong.getInstance().deleteLuong(id_luong);
    }

    // ================= CẬP NHẬT =================
    public boolean updateLuong(int id_luong, int id_nv,
                               int luong_co_ban, int phu_cap,
                               int thang, int nam) {

        if (luong_co_ban < 0 || phu_cap < 0) return false;
        if (thang < 1 || thang > 12) return false;

        return daoLuong.getInstance()
                .updateLuong(id_luong, id_nv,
                             luong_co_ban, phu_cap, thang, nam);
    }
}