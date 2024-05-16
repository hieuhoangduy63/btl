package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Panel;

public class BossHP extends Sprite {
    Panel p;
    public BossHP(Panel p){
        this.p=p;
        width=100;
        height=5;
        x=1000;
        y=1000;
    }
    public void update(){
        if(p.boss.alive==true){
            x=p.boss.x;
            y=p.boss.y-20;
            width=p.boss.life*4;
        }
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.red);
        g2.fillRect(x,y,width,height);
    }
}
