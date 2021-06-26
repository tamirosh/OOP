package UI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import BusinessLayer.Empty;
import BusinessLayer.Enemy;
import BusinessLayer.Health;
import BusinessLayer.Monster;
import BusinessLayer.Player;
import BusinessLayer.Position;
import BusinessLayer.Tile;
import BusinessLayer.Trap;
import BusinessLayer.Wall;
import BusinessLayer.Warrior;

public class Game {
	
	public Game() {
		
	}
	

	
	public static int choosePlayer()
	{
        System.out.println("Select player:");
        System.out.println("1. Jon Snow               Health: 300/300           Attack: 30              Defense: 4              Level: 1          Experience: 0/50                Cooldown: 0/3");
        System.out.println("2. The Hound              Health: 400/400           Attack: 20              Defense: 6              Level: 1          Experience: 0/50                Cooldown: 0/5");
        System.out.println("3. Melisandre             Health: 100/100           Attack: 5               Defense: 1              Level: 1          Experience: 0/50                Mana: 75/300            Spell Power: 15");
        System.out.println("4. Thoros of Myr          Health: 250/250           Attack: 25              Defense: 4              Level: 1          Experience: 0/50                Mana: 37/150            Spell Power: 20");
        System.out.println("5. Arya Stark             Health: 150/150           Attack: 40              Defense: 2              Level: 1          Experience: 0/50                Energy: 100/100");
        System.out.println("6. Bronn                  Health: 250/250           Attack: 35              Defense: 3              Level: 1          Experience: 0/50                Energy: 100/100");
        Scanner s = new Scanner(System.in);
        int choice=s.nextInt();
        while (choice<1&&choice>6)
        {
        	System.out.println("You didn't choose a player");
        	choice=s.nextInt();
        }
        return choice;

	}
	public static List<String> convertToString(File level)
	{
		
		List <String> lines= new ArrayList<String>();
		
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(level));
			String line;
			while((line=br.readLine())!=null)
			{
				lines.add(line);
				
			}
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return lines;
	}
		
	public static void convertToTiles(List<String> lines)
	{
		int columns = lines.size();
		int rows= lines.get(0).length();
		List <Tile> tiles= new ArrayList<Tile>();
		List <Enemy> enemies= new ArrayList<Enemy>();
		char[][] levelByChar = StringToCharConverter(lines,rows,columns);
		
		
		for(int i=0; i<rows;i++)
		{
			for(int j=0; j<columns;j++)
			{
				Position p = new Position (i+1,j+1);
				
				switch (levelByChar[i][j])
				{
				case '@':
					
				case '.':
					Empty e= new Empty(p);
					tiles.add(e);
				case '#':
					Wall wall= new Wall(p);
					tiles.add(wall);
					
				case 's':
					Health hs = new Health (80);
					Monster s= new Monster (levelByChar[i][j],p,"Lannister Solider",hs,8,3,25,3);
					tiles.add(s);
					enemies.add(s);
				case 'k':
					Health hk = new Health (200);
					Monster k= new Monster (levelByChar[i][j],p,"Lannister Knight",hk,14,8,50,4);
					tiles.add(k);
					enemies.add(k);
				case 'q':
					Health hq = new Health (400);
					Monster q= new Monster (levelByChar[i][j],p,"Queen’s Guard ",hq,20,15,100,5);
					tiles.add(q);
					enemies.add(q);
				case 'z':
					Health hz = new Health (600);
					Monster z= new Monster (levelByChar[i][j],p,"Wright",hz,30,15,100,3);
					tiles.add(z);
					enemies.add(z);
				case 'b':
					Health hb = new Health (1000);
					Monster b= new Monster (levelByChar[i][j],p,"Bear-Wright",hb,75,30,250,4);
					tiles.add(b);
					enemies.add(b);
				case 'g':
					Health hg = new Health (1500);
					Monster g= new Monster (levelByChar[i][j],p,"Giant-Wright",hg,100,40,500,5);
					tiles.add(g);
					enemies.add(g);
				case 'w':
					Health hw = new Health (2000);
					Monster w= new Monster (levelByChar[i][j],p,"White Walker ",hw,150,50,1000,6);
					tiles.add(w);
					enemies.add(w);
				case 'M':
					Health hM = new Health (1000);
					Monster M= new Monster (levelByChar[i][j],p,"The Mountain",hM,60,25,500,6);
					tiles.add(M);
					enemies.add(M);
				case 'C':
					Health hC = new Health (100);
					Monster C= new Monster (levelByChar[i][j],p,"Queen Cersei ",hC,10,10,1000,1);
					tiles.add(C);
					enemies.add(C);
				case 'K':
					Health hK = new Health (5000);
					Monster K= new Monster (levelByChar[i][j],p,"Night’s King",hK,300,150,5000,8);
					tiles.add(K);
					enemies.add(K);
				case 'B':
					Health hB = new Health (1);
					Trap B= new Trap(levelByChar[i][j],p,"Bonus Trap",hB,1,1,250,1,5);
					tiles.add(B);
					enemies.add(B);
				case 'Q':
					Health hQ = new Health (250);
					Trap Q= new Trap(levelByChar[i][j],p,"Queen’s Trap ",hQ,50,10,250,3,7);
					tiles.add(Q);
					enemies.add(Q);
				case 'D':
					Health hD = new Health (500);
					Trap D= new Trap(levelByChar[i][j],p,"Death Trap",hD,100,20,250,1,10);
					tiles.add(D);
					enemies.add(D);
				}
				
			}
		}


		
		
	}



	private static char[][] StringToCharConverter(List<String> lines,int rows,int columns)
	{
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












	public static void main(final String[] args)
	{
		File dir = new File(args[0]);
		List<File> files= Arrays.stream(dir.listFiles()).filter(m -> m.getName().matches("level\\d.txt$")).collect(Collectors.toList());



		
	}

}
