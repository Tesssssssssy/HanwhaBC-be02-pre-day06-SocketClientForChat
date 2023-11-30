package day08;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class MultiplayerGamePanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    static final int WIDTH = 700;
    static final int HEIGHT = 700;
    static final int UNIT_SIZE = 20;
    static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    final int[] x1 = new int[NUMBER_OF_UNITS];
    final int[] y1 = new int[NUMBER_OF_UNITS];
    int length1 = 5;
    char direction1 = 'D';

    final int[] x2 = new int[NUMBER_OF_UNITS];
    final int[] y2 = new int[NUMBER_OF_UNITS];
    int length2 = 5;
    char direction2 = 'D';

    int foodX;
    int foodY;
    int foodEaten1;
    int foodEaten2;

    boolean running = false;
    boolean gameOver = false; // 추가
    Random random;
    Timer timer;

    MultiplayerGamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        play();
    }

    public void play() {
        addFood();
        running = true;

        timer = new Timer(80, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void move() {
        moveSnake(x1, y1, length1, direction1);
        moveSnake(x2, y2, length2, direction2);
    }

    private void moveSnake(int[] x, int[] y, int length, char direction) {
        for (int i = length; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if (direction == 'L') {
            x[0] = x[0] - UNIT_SIZE;
        } else if (direction == 'R') {
            x[0] = x[0] + UNIT_SIZE;
        } else if (direction == 'U') {
            y[0] = y[0] - UNIT_SIZE;
        } else if (direction == 'D') {
            y[0] = y[0] + UNIT_SIZE;
        }
    }

    public void checkFood() {
        checkFoodEaten(x1, y1, length1);
        checkFoodEaten(x2, y2, length2);
    }

    private void checkFoodEaten(int[] x, int[] y, int length) {
        if (x[0] == foodX && y[0] == foodY) {
            addFood();
            if (x == x1) {
                length1++;
                foodEaten1++;
            } else if (x == x2) {
                length2++;
                foodEaten2++;
            }
        }
    }

    public void draw(Graphics graphics) {
        if (running) {
            drawFood(graphics);
            drawSnake(graphics, x1, y1, length1, new Color(210, 115, 90));
            drawSnake(graphics, x2, y2, length2, new Color(40, 200, 150));

            graphics.setColor(Color.white);
            graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Player 1 Score: " + foodEaten1, 10, HEIGHT - 10);
            graphics.drawString("Player 2 Score: " + foodEaten2, WIDTH - metrics.stringWidth("Player 2 Score: " + foodEaten2) - 10, HEIGHT - 10);

        } else {
            if (!gameOver) {
                gameOver(graphics);
                promptForRestart();
            }
        }
    }

    private void promptForRestart() {
        int result = JOptionPane.showConfirmDialog(this, "게임을 다시 시작하시겠습니까?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void restartGame() {
        x1[0] = 0;
        y1[0] = 0;
        length1 = 5;
        direction1 = 'D';

        x2[0] = WIDTH - UNIT_SIZE;
        y2[0] = HEIGHT - UNIT_SIZE;
        length2 = 5;
        direction2 = 'U';

        foodEaten1 = 0;
        foodEaten2 = 0;
        addFood();

        running = true;
        gameOver = false;
        timer.start();
    }

    private void gameOver(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 50));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
        metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Player 1 Score: " + foodEaten1, 10, HEIGHT - 10);
        graphics.drawString("Player 2 Score: " + foodEaten2, WIDTH - metrics.stringWidth("Player 2 Score: " + foodEaten2) - 10, HEIGHT - 10);
        gameOver = true;
    }

    public void addFood() {
        foodX = random.nextInt((int)(WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        foodY = random.nextInt((int)(HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void checkHit() {
        checkSnakeCollision(x1, y1, length1);
        checkSnakeCollision(x2, y2, length2);
        checkWallCollision(x1, y1);
        checkWallCollision(x2, y2);

        if (!running) {
            timer.stop();
        }
    }

    private void checkSnakeCollision(int[] x, int[] y, int length) {
        for (int i = length; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }
    }

    private void checkWallCollision(int[] x, int[] y) {
        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
            running = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (running) {
            move();
            checkFood();
            checkHit();
        }
        repaint();
    }

    private void drawSnake(Graphics graphics, int[] x, int[] y, int length, Color color) {
        graphics.setColor(color);
        for (int i = 0; i < length; i++) {
            graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }
    }

    private void drawFood(Graphics graphics) {
        graphics.setColor(new Color(210, 115, 90));
        graphics.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);
    }


    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction1 != 'R') {
                        direction1 = 'L';
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (direction1 != 'L') {
                        direction1 = 'R';
                    }
                    break;

                case KeyEvent.VK_UP:
                    if (direction1 != 'D') {
                        direction1 = 'U';
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction1 != 'U') {
                        direction1 = 'D';
                    }
                    break;

                case KeyEvent.VK_A:
                    if (direction2 != 'R') {
                        direction2 = 'L';
                    }
                    break;

                case KeyEvent.VK_D:
                    if (direction2 != 'L') {
                        direction2 = 'R';
                    }
                    break;

                case KeyEvent.VK_W:
                    if (direction2 != 'D') {
                        direction2 = 'U';
                    }
                    break;

                case KeyEvent.VK_S:
                    if (direction2 != 'U') {
                        direction2 = 'D';
                    }
                    break;
            }
        }
    }
}
