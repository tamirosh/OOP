package UI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import BusinessLayer.*;

public class Game {

    public Game() {

    }

	/*public static List<String> convertToString(File level) {
		List <String> lines= new ArrayList<String>();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(level));
			String line;
			while((line=br.readLine())!=null)
			{
				lines.add(line);
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return lines;
	}*/

    public static GameManager convertToTiles(List<String> lines, Player player) {
        int rows = lines.size();
        int columns = lines.get(0).length();
        List <Tile> tiles = new ArrayList<Tile>();
        List <Enemy> enemies = new ArrayList<Enemy>();
        char[][] levelByChar = StringToCharConverter(lines,rows,columns);
        for(int i=0; i<rows;i++)
        {
            for(int j=0; j<columns;j++) {
                Position p = new Position(i + 1, j + 1);
                char charToCheck = levelByChar[i][j];
                switch (charToCheck) {
                    case '@':
                        player.setPosition(p);
                        tiles.add(player);
                        break;
                    case '.':
                        Empty e = new Empty(p);
                        tiles.add(e);
                        break;
                    case '#':
                        Wall wall = new Wall(p);
                        tiles.add(wall);
                        break;
                    case 's':
                        Health hs = new Health(80);
                        Monster s = new Monster(levelByChar[i][j], p, "Lannister Solider", hs, 8, 3, 25, 3);
                        tiles.add(s);
                        enemies.add(s);
                        break;
                    case 'k':
                        Health hk = new Health(200);
                        Monster k = new Monster(levelByChar[i][j], p, "Lannister Knight", hk, 14, 8, 50, 4);
                        tiles.add(k);
                        enemies.add(k);
                        break;
                    case 'q':
                        Health hq = new Health(400);
                        Monster q = new Monster(levelByChar[i][j], p, "Queen’s Guard ", hq, 20, 15, 100, 5);
                        tiles.add(q);
                        enemies.add(q);
                        break;
                    case 'z':
                        Health hz = new Health(600);
                        Monster z = new Monster(levelByChar[i][j], p, "Wright", hz, 30, 15, 100, 3);
                        tiles.add(z);
                        enemies.add(z);
                        break;
                    case 'b':
                        Health hb = new Health(1000);
                        Monster b = new Monster(levelByChar[i][j], p, "Bear-Wright", hb, 75, 30, 250, 4);
                        tiles.add(b);
                        enemies.add(b);
                        break;
                    case 'g':
                        Health hg = new Health(1500);
                        Monster g = new Monster(levelByChar[i][j], p, "Giant-Wright", hg, 100, 40, 500, 5);
                        tiles.add(g);
                        enemies.add(g);
                        break;
                    case 'w':
                        Health hw = new Health(2000);
                        Monster w = new Monster(levelByChar[i][j], p, "White Walker ", hw, 150, 50, 1000, 6);
                        tiles.add(w);
                        enemies.add(w);
                        break;
                    case 'M':
                        Health hM = new Health(1000);
                        Monster M = new Monster(levelByChar[i][j], p, "The Mountain", hM, 60, 25, 500, 6);
                        tiles.add(M);
                        enemies.add(M);
                        break;
                    case 'C':
                        Health hC = new Health(100);
                        Monster C = new Monster(levelByChar[i][j], p, "Queen Cersei ", hC, 10, 10, 1000, 1);
                        tiles.add(C);
                        enemies.add(C);
                        break;
                    case 'K':
                        Health hK = new Health(5000);
                        Monster K = new Monster(levelByChar[i][j], p, "Night’s King", hK, 300, 150, 5000, 8);
                        tiles.add(K);
                        enemies.add(K);
                        break;
                    case 'B':
                        Health hB = new Health(1);
                        Trap B = new Trap(levelByChar[i][j], p, "Bonus Trap", hB, 1, 1, 250, 1, 5);
                        tiles.add(B);
                        enemies.add(B);
                        break;
                    case 'Q':
                        Health hQ = new Health(250);
                        Trap Q = new Trap(levelByChar[i][j], p, "Queen’s Trap ", hQ, 50, 10, 250, 3, 7);
                        tiles.add(Q);
                        enemies.add(Q);
                        break;
                    case 'D':
                        Health hD = new Health(500);
                        Trap D = new Trap(levelByChar[i][j], p, "Death Trap", hD, 100, 20, 250, 1, 10);
                        tiles.add(D);
                        enemies.add(D);
                        break;
                }
            }
        }
        return new GameManager(player, rows, columns, tiles, enemies);
    }

    private static char[][] StringToCharConverter(List<String> lines,int rows,int columns) {
        char [][] chars = new char [rows][columns] ;
        for(int i=0; i< rows;i++)
        {
            for(int j=0; j<columns; j++)
            {
                chars[i][j]= lines.get(i).charAt(j);
            }
        }
        return chars;
    }

    public static void main(final String[] args) {
        //File dir = new File(args[0]);
        //File f=new File("./"+args[0]);
        //List<File> files= Arrays.stream(dir.listFiles()).filter(m -> m.getName().matches("level\\d.txt$")).collect(Collectors.toList());
        String[] pathNames;
        File f=new File("./"+args[0]);
        pathNames=f.list();
        if (pathNames == null || pathNames.length == 0)
            System.out.println("no files uploaded. you need to upluad level files to playing.");
        else
        {
            List<List<String>> levels = new LinkedList<List<String>>();
            for (String levelPath : pathNames) {
                List<String> levelData;
                try {
                    levelData = Files.readAllLines(Paths.get("./" + args[0] + "/" + levelPath , new String[0]));
                    levels.add(levelData);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Scanner sc= new Scanner(System.in);
            System.out.println("Select player:");
            System.out.println("Warriors:");
            System.out.println("1. Jon Snow               Health: 300/300           Attack: 30              Defense: 4              Level: 1          Experience: 0/50                Cooldown: 0/3");
            System.out.println("2. The Hound              Health: 400/400           Attack: 20              Defense: 6              Level: 1          Experience: 0/50                Cooldown: 0/5");
            System.out.println("Mages:");
            System.out.println("3. Melisandre             Health: 100/100           Attack: 5               Defense: 1              Level: 1          Experience: 0/50                Mana: 75/300            Spell Power: 15");
            System.out.println("4. Thoros of Myr          Health: 250/250           Attack: 25              Defense: 4              Level: 1          Experience: 0/50                Mana: 37/150            Spell Power: 20");
            System.out.println("Rogues:");
            System.out.println("5. Arya Stark             Health: 150/150           Attack: 40              Defense: 2              Level: 1          Experience: 0/50                Energy: 100/100");
            System.out.println("6. Bronn                  Health: 250/250           Attack: 35              Defense: 3              Level: 1          Experience: 0/50                Energy: 100/100");
            System.out.println("Hunters:");
            System.out.println("7. Ygritte             Health: 220/220           Attack: 30              Defense: 2              Level: 1          Experience: 0/50                Energy: 100/100");
            int choise = sc.nextInt();
            while (choise < 1 || choise > 7)
            {
                System.out.println("You didn't choose a player. choose again nuber between 1-6.");
                choise = sc.nextInt();
            }
            Player player = null;
            switch(choise)
            {
                case 1:
                    player = new Warrior(null, "Jon Snow", new Health(300), 30, 4, 3);
                    break;
                case 2:
                    player = new Warrior(null, "The Hound", new Health(400), 20, 6, 5);
                    break;
                case 3:
                    player = new Mage(null, "Melisandre", new Health(100), 5, 1, 300, 30, 15, 5, 6);
                    break;
                case 4:
                    player = new Mage(null, "Thoros of Myr", new Health(100), 5, 1, 300, 30, 15, 5, 6);
                    break;
                case 5:
                    player = new Rogue(null, "Arya Stark", new Health(150), 40, 2, 20);
                    break;
                case 6:
                    player = new Rogue(null, "Bronn", new Health(250), 35, 3, 50);
                    break;
                case 7:
                    player = new Hunter(null, "Ygritte", new Health(220), 30, 20, 6);
                    break;
            }
            int level = 0;
            GameManager gameManager = convertToTiles(levels.get(level), player);
            System.out.println(gameManager.printBoard());
            while (level < levels.size() && player.getHealthAmount() > 0)
            {
                if (gameManager.enemiesNum() == 0)
                {
                    gameManager = convertToTiles(levels.get(level), player);
                    System.out.println(gameManager.printBoard());
                }
                else
                {
                    String answer = sc.nextLine();
                    switch (answer)
                    {
                        case "w":
                            System.out.println(gameManager.turn(Turns.w));
                            break;
                        case "a":
                            System.out.println(gameManager.turn(Turns.a));
                            break;
                        case "s":
                            System.out.println(gameManager.turn(Turns.s));
                            break;
                        case "d":
                            System.out.println(gameManager.turn(Turns.d));
                            break;
                        case "e":
                            System.out.println(gameManager.turn(Turns.e));
                            break;
                        case "q":
                            System.out.println(gameManager.turn(Turns.q));
                            break;
                    }
                    if (gameManager.enemiesNum() == 0) {
                        level++;
                    }
                }
            }
            System.out.println();
            System.out.println("The game ended. thank you for your playing ;)" + "\n");
        }
    }

}