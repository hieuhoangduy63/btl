package main;
import javax.swing.JFrame;

public class Main extends JFrame {
    public  Main(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Star War Game");
        this.setLocation(400, 50);
        this.setVisible(true);
        this.setSize(700, 700);
        Panel panel=new Panel();
        this.addKeyListener(panel.KIP);
        this.add(panel);
        pack();
        panel.setUpGame();
        panel.startGameThread();
        Sound sound=new Sound(panel);
        sound.startSoundThread();
        
    }
    public static void main(String[] args){
        new Main();
    }
}
    