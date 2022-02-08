package com.company.khang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.util.HashMap;

public class loginpage extends JFrame implements KeyListener,ActionListener{
    //JFrame frame =new JFrame("Login");
    JButton login=new JButton("Login");
    background1 panel=new background1();
    JTextField userID=new JTextField();
    ImageIcon icon=new ImageIcon("9-93303_login-youtube-desktop-wallpaper-png-1494x1723px-biu-tng.jpg");
    JPasswordField userpasword=new JPasswordField();
    JLabel useridlabel=new JLabel("UserID: ");
    JLabel userpaswordlabel=new JLabel("Password: ");
    JLabel message=new JLabel();
    JButton exit=new JButton("Exit");
    JButton regis=new JButton("Regis");
    String tk1;
    loginpage() {
        useridlabel.setBounds(150, 100, 70, 50);
        useridlabel.setForeground(Color.gray);
        this.add(useridlabel);

        userpaswordlabel.setBounds(150, 150, 70, 50);
        userpaswordlabel.setForeground(Color.gray);
        this.add(userpaswordlabel);

        userID.setBounds(220, 110, 150, 30);
        userID.setForeground(Color.black);
        userID.addKeyListener(this);
        this.add(userID);

        userpasword.setBounds(220, 160, 150, 30);
        userpasword.setForeground(Color.black);
        userpasword.addKeyListener(this);
        this.add(userpasword);

        message.setBounds(220, 190, 200, 30);
        this.add(message);

        login.setBounds(220, 230, 70, 30);
        login.setForeground(Color.black);
        login.setFocusable(false);
        login.setBackground(Color.LIGHT_GRAY);
        login.addActionListener(this);
        this.add(login);

        exit.setBounds(290, 230, 70, 30);
        exit.setForeground(Color.black);
        exit.setFocusable(false);
        exit.addActionListener(this);
        exit.setBackground(Color.LIGHT_GRAY);
        this.add(exit);

        regis.setBounds(220, 270, 70, 30);
        regis.setForeground(Color.black);
        regis.setFocusable(false);
        regis.addActionListener(this);
        regis.setBackground(Color.LIGHT_GRAY);
        this.add(regis);

        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 435);
        this.add(panel);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
      if(e.getKeyCode()==KeyEvent.VK_ENTER){
          String userid = userID.getText();
          String mk = String.valueOf(userpasword.getPassword());
          try {
              Connection conn=DBConnectionFactory.getconection();
              String sql="select * from taikhoan where taikhoan=? and matkhau=?";
              PreparedStatement stm= conn.prepareStatement(sql);
              stm.setString(1,userid);
              stm.setString(2,mk);
              ResultSet rs=stm.executeQuery();
                  if (rs.next()) {
                      message.setForeground(Color.GREEN);
                      message.setText("login successful");
                      tk1=userid;
                      new manhinhchinh();
                      dispose();
                  } else {
                      message.setForeground(Color.red);
                      message.setText("sai tai khoan hoac mat khau");
                  }
          }catch (SQLException ex){
              System.out.println("cannot conect"+ex);
          }
      }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login){
            String userid = userID.getText();
            String mk = String.valueOf(userpasword.getPassword());
            try {
                Connection conn=DBConnectionFactory.getconection();
                String sql="select * from taikhoan where taikhoan=? and matkhau=?";
                PreparedStatement stm= conn.prepareStatement(sql);
                stm.setString(1,userid);
                stm.setString(2,mk);
                ResultSet rs=stm.executeQuery();
                if (rs.next()) {
                    message.setForeground(Color.GREEN);
                    message.setText("login successful");
                    tk1=userid;
                    new manhinhchinh();
                    dispose();
                } else {
                    message.setForeground(Color.red);
                    message.setText("sai tai khoan hoac mat khau");
                }
            }catch (SQLException ex){
                System.out.println("cannot conect"+ex);
            }
        }
        if(e.getSource()==exit){
                dispose();
        }
        if(e.getSource()==regis){
             new regis();
        }
    }
}
