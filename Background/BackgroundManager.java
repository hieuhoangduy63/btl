package Background;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;

public class BackgroundManager {
    private ImageIcon backgroundImage;
    private int backgroundY1; // Tọa độ y của ảnh background thứ nhất
    private int backgroundY2; // Tọa độ y của ảnh background thứ hai
    private int screenHeight; // Chiều cao của màn hình

    public BackgroundManager(int screenHeight) {
        this.screenHeight = screenHeight;
        backgroundImage = new ImageIcon(getClass().getResource("/Image/background.png"));
        backgroundY1 = 0; // Ảnh background thứ nhất bắt đầu từ đỉnh
        backgroundY2 = -screenHeight; // Ảnh background thứ hai bắt đầu từ dưới
    }

    public void update() {
        backgroundY1 += 2; // Tăng tọa độ y của ảnh background thứ nhất
        backgroundY2 += 2; // Tăng tọa độ y của ảnh background thứ hai

        // Khi ảnh background thứ nhất ra khỏi màn hình, đưa nó về vị trí ban đầu của ảnh background thứ hai
        if (backgroundY1 >= screenHeight) {
            backgroundY1 = backgroundY2 - screenHeight;
        }

        // Khi ảnh background thứ hai ra khỏi màn hình, đưa nó về vị trí ban đầu của ảnh background thứ nhất
        if (backgroundY2 >= screenHeight) {
            backgroundY2 = backgroundY1 - screenHeight;
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(backgroundImage.getImage(), 0, backgroundY1, null);
        g2.drawImage(backgroundImage.getImage(), 0, backgroundY2, null);
    }
}