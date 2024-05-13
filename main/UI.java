package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
    Panel p;
    Font marunMonica;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public int comN=0;

    public UI(Panel p) {
        this.p = p;

        try {
            InputStream is = getClass().getResourceAsStream("/x12y16pxMaruMonica.ttf");
            marunMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        if (p.gameState == p.menuState) {
            drawMenuS(g2);
        } else if (p.gameState==p.helpState) {
            drawHelpScreen(g2);
        } else if (p.gameState==p.pauseState) {
            drawPauseScreen(g2);

        }else if (p.gameState == p.playState) {
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
            g2.setColor(Color.white);
            g2.drawString("Score: " + p.score, 20, 50);
            if (p.score < 24) {
                playTime += (double) 1 / 60;
            }
            g2.drawString("Time: " + dFormat.format(playTime), 530, 50);
        }
    }

    // ve menu
    public void drawMenuS(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));

        // Vẽ tiêu đề
        String titleText = "STAR WAR GAME";
        int titleX = (p.screenWidth - g2.getFontMetrics().stringWidth(titleText)) / 2;
        g2.drawString(titleText, titleX, 200);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        // Vẽ nút Play
        g2.setColor(Color.WHITE);
        String playText = "Chơi luôn";
        g2.drawString(playText, titleX+180,350 );
        if(comN == 0){
            g2.drawString(">", titleX +160,350);

        }

        // Vẽ nút Help
        g2.setColor(Color.WHITE);
        String helpText = "Hướng dẫn";
        g2.drawString(helpText, titleX+180,420 );
        if(comN == 1){
            g2.drawString(">", titleX +160,420);

        }
        // Vẽ nút Quit
        g2.setColor(Color.WHITE);
        String quitText = "Thoát";
        g2.drawString(quitText, titleX+180,490 );
        if(comN == 2){
            g2.drawString(">", titleX +169,490);

        }


    }
    // ve huong dan
    public void drawHelpScreen(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        String titleText = "Hướng dẫn";
        int titleX = (p.screenWidth - g2.getFontMetrics().stringWidth(titleText)) / 2;
        g2.drawString(titleText, titleX, 50);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        g2.setColor(Color.WHITE);
        String playText = "Di chuyển: A để sang phải, D để sang trái";
        g2.drawString(playText,50,150 );
        String play1Text = "Tấn công: Space";
        g2.drawString(play1Text,50,190 );
        String play2Text = "Dừng game: P";
        g2.drawString(play2Text,50,230 );
        String play3Text = "Thoát khỏi game:ESC";
        g2.drawString(play3Text,50,270 );
        String play4Text = "Quay lại(esc)";
        g2.drawString(play4Text,50,310 );

        }
        public void drawPauseScreen(Graphics2D g2){
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
            String titleText = "Dừng";

            g2.drawString(titleText, 275, 350);
        }


    }


