package Main;
import java.io.Serializable;
import java.util.Date;
public class SinhVien {
    // KHAI BAO SINH VIEN
    private String mssv, hoTen, gioiTinh, diaChi,maKhoa,maNganh;
    private Date ngaySinh; // dd//mm/yyyy
    private int KhoaHoc;
    // SET
    public void setMSSV(String mssv){
        this.mssv = mssv;
    }
    public void setHoTen(String hoTen){
        this.hoTen = hoTen;
    }
    public void setGioiTinh(String gioiTinh){
        this.gioiTinh = gioiTinh;
    }
    public void setNgaySinh(Date ngaySinh){
        this.ngaySinh = ngaySinh;
    }
    public void setDiaChi(String diaChi){
        this.diaChi = diaChi;
    }
    public void setMaKhoa(String maKhoa){
        this.maKhoa = maKhoa;
    }
    public void setMaNganh(String maNganh){
        this.maNganh = maNganh;
    }
    public void setKhoaHoc(int KhoaHoc){
        this.KhoaHoc = KhoaHoc;
    }
    // GET
    public String getMSSV(){
        return mssv;
    }
    public String getHoTen(){
        return hoTen;
    }
    public String getGioiTinh(){
        return gioiTinh;
    }
    public Date getNgaySinh(){
        return ngaySinh;
    }
    public String getDiaChi(){
        return diaChi;
    }
    public String getMaKhoa(){
        return maKhoa;
    }
    public String getMaNganh(){
        return maNganh;
    }
    public int getKhoaHoc(){
        return KhoaHoc;
    }
}
