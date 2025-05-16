import javax.swing.*;
import java.awt.*;
import java.util.HashSet;



public class GestioneDllanimazioneDegliSprite {
    private final int ROW = 31;
    private final int COLL = 28;

    private int tileSize;

    //X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    private String[] tileMap = {
            "YUUUUUUUUUUUUqwUUUUUUUUUUUUI",
            "L            lc            R",
            "L iddy idddy lc idddy iddy R",
            "LMlOOc lOOOc lc lOOOc lOOcMR",
            "L fuus fuuus fs fuuus fuus R",
            "L                          R",
            "L iddy iy iddddddy iy iddy R",
            "L fuus lc fuuyiuus lc fuus R",
            "L      lc    lc    lc      R",
            "SDDDDy lfddyOlcOiddsc iDDDDF",
            "OOOOOL liuusOfsOfuuyc ROOOOO",
            "OOOOOL lcOOOOrOOOOOlc ROOOOO",
            "OOOOOL lcOZDQOOWDEOlc ROOOOO",
            "UUUUUs fsOROOOOOOLOfs fUUUUU",
            "OOOOOO OOOROObpoOLOOO OOOOOO",
            "DDDDDy iyOROOOOOOLOiy iDDDDD",
            "OOOOOL lcOVUUUUUUXOlc ROOOOO",
            "OOOOOL lcOOOOOOOOOOlc ROOOOO",
            "OOOOOL lcOiddddddyOlc ROOOOO",
            "YUUUUs fsOfuuyiuusOfs fUUUUI",
            "L            lc            R",
            "L iddy idddy lc idddy iddy R",
            "L fuyc fuuus fs fuuus lius R",
            "LM  lc       P        lc  MR",
            "edy lc iy iddddddy iy lc idx",
            "zus fs lc fuuyiuus lc fs fuv",
            "L      lc    lc    lc      R",
            "L iddddsfddy lc iddsfddddy R",
            "L fuuuuuuuus fs fuuuuuuuus R",
            "L                          R",
            "SDDDDDDDDDDDDDDDDDDDDDDDDDDF"
    };

    private Image wall_UP_IMG;
    private Image wall_D_IMG;
    private Image wall_L_IMG;
    private Image wall_R_IMG;

    private Image wall_U_L_IMG;
    private Image wall_D_L_IMG;
    private Image wall_L_L_IMG;
    private Image wall_R_L_IMG;

    private Image wall_UR_IMG;
    private Image wall_UL_IMG;
    private Image wall_DL_IMG;
    private Image wall_DR_IMG;

    private Image wall_UR_S_IMG;
    private Image wall_UL_S_IMG;
    private Image wall_DL_S_IMG;
    private Image wall_DR_S_IMG;

    private Image wall_UR_BR_IMG;
    private Image wall_UL_BR_IMG;

    private Image wall_RL_BR_IMG;
    private Image wall_RLD_BR_IMG;

    private Image wall_LR_BR_IMG;
    private Image wall_LRD_BR_IMG;

    private Image wall_GD_R;
    private Image wall_GD_L;
    private Image wall_GD_RL;
    private Image wall_GD_RDL;
    private Image wall_GD_RR;
    private Image wall_GD_RDR;

    private Image black_wall;

    //sprites
    //RED GHOST
    private Image redGhostU1IMG;
    private Image redGhostU2IMG;
    private Image redGhostR1IMG;
    private Image redGhostR2IMG;
    private Image redGhostD1IMG;
    private Image redGhostD2IMG;
    private Image redGhostL1IMG;
    private Image redGhostL2IMG;

    //PINK GHOST
    private Image pinkGhostU1IMG;
    private Image pinkGhostU2IMG;
    private Image pinkGhostR1IMG;
    private Image pinkGhostR2IMG;
    private Image pinkGhostD1IMG;
    private Image pinkGhostD2IMG;
    private Image pinkGhostL1IMG;
    private Image pinkGhostL2IMG;

    //BLUE GHOST
    private Image blueGhostU1IMG;
    private Image blueGhostU2IMG;
    private Image blueGhostR1IMG;
    private Image blueGhostR2IMG;
    private Image blueGhostD1IMG;
    private Image blueGhostD2IMG;
    private Image blueGhostL1IMG;
    private Image blueGhostL2IMG;

    //BLUE GHOST
    private Image orangeGhostU1IMG;
    private Image orangeGhostU2IMG;
    private Image orangeGhostR1IMG;
    private Image orangeGhostR2IMG;
    private Image orangeGhostD1IMG;
    private Image orangeGhostD2IMG;
    private Image orangeGhostL1IMG;
    private Image orangeGhostL2IMG;

    private Image foodIMG;
    private Image powerFoodIMG;

    //Pacman
    public Image pacmanR1IMG;
    public Image pacmanR2IMG;
    public Image pacmanD1IMG;
    public Image pacmanD2IMG;
    public Image pacmanL1IMG;
    public Image pacmanL2IMG;
    public Image pacmanU1IMG;
    public Image pacmanU2IMG;
    public Image pacmanRRIMG;

    //Dead Pacman
    public Image pacmanD1;
    public Image pacmanD2;
    public Image pacmanD3;
    public Image pacmanD4;
    public Image pacmanD5;
    public Image pacmanD6;
    public Image pacmanD7;
    public Image pacmanD8;
    public Image pacmanD9;
    public Image pacmanD10;
    public Image pacmanD11;


    public Image scaryGhostS1;
    public Image scaryGhostS2;

    public Image gameOverBaner;
    public Image victoryBaner;

    public static HashSet<Block> walls;
    public HashSet<Block> ghosts;
    public HashSet<Block> foods;
    public Block pacmanB;

    //Animation instance
    private boolean redGhostAnimationFrame = false;
    private boolean blueGhostAnimationFrame = false;
    private boolean pinkGhostAnimationFrame = false;
    private boolean orangeGhostAnimationFrame = false;

    //Reset Game
    public boolean reset = true;

    private boolean scaryGhostAnimationFrame = false;
    public GestioneDllanimazioneDegliSprite(int tileSize) {
        this.tileSize = tileSize ;
    }

    public void LoadIMGsprite(){
        //load walls Sprits
        victoryBaner = new  ImageIcon(getClass().getResource("./Sprites/VictoryBaner/victory.png")).getImage();
        black_wall = new ImageIcon(getClass().getResource("./Sprites/Walls/Black_Wall.png")).getImage();
        gameOverBaner = new ImageIcon(getClass().getResource("./Sprites/GameOverBaner/Game_Over_Baner.png")).getImage();
        wall_UP_IMG = new ImageIcon(getClass().getResource("./Sprites/Walls/Wall_U.png")).getImage();
        wall_D_IMG = new ImageIcon(getClass().getResource("./Sprites/Walls/Wall_D.png")).getImage();
        wall_L_IMG = new ImageIcon(getClass().getResource("./Sprites/Walls/Wall_L.png")).getImage();
        wall_R_IMG = new ImageIcon(getClass().getResource("./Sprites/Walls/Wall_R.png")).getImage();

        wall_U_L_IMG = new ImageIcon(getClass().getResource("./Sprites/Walls/Wall_U_L.png")).getImage();
        wall_D_L_IMG = new ImageIcon(getClass().getResource("./Sprites/Walls/Wall_D_L.png")).getImage();
        wall_L_L_IMG = new ImageIcon(getClass().getResource("./Sprites/Walls/Wall_L_L.png")).getImage();
        wall_R_L_IMG = new ImageIcon(getClass().getResource("./Sprites/Walls/Wall_R_L.png")).getImage();

        wall_UL_IMG = new ImageIcon(getClass().getResource("./Sprites/Walls/Wall_UL_rounding.png")).getImage();
        wall_UR_IMG = new ImageIcon(getClass().getResource("./Sprites/Walls/Wall_UR_rounding.png")).getImage();
        wall_DL_IMG = new ImageIcon(getClass().getResource("./Sprites/Walls/Wall_DL_rounding.png")).getImage();
        wall_DR_IMG = new ImageIcon(getClass().getResource("./Sprites/Walls/Wall_DR_rounding.png")).getImage();

        wall_UL_S_IMG = new ImageIcon(getClass().getResource("Sprites/Walls/Wall_UL_SR.png")).getImage();
        wall_UR_S_IMG = new ImageIcon(getClass().getResource("Sprites/Walls/Wall_UR_SR.png")).getImage();
        wall_DL_S_IMG = new ImageIcon(getClass().getResource("Sprites/Walls/Wall_DL_SR.png")).getImage();
        wall_DR_S_IMG = new ImageIcon(getClass().getResource("Sprites/Walls/Wall_DR_SR.png")).getImage();

        wall_UR_BR_IMG = new ImageIcon(getClass().getResource("Sprites/Walls/Wall_UR_BR.png")).getImage();
        wall_UL_BR_IMG = new ImageIcon(getClass().getResource("Sprites/Walls/Wall_UL_BR.png")).getImage();
        wall_LR_BR_IMG = new ImageIcon(getClass().getResource("Sprites/Walls/Wall_LR_BR.png")).getImage();
        wall_LRD_BR_IMG = new ImageIcon(getClass().getResource("Sprites/Walls/Wall_LRD_BR.png")).getImage();
        wall_RL_BR_IMG = new ImageIcon(getClass().getResource("Sprites/Walls/Wall_RL_BR.png")).getImage();
        wall_RLD_BR_IMG = new ImageIcon(getClass().getResource("Sprites/Walls/Wall_RLD_BR.png")).getImage();

        wall_GD_R = new ImageIcon(getClass().getResource("Sprites/Walls/Ghost_wall_DR.png")).getImage();
        wall_GD_L = new ImageIcon(getClass().getResource("Sprites/Walls/Ghost_wall_DL.png")).getImage();

        wall_GD_RR = new ImageIcon(getClass().getResource("Sprites/Walls/Ghost_wall_RL.png")).getImage();
        wall_GD_RL = new ImageIcon(getClass().getResource("Sprites/Walls/Ghost_wall_RR.png")).getImage();

        wall_GD_RDL = new ImageIcon(getClass().getResource("Sprites/Walls/Ghost_wall_RDR.png")).getImage();
        wall_GD_RDR = new ImageIcon(getClass().getResource("Sprites/Walls/Ghost_wall_RDL.png")).getImage();

        //load Sprits

        //RED GHOST
        redGhostR1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/RedGhostSprite/RED_GHOST_R1.png")).getImage();
        redGhostR2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/RedGhostSprite/RED_GHOST_R2.png")).getImage();
        redGhostL1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/RedGhostSprite/RED_GHOST_L1.png")).getImage();
        redGhostL2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/RedGhostSprite/RED_GHOST_L2.png")).getImage();
        redGhostU1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/RedGhostSprite/RED_GHOST_U1.png")).getImage();
        redGhostU2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/RedGhostSprite/RED_GHOST_U2.png")).getImage();
        redGhostD1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/RedGhostSprite/RED_GHOST_D1.png")).getImage();
        redGhostD2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/RedGhostSprite/RED_GHOST_D2.png")).getImage();

        //PINK GHOST
        pinkGhostR1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/PinkGhostSprite/PINK_GHOST_R1.png")).getImage();
        pinkGhostR2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/PinkGhostSprite/PINK_GHOST_R2.png")).getImage();
        pinkGhostL1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/PinkGhostSprite/PINK_GHOST_L1.png")).getImage();
        pinkGhostL2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/PinkGhostSprite/PINK_GHOST_L2.png")).getImage();
        pinkGhostU1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/PinkGhostSprite/PINK_GHOST_U1.png")).getImage();
        pinkGhostU2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/PinkGhostSprite/PINK_GHOST_U2.png")).getImage();
        pinkGhostD1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/PinkGhostSprite/PINK_GHOST_D1.png")).getImage();
        pinkGhostD2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/PinkGhostSprite/PINK_GHOST_D2.png")).getImage();

        //BLUE GHOST
        blueGhostR1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/BlueGhostSprite/BLUE_GHOST_R1.png")).getImage();
        blueGhostR2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/BlueGhostSprite/BLUE_GHOST_R2.png")).getImage();
        blueGhostL1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/BlueGhostSprite/BLUE_GHOST_L1.png")).getImage();
        blueGhostL2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/BlueGhostSprite/BLUE_GHOST_L2.png")).getImage();
        blueGhostU1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/BlueGhostSprite/BLUE_GHOST_U1.png")).getImage();
        blueGhostU2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/BlueGhostSprite/BLUE_GHOST_U2.png")).getImage();
        blueGhostD1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/BlueGhostSprite/BLUE_GHOST_D1.png")).getImage();
        blueGhostD2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/BlueGhostSprite/BLUE_GHOST_D2.png")).getImage();

        //ORANGE GHOST
        orangeGhostR1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/OrangeGhostSprite/ORANGE_GHOST_R1.png")).getImage();
        orangeGhostR2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/OrangeGhostSprite/ORANGE_GHOST_R2.png")).getImage();
        orangeGhostL1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/OrangeGhostSprite/ORANGE_GHOST_L1.png")).getImage();
        orangeGhostL2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/OrangeGhostSprite/ORANGE_GHOST_L2.png")).getImage();
        orangeGhostU1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/OrangeGhostSprite/ORANGE_GHOST_U1.png")).getImage();
        orangeGhostU2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/OrangeGhostSprite/ORANGE_GHOST_U2.png")).getImage();
        orangeGhostD1IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/OrangeGhostSprite/ORANGE_GHOST_D1.png")).getImage();
        orangeGhostD2IMG = new ImageIcon(getClass().getResource("./Sprites/Ghosts/OrangeGhostSprite/ORANGE_GHOST_D2.png")).getImage();

        //PACMAN
        pacmanR1IMG = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/PACMAN_R1.png")).getImage();
        pacmanR2IMG = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/PACMAN_R2.png")).getImage();
        pacmanD1IMG = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/PACMAN_D1.png")).getImage();
        pacmanD2IMG = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/PACMAN_D2.png")).getImage();
        pacmanU1IMG = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/PACMAN_U1.png")).getImage();
        pacmanU2IMG = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/PACMAN_U2.png")).getImage();
        pacmanL1IMG = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/PACMAN_L1.png")).getImage();
        pacmanL2IMG = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/PACMAN_L2.png")).getImage();
        pacmanRRIMG = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/PACMAN_RR.png")).getImage();

        //Dead Pacman
        pacmanD1 = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/deadPacman/PACMAN_Dead1.png")).getImage();
        pacmanD2 = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/deadPacman/PACMAN_Dead2.png")).getImage();
        pacmanD3 = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/deadPacman/PACMAN_Dead3.png")).getImage();
        pacmanD4 = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/deadPacman/PACMAN_Dead4.png")).getImage();
        pacmanD5 = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/deadPacman/PACMAN_Dead5.png")).getImage();
        pacmanD6 = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/deadPacman/PACMAN_Dead6.png")).getImage();
        pacmanD7 = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/deadPacman/PACMAN_Dead7.png")).getImage();
        pacmanD8 = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/deadPacman/PACMAN_Dead8.png")).getImage();
        pacmanD9 = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/deadPacman/PACMAN_Dead9.png")).getImage();
        pacmanD10 = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/deadPacman/PACMAN_Dead10.png")).getImage();
        pacmanD11 = new ImageIcon(getClass().getResource("./Sprites/PacmanSprites/deadPacman/PACMAN_Dead11.png")).getImage();

        //Scary Ghost
        scaryGhostS1 = new ImageIcon(getClass().getResource("./Sprites/Ghosts/Scary_Gost_S1.png")).getImage();
        scaryGhostS2 = new ImageIcon(getClass().getResource("./Sprites/Ghosts/Scary_Gost_S2.png")).getImage();


        foodIMG = new ImageIcon(getClass().getResource("./Sprites/Foods/SmallFood.png")).getImage();
        powerFoodIMG = new ImageIcon(getClass().getResource("./Sprites/Foods/powerFood.png")).getImage();
    }

    public void loadMap(){
        int size = 24;
        walls = new HashSet<>();
        foods = new HashSet<>();
        ghosts = new HashSet<>();

        for (int row = 0; row < ROW; row++) {
            for (int coll = 0; coll < COLL; coll++) {
                String rowC = tileMap[row];
                char tileMapChar = rowC.charAt(coll);

                int x = coll * tileSize;
                int y = row * tileSize;
                loadMapWalls();
                if (tileMapChar == 'p'){//pink ghost
                    Block ghost = new Block(pinkGhostR1IMG, x , y , tileSize , tileSize , size,  size,"p");
                    ghosts.add(ghost);
                }else if (tileMapChar == 'r'){//red ghost
                    Block gost = new Block(redGhostR1IMG, x , y , tileSize , tileSize ,size,size, "r");
                    ghosts.add(gost);
                }else if (tileMapChar == 'b'){//pink ghost
                    Block ghost = new Block(blueGhostR1IMG, x , y , tileSize, tileSize ,size,size, "b");
                    ghosts.add(ghost);
                }else if (tileMapChar == 'o'){//red ghost
                    Block gost = new Block(orangeGhostR1IMG, x , y , tileSize , tileSize ,size,size, "o");
                    ghosts.add(gost);
                }else if (tileMapChar == 'P'){//pacman
                    pacmanB = new Block(pacmanR1IMG, x , y , tileSize , tileSize,size,size, "P");
                }else if (tileMapChar == ' '){//food
                    Block food = new Block(foodIMG , x , y , tileSize , tileSize,tileSize,tileSize, " ");
                    foods.add(food);
                }else if(tileMapChar == 'M'){
                    Block food = new Block(powerFoodIMG , x , y , tileSize , tileSize , 32,32,"M");
                    foods.add(food);
                }
                //add other
            }
        }
    }


    public void loadMapWalls(){
        walls = new HashSet<>();
        for (int row = 0; row < ROW; row++) {
            for (int coll = 0; coll < COLL; coll++) {
                String rowC = tileMap[row];
                char tileMapChar = rowC.charAt(coll);

                int x = coll * tileSize;
                int y = row * tileSize;

                if (tileMapChar == 'U'){//wall
                    Block wall = new Block(wall_UP_IMG , x , y , tileSize , tileSize , tileSize,tileSize ,  "W");
                    walls.add(wall);
                } else if (tileMapChar == 'D'){//wall
                    Block wall = new Block(wall_D_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'L'){//wall
                    Block wall = new Block(wall_L_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'R'){//wall
                    Block wall = new Block(wall_R_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'Y'){//wall
                    Block wall = new Block(wall_UL_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'I'){//wall
                    Block wall = new Block(wall_UR_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'S'){//wall
                    Block wall = new Block(wall_DL_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'F'){//wall
                    Block wall = new Block(wall_DR_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'y'){//wall
                    Block wall = new Block(wall_UL_S_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'i'){//wall
                    Block wall = new Block(wall_UR_S_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 's'){//wall
                    Block wall = new Block(wall_DL_S_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'f'){//wall
                    Block wall = new Block(wall_DR_S_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'u'){//wall
                    Block wall = new Block(wall_U_L_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'd'){//wall
                    Block wall = new Block(wall_D_L_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'l'){//wall
                    Block wall = new Block(wall_L_L_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'c'){//wall
                    Block wall = new Block(wall_R_L_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'q'){//wall
                    Block wall = new Block(wall_UR_BR_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'w'){//wall
                    Block wall = new Block(wall_UL_BR_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'e'){//wall
                    Block wall = new Block(wall_LR_BR_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'z'){//wall
                    Block wall = new Block(wall_LRD_BR_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'x'){//wall
                    Block wall = new Block(wall_RL_BR_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'v'){//wall
                    Block wall = new Block(wall_RLD_BR_IMG , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }

                else if (tileMapChar == 'Q'){//wall--------------------------------
                    Block wall = new Block(wall_GD_L , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'W'){//wall
                    Block wall = new Block(wall_GD_R , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'E'){//wall
                    Block wall = new Block(wall_GD_RL , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'Z'){//wall
                    Block wall = new Block(wall_GD_RR , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'X'){//wall
                    Block wall = new Block(wall_GD_RDL , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
                else if (tileMapChar == 'V'){//wall
                    Block wall = new Block(wall_GD_RDR , x , y , tileSize , tileSize, tileSize,tileSize ,"W");
                    walls.add(wall);
                }
            }
        }
    }
    private int counter= 1;

    //Animation
    public void deadAnimationPacman(){
        switch (counter){
            case 1:
                pacmanB.sprit = pacmanD1;
                break;
            case 2:
                pacmanB.sprit = pacmanD2;
                break;
            case 3:
                pacmanB.sprit = pacmanD3;
                break;
            case 4:
                pacmanB.sprit = pacmanD4;
                break;
            case 5:
                pacmanB.sprit = pacmanD5;
                break;
            case 6:
                pacmanB.sprit = pacmanD6;
                break;
            case 7:
                pacmanB.sprit = pacmanD7;
                break;
            case 8:
                pacmanB.sprit = pacmanD8;
                break;
            case 9:
                pacmanB.sprit = pacmanD9;
                break;
            case 10:
                pacmanB.sprit = pacmanD10;
                break;
            case 11:
                pacmanB.sprit = pacmanD11;
                break;
            case 12:
                reset = false;
                counter = 1;
                break;
        }
        counter ++;
    }

    public void hideGhosts(boolean hide){
        if (hide){
            for (Block ghost : ghosts) {
                ghost.sprit = black_wall;
            }
        }else {
            for (Block ghost : ghosts) {
                changeSprite(ghost);
            }
        }
    }

    public void scaryGhostAnimation(){
        for (Block ghost : ghosts) {
            if (scaryGhostAnimationFrame) {
                ghost.sprit = scaryGhostS1;
            } else {
                ghost.sprit = scaryGhostS2;
            }
        }
        scaryGhostAnimationFrame = !scaryGhostAnimationFrame;
    }

    public void changeSprite(Block ghost){
        switch (ghost.type){
            case "p":
                if (ghost.direction == 'R'){
                    if (pinkGhostAnimationFrame) {
                        ghost.sprit = pinkGhostR1IMG;
                    } else {
                        ghost.sprit = pinkGhostR2IMG;
                    }
                }
                if (ghost.direction == 'D'){
                    if (pinkGhostAnimationFrame) {
                        ghost.sprit = pinkGhostD1IMG;
                    } else {
                        ghost.sprit = pinkGhostD2IMG;
                    }
                }
                if (ghost.direction == 'L'){
                    if (pinkGhostAnimationFrame) {
                        ghost.sprit = pinkGhostL1IMG;
                    } else {
                        ghost.sprit = pinkGhostL2IMG;
                    }
                }
                if (ghost.direction == 'U'){
                    if (pinkGhostAnimationFrame) {
                        ghost.sprit = pinkGhostU1IMG;
                    } else {
                        ghost.sprit = pinkGhostU2IMG;
                    }
                }
                pinkGhostAnimationFrame = !pinkGhostAnimationFrame;
                break;
            case "r":
                if (ghost.direction == 'R'){
                    if (redGhostAnimationFrame) {
                        ghost.sprit = redGhostR1IMG;
                    } else {
                        ghost.sprit = redGhostR2IMG;
                    }
                }
                if (ghost.direction == 'D'){
                    if (redGhostAnimationFrame) {
                        ghost.sprit = redGhostD1IMG;
                    } else {
                        ghost.sprit = redGhostD2IMG;
                    }
                }
                if (ghost.direction == 'L'){
                    if (redGhostAnimationFrame) {
                        ghost.sprit = redGhostL1IMG;
                    } else {
                        ghost.sprit = redGhostL2IMG;
                    }
                }
                if (ghost.direction == 'U'){
                    if (redGhostAnimationFrame) {
                        ghost.sprit = redGhostU1IMG;
                    } else {
                        ghost.sprit = redGhostU2IMG;
                    }
                }
                redGhostAnimationFrame = !redGhostAnimationFrame;
                break;
            case "b":
                if (ghost.direction == 'R'){
                    if (blueGhostAnimationFrame) {
                        ghost.sprit = blueGhostR1IMG;
                    } else {
                        ghost.sprit = blueGhostR2IMG;
                    }
                }
                if (ghost.direction == 'D'){
                    if (blueGhostAnimationFrame) {
                        ghost.sprit = blueGhostD1IMG;
                    } else {
                        ghost.sprit = blueGhostD2IMG;
                    }
                }
                if (ghost.direction == 'L'){
                    if (blueGhostAnimationFrame) {
                        ghost.sprit = blueGhostL1IMG;
                    } else {
                        ghost.sprit = blueGhostL2IMG;
                    }
                }
                if (ghost.direction == 'U'){
                    if (blueGhostAnimationFrame) {
                        ghost.sprit = blueGhostU1IMG;
                    } else {
                        ghost.sprit = blueGhostU2IMG;
                    }
                }
                blueGhostAnimationFrame = !blueGhostAnimationFrame;
                break;
            case "o":
                if (ghost.direction == 'R'){
                    if (orangeGhostAnimationFrame) {
                        ghost.sprit = orangeGhostR1IMG;
                    } else {
                        ghost.sprit = orangeGhostR2IMG;
                    }
                }
                if (ghost.direction == 'D'){
                    if (orangeGhostAnimationFrame) {
                        ghost.sprit = orangeGhostD1IMG;
                    } else {
                        ghost.sprit = orangeGhostD2IMG;
                    }
                }
                if (ghost.direction == 'L'){
                    if (orangeGhostAnimationFrame) {
                        ghost.sprit = orangeGhostL1IMG;
                    } else {
                        ghost.sprit = orangeGhostL2IMG;
                    }
                }
                if (ghost.direction == 'U'){
                    if (orangeGhostAnimationFrame) {
                        ghost.sprit = orangeGhostU1IMG;
                    } else {
                        ghost.sprit = orangeGhostU2IMG;
                    }
                }
                orangeGhostAnimationFrame = !orangeGhostAnimationFrame;
                break;
        }
    }

    private  int animationState = 0;
    public void  pacmanAnimation(){

        animationState = (animationState + 1) % 3; // 0, 1, 2

        if (pacmanB.direction == 'R') {
            if (animationState == 0) {
                pacmanB.sprit = pacmanR1IMG; // Закритий рот
            } else if (animationState == 1) {
                pacmanB.sprit = pacmanR2IMG; // Рот відкритий
            } else {
                pacmanB.sprit = pacmanRRIMG; // Повністю відкритий рот
            }
        }
        if (pacmanB.direction == 'D') {
            if (animationState == 0) {
                pacmanB.sprit = pacmanD1IMG; // Закритий рот
            } else if (animationState == 1) {
                pacmanB.sprit = pacmanD2IMG; // Рот відкритий
            } else {
                pacmanB.sprit = pacmanRRIMG; // Повністю відкритий рот
            }
        }
        if (pacmanB.direction == 'L') {
            if (animationState == 0) {
                pacmanB.sprit = pacmanL1IMG; // Закритий рот
            } else if (animationState == 1) {
                pacmanB.sprit = pacmanL2IMG; // Рот відкритий
            } else {
                pacmanB.sprit = pacmanRRIMG; // Повністю відкритий рот
            }
        }
        if (pacmanB.direction == 'U') {
            if (animationState == 0) {
                pacmanB.sprit = pacmanU1IMG; // Закритий рот
            } else if (animationState == 1) {
                pacmanB.sprit = pacmanU2IMG; // Рот відкритий
            } else {
                pacmanB.sprit = pacmanRRIMG; // Повністю відкритий рот
            }
        }
    }

}