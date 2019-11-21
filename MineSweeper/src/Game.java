import javax.swing.*;
import java.awt.*;

public class Game {

    public static void main(String[] args){

        JFrame gameFrame = new JFrame();
        GamePanel gamePanel = new GamePanel(20,20);
        int[] frameSize = gamePanel.returnSize();
        gameFrame.setSize(frameSize[0],frameSize[1]);
        Container gameContainer = gameFrame.getContentPane();
        gameContainer.add(gamePanel);
        gameFrame.setTitle("MineSweeper");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameFrame.setVisible(true);
        MyButton mbt = new MyButton();
        ImageIcon icon = new ImageIcon("D:\\java practice\\MineSweeper\\src\\blank.jpg");
        mbt.setIcon(icon);
        System.out.println(mbt.getIcon().toString());
        //System.out.println(mbt.getText());

    }

}
