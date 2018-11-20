package Main;
public class HocPhan {
    private String maHP, tenHP, maKhoa;
    private int soTC;
    //////////////////////////////////
    public void setMaHP(String maHP){
        this.maHP = maHP;
    }
    public void setTenHP(String tenHP){
        this.tenHP = tenHP;
    }
    public void setMaKhoa(String maKhoa){
        this.maKhoa = maKhoa;
    }
    public void setSoTC(int soTC){
        this.soTC = soTC;
    }
     public String getMaHP(){
        return maHP;
    }
      public String getTenHP(){
        return tenHP;
    }
       public String getMaKhoa(){
        return maKhoa;
    }
    public int getSoTC(){
        return soTC;
    }
}
