package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import main.Panel;

public class Bullet extends Sprite {
    Panel p;
    public double countDown=0;
    public Bullet (Panel p){
        this.p=p;
        setDefaultValue();
    }

    public void setDefaultValue(){
        this.x=2000;
        this.y=2000;
        this.width=8;
        this.height=16;
        speed=4;
        this.alive=false;
    }
    public void update(){
        if(alive==false&&countDown<=0){
            alive=true;
            Random generator=new Random();
            int value1=generator.nextInt(4);
            int value2=generator.nextInt(6);
            x=p.alien[value1][value2].x+12;
            y=p.alien[value1][value2].y+32;
            countDown=300/speed;
        }
        else if(alive==true){
            y+=speed;
        }
        if(y>=700){setDefaultValue();}
        if(p.lv2==true){speed=8;}
        countDown-=1;
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.green);
        g2.fillRect(x,y,width,height);
    }
}
