package com.company.khang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class regis extends JFrame implements ActionListener, KeyListener {
    JButton confirm=new JButton("Confirm");
    background1 panel=new background1();
    JTextField userID=new JTextField();
    ImageIcon icon=new ImageIcon("9-93303_login-youtube-desktop-wallpaper-png-1494x1723px-biu-tng.jpg");
    JPasswordField userpasword=new JPasswordField();
    JLabel useridlabel=new JLabel("UserID: ");
    JLabel userpaswordlabel=new JLabel("Password: ");
    JLabel confirmpaswordlabel=new JLabel("Confirm Password: ");
    JPasswordField confirmpasword=new JPasswordField();
    JLabel message=new JLabel();
    JButton exit=new JButton("Exit");
    regis() {
        useridlabel.setBounds(150, 100, 70, 50);
        useridlabel.setForeground(Color.gray);
        this.add(useridlabel);

        userpaswordlabel.setBounds(150, 150, 70, 50);
        userpaswordlabel.setForeground(Color.gray);
        this.add(userpaswordlabel);

        confirmpaswordlabel.setBounds(100,200, 120, 50);
        confirmpaswordlabel.setForeground(Color.gray);
        this.add(confirmpaswordlabel);

        userID.setBounds(220, 110, 150, 30);
        userID.setForeground(Color.black);
        userID.addKeyListener(this);
        this.add(userID);

        userpasword.setBounds(220, 160, 150, 30);
        userpasword.setForeground(Color.black);
        userpasword.addKeyListener(this);
        this.add(userpasword);

        confirmpasword.setBounds(220, 210, 150, 30);
        confirmpasword.setForeground(Color.black);
        confirmpasword.addKeyListener(this);
        this.add(confirmpasword);

        message.setBounds(220, 240, 250, 30);
        this.add(message);

        confirm.setBounds(220, 280, 100, 30);
        confirm.setForeground(Color.black);
        confirm.setFocusable(false);
        confirm.setBackground(Color.LIGHT_GRAY);
        confirm.addActionListener(this);
        this.add(confirm);

        exit.setBounds(320, 280, 70, 30);
        exit.setForeground(Color.black);
        exit.setFocusable(false);
        exit.addActionListener(this);
        exit.setBackground(Color.LIGHT_GRAY);
        this.add(exit);

        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
            String cfmk=String.valueOf(confirmpasword.getPassword());
            if(userid.equals("")||mk.equals("")||cfmk.equals("")){
                JOptionPane.showMessageDialog(null,"bạn chưa nhập  đủ thông tin ","lỗi",JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    if (mk.equals(cfmk)) {
                        Connection conn = DBConnectionFactory.getconection();
                        String sql = "insert into taikhoan values (?,?)";
                        PreparedStatement stm = conn.prepareStatement(sql);
                        stm.setString(1, userid);
                        stm.setString(2, mk);
                        stm.executeUpdate();
                        message.setForeground(Color.GREEN);
                        message.setText("Regis successful");
                        stm.close();
                    } else {
                        message.setForeground(Color.red);
                        message.setText("mật khẩu không trùng khớp");
                    }
                } catch (SQLException ex) {
                    message.setForeground(Color.red);
                    message.setText("Tài khoản đã tồn tại");
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==confirm){
            String userid = userID.getText();
            String mk = String.valueOf(userpasword.getPassword());
            String cfmk=String.valueOf(confirmpasword.getPassword());
            if(userid.equals("")||mk.equals("")||cfmk.equals("")){
                JOptionPane.showMessageDialog(null,"bạn chưa nhập  đủ thông tin ","lỗi",JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    if (mk.equals(cfmk)) {
                        Connection conn = DBConnectionFactory.getconection();
                        String sql = "insert into taikhoan values (?,?)";
                        PreparedStatement stm = conn.prepareStatement(sql);
                        stm.setString(1, userid);
                        stm.setString(2, mk);
                        stm.executeUpdate();
                        message.setForeground(Color.GREEN);
                        message.setText("Regis successful");
                        stm.close();
                    } else {
                        message.setForeground(Color.red);
                        message.setText("mật khẩu không trùng khớp");
                    }

                } catch (SQLException ex) {
                    message.setForeground(Color.red);
                    message.setText("Tài khoản đã tồn tại");
                }
            }
        }
        if(e.getSource()==exit){
            dispose();
        }

    }

    public static void main(String[] args) {
        new regis();
    }
}

