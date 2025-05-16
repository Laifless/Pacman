import javax.swing.*;
import java.awt.*;

public class Block {
    private int tileSize = 16;
    public int x;
    public int y;
    public int width;
    public int height;
    public int sizeIMGwidth;
    public int sizeIMGheight;
    public Image sprit;

    public int startX;
    public int startY;

    public char direction = '\0';
    public int velocityX = 0;
    public int velocityY = 0;

    public String type;

    public Block(Image sprite ,int x ,int y , int width ,int height , int sizeIMGwidth , int sizeIMGheight, String type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprit = sprite;
        this.startX = x;
        this.startY = y;
        this.type = type;
        this.sizeIMGheight = sizeIMGheight;
        this.sizeIMGwidth = sizeIMGwidth;
    }

    void updateDirection(char direction) {
        char prevDirection = this.direction;
        this.direction = direction;
        updateVelocity();
        this.x += this.velocityX;
        this.y += this.velocityY;

        for (Block wall : GestioneDllanimazioneDegliSprite.walls) {
          if (PacMan.collision(this, wall)) {
                this.x -= this.velocityX;
                this.y -= this.velocityY;
                this.direction = prevDirection;
                updateVelocity();
            }
        }
    }

    public void updateVelocity(){
        if (this.direction == 'U'){
            this.velocityX = 0;
            this.velocityY = -tileSize/4;
        }else if (this.direction == 'D'){
            this.velocityX = 0;
            this.velocityY = tileSize/4;
        }else if (this.direction == 'R'){
            this.velocityX = tileSize/4;
            this.velocityY = 0;
        }else if (this.direction == 'L'){
            this.velocityX = -tileSize/4;
            this.velocityY = 0;
        }
    }

    void reset(){
        this.x = this.startX;
        this.y = this.startY;
    }
}
