package Main;
import java.io.Serializable;
public class Khoa {
    private String maKhoa, tenKhoa;
    int soSV;
    //////////////////////////////////
    public void setMaKhoa(String maKhoa){
        this.maKhoa = maKhoa;
    }
    public void setTenKhoa(String tenKhoa){
        this.tenKhoa = tenKhoa;
    }
    public void setTongSV(int soSV){
        this.soSV = soSV;
    }
    public String getMaKhoa(){
        return maKhoa;
    }
    public String getTenKhoa(){
        return tenKhoa;
    }
    public int getTongSV(){
        return soSV;
    }
}
