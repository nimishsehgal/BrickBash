// Importing the library
import javax.swing.JFrame;

public class MainCode {
    public static void main(String[] args) {
        JFrame mdl= new JFrame();
        GameActive game= new GameActive();
        mdl.setBounds(10,10,700,600);
        mdl.setTitle("BrickBreaker");
        mdl.setResizable(false);
        mdl.setVisible(true);
        mdl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mdl.add(game);
    }
}