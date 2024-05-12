package entity;
import entity.Bullet;
import java.awt.Color;
import java.awt.Graphics2D;


public class Bullet extends Sprite {
    private boolean alive;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
        speed = 5;
        width = 4;
        height = 8;
        alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public void update() {
        if (alive) {
            y += speed;
            if (y > 700) { // Nếu đạn ra khỏi màn hình
                alive = false;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (alive) {
            g2.setColor(Color.GREEN);
            g2.fillRect(x, y, width, height);
        }
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
