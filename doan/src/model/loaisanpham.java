package model;

public class loaisanpham {
    private String id;
    private String loai;

    public loaisanpham(String id, String loai) {
        this.id = id;
        this.loai = loai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
}
