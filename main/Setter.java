package main;

import entity.Alien;
import entity.Bullet;
import entity.Shield;


public class Setter {
     Panel p;
     public Setter(Panel p){
        this.p=p;
     }
     //cài vị trí ban đầu của quái
     public void setAlien(){
        for(int i=0;i<4;i++){
            for(int j=0;j<6;j++){
            p.alien[i][j]=new Alien((j+1)*100,(i+2)*50,(j+1)*100,p);
        }}
   }
      public void setAlien_lv2(){
         for(int i=0;i<4;i++){
            for(int j=0;j<6;j++){
            p.alien[i][j]=new Alien((j+1)*100,(i+2)*50,(j+1)*100,p);
            p.alien[i][j].speed=2;
        }}
      }
     //cài vị trí ban đầu của khiên
     public void setShield(){
         for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
            p.shield[i][j]=new Shield(j*96+103*(j+1),i*16+450);
     }}
     
     }
     //cài các thông số ban đầu của Bullet
     public void setBullet(){
      for(int i=0;i<5;i++){
         p.bullet[i]=new Bullet(p);
      }
  }
}