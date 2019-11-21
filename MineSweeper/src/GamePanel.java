import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel {

    //rows and cols
    private int rows;
    private int cols;
    //the number of bombs;
    private int bombCount;
    //the width and height of each square
    private final int BLOCK_SIZE = 20;
    //array JLabel to save the information of each square
    private JLabel[][] labels;
    private MyButton[][] myButton;
    private final int[][] offset = {{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}};
    private ImageIcon icon = new ImageIcon("D:\\java practice\\MineSweeper\\src\\flag.png");
    private ImageIcon iconQ = new ImageIcon("D:\\java practice\\MineSweeper\\src\\qMark.jpg");
    private ImageIcon iconB = new ImageIcon("D:\\java practice\\MineSweeper\\src\\blank.jpg");

    public GamePanel(int rows, int cols){

        this.rows = rows;
        this.cols = cols;
        this.bombCount = rows * cols /10;
        this.myButton = new MyButton[rows][cols];
        this.initButtons();
        this.labels = new JLabel[rows][cols];
        this.initLabels();
        this.randomBomb();
        this.writeNumbers();

        this.setLayout(null);

    }
    //function of initialise JLabel array;
    private void initLabels(){

        for(int i = 0;i<rows;i++){

            for (int j = 0;j<cols;j++){

                JLabel tempLabel = new JLabel("",JLabel.CENTER);
                tempLabel.setBounds(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE ,BLOCK_SIZE);
                tempLabel.setBorder(BorderFactory.createLineBorder(Color.gray));
                tempLabel.setOpaque(true);
                tempLabel.setBackground(Color.lightGray);
                this.add(tempLabel);
                labels[i][j] = tempLabel;

            }

        }

    }
    //function to return a size of container
    public int[] returnSize(){

        int[] size = new int[2];
        size[0] = rows * BLOCK_SIZE + 40;
        size[1] = cols * BLOCK_SIZE + 20;
        return size;

    }
    //function to generate a bomb randomly
    private void randomBomb(){

        for(int i = 0;i<bombCount;i++){

            int rRow = (int)(Math.random() * rows);
            int rCol = (int)(Math.random() * cols);
            labels[rRow][rCol].setText("*");
            labels[rRow][rCol].setBackground(Color.RED);
            labels[rRow][rCol].setForeground(Color.darkGray);

        }

    }
    //function to initialise button
    private void initButtons(){

        for(int i = 0;i<rows;i++){

            for (int j = 0;j<cols;j++){

                MyButton mbt = new MyButton();
                mbt.setBounds(j * BLOCK_SIZE, i * BLOCK_SIZE, BLOCK_SIZE ,BLOCK_SIZE);

                mbt.setIcon(iconB);
                this.add(mbt);

                //record the position of each button
                mbt.row = i;
                mbt.col = j;
                myButton[i][j] = mbt;
                /*mbt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        open((MyButton)(e.getSource()));

                    }
                });*/
                mbt.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        /*if(e.isMetaDown()&&mbt.getIcon().equals(iconB)){

                            sign((MyButton)(e.getSource()));

                        }else{

                            open((MyButton)(e.getSource()));

                        }*/
                        if(e.getButton() == MouseEvent.BUTTON1){

                            open((MyButton)(e.getSource()));

                        }
                        if(e.getButton() == MouseEvent.BUTTON3){

                            sign((MyButton)(e.getSource()));

                        }

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

            }

        }

    }
    //judge whether the position is at outside or not
    private boolean verify(int row,int col){

        boolean result = false;
        if(row >= 0 && row < this.rows && col >= 0 && col < this.rows){

            result = true;

        }

        return  result;

    }
    //function of write number of bombs
    private void writeNumbers(){

        for(int i = 0;i<rows;i++){

            for(int j = 0;j<cols;j++){

                int countBombs = 0;
                if(labels[i][j].getText().equals("*")){

                    continue;

                }
                for(int[] off:offset){

                    int row = i + off[0];
                    int col = j + off[1];
                    if(verify(row,col)&&labels[row][col].getText().equals("*")){

                        countBombs++;

                    }

                }
                if(countBombs>0){

                    labels[i][j].setText(String.valueOf(countBombs));

                }

            }

        }

    }
    //function of click button
    private void open(MyButton mbt){

        if(!(mbt.getIcon().equals(icon) || mbt.getIcon().equals(iconQ))){

            mbt.setVisible(false);

        }
        //can also use a switch case loop
        if(labels[mbt.row][mbt.col].getText().equals("*")){

            for(int i = 0;i<rows;i++){

                for(int j = 0;j<cols;j++){

                    myButton[i][j].setVisible(false);

                }

            }
            return;

        }else if(labels[mbt.row][mbt.col].getText().equals("")){

            for(int[] off:offset){

                int row = mbt.row + off[0];
                int col = mbt.col + off[1];
                if(verify(row,col)){

                    MyButton newButton = myButton[row][col];
                    if(newButton.isVisible()){

                            open(newButton);

                    }

                }

            }

        }else {

        }

    }
    //function to sign a mine
    private void sign(MyButton mbt){

        //ImageIcon icon = new ImageIcon("D:\\java practice\\MineSweeper\\src\\flag.png");
        if(mbt.getIcon().equals(iconB)){

            icon.setImage(icon.getImage().getScaledInstance(BLOCK_SIZE,BLOCK_SIZE,Image.SCALE_DEFAULT));
            mbt.setIcon(icon);

        }else if(mbt.getIcon().equals(icon)){

            iconQ.setImage(iconQ.getImage().getScaledInstance(BLOCK_SIZE-8,BLOCK_SIZE-8,Image.SCALE_DEFAULT));
            mbt.setIcon(iconQ);

        }else{

            iconB.setImage(iconB.getImage().getScaledInstance(BLOCK_SIZE,BLOCK_SIZE,Image.SCALE_DEFAULT));
            mbt.setIcon(iconB);

        }

    }




}
