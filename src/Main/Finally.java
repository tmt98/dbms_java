package Main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class Finally extends javax.swing.JFrame {

    /**
     * Creates new form Finally
     */
    
    private ArrayList<SinhVien> list;
    DefaultTableModel model;
    private ArrayList<Nganh> listN;
    DefaultTableModel modelN;
    private ArrayList<Khoa> listK;
    DefaultTableModel modelK;
    private ArrayList<KetQua> listKQ;
    DefaultTableModel modelKQ;
    private ArrayList<HocPhan> listHP;
    DefaultTableModel modelHP;
    int temp;
    public Finally() {
        initComponents();
        this.setLocationRelativeTo(null);
        list = new Connect().getDanhSachSinhVien();
        model = (DefaultTableModel) SinhVienTable.getModel();
        model.setColumnIdentifiers(new Object[]{
            "MSSV", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Mã Khoa", "Mã Ngành","Khóa học"
        });
        reloadTable();
        listN = new Connect().getNganh();
        modelN = (DefaultTableModel) NganhTable.getModel();
        modelN.setColumnIdentifiers(new Object[]{
            "Mã Khoa", "Tên Khoa", "Mã Ngành", "Tên Ngành"
        });
        reloadNganhTable();
        listK = new Connect().getSoSV();
        modelK = (DefaultTableModel) TongSVTB.getModel();
        modelK.setColumnIdentifiers(new Object[]{
            "Tổng Sinh Viên", "Tên Khoa"
        });
        reloadTongSoSV();
        
        mahpText.setEditable(false);
        tenhpText.setEditable(false);
        hockiText.setEditable(false);
        namhocText.setEditable(false);
        hotenTab2.setEditable(false);
        makhoaTab2.setEditable(false);
        manganhTab2.setEditable(false);
        DiemTB.setEditable(false);
       
    }
     
   public void reloadTable() {
        model = (DefaultTableModel) SinhVienTable.getModel();
        Object[] row = new Object[10];
        for (int g = 0; g <list.size(); g++) {
            row[0] = list.get(g).getMSSV();
            row[1] = list.get(g).getHoTen();
            row[2] = list.get(g).getGioiTinh();
            row[3] = list.get(g).getNgaySinh();
            row[4] = list.get(g).getDiaChi();
            row[5] = list.get(g).getMaKhoa();
            row[6] = list.get(g).getMaNganh();
            row[7] = list.get(g).getKhoaHoc();
            model.addRow(row);
        }
    }
   public void reloadNganhTable() {
        modelN = (DefaultTableModel) NganhTable.getModel();
        Object[] row = new Object[4];
        for (int g = 0; g <listN.size(); g++) {
            row[0] = listN.get(g).getMaKhoa();
            row[1] = listN.get(g).getTenKhoa();
            row[2] = listN.get(g).getMaNganh();
            row[3] = listN.get(g).getTenNganh();
            modelN.addRow(row);
        }
    }
    public void reloadHocPhanTable() {
        modelKQ = (DefaultTableModel) HocPhanTable.getModel();
        Object[] row = new Object[7];
        for (int g = 0; g <listKQ.size(); g++) {
            row[0] = listKQ.get(g).getMaHP();
            row[1] = listKQ.get(g).getTenHP();
            System.out.println(listKQ.get(g).getTenHP());
            row[2] = listKQ.get(g).getSoTC();
            row[3] = listKQ.get(g).getHocKi();
            row[4] = listKQ.get(g).getNamHoc();
            row[5] = listKQ.get(g).getDiem();
            String diemChu;
            if (listKQ.get(g).getDiem() == -1) diemChu = "";
            else if (listKQ.get(g).getDiem() < 4) diemChu = "F";
            else if (listKQ.get(g).getDiem() < 5) diemChu = "D";
            else if (listKQ.get(g).getDiem() < 6) diemChu = "C";
            else if (listKQ.get(g).getDiem() < 8) diemChu = "B";
            else diemChu = "A";
            row[6] = diemChu;
            modelKQ.addRow(row);
        }
    }
    public void reloadHocPhanTable1() {
        modelKQ = (DefaultTableModel) HocPhanTable1.getModel();
        Object[] row = new Object[7];
        for (int g = 0; g <listKQ.size(); g++) {
            row[0] = listKQ.get(g).getMaHP();
            row[1] = listKQ.get(g).getTenHP();
            System.out.println(listKQ.get(g).getTenHP());
            row[2] = listKQ.get(g).getSoTC();
            row[3] = listKQ.get(g).getHocKi();
            row[4] = listKQ.get(g).getNamHoc();
            row[5] = listKQ.get(g).getDiem();
            String diemChu;
            if (listKQ.get(g).getDiem() == -1) diemChu = "";
            else if (listKQ.get(g).getDiem() < 4) diemChu = "F";
            else if (listKQ.get(g).getDiem() < 5) diemChu = "D";
            else if (listKQ.get(g).getDiem() < 6) diemChu = "C";
            else if (listKQ.get(g).getDiem() < 8) diemChu = "B";
            else diemChu = "A";
            row[6] = diemChu;
            modelKQ.addRow(row);
        }
    }
    public void hocPhan(){
        modelHP = (DefaultTableModel) DSHP.getModel();
        Object[] row = new Object[3];
        for (int g = 0;g < listHP.size(); g++){
            row[0] = listHP.get(g).getMaHP();
            row[1] = listHP.get(g).getTenHP();
            row[2] = listHP.get(g).getSoTC();
            modelHP.addRow(row);
        }
    }
    public float pointAvg(){
        
        int stc=0,spoint=0, temp;
        float point;
        for (int i = 0; i < listKQ.size(); i++){
            temp = listKQ.get(i).getSoTC();
            if (HocPhanTable.getModel().getValueAt(i, 6).toString().equals("A")) {
                stc= stc+temp;
                spoint=spoint + temp*4;
            }
            else if (HocPhanTable.getModel().getValueAt(i, 6).toString().equals("B")) {
                stc= stc+temp;
                spoint=spoint + temp*3;
            }
            else if (HocPhanTable.getModel().getValueAt(i, 6).toString().equals("C")) {
                stc= stc+temp;
                spoint=spoint + temp*2;
            }
            else if (HocPhanTable.getModel().getValueAt(i, 6).toString().equals("D")) {
                stc= stc+temp;
                spoint=spoint + temp*1;
            }
            else if (HocPhanTable.getModel().getValueAt(i, 6).toString().equals("F")) {
                stc= stc+temp;
                spoint=spoint + temp*0;
            }
        }
        point =  (float) spoint / stc;
        return point;
    }
    public float pointAvg1(){
        
        int stc=0,spoint=0, temp;
        float point;
        for (int i = 0; i < listKQ.size(); i++){
            temp = listKQ.get(i).getSoTC();
            if (HocPhanTable1.getModel().getValueAt(i, 6).toString().equals("A")) {
                stc= stc+temp;
                spoint=spoint + temp*4;
            }
            else if (HocPhanTable1.getModel().getValueAt(i, 6).toString().equals("B")) {
                stc= stc+temp;
                spoint=spoint + temp*3;
            }
            else if (HocPhanTable1.getModel().getValueAt(i, 6).toString().equals("C")) {
                stc= stc+temp;
                spoint=spoint + temp*2;
            }
            else if (HocPhanTable1.getModel().getValueAt(i, 6).toString().equals("D")) {
                stc= stc+temp;
                spoint=spoint + temp*1;
            }
            else if (HocPhanTable1.getModel().getValueAt(i, 6).toString().equals("F")) {
                stc= stc+temp;
                spoint=spoint + temp*0;
            }
        }
        point =  (float) spoint / stc;
        return point;
    }
    public void reloadTB(){
        String mssv = mssvKQText.getText();
        DiemTB.setText("");
        temp=temp+1;
        if (temp == 1){
        int t=0;
        System.out.println(mssv);
        for (int i = 0; i <list.size();i++){
        if (mssv.equals(model.getValueAt(i, 0).toString())){
            
            hotenTab2.setText(SinhVienTable.getModel().getValueAt(i, 1).toString());
            makhoaTab2.setText(SinhVienTable.getModel().getValueAt(i, 5).toString());
            manganhTab2.setText(SinhVienTable.getModel().getValueAt(i, 6).toString());
            listKQ = new Connect().getKetQua(mssv);
                modelKQ = (DefaultTableModel) HocPhanTable.getModel();
                modelKQ.setColumnIdentifiers(new Object[]{
                "Mã HP", "Tên HP","Số Tín Chỉ", "Học Kì", "Năm Học","Điểm Số","Điểm Chữ"
            });
            modelKQ.fireTableDataChanged();
            reloadHocPhanTable(); 
            t=1;
            DiemTB.setText(Float.toString(pointAvg()));
            }
        }
        if (t == 0) {JOptionPane.showMessageDialog(rootPane, "Không có sinh viên với mã số đó!");
        hotenTab2.setText("");
        makhoaTab2.setText("");
        manganhTab2.setText("");
        temp=0;
        DiemTB.setText("");}
    } else {
        int t=0;
        for (int g = 0; g <listKQ.size(); g++) {
            modelKQ.removeRow(0);
        }
        System.out.println(mssv);
        for (int i = 0; i <list.size();i++){
        if (mssv.equals(model.getValueAt(i, 0).toString())){
            
            hotenTab2.setText(SinhVienTable.getModel().getValueAt(i, 1).toString());
            makhoaTab2.setText(SinhVienTable.getModel().getValueAt(i, 5).toString());
            manganhTab2.setText(SinhVienTable.getModel().getValueAt(i, 6).toString());
            listKQ = new Connect().getKetQua(mssv);
            modelKQ = (DefaultTableModel) HocPhanTable.getModel();
            modelKQ.setColumnIdentifiers(new Object[]{
            "Mã HP", "Tên HP","Số Tín Chỉ", "Học Kì", "Năm Học","Điểm Số","Điểm Chữ"
            });
            modelKQ.fireTableDataChanged();
            reloadHocPhanTable(); 
            t=1;
            DiemTB.setText(Float.toString(pointAvg()));
            }
        }
        if (t == 0) {JOptionPane.showMessageDialog(rootPane, "Không có sinh viên với mã số đó!");
        hotenTab2.setText("");
        makhoaTab2.setText("");
        manganhTab2.setText("");
        temp=0;
        DiemTB.setText("");}
    }}
    public void reloadTBSV(String s){
    for (int i = 0; i <list.size();i++){

        System.out.println(model.getValueAt(i, 0).toString());
        if (s.equals(model.getValueAt(i, 0).toString())){
            mssvSVF.setText(SinhVienTable.getModel().getValueAt(i, 0).toString());mssvSVF.setEditable(false);
            hotenTab3.setText(SinhVienTable.getModel().getValueAt(i, 1).toString());hotenTab3.setEditable(false);
            makhoaTab3.setText(SinhVienTable.getModel().getValueAt(i, 5).toString());makhoaTab3.setEditable(false);
            manganhTab3.setText(SinhVienTable.getModel().getValueAt(i, 6).toString());manganhTab3.setEditable(false);
            diachisvText.setText(SinhVienTable.getModel().getValueAt(i, 4).toString());diachisvText.setEditable(false);
            khoahocsvText.setText(SinhVienTable.getModel().getValueAt(i, 7).toString());khoahocsvText.setEditable(false);
            listKQ = new Connect().getKetQua(s);
                modelKQ = (DefaultTableModel) HocPhanTable1.getModel();
                modelKQ.setColumnIdentifiers(new Object[]{
                "Mã HP", "Tên HP","Số Tín Chỉ", "Học Kì", "Năm Học","Điểm Số","Điểm Chữ"
            });
            modelKQ.fireTableDataChanged();
            reloadHocPhanTable1(); 
            DiemTB1.setText(Float.toString(pointAvg1()));
            }
        }}
    public void reloadTongSoSV() {
        modelK = (DefaultTableModel) TongSVTB.getModel();
        Object[] row = new Object[2];
        for (int g = 0; g <listK.size(); g++) {
            row[0] = listK.get(g).getTongSV();
            row[1] = listK.get(g).getTenKhoa();
            
            modelK.addRow(row);
        }
    }
    public void ShowSinhVienPanel(){
        SinhVienFrame.setVisible(true);
        SinhVienFrame.setResizable(false);
        SinhVienFrame.setSize(1015, 629);
        SinhVienFrame.setLocationRelativeTo(null);
    }
    public void exportExcel(JTable table) {
                JFileChooser chooser = new JFileChooser();
             int i = chooser.showSaveDialog(chooser);
            if (i == JFileChooser.APPROVE_OPTION) {
             File file = chooser.getSelectedFile();
            try {
             FileWriter out = new FileWriter(file + ".xls");
             BufferedWriter bwrite = new BufferedWriter(out);
             DefaultTableModel model = (DefaultTableModel) table.getModel();
             // ten Cot
             for (int j = 0; j < table.getColumnCount(); j++) {
              bwrite.write(model.getColumnName(j) + "\t");
             }
             bwrite.write("\n");
                            // Lay du lieu dong
                            for (int j = 0; j < table.getRowCount(); j++) {
                             for (int k = 0; k < table.getColumnCount(); k++) {
                    bwrite.write(model.getValueAt(j, k) + "\t");
                   }
                   bwrite.write("\n");
                  }
                  bwrite.close();
                  JOptionPane.showMessageDialog(null, "Lưu file thành công!");
                 } catch (Exception e2) {
                  JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
                 }
                }
               }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ThemSinhVienDialog = new javax.swing.JDialog();
        nganhLabel = new javax.swing.JLabel();
        diachiText = new javax.swing.JTextField();
        makhoaText = new javax.swing.JTextField();
        khoahocLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        khoahocBox = new javax.swing.JComboBox<>();
        yesAddButton = new javax.swing.JButton();
        mssvLabel = new javax.swing.JLabel();
        namButton = new javax.swing.JRadioButton();
        hotenLabel = new javax.swing.JLabel();
        nuButton = new javax.swing.JRadioButton();
        gioitinhLabel = new javax.swing.JLabel();
        mssvText = new javax.swing.JTextField();
        manganhText = new javax.swing.JTextField();
        ngaysinhLabel = new javax.swing.JLabel();
        hotenText = new javax.swing.JTextField();
        diachiLabel = new javax.swing.JLabel();
        ngaysinhText = new javax.swing.JTextField();
        makhoaLabel = new javax.swing.JLabel();
        gioitinhGroup = new javax.swing.ButtonGroup();
        CapNhatSVDialog = new javax.swing.JDialog();
        gioitinhLabel1 = new javax.swing.JLabel();
        makhoaText1 = new javax.swing.JTextField();
        mssvText1 = new javax.swing.JTextField();
        khoahocLabel1 = new javax.swing.JLabel();
        manganhText1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ngaysinhLabel1 = new javax.swing.JLabel();
        khoahocBox1 = new javax.swing.JComboBox<>();
        hotenText1 = new javax.swing.JTextField();
        yesUpdateButton = new javax.swing.JButton();
        diachiLabel1 = new javax.swing.JLabel();
        mssvLabel1 = new javax.swing.JLabel();
        ngaysinhText1 = new javax.swing.JTextField();
        namButton1 = new javax.swing.JRadioButton();
        makhoaLabel1 = new javax.swing.JLabel();
        hotenLabel1 = new javax.swing.JLabel();
        nganhLabel1 = new javax.swing.JLabel();
        nuButton1 = new javax.swing.JRadioButton();
        diachiText1 = new javax.swing.JTextField();
        SinhVienFrame = new javax.swing.JFrame();
        QuanLyDiemTable1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        HocPhanTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        hotenTab3 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        makhoaTab3 = new javax.swing.JTextField();
        manganhTab3 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        ngaysinhsvText = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        khoahocsvText = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        diachisvText = new javax.swing.JTextField();
        mssvSVF = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        DiemTB1 = new javax.swing.JTextField();
        themhocphan = new javax.swing.JButton();
        xoahocphan = new javax.swing.JButton();
        capnhatthongtin = new javax.swing.JButton();
        DanhSachHP = new javax.swing.JDialog();
        jScrollPane6 = new javax.swing.JScrollPane();
        DSHP = new javax.swing.JTable();
        hockiCombo = new javax.swing.JComboBox<>();
        namhocCombo = new javax.swing.JComboBox<>();
        themhpbutton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        TabPane = new javax.swing.JTabbedPane();
        QLSVPane = new javax.swing.JPanel();
        CapNhatSV = new javax.swing.JButton();
        TimKiemButton = new javax.swing.JButton();
        SapXepButton = new javax.swing.JButton();
        ThemSinhVienButton = new javax.swing.JButton();
        XoaSinhVienButton = new javax.swing.JButton();
        TimKiemText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        SinhVienTable = new javax.swing.JTable();
        Export = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        NganhTable = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        SapXepBox = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TongSVTB = new javax.swing.JTable();
        QuanLyDiemTable = new javax.swing.JPanel();
        mssvKQText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        TimKQText = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        HocPhanTable = new javax.swing.JTable();
        CapNhatDiemPanel = new javax.swing.JPanel();
        mahpText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tenhpText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        hockiText = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        namhocText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        diemText = new javax.swing.JTextField();
        CapNhatDiem = new javax.swing.JButton();
        ShowInfoPanel = new javax.swing.JPanel();
        hotenTab2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        makhoaTab2 = new javax.swing.JTextField();
        manganhTab2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        DiemTBPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        DiemTB = new javax.swing.JTextField();
        ThongKePanel = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        nganhLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nganhLabel.setText("Mã Ngành:");

        diachiText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        makhoaText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        khoahocLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        khoahocLabel.setText("Khóa Học:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("--Thêm sinh viên--");

        khoahocBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        khoahocBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "39", "40", "41", "42", "43", "44" }));
        khoahocBox.setMinimumSize(new java.awt.Dimension(46, 23));
        khoahocBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khoahocBoxActionPerformed(evt);
            }
        });

        yesAddButton.setText("Đồng ý");
        yesAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesAddButtonActionPerformed(evt);
            }
        });

        mssvLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mssvLabel.setText("Mã Số Sinh Viên:");

        gioitinhGroup.add(namButton);
        namButton.setText("Nam");
        namButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namButtonActionPerformed(evt);
            }
        });

        hotenLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hotenLabel.setText("Họ Tên Sinh Viên:");

        gioitinhGroup.add(nuButton);
        nuButton.setText("Nữ");

        gioitinhLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gioitinhLabel.setText("Giới Tính:");

        mssvText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        manganhText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ngaysinhLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ngaysinhLabel.setText("Ngày Sinh:");

        hotenText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        diachiLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        diachiLabel.setText("Địa Chỉ:");

        ngaysinhText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        makhoaLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        makhoaLabel.setText("Mã Khoa:");

        javax.swing.GroupLayout ThemSinhVienDialogLayout = new javax.swing.GroupLayout(ThemSinhVienDialog.getContentPane());
        ThemSinhVienDialog.getContentPane().setLayout(ThemSinhVienDialogLayout);
        ThemSinhVienDialogLayout.setHorizontalGroup(
            ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThemSinhVienDialogLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThemSinhVienDialogLayout.createSequentialGroup()
                        .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(khoahocLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(diachiLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(makhoaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nganhLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(diachiText)
                            .addComponent(makhoaText)
                            .addComponent(khoahocBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, 151, Short.MAX_VALUE)
                            .addComponent(manganhText)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ThemSinhVienDialogLayout.createSequentialGroup()
                        .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mssvLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hotenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ngaysinhLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gioitinhLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(mssvText, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                .addComponent(hotenText)
                                .addComponent(ngaysinhText))
                            .addGroup(ThemSinhVienDialogLayout.createSequentialGroup()
                                .addComponent(namButton)
                                .addGap(18, 18, 18)
                                .addComponent(nuButton)))))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThemSinhVienDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(yesAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        ThemSinhVienDialogLayout.setVerticalGroup(
            ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThemSinhVienDialogLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mssvLabel)
                    .addComponent(mssvText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hotenLabel)
                    .addComponent(hotenText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gioitinhLabel)
                    .addComponent(namButton)
                    .addComponent(nuButton))
                .addGap(18, 18, 18)
                .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ngaysinhLabel)
                    .addComponent(ngaysinhText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diachiLabel)
                    .addComponent(diachiText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(makhoaLabel)
                    .addComponent(makhoaText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nganhLabel)
                    .addComponent(manganhText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ThemSinhVienDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(khoahocLabel)
                    .addComponent(khoahocBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(yesAddButton)
                .addGap(30, 30, 30))
        );

        gioitinhLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gioitinhLabel1.setText("Giới Tính:");

        makhoaText1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        mssvText1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        khoahocLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        khoahocLabel1.setText("Khóa Học:");

        manganhText1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("--Cập nhật sinh viên--");

        ngaysinhLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ngaysinhLabel1.setText("Ngày Sinh:");

        khoahocBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        khoahocBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "39", "40", "41", "42", "43", "44" }));
        khoahocBox1.setMinimumSize(new java.awt.Dimension(46, 23));

        hotenText1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        yesUpdateButton.setText("Đồng ý");
        yesUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesUpdateButtonActionPerformed(evt);
            }
        });

        diachiLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        diachiLabel1.setText("Địa Chỉ:");

        mssvLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mssvLabel1.setText("Mã Số Sinh Viên:");

        ngaysinhText1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        gioitinhGroup.add(namButton1);
        namButton1.setText("Nam");
        namButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namButton1ActionPerformed(evt);
            }
        });

        makhoaLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        makhoaLabel1.setText("Mã Khoa:");

        hotenLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hotenLabel1.setText("Họ Tên Sinh Viên:");

        nganhLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nganhLabel1.setText("Mã Ngành:");

        gioitinhGroup.add(nuButton1);
        nuButton1.setText("Nữ");

        diachiText1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout CapNhatSVDialogLayout = new javax.swing.GroupLayout(CapNhatSVDialog.getContentPane());
        CapNhatSVDialog.getContentPane().setLayout(CapNhatSVDialogLayout);
        CapNhatSVDialogLayout.setHorizontalGroup(
            CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CapNhatSVDialogLayout.createSequentialGroup()
                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CapNhatSVDialogLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CapNhatSVDialogLayout.createSequentialGroup()
                                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(khoahocLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(diachiLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(makhoaLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nganhLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(diachiText1)
                                    .addComponent(makhoaText1)
                                    .addComponent(khoahocBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 151, Short.MAX_VALUE)
                                    .addComponent(manganhText1)))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(CapNhatSVDialogLayout.createSequentialGroup()
                                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mssvLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hotenLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ngaysinhLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(gioitinhLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(mssvText1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                        .addComponent(hotenText1)
                                        .addComponent(ngaysinhText1))
                                    .addGroup(CapNhatSVDialogLayout.createSequentialGroup()
                                        .addComponent(namButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(nuButton1))))))
                    .addGroup(CapNhatSVDialogLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(yesUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        CapNhatSVDialogLayout.setVerticalGroup(
            CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CapNhatSVDialogLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mssvLabel1)
                    .addComponent(mssvText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hotenLabel1)
                    .addComponent(hotenText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gioitinhLabel1)
                    .addComponent(namButton1)
                    .addComponent(nuButton1))
                .addGap(18, 18, 18)
                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ngaysinhLabel1)
                    .addComponent(ngaysinhText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diachiLabel1)
                    .addComponent(diachiText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(makhoaLabel1)
                    .addComponent(makhoaText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nganhLabel1)
                    .addComponent(manganhText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CapNhatSVDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(khoahocLabel1)
                    .addComponent(khoahocBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(yesUpdateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        SinhVienFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        QuanLyDiemTable1.setBackground(new java.awt.Color(255, 102, 102));
        QuanLyDiemTable1.setMaximumSize(new java.awt.Dimension(995, 400));
        QuanLyDiemTable1.setMinimumSize(new java.awt.Dimension(995, 400));

        HocPhanTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HP", "Tên HP", "Số Tín Chỉ", "Học Kì", "Năm Học", "Điểm Số", "Điểm Chữ"
            }
        ));
        HocPhanTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HocPhanTable1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(HocPhanTable1);

        jLabel19.setText("Họ Tên:");

        jLabel20.setText("Mã Khoa:");

        jLabel21.setText("Mã Ngành:");

        jLabel23.setText("Ngày Sinh:");

        jLabel24.setText("Địa Chỉ:");

        jLabel25.setText("Khóa Học:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(mssvSVF, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(makhoaTab3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hotenTab3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(15, 15, 15)
                        .addComponent(ngaysinhsvText, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(15, 15, 15)
                        .addComponent(manganhTab3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(29, 29, 29)
                        .addComponent(diachisvText, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(khoahocsvText, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hotenTab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(ngaysinhsvText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(diachisvText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mssvSVF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(makhoaTab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(manganhTab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(khoahocsvText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel22.setText("Điểm trung bình tích lũy:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel22)
                .addGap(29, 29, 29)
                .addComponent(DiemTB1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(DiemTB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        themhocphan.setText("Thêm Học Phần");
        themhocphan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themhocphanActionPerformed(evt);
            }
        });

        xoahocphan.setText("Xóa Học Phần");
        xoahocphan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoahocphanActionPerformed(evt);
            }
        });

        capnhatthongtin.setText("Cập Nhật Thông Tin");
        capnhatthongtin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capnhatthongtinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout QuanLyDiemTable1Layout = new javax.swing.GroupLayout(QuanLyDiemTable1);
        QuanLyDiemTable1.setLayout(QuanLyDiemTable1Layout);
        QuanLyDiemTable1Layout.setHorizontalGroup(
            QuanLyDiemTable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuanLyDiemTable1Layout.createSequentialGroup()
                .addGroup(QuanLyDiemTable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QuanLyDiemTable1Layout.createSequentialGroup()
                        .addGap(654, 654, 654)
                        .addGroup(QuanLyDiemTable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(capnhatthongtin, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(xoahocphan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(themhocphan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(QuanLyDiemTable1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(QuanLyDiemTable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(QuanLyDiemTable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(80, 80, 80))
        );
        QuanLyDiemTable1Layout.setVerticalGroup(
            QuanLyDiemTable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuanLyDiemTable1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(themhocphan)
                .addGap(15, 15, 15)
                .addComponent(xoahocphan)
                .addGap(15, 15, 15)
                .addComponent(capnhatthongtin)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SinhVienFrameLayout = new javax.swing.GroupLayout(SinhVienFrame.getContentPane());
        SinhVienFrame.getContentPane().setLayout(SinhVienFrameLayout);
        SinhVienFrameLayout.setHorizontalGroup(
            SinhVienFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SinhVienFrameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(QuanLyDiemTable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        SinhVienFrameLayout.setVerticalGroup(
            SinhVienFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SinhVienFrameLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(QuanLyDiemTable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        DSHP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Học Phần", "Tên Học Phần", "Số Tín Chỉ"
            }
        ));
        jScrollPane6.setViewportView(DSHP);

        hockiCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "Hè" }));

        namhocCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "2019", "2020" }));

        themhpbutton.setText("Thêm");
        themhpbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themhpbuttonActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setText("Thêm Học Phần");

        javax.swing.GroupLayout DanhSachHPLayout = new javax.swing.GroupLayout(DanhSachHP.getContentPane());
        DanhSachHP.getContentPane().setLayout(DanhSachHPLayout);
        DanhSachHPLayout.setHorizontalGroup(
            DanhSachHPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DanhSachHPLayout.createSequentialGroup()
                .addGroup(DanhSachHPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DanhSachHPLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(DanhSachHPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DanhSachHPLayout.createSequentialGroup()
                                .addComponent(hockiCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(namhocCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(themhpbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DanhSachHPLayout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(jLabel13)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        DanhSachHPLayout.setVerticalGroup(
            DanhSachHPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DanhSachHPLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel13)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(DanhSachHPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hockiCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namhocCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(themhpbutton))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        QLSVPane.setBackground(new java.awt.Color(255, 102, 102));
        QLSVPane.setPreferredSize(new java.awt.Dimension(995, 450));

        CapNhatSV.setText("Cập Nhập SV");
        CapNhatSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapNhatSVActionPerformed(evt);
            }
        });

        TimKiemButton.setText("Tìm Kiếm");
        TimKiemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemButtonActionPerformed(evt);
            }
        });

        SapXepButton.setText("Sắp Xếp");
        SapXepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SapXepButtonActionPerformed(evt);
            }
        });

        ThemSinhVienButton.setText("Thêm Sinh Viên");
        ThemSinhVienButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemSinhVienButtonActionPerformed(evt);
            }
        });

        XoaSinhVienButton.setText("Xóa Sinh Viên");
        XoaSinhVienButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XoaSinhVienButtonActionPerformed(evt);
            }
        });

        TimKiemText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TimKiemTextFocusGained(evt);
            }
        });
        TimKiemText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TimKiemTextMouseClicked(evt);
            }
        });
        TimKiemText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKiemTextActionPerformed(evt);
            }
        });
        TimKiemText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TimKiemTextKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TimKiemTextKeyTyped(evt);
            }
        });

        SinhVienTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MSSV", "Họ Tên", "Giới Tính", "Ngày Sinh", "Địa Chỉ", "Mã Khoa", "Mã Ngành", "Khóa Học"
            }
        ));
        jScrollPane1.setViewportView(SinhVienTable);

        Export.setText("Export To Excel");
        Export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportActionPerformed(evt);
            }
        });

        NganhTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khoa", "Tên Khoa", "Mã Ngành", "Tên Ngành"
            }
        ));
        NganhTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                NganhTableFocusGained(evt);
            }
        });
        NganhTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NganhTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(NganhTable);

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        SapXepBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MSSV", "Họ Tên", "Ngày Sinh", "Địa Chỉ", "Mã Khoa", "Mã Ngành" }));
        SapXepBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SapXepBoxActionPerformed(evt);
            }
        });

        TongSVTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tổng Số SV", "Khoa"
            }
        ));
        jScrollPane2.setViewportView(TongSVTB);

        javax.swing.GroupLayout QLSVPaneLayout = new javax.swing.GroupLayout(QLSVPane);
        QLSVPane.setLayout(QLSVPaneLayout);
        QLSVPaneLayout.setHorizontalGroup(
            QLSVPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QLSVPaneLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(QLSVPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QLSVPaneLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(QLSVPaneLayout.createSequentialGroup()
                        .addComponent(ThemSinhVienButton)
                        .addGap(20, 20, 20)
                        .addComponent(CapNhatSV)
                        .addGap(20, 20, 20)
                        .addComponent(XoaSinhVienButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TimKiemText, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TimKiemButton)
                        .addGap(66, 66, 66))))
            .addGroup(QLSVPaneLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(QLSVPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QLSVPaneLayout.createSequentialGroup()
                        .addComponent(SapXepBox, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SapXepButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(refreshButton)
                        .addGap(18, 18, 18)
                        .addComponent(Export)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QLSVPaneLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))))
        );
        QLSVPaneLayout.setVerticalGroup(
            QLSVPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QLSVPaneLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(QLSVPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TimKiemButton)
                    .addComponent(TimKiemText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ThemSinhVienButton)
                    .addComponent(XoaSinhVienButton)
                    .addComponent(CapNhatSV))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(QLSVPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(QLSVPaneLayout.createSequentialGroup()
                        .addGroup(QLSVPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Export)
                            .addComponent(refreshButton)
                            .addComponent(SapXepButton)
                            .addComponent(SapXepBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        TabPane.addTab("Thông Tin Sinh Viên", QLSVPane);

        QuanLyDiemTable.setBackground(new java.awt.Color(255, 102, 102));
        QuanLyDiemTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                QuanLyDiemTableFocusGained(evt);
            }
        });

        mssvKQText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mssvKQTextMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nhập Mã Số Sinh Viên:");

        TimKQText.setText("Tìm");
        TimKQText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimKQTextActionPerformed(evt);
            }
        });

        HocPhanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HP", "Tên HP", "Số Tín Chỉ", "Học Kì", "Năm Học", "Điểm Số", "Điểm Chữ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        HocPhanTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HocPhanTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(HocPhanTable);
        if (HocPhanTable.getColumnModel().getColumnCount() > 0) {
            HocPhanTable.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel6.setText("Mã Học Phần:");

        jLabel7.setText("Tên Học Phần:");

        jLabel8.setText("Học Kỳ:");

        jLabel9.setText("Năm Học:");

        jLabel10.setText("Điểm:");

        CapNhatDiem.setText("Cập Nhật");
        CapNhatDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CapNhatDiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CapNhatDiemPanelLayout = new javax.swing.GroupLayout(CapNhatDiemPanel);
        CapNhatDiemPanel.setLayout(CapNhatDiemPanelLayout);
        CapNhatDiemPanelLayout.setHorizontalGroup(
            CapNhatDiemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CapNhatDiemPanelLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(CapNhatDiemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(CapNhatDiemPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(diemText, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CapNhatDiemPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(namhocText, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CapNhatDiemPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hockiText, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CapNhatDiemPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mahpText, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CapNhatDiemPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(35, 35, 35)
                        .addComponent(tenhpText, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
            .addGroup(CapNhatDiemPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(CapNhatDiem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CapNhatDiemPanelLayout.setVerticalGroup(
            CapNhatDiemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CapNhatDiemPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(CapNhatDiemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mahpText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CapNhatDiemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenhpText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CapNhatDiemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hockiText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CapNhatDiemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namhocText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CapNhatDiemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diemText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(CapNhatDiem)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel4.setText("Họ Tên:");

        jLabel5.setText("Mã Khoa:");

        jLabel11.setText("Mã Ngành:");

        javax.swing.GroupLayout ShowInfoPanelLayout = new javax.swing.GroupLayout(ShowInfoPanel);
        ShowInfoPanel.setLayout(ShowInfoPanelLayout);
        ShowInfoPanelLayout.setHorizontalGroup(
            ShowInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowInfoPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(15, 15, 15)
                .addComponent(hotenTab2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addGap(15, 15, 15)
                .addComponent(makhoaTab2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addGap(15, 15, 15)
                .addComponent(manganhTab2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(321, Short.MAX_VALUE))
        );
        ShowInfoPanelLayout.setVerticalGroup(
            ShowInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowInfoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ShowInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ShowInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(manganhTab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ShowInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(hotenTab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(makhoaTab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel12.setText("Điểm trung bình tích lũy:");

        javax.swing.GroupLayout DiemTBPanelLayout = new javax.swing.GroupLayout(DiemTBPanel);
        DiemTBPanel.setLayout(DiemTBPanelLayout);
        DiemTBPanelLayout.setHorizontalGroup(
            DiemTBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiemTBPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(DiemTB, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        DiemTBPanelLayout.setVerticalGroup(
            DiemTBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DiemTBPanelLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(DiemTBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(DiemTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout QuanLyDiemTableLayout = new javax.swing.GroupLayout(QuanLyDiemTable);
        QuanLyDiemTable.setLayout(QuanLyDiemTableLayout);
        QuanLyDiemTableLayout.setHorizontalGroup(
            QuanLyDiemTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuanLyDiemTableLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(QuanLyDiemTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QuanLyDiemTableLayout.createSequentialGroup()
                        .addComponent(DiemTBPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                        .addComponent(CapNhatDiemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163))
                    .addGroup(QuanLyDiemTableLayout.createSequentialGroup()
                        .addGroup(QuanLyDiemTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                            .addGroup(QuanLyDiemTableLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mssvKQText, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TimKQText))
                            .addComponent(ShowInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        QuanLyDiemTableLayout.setVerticalGroup(
            QuanLyDiemTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuanLyDiemTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(QuanLyDiemTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mssvKQText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(TimKQText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ShowInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(QuanLyDiemTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(QuanLyDiemTableLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(CapNhatDiemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DiemTBPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        TabPane.addTab("Quản Lý Điểm", QuanLyDiemTable);

        ThongKePanel.setBackground(new java.awt.Color(255, 102, 102));

        jButton4.setText("Test");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ThongKePanelLayout = new javax.swing.GroupLayout(ThongKePanel);
        ThongKePanel.setLayout(ThongKePanelLayout);
        ThongKePanelLayout.setHorizontalGroup(
            ThongKePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addContainerGap(932, Short.MAX_VALUE))
        );
        ThongKePanelLayout.setVerticalGroup(
            ThongKePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongKePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addContainerGap(557, Short.MAX_VALUE))
        );

        TabPane.addTab("Thống Kê", ThongKePanel);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel14.setText("Quản Lý Sinh Viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabPane)
            .addGroup(layout.createSequentialGroup()
                .addGap(407, 407, 407)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TabPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CapNhatSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapNhatSVActionPerformed
        int row = SinhVienTable.getSelectedRow();   
        if (row == -1) JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn 1 sinh viên!");
        else {
        mssvText1.setText(SinhVienTable.getModel().getValueAt(row, 0).toString());
        mssvText1.setEnabled(false);
        hotenText1.setText(SinhVienTable.getModel().getValueAt(row, 1).toString());
        if (SinhVienTable.getModel().getValueAt(row, 2).toString().equals("Nam")) namButton1.setSelected(true);
        else nuButton1.setSelected(true);

        ngaysinhText1.setText(SinhVienTable.getModel().getValueAt(row, 3).toString());
        diachiText1.setText(SinhVienTable.getModel().getValueAt(row, 4).toString());
        makhoaText1.setText(SinhVienTable.getModel().getValueAt(row, 5).toString());
        manganhText1.setText(SinhVienTable.getModel().getValueAt(row, 6).toString());
        if (SinhVienTable.getModel().getValueAt(row, 7).toString().equals("39")) khoahocBox1.setSelectedIndex(0);
        else if (SinhVienTable.getModel().getValueAt(row, 7).toString().equals("40")) khoahocBox1.setSelectedIndex(1);
        else if (SinhVienTable.getModel().getValueAt(row, 7).toString().equals("41")) khoahocBox1.setSelectedIndex(2);
        else if (SinhVienTable.getModel().getValueAt(row, 7).toString().equals("42")) khoahocBox1.setSelectedIndex(3);
        else if (SinhVienTable.getModel().getValueAt(row, 7).toString().equals("43")) khoahocBox1.setSelectedIndex(4);
        else khoahocBox1.setSelectedIndex(5);
        CapNhatSVDialog.setVisible(true);
        CapNhatSVDialog.setSize(353, 500);
        CapNhatSVDialog.setResizable(false);}
    }//GEN-LAST:event_CapNhatSVActionPerformed

    private void TimKiemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemButtonActionPerformed
       String hoTen = TimKiemText.getText();
        for (int g = 0; g <list.size(); g++) {
            model.removeRow(0);
        }
        list = new Connect().tiemKiemSinhVien(hoTen);
        model = (DefaultTableModel) SinhVienTable.getModel();
        model.setColumnIdentifiers(new Object[]{
            "MSSV", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Mã khoa", "Mã ngành","Khóa học"
        });
        reloadTable();
                        
    }//GEN-LAST:event_TimKiemButtonActionPerformed

    private void SapXepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SapXepButtonActionPerformed
        for (int g = 0; g <list.size(); g++) {
            model.removeRow(0);
        }
        if (SapXepBox.getSelectedItem().toString().equals("MSSV")) SapXepBox.setActionCommand("mssv");
        else if (SapXepBox.getSelectedItem().toString().equals("Họ Tên")) SapXepBox.setActionCommand("hoTen");
        else if (SapXepBox.getSelectedItem().toString().equals("Ngày Sinh")) SapXepBox.setActionCommand("ngaySinh");
        else if (SapXepBox.getSelectedItem().toString().equals("Địa Chỉ")) SapXepBox.setActionCommand("diaChi");
        else if (SapXepBox.getSelectedItem().toString().equals("Mã Khoa")) SapXepBox.setActionCommand("maKhoa");
        else SapXepBox.setActionCommand("maNganh");
        String t = SapXepBox.getActionCommand();
        list = new Connect().sapXepSV(t);
        model = (DefaultTableModel) SinhVienTable.getModel();
        model.setColumnIdentifiers(new Object[]{
            "MSSV", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Mã khoa", "Mã ngành","Khóa học"
        });
        reloadTable();
                    
    }//GEN-LAST:event_SapXepButtonActionPerformed

    private void ThemSinhVienButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemSinhVienButtonActionPerformed
          ThemSinhVienDialog.setVisible(true);
          ThemSinhVienDialog.setSize(353, 500);
          ThemSinhVienDialog.setResizable(false);
    }//GEN-LAST:event_ThemSinhVienButtonActionPerformed

    private void XoaSinhVienButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XoaSinhVienButtonActionPerformed
        int row = SinhVienTable.getSelectedRow();
        if (row == -1) JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn 1 sinh viên!");
        else {
        String del = SinhVienTable.getModel().getValueAt(row, 0).toString();
        new Connect().delete(del);
        new Connect().deleteUser(del);
        model.removeRow(row);
        for (int g = 0; g <list.size()-1; g++) {
            model.removeRow(0);
        }
        list = new Connect().getDanhSachSinhVien();
        model = (DefaultTableModel) SinhVienTable.getModel();
        model.setColumnIdentifiers(new Object[]{
            "MSSV", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Mã khoa", "Mã ngành","Khóa học"
        });
        reloadTable();
       }

    }//GEN-LAST:event_XoaSinhVienButtonActionPerformed

    private void TimKiemTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKiemTextActionPerformed
        String hoTen = TimKiemText.getText();
        for (int g = 0; g <list.size(); g++) {
            model.removeRow(0);
        }
        list = new Connect().tiemKiemSinhVien(hoTen);
        model = (DefaultTableModel) SinhVienTable.getModel();
        model.setColumnIdentifiers(new Object[]{
            "MSSV", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Mã khoa", "Mã ngành","Khóa học"
        });
        reloadTable();
    }//GEN-LAST:event_TimKiemTextActionPerformed

    private void namButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        for (int g = 0; g <list.size(); g++) {
            model.removeRow(0);
        }
        list = new Connect().getDanhSachSinhVien();
        model = (DefaultTableModel) SinhVienTable.getModel();
        model.setColumnIdentifiers(new Object[]{
            "MSSV", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Mã khoa", "Mã ngành","Khóa học"
        });
        reloadTable();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void yesAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesAddButtonActionPerformed
        SinhVien s = new SinhVien();
        s.setMSSV(mssvText.getText());
        s.setHoTen(hotenText.getText());
        namButton.setActionCommand("Nam");
        nuButton.setActionCommand("Nu");
        s.setGioiTinh(gioitinhGroup.getSelection().getActionCommand());
        try {
            s.setNgaySinh(new SimpleDateFormat("dd/MM/yyyy").parse(ngaysinhText.getText()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        s.setDiaChi(diachiText.getText());
        s.setMaKhoa(makhoaText.getText());
        s.setMaNganh(manganhText.getText());
        s.setKhoaHoc(Integer.parseInt(khoahocBox.getSelectedItem().toString()));
        if(new Connect().addStudent(s)){
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
            list.add(s); // them vao danh sach SV
        } else{
            JOptionPane.showMessageDialog(rootPane, "Đã có lỗi xảy ra!");
        }
        if(new Connect().createUser(s)){
            System.out.print("DONE");
        } else{
            System.out.print("EROR");
        }
        model = (DefaultTableModel) SinhVienTable.getModel();
        model.setRowCount(0);
        reloadTable();
        
    }//GEN-LAST:event_yesAddButtonActionPerformed

    private void yesUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesUpdateButtonActionPerformed
        String  mssv,hoTen,gioiTinh,diaChi,maKhoa,maNganh; int KhoaHoc; String ngaySinh;
        int row = SinhVienTable.getSelectedRow();   
        SinhVien t = new SinhVien();
       // String mssvnv = SinhVienTable.getModel().getValueAt(row, 0).toString();
        mssv = mssvText1.getText();t.setMSSV(mssv);
        hoTen = hotenText1.getText();t.setHoTen(hoTen);
        namButton1.setActionCommand("Nam");
        nuButton1.setActionCommand("Nu");
        t.setGioiTinh(gioitinhGroup.getSelection().getActionCommand());
        ngaySinh = ngaysinhText1.getText();
                            try{
                                t.setNgaySinh(new SimpleDateFormat("yyyy-MM-dd").parse(ngaysinhText1.getText()));}
                            catch (ParseException ex) {
                                 ex.printStackTrace();
                            }          
        diaChi = diachiText1.getText();t.setDiaChi(diaChi);
        maKhoa = makhoaText1.getText();t.setMaKhoa(maKhoa);
        maNganh = manganhText1.getText();t.setMaNganh(maNganh);
        KhoaHoc = Integer.parseInt(khoahocBox1.getSelectedItem().toString());t.setKhoaHoc(KhoaHoc);
        System.out.println(KhoaHoc);
        if(new Connect().update(t)){
            JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công!");
            list.set(row, t);
            CapNhatSVDialog.setVisible(false);
        } else{
            JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại!");
            CapNhatSVDialog.setVisible(false);
        }
        model = (DefaultTableModel) SinhVienTable.getModel();
        model.fireTableDataChanged();
        model.setRowCount(0);
        reloadTable();
    }//GEN-LAST:event_yesUpdateButtonActionPerformed

    private void namButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namButton1ActionPerformed

    private void TimKiemTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemTextKeyTyped
//        
//        System.out.println(TimKiemText.getText()+"");
//        String hoTen = TimKiemText.getText();
//        for (int g = 0; g <list.size(); g++) {
//            model.removeRow(0);
//        }
//        list = new Connect().tiemKiemSinhVien(hoTen);
//        model = (DefaultTableModel) SinhVienTable.getModel();
//        model.setColumnIdentifiers(new Object[]{
//            "MSSV", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Mã khoa", "Mã ngành","Khóa học"
//        });
//        reloadTable();
    }//GEN-LAST:event_TimKiemTextKeyTyped

    private void TimKiemTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimKiemTextKeyPressed
        
    }//GEN-LAST:event_TimKiemTextKeyPressed

    private void TimKiemTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TimKiemTextMouseClicked
      
        
    }//GEN-LAST:event_TimKiemTextMouseClicked

    private void TimKiemTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TimKiemTextFocusGained
     
    }//GEN-LAST:event_TimKiemTextFocusGained

    private void NganhTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NganhTableFocusGained
       
    }//GEN-LAST:event_NganhTableFocusGained

    private void NganhTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NganhTableMouseClicked
        // TODO add your handling code here:
        int row = NganhTable.getSelectedRow(); 
        for (int g = 0; g <list.size(); g++) {
           model.removeRow(0);
       }
        String mk,mn;
        System.out.println();
       mk = NganhTable.getModel().getValueAt(row, 0).toString();
        mn = NganhTable.getModel().getValueAt(row, 2).toString();
      list = new Connect().selectTableNganh(mk, mn);
        model = (DefaultTableModel) SinhVienTable.getModel();
       model.setColumnIdentifiers(new Object[]{
           "MSSV", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Mã khoa", "Mã ngành","Khóa học"
      });
      reloadTable();
    }//GEN-LAST:event_NganhTableMouseClicked

    private void ExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportActionPerformed
        // TODO add your handling code here:
        exportExcel(SinhVienTable);
    }//GEN-LAST:event_ExportActionPerformed

    private void TimKQTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimKQTextActionPerformed
        for (int g = 0; g <list.size(); g++) {
            model.removeRow(0);
        }
        list = new Connect().getDanhSachSinhVien();
        model = (DefaultTableModel) SinhVienTable.getModel();
        model.setColumnIdentifiers(new Object[]{
            "MSSV", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Mã khoa", "Mã ngành","Khóa học"
        });
        reloadTable();
        reloadTB();
    }//GEN-LAST:event_TimKQTextActionPerformed

    private void HocPhanTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HocPhanTableMouseClicked
        // TODO add your handling code here:
        int row = HocPhanTable.getSelectedRow();   
        mahpText.setText(HocPhanTable.getModel().getValueAt(row, 0).toString());
        mahpText.setEnabled(false);
        tenhpText.setText(HocPhanTable.getModel().getValueAt(row, 1).toString());
        tenhpText.setEnabled(false);
        hockiText.setText(HocPhanTable.getModel().getValueAt(row, 3).toString());
        hockiText.setEnabled(false);
        namhocText.setText(HocPhanTable.getModel().getValueAt(row, 4).toString());
        namhocText.setEnabled(false);
        diemText.setText(HocPhanTable.getModel().getValueAt(row, 5).toString());
    }//GEN-LAST:event_HocPhanTableMouseClicked

    private void CapNhatDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CapNhatDiemActionPerformed
        if (Integer.parseInt(diemText.getText())>=0 && Integer.parseInt(diemText.getText())<=10){
        int row = HocPhanTable.getSelectedRow();
        KetQua kq = new KetQua();
        kq.setMSSV(mssvKQText.getText());
        kq.setMaHP(mahpText.getText());
        kq.setTenHP(HocPhanTable.getModel().getValueAt(row, 2).toString());
        kq.setHocKi(Integer.parseInt(HocPhanTable.getModel().getValueAt(row, 3).toString()));
        kq.setNamHoc(Integer.parseInt(HocPhanTable.getModel().getValueAt(row, 4).toString()));
        kq.setDiem(Integer.parseInt(diemText.getText()));
        
        
 
        if(new Connect().updateDiem(kq)){
            JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công!");
         //   listKQ.set(row, kq);
            reloadTB();
        } else{
            JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại!");
        }
        } else JOptionPane.showMessageDialog(rootPane, "Nhập sai điểm!");
    }//GEN-LAST:event_CapNhatDiemActionPerformed

    private void HocPhanTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HocPhanTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_HocPhanTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        SinhVienFrame.setVisible(true);
        SinhVienFrame.setSize(1000, 725);
        SinhVienFrame.setResizable(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void khoahocBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khoahocBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_khoahocBoxActionPerformed

    private void QuanLyDiemTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_QuanLyDiemTableFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_QuanLyDiemTableFocusGained

    private void mssvKQTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mssvKQTextMouseClicked
        // TODO add your handling code here:
//        for (int g = 0; g <list.size(); g++) {
//            model.removeRow(0);
//        }
//        list = new Connect().getDanhSachSinhVien();
//        model = (DefaultTableModel) SinhVienTable.getModel();
//        model.setColumnIdentifiers(new Object[]{
//            "MSSV", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Mã khoa", "Mã ngành","Khóa học"
//        });
//        reloadTable();
    }//GEN-LAST:event_mssvKQTextMouseClicked

    private void themhocphanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themhocphanActionPerformed
        // TODO add your handling code here:
        DanhSachHP.setVisible(true);
        DanhSachHP.setSize(560, 383);
        DanhSachHP.setResizable(false);
        String makhoa =  makhoaTab3.getText();
        listHP = new Connect().getHP(makhoa);
        modelHP = (DefaultTableModel) DSHP.getModel();
        modelHP.setColumnIdentifiers(new Object[]{
            "Mã Học Phần", "Tên Học Phần","Số TC"
        });
        hocPhan();
    }//GEN-LAST:event_themhocphanActionPerformed

    private void themhpbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themhpbuttonActionPerformed
        // TODO add your handling code here:
        int row = DSHP.getSelectedRow();
        if (row == -1) JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn 1 học phần để thêm!");
        else{
            String mahp = DSHP.getModel().getValueAt(row, 0).toString();
            String mssv = mssvSVF.getText();
            int namhoc = Integer.parseInt(namhocCombo.getSelectedItem().toString());
            if (hockiCombo.getSelectedItem().toString().equals("1")) hockiCombo.setActionCommand("1");
            else if (hockiCombo.getSelectedItem().toString().equals("2")) hockiCombo.setActionCommand("2");
            else if (hockiCombo.getSelectedItem().toString().equals("Hè")) hockiCombo.setActionCommand("3");
            int hocki = Integer.parseInt(hockiCombo.getActionCommand());
            if(new Connect().themHP(mahp,mssv,hocki,namhoc)){
                JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
                for (int g = 0; g < listKQ.size(); g++){
                    modelKQ.removeRow(0);
                }
                reloadTBSV(mssv);
            } else{
            JOptionPane.showMessageDialog(rootPane, "Đã có lỗi xảy ra!");
        }
        }
    }//GEN-LAST:event_themhpbuttonActionPerformed

    private void SapXepBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SapXepBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SapXepBoxActionPerformed

    private void xoahocphanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoahocphanActionPerformed
        // TODO add your handling code here:
        int row = HocPhanTable1.getSelectedRow();
        if (row == -1) JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn 1 học phần để xóa!");
        else{
            String mssv = mssvSVF.getText();
            String maHP = HocPhanTable1.getModel().getValueAt(row, 0).toString();
            new Connect().deleteHP(mssv, maHP);
            modelKQ.removeRow(row);
            for (int g = 0; g <listKQ.size()-1; g++) {
            modelKQ.removeRow(0);
            }
            reloadTBSV(mssv);
            reloadTB();
        }
    }//GEN-LAST:event_xoahocphanActionPerformed

    private void capnhatthongtinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capnhatthongtinActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(rootPane, "Chức năng đang được hoàn thiện!");
    }//GEN-LAST:event_capnhatthongtinActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Finally.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Finally.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Finally.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Finally.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Finally().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CapNhatDiem;
    private javax.swing.JPanel CapNhatDiemPanel;
    private javax.swing.JButton CapNhatSV;
    private javax.swing.JDialog CapNhatSVDialog;
    private javax.swing.JTable DSHP;
    private javax.swing.JDialog DanhSachHP;
    private javax.swing.JTextField DiemTB;
    private javax.swing.JTextField DiemTB1;
    private javax.swing.JPanel DiemTBPanel;
    private javax.swing.JButton Export;
    private javax.swing.JTable HocPhanTable;
    private javax.swing.JTable HocPhanTable1;
    private javax.swing.JTable NganhTable;
    private javax.swing.JPanel QLSVPane;
    private javax.swing.JPanel QuanLyDiemTable;
    private javax.swing.JPanel QuanLyDiemTable1;
    private javax.swing.JComboBox<String> SapXepBox;
    private javax.swing.JButton SapXepButton;
    private javax.swing.JPanel ShowInfoPanel;
    private javax.swing.JFrame SinhVienFrame;
    private javax.swing.JTable SinhVienTable;
    private javax.swing.JTabbedPane TabPane;
    private javax.swing.JButton ThemSinhVienButton;
    private javax.swing.JDialog ThemSinhVienDialog;
    private javax.swing.JPanel ThongKePanel;
    private javax.swing.JButton TimKQText;
    private javax.swing.JButton TimKiemButton;
    private javax.swing.JTextField TimKiemText;
    private javax.swing.JTable TongSVTB;
    private javax.swing.JButton XoaSinhVienButton;
    private javax.swing.JButton capnhatthongtin;
    private javax.swing.JLabel diachiLabel;
    private javax.swing.JLabel diachiLabel1;
    private javax.swing.JTextField diachiText;
    private javax.swing.JTextField diachiText1;
    private javax.swing.JTextField diachisvText;
    private javax.swing.JTextField diemText;
    private javax.swing.ButtonGroup gioitinhGroup;
    private javax.swing.JLabel gioitinhLabel;
    private javax.swing.JLabel gioitinhLabel1;
    private javax.swing.JComboBox<String> hockiCombo;
    private javax.swing.JTextField hockiText;
    private javax.swing.JLabel hotenLabel;
    private javax.swing.JLabel hotenLabel1;
    private javax.swing.JTextField hotenTab2;
    private javax.swing.JTextField hotenTab3;
    private javax.swing.JTextField hotenText;
    private javax.swing.JTextField hotenText1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JComboBox<String> khoahocBox;
    private javax.swing.JComboBox<String> khoahocBox1;
    private javax.swing.JLabel khoahocLabel;
    private javax.swing.JLabel khoahocLabel1;
    private javax.swing.JTextField khoahocsvText;
    private javax.swing.JTextField mahpText;
    private javax.swing.JLabel makhoaLabel;
    private javax.swing.JLabel makhoaLabel1;
    private javax.swing.JTextField makhoaTab2;
    private javax.swing.JTextField makhoaTab3;
    private javax.swing.JTextField makhoaText;
    private javax.swing.JTextField makhoaText1;
    private javax.swing.JTextField manganhTab2;
    private javax.swing.JTextField manganhTab3;
    private javax.swing.JTextField manganhText;
    private javax.swing.JTextField manganhText1;
    private javax.swing.JTextField mssvKQText;
    private javax.swing.JLabel mssvLabel;
    private javax.swing.JLabel mssvLabel1;
    private javax.swing.JTextField mssvSVF;
    private javax.swing.JTextField mssvText;
    private javax.swing.JTextField mssvText1;
    private javax.swing.JRadioButton namButton;
    private javax.swing.JRadioButton namButton1;
    private javax.swing.JComboBox<String> namhocCombo;
    private javax.swing.JTextField namhocText;
    private javax.swing.JLabel nganhLabel;
    private javax.swing.JLabel nganhLabel1;
    private javax.swing.JLabel ngaysinhLabel;
    private javax.swing.JLabel ngaysinhLabel1;
    private javax.swing.JTextField ngaysinhText;
    private javax.swing.JTextField ngaysinhText1;
    private javax.swing.JTextField ngaysinhsvText;
    private javax.swing.JRadioButton nuButton;
    private javax.swing.JRadioButton nuButton1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField tenhpText;
    private javax.swing.JButton themhocphan;
    private javax.swing.JButton themhpbutton;
    private javax.swing.JButton xoahocphan;
    private javax.swing.JButton yesAddButton;
    private javax.swing.JButton yesUpdateButton;
    // End of variables declaration//GEN-END:variables
}
