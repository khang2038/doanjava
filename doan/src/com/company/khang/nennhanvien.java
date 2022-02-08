package com.company.khang;

import javax.swing.*;
import java.awt.*;

public class nennhanvien extends JPanel {
    Image nen;
    nennhanvien(){
        nen=new ImageIcon("nennhanvien.png").getImage();
        this.setPreferredSize(new Dimension(1200,700));
    }
    public void paint(Graphics g) {
        Graphics2D g2D=(Graphics2D) g;
        g2D.drawImage(nen,0,0,null);
    }
}
