import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class PacMan extends JPanel implements ActionListener , KeyListener {

    private int score = 0;
    private int lives = 3;
    private boolean isCollidedByGhost = false;
    private boolean gameOver = false;
    private boolean victory = false;

    private boolean isPowerFoodActivated = false;
    private int PowerFoodTime = 100;
    private int powerFoodDurationRemaining = 0;

    private final int ROW = 31;
    private final int COLL = 28;

    private final int tileSize = 16;

    private final int boardWidth = COLL * tileSize;
    private final int boardHieght = ROW * tileSize;

    private Block pacman;

    Timer gameLoop;
    GestioneDllanimazioneDegliSprite gestione = new GestioneDllanimazioneDegliSprite(tileSize);

    char[] directions = {'U', 'D', 'L', 'R'}; //up down left right
    Random random = new Random();

    public PacMan(){
        setPreferredSize(new Dimension(boardWidth, boardHieght));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        gestione.LoadIMGsprite();
        //load Map
        gestione.loadMap();

        pacman = gestione.pacmanB;
        moveGhost();
        gameLoop = new Timer(50 ,this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        for (Block wall : gestione.walls){
            g.drawImage(wall.sprit,wall.x , wall.y , wall.width , wall.height, null);
        }
        for (Block food : gestione.foods){
            g.drawImage(food.sprit,food.x , food.y , food.width , food.height, null);
        }

        if (!gameOver ){
            if (!victory){
                drawSprites(g);
            }else {
                g.drawImage(gestione.victoryBaner , 155 , 270 , 112 , 16, null);
            }
        }else {
            g.drawImage(gestione.gameOverBaner , 168 , 270 , 112 , 16, null);
        }

        g.setColor(Color.white);
        g.setFont(new Font("MBA SLICE MONO", Font.PLAIN, 18));
        if (gameOver) {
            g.drawString("Game Over " + String.valueOf(score), tileSize / 2, tileSize / 2);
        }else if(victory){
            g.drawString("Victory" + String.valueOf(score), tileSize / 2, tileSize / 2);
        } else {
            g.drawString("x" + String.valueOf(lives) + " Score: " + String.valueOf(score), tileSize / 2, tileSize / 2);
        }
    }

    public void  drawSprites(Graphics g){
        for (Block ghost : gestione.ghosts){
            int ghostX = ghost.x - (ghost.sizeIMGwidth - ghost.width) / 2;
            int ghostY = ghost.y - (ghost.sizeIMGheight - ghost.height) / 2;
            g.drawImage(ghost.sprit, ghostX, ghostY, ghost.sizeIMGwidth, ghost.sizeIMGheight, null);
        }

        // Center the Pacman image
        int pacmanX = gestione.pacmanB.x - (pacman.sizeIMGwidth - pacman.sizeIMGwidth) / 2;
        int pacmanY = gestione.pacmanB.y - (pacman.sizeIMGheight - pacman.sizeIMGheight) / 2;
        g.drawImage(pacman.sprit, pacmanX, pacmanY, pacman.sizeIMGwidth, pacman.sizeIMGheight, null);
    }

    public void  move(){
        pacman.x += pacman.velocityX;
        pacman.y += pacman.velocityY;

        for (Block wall : gestione.walls){
            if (collision(pacman, wall)){
                pacman.x -= pacman.velocityX;
                pacman.y -= pacman.velocityY;
                break;
            }
        }
        gestione.pacmanAnimation();

        for (Block ghost : gestione.ghosts) {
            if (collision(ghost, pacman)) {
                if (!isPowerFoodActivated){
                    isCollidedByGhost = true;
                }else{
                    resetGhostPositions(ghost);
                }
            }

            if (ghost.y == tileSize*9 && ghost.direction != 'U' && ghost.direction != 'D') {
                ghost.updateDirection('U');
            }
            ghost.x += ghost.velocityX;
            ghost.y += ghost.velocityY;
            for (Block wall : gestione.walls) {
                if (collision(ghost, wall) || ghost.x <= 0 || ghost.x + ghost.width >= boardWidth) {
                    ghost.x -= ghost.velocityX;
                    ghost.y -= ghost.velocityY;
                    char newDirection = directions[random.nextInt(4)];
                    ghost.updateDirection(newDirection);
                }
            }
            if (isPowerFoodActivated){
                gestione.scaryGhostAnimation();
            }else {
                gestione.changeSprite(ghost);
            }
        }
        foodCollision();
    }

    public void foodCollision() { // Aggiunta food collision
        Block foodEaten = null;
        for (Block food : gestione.foods) {
            if (collision(pacman, food)) {
                if (food.type.equals("M")){
                    foodEaten = food;
                    score += 200;
                    isPowerFoodActivated = true;
                    powerFoodDurationRemaining = PowerFoodTime;
                }else {
                    foodEaten = food;
                    score += 10;
                }
            }
        }

        gestione.foods.remove(foodEaten);

        if (gestione.foods.isEmpty()) {
            victory = true;
            System.out.println("VICTORY!");
            gestione.hideGhosts(true);
        }
    }

    public void moveGhost(){
        for (Block ghost : gestione.ghosts) {
            char newDirection = directions[random.nextInt(4)];
            ghost.updateDirection(newDirection);
        }
    }

    public static boolean collision(Block a, Block b) {
        return  a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y;
    }

    public void resetPositions() {
        pacman.reset();
        pacman.velocityX = 0;
        pacman.velocityY = 0;
        for (Block ghost : gestione.ghosts) {
            ghost.reset();
            char newDirection = directions[random.nextInt(4)];
            ghost.updateDirection(newDirection);
        }
    }

    public void resetGhostPositions(Block ghost) {
        ghost.reset();
        char newDirection = directions[random.nextInt(4)];
        ghost.updateDirection(newDirection);
    }

    public void resetGameAfterDefeat(){
        if (gestione.reset){
            gestione.hideGhosts(true);
            gestione.deadAnimationPacman();
        }else{
            gestione.hideGhosts(false);
            lives -= 1;
            gestione.reset = true;
            isCollidedByGhost = false;
            if (lives == 0){
                gameOver = true;
                System.out.println("GAME OVER");
            }else {
                resetPositions();
            }
        }
    }

    //GameLoop
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver){
            if (!victory){
                if (isCollidedByGhost){
                    resetGameAfterDefeat();
                }else {
                    move();
                    powerFoodEfect();
                }
            }
        }
        repaint();
    }

    public void powerFoodEfect(){
        if (isPowerFoodActivated) {
            powerFoodDurationRemaining--;
            if (powerFoodDurationRemaining <= 0) {
                isPowerFoodActivated = false;
            }
        }
    }

    //Game Controls
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
            pacman.updateDirection('U');
        }else
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            pacman.updateDirection('D');

        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            pacman.updateDirection('R');
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            pacman.updateDirection('L');
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}