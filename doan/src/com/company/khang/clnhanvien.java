package com.company.khang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class clnhanvien extends JFrame {
        nennhanvien nentieude=new nennhanvien();
        JLabel tieude=new JLabel("QUẢN LÍ NHÂN VIÊN");
        JLabel idnv=new JLabel("ID Nhân viên: ");
        JTextField idnv1=new JTextField();
        JLabel tennv=new JLabel("Tên Nhân Viên: ");
        JTextField tennv1=new JTextField();
        Vector vtitle=new Vector();
        Vector vdata=new Vector();
        JButton themmoi=new JButton("Thêm ");
        JButton sua=new JButton("Sửa");
        JButton xoa=new JButton("Xóa");
        JButton lammoi=new JButton("Làm Mới");
        JScrollPane table;
        DefaultTableModel model;
        JTable tb=new JTable();
        Connection conn;
        Statement sm;
        int selectedrow=0;
        JTextField timkiem=new JTextField();
        JButton timkiem1=new JButton("Tìm Kiếm");
        ImageIcon them=new ImageIcon("themmoi.jpg");
        ImageIcon sua1=new ImageIcon("sửa.jpg");
        ImageIcon xoa1=new ImageIcon("xóa.jpg");
        ImageIcon lammoi1=new ImageIcon("làmmoi.jpg");
        ImageIcon anhtk=new ImageIcon("timkiem.jpg");
        ImageIcon icon=new ImageIcon("nennhanvien.png");
        JLabel gioitinh =new JLabel("Giới Tính: ");
        JLabel ngaysinh=new JLabel("Ngày sinh: ");
        JTextField ngaysinh1=new JTextField();
        JRadioButton nam=new JRadioButton("Nam");
        JRadioButton nu=new JRadioButton("Nữ");
        JRadioButton khac=new JRadioButton("Khác");
        ButtonGroup group =new ButtonGroup();
        String gt;
        JLabel sdt=new JLabel("Số điện thoại: ");
        JTextField sdt1=new JTextField();
        JLabel email=new JLabel("Email: ");
        JTextField email1=new JTextField();
        JLabel diachi=new JLabel("Địa chỉ: ");
        JTextArea diachi1=new JTextArea();
        JScrollPane diachi2;
        String ngaythang;
        JButton chonanh=new JButton("Chọn ảnh");
        JTextField tenanh=new JTextField();
        JLabel anh=new JLabel();
        JLabel sapxeptheo=new JLabel("sắp xếp theo:");
        JComboBox sapxeptheo1;
        JButton btsapxeptang=new JButton("Sắp xếp tăng");
        JButton btsapxepgiam=new JButton("Sắp xếp giảm");
        clnhanvien(){
                tieude.setForeground(new Color(160,82,45));
                tieude.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,25));
                tieude.setBounds(400,0,400,50);
                this.add(tieude);

                anh.setBounds(360,70,200,300);
                this.add(anh);

                idnv.setForeground(Color.black);
                idnv.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
                idnv.setBounds(0,60,100,50);
                this.add(idnv);
                

                idnv1.setBounds(150,70,200,30);
                this.add(idnv1);

                timkiem.setBounds(0,600,200,30);
                this.add(timkiem);


                tennv.setForeground(Color.black);
                tennv.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
                tennv.setBounds(0,110,110,50);
                this.add(tennv);

                tennv1.setBounds(150,120,200,30);
                this.add(tennv1);

                gioitinh.setForeground(Color.black);
                gioitinh.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
                gioitinh.setBounds(0,170,110,50);
                this.add(gioitinh);

                ngaysinh.setForeground(Color.black);
                ngaysinh.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
                ngaysinh.setBounds(0,230,110,50);
                this.add(ngaysinh);

                sdt.setForeground(Color.black);
                sdt.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
                sdt.setBounds(0,270,120,50);
                this.add(sdt);

                ngaysinh1.setBounds(150,240,200,30);
                this.add(ngaysinh1);

                sdt1.setBounds(150,280,200,30);
                this.add(sdt1);

                email.setForeground(Color.black);
                email.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
                email.setBounds(0,310,120,50);
                this.add(email);

                email1.setBounds(150,320,200,30);
                this.add(email1);

                diachi.setForeground(Color.black);
                diachi.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
                diachi.setBounds(0,350,120,50);
                this.add(diachi);

                diachi2 = new JScrollPane(diachi1);
                diachi2.setBounds(150,360,150,50);
                this.getContentPane().add(diachi2);



                chonanh.setBounds(0,430,100,30);
                chonanh.setBackground(Color.white);
                chonanh.setFocusable(false);
                chonanh.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                               JFileChooser fileChooser=new JFileChooser();
                               int response=fileChooser.showOpenDialog(null);//chon file de mơ
                               if(response==JFileChooser.APPROVE_OPTION){
                                       File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                                       tenanh.setText(file.getPath());
                                       anh.setIcon(new ImageIcon(tenanh.getText()));
                               }
                        }
                });
                this.add(chonanh);

                tenanh.setBounds(150,430,200,30);
                this.add(tenanh);

                group.add(nam);
                group.add(nu);
                group.add(khac);

                gt="Nam";

                nam.setBounds(100,180,70,30);
                nam.setFocusable(false);
                nam.setBackground(new Color(72,209,204));
                nam.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                gt=nam.getText();
                        }
                });
                this.add(nam);

                nu.setBounds(180,180,70,30);
                nu.setFocusable(false);
                nu.setBackground(new Color(72,209,204));
                nu.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                gt=nu.getText();
                        }
                });
                this.add(nu);

                khac.setBounds(260,180,70,30);
                khac.setFocusable(false);
                khac.setBackground(new Color(72,209,204));
                khac.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                gt=khac.getText();
                        }
                });
                this.add(khac);



                try {
                        conn=DBConnectionFactory.getconection();
                        sm=conn.createStatement();
                }catch (SQLException ex1){
                        System.out.println("cannot conect"+ex1);
                }

                themmoi.setBounds(200,530,120,40);
                themmoi.setBackground(Color.white);
                themmoi.setIcon(them);
                themmoi.setFocusable(false);
                themmoi.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                nhapDb();
                        }
                });
                this.add(themmoi);



                xoa.setBounds(370,530,120,40);
                xoa.setBackground(Color.white);
                xoa.setIcon( xoa1);
                xoa.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int as=JOptionPane.showConfirmDialog(null,"bạn chắc chắn muốn xóa ?","thông báo",JOptionPane.YES_NO_CANCEL_OPTION);
                                if(as==0)
                                        try{
                                                Vector vt=(Vector) vdata.elementAt(selectedrow);
                                                System.out.println(vt.elementAt(0));
                                                String sql="delete from nhanvien where ID='"+vt.elementAt(0)+"'";
                                                PreparedStatement ps=conn.prepareStatement(sql);
                                                ps.executeUpdate();
                                                vdata.remove(selectedrow);
                                                model.fireTableDataChanged();
                                                ps.close();
                                        }catch (SQLException ex3){
                                                System.out.println("cannot conect"+ex3);
                                        }

                        }
                });
                xoa.setFocusable(false);
                this.add(xoa);

                lammoi.setBounds(710,530,120,40);
                lammoi.setBackground(Color.white);
                lammoi.setIcon(lammoi1);
                lammoi.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                idnv1.setText("");
                                tennv1.setText("");
                                timkiem.setText("");
                                ngaysinh1.setText("");
                                sdt1.setText("");
                                email1.setText("");
                                diachi1.setText("");
                                reload();
                                model.fireTableDataChanged();
                                tenanh.setText("");
                                anh.setIcon(new ImageIcon());
                        }
                });
                lammoi.setFocusable(false);
                this.add(lammoi);

                reload();


                timkiem1.setBounds(250,600,150,40);
                timkiem1.setBackground(Color.white);
                timkiem1.setIcon(lammoi1);
                timkiem1.setIcon(anhtk);
                timkiem1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                try {
                                        vdata.clear();
                                        ResultSet rs1 = sm.executeQuery("select * from nhanvien where ten LIKE N'%"+timkiem.getText()+"%' ");
                                        ResultSetMetaData rsm = rs1.getMetaData();
                                        int num_column = rsm.getColumnCount();
                                        for (int i = 1; i <= num_column; i++) {
                                                vtitle.add(rsm.getColumnLabel(i));
                                        }
                                        while(rs1.next()){
                                                Vector row=new Vector(num_column);
                                                for(int i=1;i<=num_column;i++)
                                                        if(i==4){
                                                                String outNS;
                                                                try {
                                                                        DateFormat outputBD = new SimpleDateFormat("dd/MM/yyyy");
                                                                        DateFormat inputBD = new SimpleDateFormat("yyyy-MM-dd");
                                                                        Date dateBD;
                                                                        dateBD =inputBD.parse(rs1.getString(4));
                                                                        outNS = outputBD.format(dateBD);
                                                                        row.add(outNS);
                                                                } catch (Exception ex5){
                                                                        System.out.println(ex5);
                                                                }
                                                        }
                                                        else
                                                        row.add(rs1.getString(i));
                                                vdata.add(row);

                                        }
                                        rs1.close();
                                        model.fireTableDataChanged();
                                } catch (SQLException ex1){
                                        System.out.println("cannot conect"+ex1);
                                }
                        }
                });
                timkiem1.setFocusable(false);
                this.add(timkiem1);

                model = new DefaultTableModel(vdata,vtitle);
                tb=new JTable(model);
                table= new JScrollPane(tb);
                tb.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                selectedrow=tb.getSelectedRow();
                                Vector vt = (Vector) vdata.elementAt(selectedrow);
                                idnv1.setText((String)vt.elementAt(0));
                                tennv1.setText((String) vt.elementAt(1));
                                ngaysinh1.setText((String) vt.elementAt(3));
                                sdt1.setText((String) vt.elementAt(4));
                                email1.setText((String) vt.elementAt(5));
                                diachi1.setText((String) vt.elementAt(6));
                                tenanh.setText((String) vt.elementAt(7));
                                anh.setIcon(new ImageIcon(tenanh.getText()));
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                });

                sua.setBounds(540,530,120,40);
                sua.setBackground(Color.white);
                sua.setIcon(sua1);
                sua.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                suadb();
                        }
                });
                sua.setFocusable(false);
                this.add(sua);

                table.setBounds(600,60,580,380);
                this.getContentPane().add(table);

                sapxeptheo.setBounds(940,500,100,30);
                this.add(sapxeptheo);

                sapxeptheo1=new JComboBox(vtitle);
                sapxeptheo1.setBounds(940,530,100,30);
                this.add(sapxeptheo1);

                btsapxeptang.setBounds(1050,530,120,30);
                btsapxeptang.setFocusable(false);
                btsapxeptang.setBackground(Color.white);
                btsapxeptang.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                try {
                                        vdata.clear();
                                        ResultSet rs1 = sm.executeQuery("select * from nhanvien order by "+sapxeptheo1.getSelectedItem()+" ASC");
                                        ResultSetMetaData rsm = rs1.getMetaData();
                                        int num_column = rsm.getColumnCount();
                                        vtitle = new Vector(num_column);
                                        for (int i = 1; i <= num_column; i++) {
                                                vtitle.add(rsm.getColumnLabel(i));
                                        }
                                        while(rs1.next()){
                                                Vector row=new Vector(num_column);
                                                for(int i=1;i<=num_column;i++)
                                                        if(i==4){
                                                                String outNS;
                                                                try {
                                                                        DateFormat outputBD = new SimpleDateFormat("dd/MM/yyyy");
                                                                        DateFormat inputBD = new SimpleDateFormat("yyyy-MM-dd");
                                                                        Date dateBD;
                                                                        dateBD =inputBD.parse(rs1.getString(4));
                                                                        outNS = outputBD.format(dateBD);
                                                                        row.add(outNS);
                                                                } catch (Exception ex5){
                                                                        System.out.println(ex5);
                                                                }
                                                        }
                                                        else
                                                        row.add(rs1.getString(i));
                                                vdata.add(row);

                                        }
                                        rs1.close();
                                        model.fireTableDataChanged();
                                } catch (SQLException ex1){
                                        System.out.println("cannot conect"+ex1);
                                }
                        }
                });
                this.add(btsapxeptang);

                btsapxepgiam.setBounds(1050,560,120,30);
                btsapxepgiam.setFocusable(false);
                btsapxepgiam.setBackground(Color.white);
                btsapxepgiam.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                try {
                                        vdata.clear();
                                        ResultSet rs1 = sm.executeQuery("select * from nhanvien order by "+sapxeptheo1.getSelectedItem()+" DESC ");
                                        ResultSetMetaData rsm = rs1.getMetaData();
                                        int num_column = rsm.getColumnCount();
                                        vtitle = new Vector(num_column);
                                        for (int i = 1; i <= num_column; i++) {
                                                vtitle.add(rsm.getColumnLabel(i));
                                        }
                                        while(rs1.next()){
                                                Vector row=new Vector(num_column);
                                                for(int i=1;i<=num_column;i++)
                                                        if(i==4){
                                                                String outNS;
                                                                try {
                                                                        DateFormat outputBD = new SimpleDateFormat("dd/MM/yyyy");
                                                                        DateFormat inputBD = new SimpleDateFormat("yyyy-MM-dd");
                                                                        Date dateBD;
                                                                        dateBD =inputBD.parse(rs1.getString(4));
                                                                        outNS = outputBD.format(dateBD);
                                                                        row.add(outNS);
                                                                } catch (Exception ex5){
                                                                        System.out.println(ex5);
                                                                }
                                                        }
                                                        else
                                                        row.add(rs1.getString(i));
                                                vdata.add(row);

                                        }
                                        rs1.close();
                                        model.fireTableDataChanged();
                                } catch (SQLException ex1){
                                        System.out.println("cannot conect"+ex1);
                                }
                        }
                });
                this.add(btsapxepgiam);

                this.setTitle(" Nhân Viên ");
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                this.setSize(1200,700);
                this.add(nentieude);
                this.setIconImage(icon.getImage());
                this.setResizable(false);
                this.setVisible(true);
                setLocationRelativeTo(null);
        }

        public void reload(){
                try {
                        vtitle.clear();
                        vdata.clear();
                        ResultSet rs1 = sm.executeQuery("select * from nhanvien");
                        ResultSetMetaData rsm = rs1.getMetaData();
                        int num_column = rsm.getColumnCount();
                        for (int i = 1; i <= num_column; i++) {
                                vtitle.add(rsm.getColumnLabel(i));
                        }
                        while(rs1.next()){
                                Vector row=new Vector(num_column);
                                for(int i=1;i<=num_column;i++) {
                                        if(i==4){
                                                String outNS;
                                           try {
                                                   DateFormat outputBD = new SimpleDateFormat("dd/MM/yyyy");
                                                   DateFormat inputBD = new SimpleDateFormat("yyyy-MM-dd");
                                                   Date dateBD;
                                                   dateBD =inputBD.parse(rs1.getString(4));
                                                   outNS = outputBD.format(dateBD);
                                                   row.add(outNS);
                                           } catch (Exception ex5){
                                                   System.out.println(ex5);
                                           }
                                        }
                                        else
                                        row.add(rs1.getString(i));
                                }
                                vdata.add(row);
                        }
                        rs1.close();
                } catch (SQLException ex1){
                        System.out.println("cannot conect"+ex1);
                }
        }
        public void nhapDb(){
                if(idnv1.getText().equals("")||tennv1.getText().equals("")||gt.equals(null)||ngaysinh1.getText().equals("")||sdt1.getText().equals("")||email1.getText().equals("")||diachi1.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"bạn chưa nhập đầy đủ thông tin ","lỗi",JOptionPane.ERROR_MESSAGE);
                }
                else{
                        if(kiemtrasdt(sdt1.getText())==false)
                                JOptionPane.showMessageDialog(null,"bạn nhập số điện thoại không đúng ","lỗi",JOptionPane.ERROR_MESSAGE);
                        else
                        try{
                                String id=idnv1.getText();
                                String ten=tennv1.getText();
                                String outNS;
                                String sodt=sdt1.getText();
                                try {
                                        DateFormat inputBD = new SimpleDateFormat("dd/MM/yyyy");
                                        DateFormat outputBD = new SimpleDateFormat("yyyy-MM-dd");
                                        Date dateBD;
                                        dateBD =inputBD.parse( ngaysinh1.getText());
                                        outNS = outputBD.format(dateBD);
                                        ngaythang=outNS;
                                } catch (Exception ex5){
                                        JOptionPane.showMessageDialog(null,"bạn nhập sai kiểu ngày tháng ","lỗi",JOptionPane.ERROR_MESSAGE);
                                }
                                emailcheck checkem=new emailcheck();
                                try {
                                        checkem.check(email1.getText());

                                String sql="insert into nhanvien(ID,ten,gioitinh,ngaysinh,sdt,diachi,email,hinhanh) values(?,?,?,?,?,?,?,?)";
                                PreparedStatement ps=conn.prepareStatement(sql);
                                ps.setString(1,id);
                                ps.setString(2,ten);
                                ps.setString(3,gt);
                                ps.setString(4,ngaythang);
                                ps.setString(5,sodt);
                                ps.setString(6,diachi1.getText());
                                ps.setString(7,email1.getText());
                                ps.setString(8,tenanh.getText());
                                ps.executeUpdate();
                                reload();
                                model.fireTableDataChanged();
                                ps.close();
                                JOptionPane.showMessageDialog(null,"Đã thêm "+ten+" vào danh sách nhân viên","acept",JOptionPane.INFORMATION_MESSAGE);
                                }catch (EmailAddressException e){
                                        JOptionPane.showMessageDialog(null,e.getMessage(),"lỗi",JOptionPane.ERROR_MESSAGE);
                                }
                                }catch (SQLException ex2){
                                System.out.println("cannotconect"+ex2);
                        }
                }
        }
        public void suadb(){
                if(idnv1.getText().equals("")||tennv1.getText().equals("")||gt.equals(null)||ngaysinh1.getText().equals("")||sdt1.getText().equals("")||email1.getText().equals("")||diachi1.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"bạn chưa nhập đầy đủ thông tin ","lỗi",JOptionPane.ERROR_MESSAGE);
                }
                else {
                        if (kiemtrasdt(sdt1.getText()) == false)
                                JOptionPane.showMessageDialog(null, "bạn nhập số điện thoại không đúng ", "lỗi", JOptionPane.ERROR_MESSAGE);
                        else
                                try {
                                        String id = idnv1.getText();
                                        String ten = tennv1.getText();
                                        String outNS;
                                        String sodt = sdt1.getText();
                                        try {
                                                DateFormat inputBD = new SimpleDateFormat("dd/MM/yyyy");
                                                DateFormat outputBD = new SimpleDateFormat("yyyy-MM-dd");
                                                Date dateBD;
                                                dateBD = inputBD.parse(ngaysinh1.getText());
                                                outNS = outputBD.format(dateBD);
                                                ngaythang = outNS;
                                        } catch (Exception ex5) {
                                                JOptionPane.showMessageDialog(null, "bạn nhập sai kiểu ngày tháng ", "lỗi", JOptionPane.ERROR_MESSAGE);
                                        }
                                        emailcheck checkem=new emailcheck();
                                        try {
                                                checkem.check(email1.getText());
                                        Vector vt = (Vector) vdata.elementAt(selectedrow);
                                        String sql = "update nhanvien set ID='" + id + "',ten=N'" + ten + "',gioitinh=N'" + gt + "',ngaysinh='" + ngaythang + "',sdt='" + sodt + "',diachi=N'"+diachi1.getText()+"',email='"+email1.getText()+"',hinhanh='"+tenanh.getText()+"' where ID='" + vt.elementAt(0) + "'";
                                        Statement ps = conn.createStatement();
                                        ps.executeUpdate(sql);
                                        reload();
                                        model.fireTableDataChanged();
                                        ps.close();
                                        JOptionPane.showMessageDialog(null, "Đã sửa danh sách nhân viên", "acept", JOptionPane.INFORMATION_MESSAGE);
                                        }catch (EmailAddressException e){
                                                JOptionPane.showMessageDialog(null,e.getMessage(),"lỗi",JOptionPane.ERROR_MESSAGE);
                                        }
                                        } catch (SQLException ex2) {
                                        System.out.println("cannotconect" + ex2);
                                }
                }
        }
        public static Boolean kiemtrasdt(String s){
                for(int i=0;i<s.length();i++){
                        if(s.charAt(i)>57||s.charAt(i)<48) return false;
                }
                return true;
        }

                public static void main(String[] args) {
                new clnhanvien();
        }
}
