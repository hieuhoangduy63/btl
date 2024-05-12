package entity;

import main.KeyInput;
import main.Panel;
import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Sprite {
    Panel p;
    KeyInput kIP;
    //phương thức khởi tạo
    public Player (Panel p,KeyInput kIP){
        this.p=p;
        this.kIP=kIP;
        setDefaultValue();
    }
    //vị trí ban đầu và tốc độ người chơi
    public void setDefaultValue(){
        x=326;
        y=600;
        speed=5;
        width=24;
        height=24;
    }
    //cập nhật và vẽ hình ảnh
    public void update(){
        if(kIP.rightPressed==true&&x<652){
            x+=speed;
        }
        else if(kIP.leftPressed==true&&x>0){
            x-=speed;
        }
    }
    //Trạng thái màu sắc của player
    public void draw(Graphics2D g2) {
        //khi bất tử
        if (isInvincible) {
            long elapsed = (System.currentTimeMillis() - lastHitTime) % 500; // Thay đổi màu sắc mỗi 0.5 giây
            if (elapsed < 250) {
                g2.setColor(Color.WHITE); // Màu trắng nửa đầu
            } else {
                g2.setColor(Color.RED); // Màu đỏ nửa sau
            }
        } else {
        //khi bình thường
            g2.setColor(Color.WHITE);
        }
        g2.fillRect(x, y, width, height);
    }
    //Khởi tạo mạng
    private int life = 3; // Số mạng ban đầu
    public int getLife() {
        return life;
    }

    public boolean isInvincible = false; // Trạng thái bất tử tạm thời
    public long lastHitTime; // Thời gian lần cuối bị trúng đạn
    public long invincibilityDuration = 2000; // Thời gian bất tử tạm thời (2 giây)

    public boolean isInvincible() {
        return isInvincible;
    }

    public void makeInvincible() {
        isInvincible = true;
        lastHitTime = System.currentTimeMillis();
    }

    public boolean isHit(Bullet bullet) {
        return (bullet.x + bullet.width >= x && bullet.x <= x + width &&
                bullet.y + bullet.height >= y && bullet.y <= y + height);
    }
    public void loseLife() {
        if (!isInvincible) { // Chỉ mất mạng khi không ở trạng thái bất tử
            life--;
            makeInvincible(); // Bắt đầu trạng thái bất tử tạm thời
            if (life == 0) {
                p.setGameOver(true); // Đặt trạng thái trò chơi thành kết thúc
            }
        }
    }

}