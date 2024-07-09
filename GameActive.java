import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameActive extends JPanel implements KeyListener, ActionListener {
    
    private boolean play = false;
    private int scoreboard = 0;
    private int totalbricks = 21;
    private Timer Timer;
    private int delay = 8;
    private int playerX = 310;
    private int X_pos = 120;
    private int Y_pos = 350;
    private int X_move = -1;
    private int Y_move = -2;
    private Grid map;

    public GameActive() {
        map = new Grid(5, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer = new Timer(delay, this);
        Timer.start();
    }
    
     public void paint(Graphics block) {
        block.setColor(Color.black);
        block.fillRect(1, 1, 692, 592);

        map.draw((Graphics2D) block);

        block.setColor(Color.WHITE);
        block.fillRect(0, 0, 3, 592);
        block.fillRect(0, 0, 692, 3);
        block.fillRect(691, 0, 3, 592);

        block.setColor(Color.white);
        block.setFont(new Font("serif", Font.BOLD, 25));
        block.drawString("" + scoreboard, 590, 30);

        block.setColor(Color.white);
        block.fillRect(playerX, 550, 100, 8);

        // Properties of ball

        block.setColor(Color.GREEN);
        block.fillOval(X_pos, Y_pos, 20, 20);

        if (Y_pos > 570) {
            play = false;
            X_move = 0;
            Y_move = 0;
            block.setColor(Color.red);
            block.setFont(new Font("serif", Font.BOLD, 30));
            block.drawString("    Game Over Score: " + scoreboard, 190, 300);

            block.setFont(new Font("serif", Font.BOLD, 30));
            block.drawString("   Press Enter to Restart", 190, 340);
        }
        if(totalbricks == 0){
            play = false;
            Y_move = -2;
            X_move = -1;
            block.setColor(Color.red);
            block.setFont(new Font("serif",Font.BOLD,30));
            block.drawString("    Game Over: "+scoreboard,190,300);

            block.setFont(new Font("serif", Font.BOLD, 30));
            block.drawString("   Press Enter to Restart", 190, 340);


        }

        block.dispose();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();

        if (play) {
            if (new Rectangle(X_pos, Y_pos, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                Y_move = -Y_move;
            }

            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.BW + 80;
                        int brickY = i * map.BH + 50;
                        int BW = map.BW;
                        int BH = map.BH;

                        Rectangle rect = new Rectangle(brickX, brickY, BW, BH);
                        Rectangle ballrect = new Rectangle(X_pos, Y_pos, 20, 20);
                        Rectangle brickrect = rect;

                        if (ballrect.intersects(brickrect)) {
                            map.setBricksValue(0, i, j);
                            totalbricks--;
                            scoreboard += 5;
                            if (X_pos + 19 <= brickrect.x || X_pos + 1 >= brickrect.x + BW) {
                                X_move = -X_move;
                            } else {
                                Y_move = -Y_move;
                            }
                            break A;
                        }
                    }


                }
            }


            X_pos += X_move;
            Y_pos += Y_move;
            if (X_pos < 0) {
                X_move = -X_move;
            }
            if (Y_pos < 0) {
                Y_move = -Y_move;
            }
            if (X_pos > 670) {
                X_move = -X_move;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

       }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                X_pos = 120;
                Y_pos = 350;
                X_move = -1;
                Y_move = -2;
                scoreboard = 0;
                playerX = 310;
                totalbricks = 21;
                map = new Grid(3, 7);

                repaint();
            }
        }


        }

        public void moveRight ()
        {
            play = true;
            playerX += 20;
        }
        public void moveLeft ()
        {
            play = true;
            playerX -= 20;
        }
        
    
    
}