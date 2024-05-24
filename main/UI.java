package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;

public class UI {
    Panel p;
    Font PublicP;
    Font maruM;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.0");
    public int comN=0;
    Graphics2D g2;
    private ImageIcon backgroundMenuS;
    private ImageIcon pauseBackground;
    private ImageIcon menuPointer;
    private ImageIcon gameOverImage;
    private ImageIcon helpBackground;
    public UI(Panel p) {
        this.p = p;
        try {
            InputStream is = getClass().getResourceAsStream("/Font/PublicPixel-E447g.ttf");
            PublicP = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/Font/x12y16pxMaruMonica.ttf");
            maruM = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 =g2;
        g2.setFont(PublicP);
        g2.setColor(Color.WHITE);

        if (p.gameState == p.menuState) {
            drawMenuS(g2);
        } else if (p.gameState==p.helpState) {
            drawHelpScreen(g2);
        } else if (p.gameState==p.pauseState) {
            drawPauseScreen(g2);

        }else if (p.gameState == p.playState) {
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,24F));
            g2.setColor(Color.white);
            g2.drawString("Score:" + p.score, 20, 50);
            if (p.player.alive==true) {
                playTime += (double) 1 / 60;
            }
            g2.drawString("Time:" + dFormat.format(playTime), 450, 50);
        }
    }

    // ve menu
    public void drawMenuS(Graphics2D g2) {
        menuPointer = new ImageIcon(getClass().getResource("/Image/menuPointer.png"));
        backgroundMenuS = new ImageIcon(getClass().getResource("/Image/helpBackground.jpg"));
        g2.setFont(PublicP);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        g2.drawImage(backgroundMenuS.getImage(), 0, 0, null);

        // Vẽ tiêu đề
        String titleText = "STAR WAR GAME";
        int titleX = (p.screenWidth - g2.getFontMetrics().stringWidth(titleText)) / 2;
        g2.drawString(titleText, titleX, 200);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
        // Vẽ nút Play
        g2.setColor(Color.WHITE);
        String playText = "New Game";
        g2.drawString(playText, titleX+120,350 );
        if(comN == 0){
            g2.drawImage(menuPointer.getImage(), titleX +80,325, null);

        }

        // Vẽ nút Help
        g2.setColor(Color.WHITE);
        String helpText = "How to play";
        g2.drawString(helpText, titleX+120,420 );
        if(comN == 1){
            g2.drawImage(menuPointer.getImage(), titleX +80,395, null);

        }
        // Vẽ nút Quit
        g2.setColor(Color.WHITE);
        String quitText = "Quit";
        g2.drawString(quitText, titleX+120,490 );
        if(comN == 2){
            g2.drawImage(menuPointer.getImage(), titleX +80,465, null);

        }


    }
    // ve huong dan
    public void drawHelpScreen(Graphics2D g2) {
        helpBackground = new ImageIcon(getClass().getResource("/Image/backgroundMenuS.jpg"));
        g2.drawImage(helpBackground.getImage(), 0, 0, null);
        g2.setColor(Color.WHITE);
        g2.setFont(PublicP);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        String titleText = "How to play";
        int titleX = (p.screenWidth - g2.getFontMetrics().stringWidth(titleText)) / 2;
        g2.drawString(titleText, titleX, 100);


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
        g2.setColor(Color.WHITE);
        String playText = "Move: A move left, D move right";
        g2.drawString(playText,50,150 );
        String play1Text = "Attack: Space";
        g2.drawString(play1Text,50,190 );
        String play2Text = "Pause Game: P";
        g2.drawString(play2Text,50,230 );
        String play3Text = "Exit Game:Esc";
        g2.drawString(play3Text,50,270 );
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,10F));
        String play4Text = "- Return(Esc) -";
        g2.drawString(play4Text,20,30 );

        }
    public void drawPauseScreen(Graphics2D g2){
        pauseBackground = new ImageIcon(getClass().getResource("/Image/pauseBackground.jpg"));
        g2.setColor(Color.WHITE);
        g2.setFont(maruM);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        String titleText = "Pause";
        g2.drawImage(pauseBackground.getImage(), 0, 0, null);
        g2.drawString(titleText, 275, 350);
    }
    public void drawGameOverScreen(Graphics2D g2) {
        gameOverImage = new ImageIcon(getClass().getResource("/Image/gameOverImage.jpg"));
        g2.drawImage(gameOverImage.getImage(), 0, 0, null);
        g2.setColor(Color.WHITE);
        g2.setFont(maruM);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        String GameOverText = "You are dead";
        int GameOverX = (p.screenWidth - g2.getFontMetrics().stringWidth(GameOverText)) / 2;
        g2.drawString(GameOverText, GameOverX, 250);
            
        g2.setFont(maruM);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        g2.setColor(Color.WHITE);
        String GameOverScore = String.format("Score: %d", p.score);
        int GameOverScoreX = (p.screenWidth - g2.getFontMetrics().stringWidth(GameOverScore)) / 2;
        g2.drawString(GameOverScore, GameOverScoreX, 300 );
            
        g2.setFont(maruM);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        g2.setColor(Color.WHITE);
        String GameOverOption1 = "Try again";
        int GameOverOptionX = (p.screenWidth / 4);
        g2.drawString(GameOverOption1, GameOverOptionX, 400 );
        if (comN == 0){
            g2.drawString(">", GameOverOptionX - 20,400);
        }
        g2.setFont(maruM);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        g2.setColor(Color.WHITE);
        String GameOverOption2 = "Go Back";
        int GameOverOption2X = ((p.screenWidth / 4) * 3 - g2.getFontMetrics().stringWidth(GameOverOption2));
        g2.drawString(GameOverOption2, GameOverOption2X, 400 );
        if (comN == 1){
            g2.drawString(">", GameOverOption2X -20 ,400);
        }
    }
}


