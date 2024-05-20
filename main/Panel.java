package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.*;

import Background.BackgroundManager;
import entity.Alien;
import entity.Bomb;
import entity.Boss;
import entity.BossHP;
import entity.Bullet;
import entity.Player;
import entity.Shield;

public class Panel extends JPanel implements Runnable {
    //kiểm tra trạng thái game
    private boolean isGameOver = false;

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    //kích thước nhân vật
    final int originalCharactorSize=16;
    final int scale=3;

    //kích thước cửa sổ game
    public final int screenHight=700;
    public final int screenWidth=700;

    //Background
    private BackgroundManager backgroundManager;

    //FPS
    int FPS=60;
    //Điểm số
    int score=0;
    public Boolean lv2=false;
    public Boolean lv3=false;
    
    Setter set=new Setter(this);
    Thread gameThread;
    KeyInput KIP=new KeyInput(this);
    public Player player=new Player(this,KIP);
    public UI ui=new UI(this);
    //khai báo và khởi tạo quái,khiên,bom
    public Alien[][] alien=new Alien[4][6];
    Bomb bom = new Bomb(this,KIP);
    Bullet[] bullet=new Bullet[5];
    Shield[][] shield=new Shield[2][3];
    public Boss boss;
    BossHP bosshp=new BossHP(this);
   // Trang thai game
    public int gameState =0;
    public final int playState = 1;
    public final int menuState= 0;
    public final int helpState=2;
    public final int pauseState=3;

    Panel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHight));
        this.setBackground(Color.black);
        backgroundManager = new BackgroundManager(screenHight);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }

    public void setUpGame(){
        set.setAlien();
        set.setShield();
        set.setBullet();
        set.setBoss();
        set.setBullet_lv3();
        gameState = menuState;
        
    }
    @Override
public void run() {
    double drawInterval = (double) (1000000000 / this.FPS);
    double delta = 0.0D;
    long lastTime = System.nanoTime();
    long timer = 0L;

    while (this.gameThread != null && !isGameOver) { // Thêm điều kiện !isGameOver
        long currentTime = System.nanoTime();
        delta += (double) (currentTime - lastTime) / drawInterval;
        timer += currentTime - lastTime;
        lastTime = currentTime;
        if (delta >= 1.0D) {
            this.update();
            this.repaint();
            --delta;
        }

        if (timer >= 1000000000L) {
            timer = 0L;
        }
    }
}
    //Bộ đếm mạng
    public void drawLives(Graphics2D g2) {
        int livesX = 300;
        int livesY = 25;
        int livesWidth = 32;
        int livesHeight = 32;
    
        for (int i = 0; i < player.getLife(); i++) {
            g2.setColor(Color.RED);
            g2.fillRect(livesX + i * livesWidth, livesY, livesWidth, livesHeight);
            g2.setColor(Color.WHITE);
            g2.drawRect(livesX + i * livesWidth, livesY, livesWidth, livesHeight);
        }
    }
    //kiểm tra đạn có trúng không
    public void checkDestroyed(){
        long currentTime = System.currentTimeMillis();
        //check trúng quái
        for(int i=3;i>=0;i--){
            for(int j=0;j<6;j++){
                if(bom.x+bom.width>=alien[i][j].x&&bom.x<=alien[i][j].x+alien[i][j].width&&bom.y<=alien[i][j].y+alien[i][j].height&&bom.y+bom.height>=alien[i][j].y){
                    alien[i][j].alive=false;
                    bom.destroyed=true;
                    score++;
                    bom.setDefaultValue();
                }
                if(score==24&&lv2==false){
                    set.setAlien();
                    lv2=true;
                }
               if(score==48&&lv3==false){
                
                    lv2=false;
                    set.setBoss();
                    boss.x=screenWidth/2-boss.width/2;
                    boss.y=100;
                    lv3=true;}
            }
        }
        //check trúng người chơi
        for(int i=0;i<5;i++){
            if(bullet[i].x+bullet[i].width>=player.x&&bullet[i].x<=player.x+player.width&&bullet[i].y+bullet[i].height>=player.y
                &&bullet[i].y<=player.y+player.height&&!player.isInvincible()){
                    player.loseLife();
                    bullet[i].alive=false;
                    if(player.life==0){
                        player.alive=false;
                    }
            }
            else if(bullet[i].x+bullet[i].width>=player.x&&bullet[i].x<=player.x+player.width&&bullet[i].y+bullet[i].height>=player.y
            &&bullet[i].y<=player.y+player.height&&player.isInvincible()){
                bullet[i].alive=false;
            }
            if (player.isInvincible() && currentTime - player.lastHitTime > player.invincibilityDuration) {
                player.isInvincible = false; // Kết thúc trạng thái bất tử tạm thời
            }
        }
            //check trúng khiên
            for(int i=1;i>=0;i--){
                for(int j=0;j<3;j++){
                    if(bom.x+bom.width>=shield[i][j].x&&bom.x<=shield[i][j].x+shield[i][j].width&&bom.y<=shield[i][j].y+shield[i][j].height){
                        shield[i][j].alive=false;
                        bom.setDefaultValue();
                    }
                }
            }
            for(int i=1;i>=0;i--){
                for(int j=0;j<3;j++){
                    for(int k=0;k<5;k++){
                    if(bullet[k].x+bullet[k].width>=shield[i][j].x&&bullet[k].x<=shield[i][j].x+shield[i][j].width&&bullet[k].y+bullet[k].height>=shield[i][j].y){
                        bullet[k].setDefaultValue();
                }
            }
                }
            }
            //check trúng boss
                if(bom.x+bom.width>=boss.x&&bom.x<=boss.x+boss.width&&bom.y<=boss.y+boss.height&&bom.y+bom.height>=boss.y){
                    boss.life--;
                    bom.destroyed=true;
                    bom.setDefaultValue();
                    score++;
                    if(score==73){
                        boss.alive=false;
                        boss.x=1000;
                        boss.y=1000;

                    }
                }

            
        }
    
    
    //Thay đổi vị trí nhân vật và quái,bom
    public void update(){
        if (gameState == menuState) {

        }
        if (gameState==pauseState){

        }
        if (gameState==helpState){

        }

        if (gameState == playState) {
        player.update();
        checkDestroyed();
        for(int i=0;i<4;i++){
            for(int j=0;j<6;j++){
                alien[i][j].update();
            }
        }
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                shield[i][j].update();
            }
        }
        bom.update();
        boss.update();
        bosshp.update();
        if(ui.playTime>2&&lv3==false){
        for(int i=0;i<5;i++){
        bullet[i].update();}
    }   
        else if(lv3==true){
        for(int i=0;i<5;i++){
            bullet[i].update_lv3();}
    }
}
    }
    //vẽ nhân vật và quái,bom lên màn hình
        public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
            if (gameState == menuState) {
                ui.drawMenuS(g2);
            }
            if(gameState==helpState){
                ui.drawHelpScreen(g2);
            }
            if(gameState==pauseState){
                ui.drawPauseScreen(g2);
            }
            if (gameState ==playState){
        // Vẽ background
        backgroundManager.draw(g2);
        //vẽ nhân vật
        if(player.alive==true){
        player.draw(g2);}
        //vẽ quái
        for(int i=0;i<4;i++){
            for(int j=0;j<6;j++){
                alien[i][j].draw(g2);
            }
        }
        //vẽ khiên
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                shield[i][j].draw(g2);
            }
        }
        //vẽ bom
        bom.draw(g2);
        //vẽ bullet
        for(int i=0;i<5;i++){
        if(bullet[i].alive==true){
        bullet[i].draw(g2);}
    }
        //vẽ boss
        if(boss.alive=true){
            boss.draw(g2);
        }
        //vẽ thanh hp boss
        bosshp.draw(g2);
        //in điểm số
        ui.draw(g2);
        
        //in bộ đếm mạng
        drawLives(g2);
        
        g2.dispose();}
    }
}
