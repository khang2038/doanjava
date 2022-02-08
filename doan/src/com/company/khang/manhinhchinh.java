package com.company.khang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class manhinhchinh {
    manhinhchinh(){

        JFrame frame=new JFrame("Management Coffe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.setLayout(new BorderLayout(5,5));
        frame.setLocationRelativeTo(null);
        ImageIcon nensanpham=new ImageIcon("z3094191964431_618df27d14a90cbd7747a2c0e2358e31.jpg");
        ImageIcon nenloaisanpham=new ImageIcon("loại sanpham.jpg");
        ImageIcon nennhanvien=new ImageIcon("phuong-phap-danh-gia-360-1.jpg");
        ImageIcon nendoanhthu=new ImageIcon("Facebook_1422526520.jpeg");
        ImageIcon nenthongke=new ImageIcon("doanh-thu-la-gi-2.png");
        ImageIcon icon=new ImageIcon("maxresdefault.jpg");
        ImageIcon nguoitaikhoan=new ImageIcon("3724f44bdd3910674928.jpg");
        ImageIcon iconlogout=new ImageIcon("z3096645822283_f80ef529a4b792ecbc1701c9dd77fa29.jpg");
        ImageIcon dtsp=new ImageIcon("sp-hot.jpg");
        frame.setIconImage(icon.getImage());


        background2 tren=new background2();
        tren.setLayout(new BorderLayout());
        tren.setPreferredSize(new Dimension(0,150));
        frame.add(tren,BorderLayout.NORTH);


        JPanel duoi=new JPanel();
        duoi.setLayout(new BorderLayout(5,5));
        frame.add(duoi);

        JPanel acout=new JPanel();
        acout.setPreferredSize(new Dimension(150,0));
        acout.setBackground(new Color(6633000));
        acout.setLayout(new FlowLayout());
        duoi.add(acout,BorderLayout.WEST);

        JPanel control=new JPanel();
        control.setLayout(new GridLayout(2,3,20,20));
        control.setBackground(new Color(6633000));
        duoi.add(control);
        //sanpham
        JButton sanpham=new JButton();
        sanpham.setIcon(nensanpham);
        sanpham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    new clsanpham();
            }
        });
        control.add(sanpham);

        JButton loaisanpham=new JButton();
        loaisanpham.setIcon(nenloaisanpham);
        loaisanpham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new clloaisanpham();
            }
        });
        control.add(loaisanpham);


        JButton nhanvien=new JButton();
        nhanvien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new clnhanvien();
            }
        });
        control.add(nhanvien);
        nhanvien.setIcon(nennhanvien);

        JButton doanhthu=new JButton();
        doanhthu.setIcon(nendoanhthu);
        doanhthu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new cldoanhthukhachhang();
            }
        });
        control.add(doanhthu);

        JButton doanhthusp=new JButton();
        doanhthusp.setIcon(dtsp);
        doanhthusp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new cldoanhthusanpham();
            }
        });
        control.add(doanhthusp);

        JButton thongkedoanhthu=new JButton();
        thongkedoanhthu.setIcon(nenthongke);
        thongkedoanhthu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new thongke();
            }
        });
        control.add(thongkedoanhthu);

        JButton logout=new JButton("Log out");
        logout.setFocusable(false);
        logout.setBackground(Color.WHITE);
        logout.setIcon(iconlogout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new loginpage();
                frame.dispose();
            }
        });
        JLabel tk=new JLabel("User: Admin");
        tk.setForeground(Color.CYAN);
        tk.setFont(new Font("MV Boli",Font.PLAIN,15));
        tk.setIcon(nguoitaikhoan);
        acout.add(tk);
        acout.add(logout);


        frame.setVisible(true);

    }
}
