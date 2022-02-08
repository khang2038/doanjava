package com.company.khang;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class clloaisanpham extends JFrame {
    background3 nen=new background3();
    JLabel idsp=new JLabel("ID loại Sản Phẩm: ");
    JTextField idsp1=new JTextField();
    JLabel tensanpham=new JLabel("Loại Sản Phẩm: ");
    JTextField tensanpham1=new JTextField();
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
    JComboBox tk;
    JButton timkiem1=new JButton("Tìm Kiếm");
    JLabel timkiemtheo=new JLabel("Tìm kiếm theo: ");
    ImageIcon them=new ImageIcon("themmoi.jpg");
    ImageIcon sua1=new ImageIcon("sửa.jpg");
    ImageIcon xoa1=new ImageIcon("xóa.jpg");
    ImageIcon lammoi1=new ImageIcon("làmmoi.jpg");
    ImageIcon anhtk=new ImageIcon("timkiem.jpg");
    ImageIcon icon=new ImageIcon("loại sanpham.jpg");
    JLabel sapxeptheo=new JLabel("sắp xếp theo:");
    JComboBox sapxeptheo1;
    JButton btsapxeptang=new JButton("Sắp xếp tăng");
    JButton btsapxepgiam=new JButton("Sắp xếp giảm");
    clloaisanpham(){
        idsp.setForeground(Color.black);
        idsp.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
        idsp.setBounds(50,40,100,50);
        this.add(idsp);

        idsp1.setBounds(150,50,200,30);
        this.add(idsp1);

        timkiem.setBounds(450,110,200,30);
        this.add(timkiem);

        tensanpham.setForeground(Color.black);
        tensanpham.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
        tensanpham.setBounds(40,100,110,50);
        this.add(tensanpham);

        tensanpham1.setBounds(150,110,200,30);
        this.add(tensanpham1);

        try {
            conn=DBConnectionFactory.getconection();
            sm=conn.createStatement();
        }catch (SQLException ex1){
            System.out.println("cannot conect"+ex1);
        }

        themmoi.setBounds(60,300,120,40);
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



        xoa.setBounds(390,300,120,40);
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
                        String sql="delete from loaisanpham where ID='"+vt.elementAt(0)+"'";
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

        lammoi.setBounds(550,300,120,40);
        lammoi.setBackground(Color.white);
        lammoi.setIcon(lammoi1);
        lammoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idsp1.setText("");
                tensanpham1.setText("");
                timkiem.setText("");
                reload();
                model.fireTableDataChanged();
            }
        });
        lammoi.setFocusable(false);
        this.add(lammoi);

        reload();

        tk=new JComboBox(vtitle);
        tk.setBounds(450,50,150,40);
        this.add(tk);

        timkiemtheo.setBounds(450,20,150,20);
        this.add(timkiemtheo);

        timkiem1.setBounds(450,160,150,40);
        timkiem1.setBackground(Color.white);
        timkiem1.setIcon(lammoi1);
        timkiem1.setIcon(anhtk);
        timkiem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vdata.clear();
                    ResultSet rs1 = sm.executeQuery("select * from loaisanpham where "+tk.getSelectedItem()+" LIKE N'%"+timkiem.getText()+"%' ");
                    ResultSetMetaData rsm = rs1.getMetaData();
                    int num_column = rsm.getColumnCount();
                    vtitle = new Vector(num_column);
                    for (int i = 1; i <= num_column; i++) {
                        vtitle.add(rsm.getColumnLabel(i));
                    }
                    while(rs1.next()){
                        Vector row=new Vector(num_column);
                        for(int i=1;i<=num_column;i++)
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
                idsp1.setText((String)vt.elementAt(0));
                tensanpham1.setText((String) vt.elementAt(1));
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

        sua.setBounds(230,300,120,40);
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

        table.setBounds(60,350,700,300);
        this.getContentPane().add(table);


        sapxeptheo.setBounds(40,160,100,30);
        sapxeptheo.setForeground(Color.black);
        sapxeptheo.setFont(new Font("Time New Roman",Font.ROMAN_BASELINE,15));
        this.add(sapxeptheo);

        sapxeptheo1=new JComboBox(vtitle);
        sapxeptheo1.setBounds(150,160,100,30);
        this.add(sapxeptheo1);

        btsapxeptang.setBounds(260,160,120,30);
        btsapxeptang.setFocusable(false);
        btsapxeptang.setBackground(Color.white);
        btsapxeptang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vdata.clear();
                    ResultSet rs1 = sm.executeQuery("select * from loaisanpham order by "+sapxeptheo1.getSelectedItem()+" ASC");
                    ResultSetMetaData rsm = rs1.getMetaData();
                    int num_column = rsm.getColumnCount();
                    vtitle = new Vector(num_column);
                    for (int i = 1; i <= num_column; i++) {
                        vtitle.add(rsm.getColumnLabel(i));
                    }
                    while(rs1.next()){
                        Vector row=new Vector(num_column);
                        for(int i=1;i<=num_column;i++)
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

        btsapxepgiam.setBounds(260,200,120,30);
        btsapxepgiam.setFocusable(false);
        btsapxepgiam.setBackground(Color.white);
        btsapxepgiam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vdata.clear();
                    ResultSet rs1 = sm.executeQuery("select * from loaisanpham order by "+sapxeptheo1.getSelectedItem()+" DESC ");
                    ResultSetMetaData rsm = rs1.getMetaData();
                    int num_column = rsm.getColumnCount();
                    vtitle = new Vector(num_column);
                    for (int i = 1; i <= num_column; i++) {
                        vtitle.add(rsm.getColumnLabel(i));
                    }
                    while(rs1.next()){
                        Vector row=new Vector(num_column);
                        for(int i=1;i<=num_column;i++)
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

        this.setTitle(" loại Sản Phẩm");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800,700);
        this.add(nen);
        this.setIconImage(icon.getImage());
        this.setResizable(false);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }

    public void reload(){
        try {
            vtitle.clear();
            vdata.clear();
            ResultSet rs1 = sm.executeQuery("select * from loaisanpham");
            ResultSetMetaData rsm = rs1.getMetaData();
            int num_column = rsm.getColumnCount();
            for (int i = 1; i <= num_column; i++) {
                vtitle.add(rsm.getColumnLabel(i));
            }
            while(rs1.next()){
                Vector row=new Vector(num_column);
                for(int i=1;i<=num_column;i++)
                    row.add(rs1.getString(i));
                vdata.add(row);
            }
            rs1.close();
        } catch (SQLException ex1){
            System.out.println("cannot conect"+ex1);
        }
    }
    public void nhapDb(){
        if(idsp1.getText().equals("")||tensanpham1.getText().equals("")){
            JOptionPane.showMessageDialog(null,"bạn chưa nhập thông tin ","lỗi",JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                String id=idsp1.getText();
                String ten=tensanpham1.getText();
                String sql="insert into loaisanpham(ID,loai) values(?,?)";
                PreparedStatement ps=conn.prepareStatement(sql);
                ps.setString(1,id);
                ps.setString(2,ten);
                ps.executeUpdate();
                reload();
                model.fireTableDataChanged();
                ps.close();
                JOptionPane.showMessageDialog(null,"Đã thêm "+ten+" vào danh mục loại sản phẩm","acept",JOptionPane.INFORMATION_MESSAGE);
            }catch (SQLException ex2){
                System.out.println("cannotconect"+ex2);
            }
        }
    }
    public void suadb(){
        if(idsp1.getText().equals("")||tensanpham1.getText().equals("")){
            JOptionPane.showMessageDialog(null,"bạn chưa nhập thông tin ","lỗi",JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                String id = idsp1.getText();
                String ten = tensanpham1.getText();
                Vector vt = (Vector) vdata.elementAt(selectedrow);
                String sql = "update loaisanpham set ID='" + id + "',loai=N'" + ten + "' where ID='" + vt.elementAt(0) + "'";
                Statement ps = conn.createStatement();
                ps.executeUpdate(sql);
                reload();
                model.fireTableDataChanged();
                ps.close();
                JOptionPane.showMessageDialog(null, "Đã sửa danh mục loại sản phẩm", "acept", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex2) {
                System.out.println("cannotconect" + ex2);
            }
        }
    }
}
