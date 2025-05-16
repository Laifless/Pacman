import javax.swing.*;

public class Frame {
    private static final  int HEIGHT = 600;
    private static final  int WIDTH = 600;

    public  void  drawFrame(){
        int rowCount = 31;
        int columnCount = 28;
        int tileSize = 16;
        int boardWidth = columnCount * tileSize;
        int boardHeight = rowCount * tileSize;

        JFrame frame = new JFrame("Pacman");
        PacMan pacManGame = new PacMan();

        frame.setSize(boardWidth , boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.add(pacManGame);
        frame.pack();

        pacManGame.requestFocus();
        frame.setVisible(true);
    }
}
