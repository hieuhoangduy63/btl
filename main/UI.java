package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {
    Panel p;
    Font arial_30;
    double playTime;
    DecimalFormat dFormat=new DecimalFormat("#0.00");
    public UI(Panel p){
        this.p=p;
        arial_30=new Font("Arial",Font.PLAIN,30);
    }
    public void draw(Graphics2D g2){
        g2.setFont(arial_30);
        g2.setColor(Color.white);
        g2.drawString("Score: "+p.score,20,50);
        if(p.score<24){
        playTime+=(double)1/60;}
        g2.drawString("Time: "+dFormat.format(playTime),530,50);
    }

}
