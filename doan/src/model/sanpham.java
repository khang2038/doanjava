package model;

public class sanpham {
    private String ID,tensanpham,loai;
    private int gia;

    public sanpham(String ID, String tensanpham, String loai, int gia) {
        this.ID = ID;
        this.tensanpham = tensanpham;
        this.loai = loai;
        this.gia = gia;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
