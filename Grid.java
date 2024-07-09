import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Grid {
    public int map[][];
    public int BW;
    public int BH;
    public Grid(int row , int column){
        map = new int[row][column];
         for (int[] map1 : map) {
             for (int j = 0; j < map[0].length; j++) {
                 map1[j] = 1;
             }
         }
        BW = 540/column;
        BH = 150/row;
    }
    public void draw(Graphics2D block) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    block.setColor(Color.red);
                    block.fillRect(j * BW + 80, i * BH + 50, BW, BH);

                    block.setStroke(new BasicStroke(3));
                    block.setColor(Color.black);
                    block.drawRect(j * BW + 80, i * BH + 50, BW, BH);

                }
            }

        }
    }
    public void setBricksValue(int value,int row,int column)
    {
        map[row][column] = value;
    }
}