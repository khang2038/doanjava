package com.company.khang;

import javax.swing.*;
import java.awt.*;

public class background1 extends JPanel {
    Image background;
    background1(){
        background=new ImageIcon("Hinhnen-Coffee-4.jpg").getImage();
        this.setPreferredSize(new Dimension(650,435));
    }
    public void paint(Graphics g){
        Graphics2D g2D=(Graphics2D) g;
        g2D.drawImage(background,0,0,null);
    }
}
