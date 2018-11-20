package Main;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
public class Connect {    
    private Connection conn;
    public Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
          //  conn = DriverManager.getConnection("jdbc:mysql://localhost/quanlysinhvien?user=root&characterEncoding=UTF-8");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/quanlysinhvienv2?user=root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    // ADDSTUDENT:
    public boolean addStudent(SinhVien s){
        String sql = "call AddSinhVien(?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMSSV());
            ps.setString(2, s.getHoTen());
            ps.setString(3, s.getGioiTinh());
            ps.setDate(4, new Date(s.getNgaySinh().getTime()));
            ps.setString(5, s.getDiaChi());
            ps.setString(6, s.getMaKhoa());
            ps.setString(7, s.getMaNganh());
            ps.setInt(8, s.getKhoaHoc());
            return ps.executeUpdate() > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    // CREATE USER:
    public boolean createUser(SinhVien s){
        String sql = "INSERT INTO taikhoan (`mssv`, `matkhau`, `rank`) VALUES (?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMSSV());
            ps.setString(2, "1");
            ps.setString(3, "SV");
            return ps.executeUpdate() > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    // PRINTSTUDENT:
    public ArrayList<SinhVien> getDanhSachSinhVien(){
        ArrayList<SinhVien> list = new ArrayList<>();
        String sql = "call ShowSinhVien()";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            SinhVien s = new SinhVien();
            s.setMSSV(rs.getString("mssv"));
            s.setHoTen(rs.getString("hoTen"));
            s.setGioiTinh(rs.getString("gioiTinh"));
            s.setNgaySinh(rs.getDate("ngaySinh"));
            s.setDiaChi(rs.getString("diaChi"));
            s.setMaKhoa(rs.getString("maKhoa"));
            s.setMaNganh(rs.getString("maNganh"));
            s.setKhoaHoc(rs.getInt("KhoaHoc"));
            list.add(s);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    // DELETE
    public boolean delete(String mssv){
        String sql = "DELETE FROM sinhvien WHERE mssv=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mssv);
            return ps.executeUpdate() > 0;
        }
        
        catch (Exception e){
            e.printStackTrace();
        }
          return false;
    }
    public boolean deleteUser(String mssv){
        String sql = "DELETE FROM taikhoan WHERE mssv=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mssv);
            return ps.executeUpdate() > 0;
        }
        
        catch (Exception e){
            e.printStackTrace();
        }
          return false;
    }
    public boolean deleteHP(String mssv, String maHP){
        String sql = "DELETE FROM ketqua WHERE mssv=? AND maHP=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mssv);
            ps.setString(2, maHP);
            return ps.executeUpdate() > 0;
        }
        
        catch (Exception e){
            e.printStackTrace();
        }
          return false;
    }
    // SORT
    public ArrayList<SinhVien> sapXepSV(String t){
        ArrayList<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SINHVIEN ORDER BY " + t;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            SinhVien s = new SinhVien();
            s.setMSSV(rs.getString("mssv"));
            s.setHoTen(rs.getString("hoTen"));
            s.setGioiTinh(rs.getString("gioiTinh"));
            s.setNgaySinh(rs.getDate("ngaySinh"));
            s.setDiaChi(rs.getString("diaChi"));
            s.setMaKhoa(rs.getString("maKhoa"));
            s.setMaNganh(rs.getString("maNganh"));
            s.setKhoaHoc(rs.getInt("KhoaHoc"));
            list.add(s);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    
    }
    public ArrayList<SinhVien> tiemKiemSinhVien(String hoTen){
        ArrayList<SinhVien> list = new ArrayList<>();
        System.out.print(hoTen);
        String sql = "SELECT * FROM SINHVIEN WHERE hoTen LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+ hoTen+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            SinhVien s = new SinhVien();
            s.setMSSV(rs.getString("mssv"));
            s.setHoTen(rs.getString("hoTen"));
            s.setGioiTinh(rs.getString("gioiTinh"));
            s.setNgaySinh(rs.getDate("ngaySinh"));
            s.setDiaChi(rs.getString("diaChi"));
            s.setMaKhoa(rs.getString("maKhoa"));
            s.setMaNganh(rs.getString("maNganh"));
            s.setKhoaHoc(rs.getInt("KhoaHoc"));
            list.add(s);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public boolean update(SinhVien s){
       // String sql = "UPDATE sinhvien SET mssv=?, hoTen=?, gioiTinh=?, ngaySinh=?, noiSinh=?,"
     //          + " diaChi=?, maKhoa=?, maNganh=?, maCN=?, KhoaHoc=? WHERE mssv=?";
        String sql = "call UpdateSinhVien(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMSSV());
            ps.setString(2, s.getHoTen());
            ps.setString(3, s.getGioiTinh());
            ps.setDate(4, new Date(s.getNgaySinh().getTime()));
            ps.setString(5, s.getDiaChi());
            ps.setString(6, s.getMaKhoa());
            ps.setString(7, s.getMaNganh());
            ps.setInt(8, s.getKhoaHoc());
          //  ps.setString(11, s.getMSSV());
            return ps.executeUpdate() > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
          return false;
    }
    public ArrayList<Nganh> getNganh(){
        ArrayList<Nganh> listN = new ArrayList<>();
        String sql = "SELECT khoa.maKhoa, khoa.tenkhoa, nganh.maNganh, tenNganh FROM khoa, nganh WHERE khoa.maKhoa = nganh.maKhoa ORDER BY khoa.maKhoa";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            Nganh n = new Nganh();
            n.setMaKhoa(rs.getString("maKhoa"));
            n.setTenKhoa(rs.getString("tenKhoa"));
            n.setMaNganh(rs.getString("maNganh"));
            n.setTenNganh(rs.getString("tenNganh"));
            listN.add(n);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return listN;
    }
    public ArrayList<SinhVien> selectTableNganh(String mk, String mn){
        ArrayList<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM SINHVIEN WHERE makhoa=? && manganh=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mk);
            ps.setString(2, mn);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            SinhVien s = new SinhVien();
            s.setMSSV(rs.getString("mssv"));
            s.setHoTen(rs.getString("hoTen"));
            s.setGioiTinh(rs.getString("gioiTinh"));
            s.setNgaySinh(rs.getDate("ngaySinh"));
            s.setDiaChi(rs.getString("diaChi"));
            s.setMaKhoa(rs.getString("maKhoa"));
            s.setMaNganh(rs.getString("maNganh"));
            s.setKhoaHoc(rs.getInt("KhoaHoc"));
            list.add(s);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<KetQua> getKetQua(String X){
        ArrayList<KetQua> listKQ = new ArrayList<>();
        String sql = "SELECT mssv,a.maHP, soTinChi, tenHP, HocKi, NamHoc,diem FROM hocphan a,ketqua b WHERE a.maHP=b.maHP && mssv=?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,X);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            KetQua kq = new KetQua();
            kq.setMSSV(rs.getString("mssv"));
            kq.setMaHP(rs.getString("maHP"));
            kq.setTenHP(rs.getString("tenHP"));
            kq.setSoTC(rs.getInt("soTinChi"));
            kq.setHocKi(rs.getInt("HocKi"));
            kq.setNamHoc(rs.getInt("NamHoc"));
            kq.setDiem(rs.getInt("diem"));
            listKQ.add(kq);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return listKQ;
    }
    public ArrayList<HocPhan> getHP(String X){
        ArrayList<HocPhan> listHP = new ArrayList<>();
        String sql = "select maHP, tenHP, soTinChi from hocphan a where"
                + " (a.maHP not in (select a.maHP from hocphan a, ketqua b where a.mahp = b.mahp and b.mssv = ?) and makhoa=?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"B1606931");
            ps.setString(2,X);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            HocPhan hp = new HocPhan();
            hp.setMaHP(rs.getString("maHP"));
            hp.setTenHP(rs.getString("tenHP"));
            hp.setSoTC(rs.getInt("soTinChi"));
            
            listHP.add(hp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String sql1 = "select maHP, tenHP, soTinChi from hocphan a where"
                + " (a.maHP not in (select a.maHP from hocphan a, ketqua b where a.mahp = b.mahp and b.mssv = ?) and makhoa=?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"B1606931");
            ps.setString(2,"");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            HocPhan hp = new HocPhan();
            hp.setMaHP(rs.getString("maHP"));
            hp.setTenHP(rs.getString("tenHP"));
            hp.setSoTC(rs.getInt("soTinChi"));
            
            listHP.add(hp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return listHP;
    }
    public boolean updateDiem(KetQua kq){
        String sql = "UPDATE ketqua SET diem = ? WHERE mssv = ? AND maHP = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, kq.getDiem());
            ps.setString(2, kq.getMSSV());
            ps.setString(3, kq.getMaHP());
            return ps.executeUpdate() > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
          return false;
    } 
    public ArrayList<Khoa> getSoSV(){
        ArrayList<Khoa> list = new ArrayList<>();
        String sql = "SELECT COUNT(mssv) as TongSV, tenKhoa FROM sinhvien a , khoa b WHERE a.maKhoa = b.maKhoa GROUP BY a.maKhoa;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            Khoa k = new Khoa();
            k.setTongSV(rs.getInt("TongSV"));
            k.setTenKhoa(rs.getString("tenKhoa"));
            
            list.add(k);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public String login(String user, String password){
        String SV = new String("");
        String sql = "SELECT rank FROM taikhoan WHERE mssv = ? && matKhau = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){       
            SV = rs.getString("rank");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return SV;
    }
    public boolean themHP(String mahp, String mssv, int hk, int namhoc){
        String sql = "INSERT INTO ketqua (maHP, mssv, HocKi, NamHoc,diem) VALUES (?,?,?,?,-1);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mahp);
            ps.setString(2, mssv);
            ps.setInt(3, hk);
            ps.setInt(4, namhoc);
            return ps.executeUpdate() > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        new Connect();
    }
}


