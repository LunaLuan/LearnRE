package com.example.asd.learnre.model.obj;

/**
 * Created by asd on 9/17/2016.
 */

public class BaiHoc {

    private int idBaiHoc;
    private String chuDe;
    private String gioiThieu;
    private String baiDoc;

    public BaiHoc() {
    }

    public String getBaiDoc() {
        return baiDoc;
    }

    public void setBaiDoc(String baiDoc) {
        this.baiDoc = baiDoc;
    }

    public String getChuDe() {
        return chuDe;
    }

    public void setChuDe(String chuDe) {
        this.chuDe = chuDe;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public int getIdBaiHoc() {
        return idBaiHoc;
    }

    public void setIdBaiHoc(int idBaiHoc) {
        this.idBaiHoc = idBaiHoc;
    }
}
