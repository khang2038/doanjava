package com.company.khang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class cldoanhthusanpham extends JFrame {
    nennhanvien nentieude=new nennhanvien();
    JLabel tieude=new JLabel("DOANH THU SẢN PHẨM");
    JLabel idnv=new JLabel("Sản Phẩm : ");
    JTextField idnv1=new JTextField();
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
    JLabel ngayban=new JLabel("Ngày Bán: ");
    JTextField ngayban1=new JTextField();
    JLabel ban=new JLabel("Bàn: ");
    JTextField ban1=new JTextField();
    String ngaythang;
    JLabel sapxeptheo=new JLabel("sắp xếp theo:");
    JComboBox sapxeptheo1;
    JButton btsapxeptang=new JButton("Sắp xếp tăng");
    JButton btsapxepgiam=new JButton("Sắp xếp giảm");
    JLabel timkiemtheo=new JLabel("Tìm kiếm theo");
    JComboBox timkiemtheo1;
    JLabel soluong=new JLabel("Số lượng : ");
    JTextField soluong1=new JTextField();
    JLabel thanhtien=new JLabel("Thành tiền : ");
    JTextField thanhtien1=new JTextField();
    JLabel tongtien=new JLabel();
    JLabel dongia=new JLabel("Đơn Giá: ");
    JTextField dongia1=new JTextField();
    int tong;
    cldoanhthusanpham(){
        tieude.setForeground(new Color(160,82,45));
        tieude.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,25));
        tieude.setBounds(400,0,400,50);
        this.add(tieude);


        idnv.setForeground(Color.black);
        idnv.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
        idnv.setBounds(0,220,100,50);
        this.add(idnv);


        idnv1.setBounds(150,230,200,30);
        this.add(idnv1);

        timkiem.setBounds(0,600,200,30);
        this.add(timkiem);

        ngayban.setForeground(Color.black);
        ngayban.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
        ngayban.setBounds(0,260,110,50);
        this.add(ngayban);

        ngayban1.setBounds(150,270,200,30);
        this.add(ngayban1);

        dongia.setForeground(Color.black);
        dongia.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
        dongia.setBounds(0,300,110,50);
        this.add(dongia);

        dongia1.setBounds(150,310,200,30);
        this.add(dongia1);

        ban.setForeground(Color.black);
        ban.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
        ban.setBounds(0,340,120,50);
        this.add(ban);


        ban1.setBounds(150,350,200,30);
        this.add(ban1);

        soluong.setForeground(Color.black);
        soluong.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
        soluong.setBounds(400,220,120,50);
        this.add(soluong);


        soluong1.setBounds(550,230,200,30);
        this.add(soluong1);

        thanhtien.setForeground(Color.black);
        thanhtien.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
        thanhtien.setBounds(400,270,120,50);
        this.add(thanhtien);


        thanhtien1.setBounds(550,280,200,30);
        this.add(thanhtien1);

        tongtien.setBounds(980,220,200,50);
        tongtien.setForeground(Color.red);
        tongtien.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
        this.add(tongtien);

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
                        String sql="delete from doanhthusanpham where stt='"+vt.elementAt(0)+"'";
                        PreparedStatement ps=conn.prepareStatement(sql);
                        ps.executeUpdate();
                        vdata.remove(selectedrow);
                        reload();
                        model.fireTableDataChanged();
                        ps.close();
                    }catch (SQLException ex3){
                        JOptionPane.showMessageDialog(null, "bạn nhập sai gì đó ", "lỗi", JOptionPane.ERROR_MESSAGE);
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
                timkiem.setText("");
                ngayban1.setText("");
                ban1.setText("");
                soluong1.setText("");
                dongia1.setText("");
                thanhtien1.setText("");
                reload();
                model.fireTableDataChanged();
            }
        });
        lammoi.setFocusable(false);
        this.add(lammoi);

        reload();

        timkiemtheo.setBounds(0,480,120,50);
        this.add(timkiemtheo);

        timkiemtheo1=new JComboBox(vtitle);
        timkiemtheo1.setBounds(0,520,150,40);
        this.add(timkiemtheo1);

        timkiem1.setBounds(250,600,150,40);
        timkiem1.setBackground(Color.white);
        timkiem1.setIcon(lammoi1);
        timkiem1.setIcon(anhtk);
        timkiem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vdata.clear();
                    ResultSet rs1 = sm.executeQuery("select * from doanhthusanpham where  "+timkiemtheo1.getSelectedItem()+"  LIKE N'%"+timkiem.getText()+"%' ");
                    ResultSetMetaData rsm = rs1.getMetaData();
                    int num_column = rsm.getColumnCount();
                    for (int i = 1; i <= num_column; i++) {
                        vtitle.add(rsm.getColumnLabel(i));
                    }
                    tong=0;
                    while(rs1.next()){
                        Vector row=new Vector(num_column);
                        for(int i=1;i<=num_column;i++)
                        {
                            if(i==3){
                                String outNS;
                                try {
                                    DateFormat outputBD = new SimpleDateFormat("dd/MM/yyyy");
                                    DateFormat inputBD = new SimpleDateFormat("yyyy-MM-dd");
                                    java.util.Date dateBD;
                                    dateBD =inputBD.parse(rs1.getString(3));
                                    outNS = outputBD.format(dateBD);
                                    row.add(outNS);
                                } catch (Exception ex5){
                                    JOptionPane.showMessageDialog(null, "bạn nhập sai gì đó ", "lỗi", JOptionPane.ERROR_MESSAGE);
                                    System.out.println(ex5);
                                }
                            }
                            else
                                row.add(rs1.getString(i));
                            if(i==6) tong=tong+Integer.parseInt(rs1.getString(7));
                        }
                        vdata.add(row);
                    }
                    rs1.close();
                    tongtien.setText("Tổng tiền : "+tong);
                    model.fireTableDataChanged();
                } catch (SQLException ex1){
                    JOptionPane.showMessageDialog(null, "bạn nhập sai gì đó ", "lỗi", JOptionPane.ERROR_MESSAGE);
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
                idnv1.setText((String)vt.elementAt(1));
                ngayban1.setText((String) vt.elementAt(2));
                ban1.setText((String) vt.elementAt(3));
                soluong1.setText((String) vt.elementAt(4));
                dongia1.setText((String) vt.elementAt(5));
                thanhtien1.setText((String) vt.elementAt(6));
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

        table.setBounds(0,60,1200,150);
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
                    ResultSet rs1 = sm.executeQuery("select * from doanhthusanpham order by "+sapxeptheo1.getSelectedItem()+" ASC");
                    ResultSetMetaData rsm = rs1.getMetaData();
                    int num_column = rsm.getColumnCount();
                    vtitle = new Vector(num_column);
                    for (int i = 1; i <= num_column; i++) {
                        vtitle.add(rsm.getColumnLabel(i));
                    }
                    tong=0;
                    while(rs1.next()){
                        Vector row=new Vector(num_column);
                        for(int i=1;i<=num_column;i++) {
                            if (i == 3) {
                                String outNS;
                                try {
                                    DateFormat outputBD = new SimpleDateFormat("dd/MM/yyyy");
                                    DateFormat inputBD = new SimpleDateFormat("yyyy-MM-dd");
                                    java.util.Date dateBD;
                                    dateBD = inputBD.parse(rs1.getString(3));
                                    outNS = outputBD.format(dateBD);
                                    row.add(outNS);
                                } catch (Exception ex5) {
                                    System.out.println(ex5);
                                }
                            } else
                                row.add(rs1.getString(i));
                            if(i==7) tong=tong+Integer.parseInt(rs1.getString(7));
                        }
                        vdata.add(row);

                    }
                    tongtien.setText("Tổng tiền : "+tong);
                    rs1.close();
                    model.fireTableDataChanged();
                } catch (SQLException ex1){
                    JOptionPane.showMessageDialog(null, "bạn nhập sai gì đó ", "lỗi", JOptionPane.ERROR_MESSAGE);
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
                    ResultSet rs1 = sm.executeQuery("select * from doanhthusanpham order by "+sapxeptheo1.getSelectedItem()+" DESC ");
                    ResultSetMetaData rsm = rs1.getMetaData();
                    int num_column = rsm.getColumnCount();
                    vtitle = new Vector(num_column);
                    for (int i = 1; i <= num_column; i++) {
                        vtitle.add(rsm.getColumnLabel(i));
                    }
                    tong=0;
                    while(rs1.next()){
                        Vector row=new Vector(num_column);
                        for(int i=1;i<=num_column;i++) {
                            if (i == 3) {
                                String outNS;
                                try {
                                    DateFormat outputBD = new SimpleDateFormat("dd/MM/yyyy");
                                    DateFormat inputBD = new SimpleDateFormat("yyyy-MM-dd");
                                    java.util.Date dateBD;
                                    dateBD = inputBD.parse(rs1.getString(3));
                                    outNS = outputBD.format(dateBD);
                                    row.add(outNS);
                                } catch (Exception ex5) {
                                    System.out.println(ex5);
                                }
                            } else
                                row.add(rs1.getString(i));
                            if(i==7) tong=tong+Integer.parseInt(rs1.getString(7));
                        }
                        vdata.add(row);

                    }
                    tongtien.setText("Tổng tiền : "+tong);
                    rs1.close();
                    model.fireTableDataChanged();
                } catch (SQLException ex1){
                    JOptionPane.showMessageDialog(null, "bạn nhập sai gì đó ", "lỗi", JOptionPane.ERROR_MESSAGE);
                    System.out.println("cannot conect"+ex1);
                }
            }
        });
        this.add(btsapxepgiam);

        this.setTitle(" Doanh Thu Sản Phẩm ");
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
            ResultSet rs1 = sm.executeQuery("select * from doanhthusanpham");
            ResultSetMetaData rsm = rs1.getMetaData();
            int num_column = rsm.getColumnCount();
            for (int i = 1; i <= num_column; i++) {
                vtitle.add(rsm.getColumnLabel(i));
            }
            tong=0;
            while(rs1.next()){
                Vector row=new Vector(num_column);
                for(int i=1;i<=num_column;i++) {
                    if(i==3){
                        String outNS;
                        try {
                            DateFormat outputBD = new SimpleDateFormat("dd/MM/yyyy");
                            DateFormat inputBD = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date dateBD;
                            dateBD =inputBD.parse(rs1.getString(3));
                            outNS = outputBD.format(dateBD);
                            row.add(outNS);
                        } catch (Exception ex5){
                            System.out.println(ex5);
                        }
                    }
                    else
                        row.add(rs1.getString(i));
                    if(i==7) tong=tong+Integer.parseInt(rs1.getString(7));
                }
                vdata.add(row);
            }
            tongtien.setText("Tổng tiền : "+tong);
            rs1.close();
        } catch (SQLException ex1){
            System.out.println("cannot conect"+ex1);
        }
    }
    public void nhapDb(){
        if(idnv1.getText().equals("")||ngayban1.getText().equals("")||ban1.getText().equals("")||soluong1.getText().equals("")||thanhtien1.getText().equals("")||dongia1.getText().equals("")){
            JOptionPane.showMessageDialog(null,"bạn chưa nhập đầy đủ thông tin ","lỗi",JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                String id=idnv1.getText();
                String outNS;
                int soban=Integer.parseInt(ban1.getText());
                int sokhach=Integer.parseInt(soluong1.getText());
                int ttien=Integer.parseInt(thanhtien1.getText());
                int dg=Integer.parseInt(dongia1.getText());
                try {
                    DateFormat inputBD = new SimpleDateFormat("dd/MM/yyyy");
                    DateFormat outputBD = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date dateBD;
                    dateBD =inputBD.parse( ngayban1.getText());
                    outNS = outputBD.format(dateBD);
                    ngaythang=outNS;
                } catch (Exception ex5){
                    JOptionPane.showMessageDialog(null,"bạn nhập sai kiểu ngày tháng ","lỗi",JOptionPane.ERROR_MESSAGE);
                }

                String sql="insert into doanhthusanpham(sanpham,ngay,ban,soluong,dongia,thanhtien) values(?,?,?,?,?,?)";
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1,id);
                ps.setString(2,ngaythang);
                ps.setInt(3,soban);
                ps.setInt(4,sokhach);
                ps.setInt(5,dg);
                ps.setInt(6,ttien);
                ps.executeUpdate();
                reload();
                model.fireTableDataChanged();
                ps.close();
                JOptionPane.showMessageDialog(null,"Đã thêm  vào danh sách doanh thu","acept",JOptionPane.INFORMATION_MESSAGE);
            }catch (SQLException ex2){
                JOptionPane.showMessageDialog(null,"hình như bạn nhập sai gì đó ","lỗi",JOptionPane.ERROR_MESSAGE);
                System.out.println(ex2);
            }
        }
    }
    public void suadb(){
        if(idnv1.getText().equals("")||ngayban1.getText().equals("")||ban1.getText().equals("")||soluong1.getText().equals("")||thanhtien1.getText().equals("")||dongia1.getText().equals("")){
            JOptionPane.showMessageDialog(null,"bạn chưa nhập đầy đủ thông tin ","lỗi",JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                String id = idnv1.getText();
                String outNS;
                int soban = Integer.parseInt(ban1.getText());
                int sokhach=Integer.parseInt(soluong1.getText());
                int ttien=Integer.parseInt(thanhtien1.getText());
                int dg=Integer.parseInt(dongia1.getText());
                try {
                    DateFormat inputBD = new SimpleDateFormat("dd/MM/yyyy");
                    DateFormat outputBD = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateBD;
                    dateBD = inputBD.parse(ngayban1.getText());
                    outNS = outputBD.format(dateBD);
                    ngaythang = outNS;
                } catch (Exception ex5) {
                    JOptionPane.showMessageDialog(null, "bạn nhập sai kiểu ngày tháng ", "lỗi", JOptionPane.ERROR_MESSAGE);
                }
                Vector vt = (Vector) vdata.elementAt(selectedrow);
                String sql = "update doanhthusanpham set sanpham='" + id + "',ngay='" + ngaythang + "',ban= '" + soban + "',soluong= '"+sokhach+"' ,dongia= '"+dg+"' ,thanhtien= '"+ttien+"'  where stt='" + vt.elementAt(0) + "'";
                Statement ps = conn.createStatement();
                ps.executeUpdate(sql);
                reload();
                model.fireTableDataChanged();
                ps.close();
                JOptionPane.showMessageDialog(null, "Đã sửa danh sách nhân viên", "acept", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex2) {
                JOptionPane.showMessageDialog(null, "bạn nhập sai gì đó ", "lỗi", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex2);
            }
        }
    }
}
