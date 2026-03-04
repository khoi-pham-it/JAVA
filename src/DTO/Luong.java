package DTO;

public class Luong {

    private int id_luong;
    private int id_nv;
    private int luong_co_ban;
    private int phu_cap;
    private int thang;
    private int nam;

    public Luong(int id_luong, int id_nv, int luong_co_ban,
                 int phu_cap, int thang, int nam) {
        this.id_luong = id_luong;
        this.id_nv = id_nv;
        this.luong_co_ban = luong_co_ban;
        this.phu_cap = phu_cap;
        this.thang = thang;
        this.nam = nam;
    }


    public int getId_luong() {
        return id_luong;
    }

    public int getId_nv() {
        return id_nv;
    }

    public int getLuong_co_ban() {
        return luong_co_ban;
    }

    public int getPhu_cap() {
        return phu_cap;
    }

    public int getThang() {
        return thang;
    }

    public int getNam() {
        return nam;
    }

    public int getTongLuong() {
        return luong_co_ban + phu_cap;
    }


    public void setId_luong(int id_luong) {
        this.id_luong = id_luong;
    }

    public void setId_nv(int id_nv) {
        this.id_nv = id_nv;
    }

    public void setLuong_co_ban(int luong_co_ban) {
        this.luong_co_ban = luong_co_ban;
    }

    public void setPhu_cap(int phu_cap) {
        this.phu_cap = phu_cap;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }
}