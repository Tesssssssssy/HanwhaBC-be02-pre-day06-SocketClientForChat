package day08;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

    private static final long serialVersionUID = 1L;

    GameFrame() {
        //GamePanel panel = new GamePanel();
        MultiplayerGamePanel multiplayerGamePanel = new MultiplayerGamePanel();
        //this.add(panel);
        this.add(multiplayerGamePanel);
        this.setTitle("snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}