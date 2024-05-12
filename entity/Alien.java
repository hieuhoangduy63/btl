package entity;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Alien extends Sprite {
    public int x0;
    public boolean destroyed = false;
    public boolean sangphai = true;
    private List<Bullet> bullets;
    private int shootingCycle; // Chu kỳ bắn đạn
    private int shootingCounter; // Đếm chu kỳ hiện tại
    private Random random; // Để tạo số ngẫu nhiên

    public Alien(int x, int y, int x0) {
        this.x0 = x0;
        this.x = x;
        this.y = y;
        setDefaultValue();
        bullets = new ArrayList<>();
        shootingCycle = 60; // Bắn đạn sau mỗi 60 khung hình
        shootingCounter = 0;
        random = new Random();
    }

    public void setDefaultValue() {
        speed = 1;
        width = 32;
        height = 32;
    }

    public void update() {
        if (destroyed == false) {
            if (x < (x0 + 50) && sangphai == true) {
                x += speed;
            } else if (x >= (x0 + 50)) {
                x -= speed;
                sangphai = false;
            } else if (x > (x0 - 50) && (sangphai == false)) {
                x -= speed;
            } else if (x <= (x0 - 50)) {
                sangphai = true;
            }
        } else {
            x = 1000;
            y = 1000;
        }

        shootingCounter++;
        if (shootingCounter >= shootingCycle) {
            if (random.nextInt(100) < 10) { // 10% xác suất bắn đạn
                shootBullet();
            }
            shootingCounter = 0;
        }

        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.update();
            if (!bullet.isAlive()) {
                bullets.remove(i);
            }
        }
    }

    private void shootBullet() {
        Bullet bullet = new Bullet(x + width / 2, y + height);
        bullets.add(bullet);
    }

    public void draw(Graphics2D g2) {
        if (destroyed == false) {
            g2.setColor(Color.white);
            g2.fillRect(x, y, width, height);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g2);
        }
    }
    public List<Bullet> getBullets() {
        return bullets;
    }
}
