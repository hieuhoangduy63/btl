package main;

import entity.Alien;


public class Setter {
     Panel p;
     public Setter(Panel p){
        this.p=p;
     }
     public void setAlien(){
        for(int i=0;i<4;i++){
            for(int j=0;j<6;j++){
            p.alien[i][j]=new Alien((j+1)*100,(i+2)*50,(j+1)*100);
        }}
     }
}
