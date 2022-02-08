package com.company.khang;

import javax.swing.*;
import java.awt.*;

public class background3 extends JPanel {
    Image nencacbutton;
    background3(){
        nencacbutton=new ImageIcon("wood-background.png").getImage();
        this.setPreferredSize(new Dimension(600,600));
    }
    public void paint(Graphics g) {
        Graphics2D g2D=(Graphics2D) g;
        g2D.drawImage(nencacbutton,0,0,null);
    }
}
