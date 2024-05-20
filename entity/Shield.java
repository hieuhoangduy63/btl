package entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class Shield extends Sprite {
    public Shield(int x,int y){
        this.x=x;
        this.y=y;
        setDefaultValue();
    }
    public void setDefaultValue(){
        width=96;
        height=8;
        alive=true;
    }
    public void update(){
        if(alive==false){
            x=1000;
            y=1000;
        }
    }
    public void draw(Graphics2D g2){
        if(alive==true){
        g2.setColor(Color.blue);
        g2.fillRect(x,y,width,height);
        }
    }
}

