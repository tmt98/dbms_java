package Main;
import java.io.Serializable;
public class KetQua {
    private String maHP,mssv,tenHP;
    private int soTC,hocKi,namHoc,diem;
    //////////////////////////////////
    public void setMSSV(String mssv){
        this.mssv = mssv;
    }
    public void setMaHP(String maHP){
        this.maHP = maHP;
    }
    public void setTenHP(String tenHP){
        this.tenHP = tenHP;
    }
    public void setSoTC(int soTC){
        this.soTC = soTC;
    }
    public void setHocKi(int hocKi){
        this.hocKi = hocKi;
    }
    public void setNamHoc(int namHoc){
        this.namHoc = namHoc;
    }
    public void setDiem(int diem){
        this.diem = diem;
    }
    public String getMSSV(){
        return mssv;
    }
    public String getMaHP(){
        return maHP;
    }
    public String getTenHP(){
        return tenHP;
    }
    public int getSoTC(){
        return soTC;
    }
    public int getHocKi(){
        return hocKi;
    }
    public int getNamHoc(){
        return namHoc;
    }
    public int getDiem(){
        return diem;
    }
    
}
