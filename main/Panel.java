package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


import javax.swing.*;

import entity.Alien;
import entity.Bomb;
import entity.Bullet;
import entity.Player;

public class Panel extends JPanel implements Runnable {
    //kiểm tra trạng thâi game
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
    final int screenHight=700;
    final int screenWidth=700;

    //FPS
    int FPS=60;
    //Điểm số
    int score=0;
    
    Setter set=new Setter(this);
    Thread gameThread;
    KeyInput KIP=new KeyInput(this);
    public Player player=new Player(this,KIP);
    public UI ui=new UI(this);
    //khai báo và khởi tạo quái
    Alien[][] alien=new Alien[4][6];

    Bomb bom = new Bomb(this,KIP);
   // Trang thai game
    public int gameState =0;
    public final int playState = 1;
    public final int menuState= 0;
    public final int helpState=2;
    public final int pauseState=3;

    Panel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }

    public void setUpGame(){
        set.setAlien();
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
    //kiểm tra xem có trúng quái không
    public void checkDestroyed(){
        for(int i=3;i>=0;i--){
            for(int j=0;j<6;j++){
                if(bom.x+bom.width>=alien[i][j].x && bom.x<=alien[i][j].x+alien[i][j].width && bom.y<=alien[i][j].y+alien[i][j].height && bom.y+bom.height>=alien[i][j].y){
                    alien[i][j].destroyed=true;
                    bom.alive=false;
                    bom.destroyed=true;
                    score++;
                    bom.setDefaultValue();
                }
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
        bom.update();
        checkPlayerHit(); // Gọi phương thức kiểm tra trúng đạn
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
        //vẽ nhân vật
        player.draw(g2);
        //vẽ quái
        for(int i=0;i<4;i++){
            for(int j=0;j<6;j++){
                alien[i][j].draw(g2);
            }
        }
        //vẽ bom
        bom.draw(g2);
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                for (Bullet bullet : alien[i][j].getBullets()) {
                    bullet.draw(g2);
                }
            }
        }
        //in điểm số
        ui.draw(g2);
        
        //in bộ đếm mạng
        drawLives(g2);
        
        g2.dispose();}
    }
    //kiểm tra xem có bị trúng đạn không
    public void checkPlayerHit() {
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                for (Bullet bullet : alien[i][j].getBullets()) {
                    if (bullet.isAlive() && !player.isInvincible() && player.isHit(bullet)) {
                        player.loseLife();
                        bullet.setAlive(false);
                        break;
                    } else if (player.isInvincible() && currentTime - player.lastHitTime > player.invincibilityDuration) {
                        player.isInvincible = false; // Kết thúc trạng thái bất tử tạm thời
                    }
                }
            }
        }
    }
}
