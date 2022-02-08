package com.company.khang;

import javax.swing.*;
import java.awt.*;

public class background2 extends JPanel {
    Image anhtieude;
    background2(){
        anhtieude=new ImageIcon("bcf51bf542a78ff9d6b6.jpg").getImage();
        this.setPreferredSize(new Dimension(1000,150));
    }
    public void paint(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        g2d.drawImage(anhtieude,0,0,null);
    }
}
