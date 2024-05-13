package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import main.Panel;

import main.KeyInput;

public class Bomb extends Sprite {
    Panel p;
    KeyInput kIP;
    public Boolean destroyed=false;

    public boolean alive=false;
    public Bomb (Panel p,KeyInput kIP){
        this.p=p;
        this.kIP=kIP;
        setDefaultValue();
    }

    public void setDefaultValue(){
        this.x=2000;
        this.y=2000;
        speed=10;
        this.width=8;
        this.height=16;
    }
    public void update(){
        if(kIP.spacePressed==true&&alive==false){
            alive=true;
            x=p.player.x-3;
            y=600;
        }
        else if(alive==true){
            y-=speed;
        }
        if(y<=0){alive=false;setDefaultValue();}
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.fillRect(x,y,width,height);
    }
    
}
