package entity;


import java.awt.Graphics2D;
import main.Panel;
import javax.swing.ImageIcon;



public class Alien extends Sprite {
    public int x0;
    Panel p;
    public boolean sangphai=true;
    private ImageIcon alienSkin1;
    public Alien(int x,int y,int x0,Panel p){
        this.x0=x0;
        this.x=x;
        this.y=y;
        this.p=p;
        setDefaultValue();
    }
    public void setDefaultValue(){
        speed=1;
        width=32;
        height=32;
        alive=true;
    }
    public void update(){
        if(alive){
        if(x<(x0+50)&&sangphai==true){
        x+=speed;}
        else if(x>=(x0+50)){
            x-=speed;
            sangphai=false;
        }
        else if(x>(x0-50)&&(sangphai==false)){
            x-=speed;
        }
        else if(x<=(x0-50)){
            sangphai=true;
        }}
        else{
            x=1000;
            y=1000;
        }
        if(p.lv2==true){speed=2;}
    }
    public void draw(Graphics2D g2){
        if(alive==true){
        alienSkin1 = new ImageIcon(getClass().getResource("/Image/AlienSkin1.gif"));
        g2.drawImage(alienSkin1.getImage(), x, y, null);
        }
    }
}
