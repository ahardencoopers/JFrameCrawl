package com.example;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.lang.System;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JFrameCrawl extends JFrame implements Runnable, MouseListener, KeyListener {
	
	public static final int WIDTH = 800; //Constante del ancho del JFrame
	public static final int HEIGHT = 600; //Constante de la altura del JFrame
	public static final int BORDESUP = 31; //Constante de la altura del JFrame
	public static final int BORDEINF = 8; //Constante de la altura del JFrame
	public static final double TIEMPO = 0.016; //Constante de tiempo
	
	//Scrolling
	public static final int WORLD_SIZE_X = 5000;
	public static final int WORLD_SIZE_Y = 5000;
	public static final int VIEWPORT_SIZE_X = 800;
	public static final int VIEWPORT_SIZE_Y = 600;
	
	private int offsetMaxX;
	private int offsetMaxY;
	private int offsetMinX;
	private int offsetMinY;
	
	private int camX;
	private int camY;
	
	private int nivelActual = 3;
	
	
	public static final int tamTile = 30; //Constante de tiempo
	
	
	private Image dbImage; 
	private Graphics dbg; 
	
	private String nombreNivel; //string donde se guarda el nombre del nivel
	
	char [][]mapa = new char[1][1];
	private int col;
	private int ren;
	
	Image messageLog = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/messageLog.png"));
	Image hudLog = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/hudLog.png"));
	
	Image tileEscaleraArriba = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEscaleraArriba.png"));
	Image tileEscaleraAbajo = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEscaleraAbajo.png"));
	
	Image tileEnemigo1Tipo1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo1Tipo1.png"));
	Image tileEnemigo2Tipo1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo2Tipo1.png"));
	Image tileEnemigo3Tipo1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo3Tipo1.png"));
	Image tileEnemigo4Tipo1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo4Tipo1.png"));
	
	Image tileEnemigo1Tipo2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo1Tipo2.png"));
	Image tileEnemigo2Tipo2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo2Tipo2.png"));
	Image tileEnemigo3Tipo2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo3Tipo2.png"));
	Image tileEnemigo4Tipo2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo4Tipo2.png"));
	
	Image tileEnemigo1Tipo3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo1Tipo3.png"));
	Image tileEnemigo2Tipo3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo2Tipo3.png"));
	Image tileEnemigo3Tipo3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo3Tipo3.png"));
	Image tileEnemigo4Tipo3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo4Tipo3.png"));
	
	Image tileEnemigo1Tipo4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo1Tipo4.png"));
	Image tileEnemigo2Tipo4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo2Tipo4.png"));
	Image tileEnemigo3Tipo4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo3Tipo4.png"));
	Image tileEnemigo4Tipo4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo4Tipo4.png"));
	
	Image tileEnemigo1Tipo5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo1Tipo5.png"));
	Image tileEnemigo2Tipo5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo2Tipo5.png"));
	Image tileEnemigo3Tipo5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo3Tipo5.png"));
	Image tileEnemigo4Tipo5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo4Tipo5.png"));
		
	Image tileEnemigo1Tipo6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo1Tipo6.png"));
	Image tileEnemigo2Tipo6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo2Tipo6.png"));
	Image tileEnemigo3Tipo6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo3Tipo6.png"));
	Image tileEnemigo4Tipo6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo4Tipo6.png"));
	
	Image tileEnemigo1Tipo7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo1Tipo7.png"));
	Image tileEnemigo2Tipo7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo2Tipo7.png"));
	Image tileEnemigo3Tipo7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo3Tipo7.png"));
	Image tileEnemigo4Tipo7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo4Tipo7.png"));
	
	Image tileEnemigo1Tipo8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo1Tipo8.png"));
	Image tileEnemigo2Tipo8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo2Tipo8.png"));
	Image tileEnemigo3Tipo8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo3Tipo8.png"));
	Image tileEnemigo4Tipo8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo4Tipo8.png"));
	
	Image tileEnemigo1Tipo9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo1Tipo9.png"));
	Image tileEnemigo2Tipo9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo2Tipo9.png"));
	Image tileEnemigo3Tipo9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo3Tipo9.png"));
	Image tileEnemigo4Tipo9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo4Tipo9.png"));
	
	Image tileEnemigo1Tipo10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo1Tipo10.png"));
	Image tileEnemigo2Tipo10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo2Tipo10.png"));
	Image tileEnemigo3Tipo10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo3Tipo10.png"));
	Image tileEnemigo4Tipo10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo4Tipo10.png"));
	
	Image tileEnemigo1Tipo11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo1Tipo11.png"));
	Image tileEnemigo2Tipo11= Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo2Tipo11.png"));
	Image tileEnemigo3Tipo11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo3Tipo11.png"));
	Image tileEnemigo4Tipo11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo4Tipo11.png"));
	
	Image tileEnemigo1Tipo12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo1Tipo12.png"));
	Image tileEnemigo2Tipo12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo2Tipo12.png"));
	Image tileEnemigo3Tipo12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo3Tipo12.png"));
	Image tileEnemigo4Tipo12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileEnemigo4Tipo12.png"));
	
	Image tilePiso = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tilePiso.png"));
	Image tilePared1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tilePared1.png"));
	Image tilePared2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tilePared2.png"));
	Image tileVacio = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileVacio.png"));
	Image tileJugador1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileJugador1.png"));
	Image tileJugador2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileJugador2.png"));
	Image tileJugador3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileJugador3.png"));
	Image tileJugador4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileJugador4.png"));
	Image tileAtaque1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileAtaque1.png"));
	Image tileAtaque2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileAtaque2.png"));
	Image tileAtaque3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileAtaque3.png"));
	Image tileAtaque4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/tileAtaque4.png"));
	Image tileAtaqueDireccion;
	
	private int messageTimer = 0;
	
	private int auxAtaqueDireccionX;
	private int auxAtaqueDireccionY;
	
	private Jugador jugador;
	
	private int nivelStrength;
	private int nivelDefense;
	private int nivelSpeed;
	
	
	private boolean presionaIzquierda;
	private boolean presionaDerecha;
	private boolean presionaArriba;
	private boolean presionaAbajo;
	private boolean presionaAtaque;
	private boolean actualizoJugador;
	
	private int recoilAtaque;
	
	private boolean muerto;
	private boolean escaleras;
	
	private LinkedList<Monstruo> monstruos;
	private LinkedList<String> messages;
	
	public void init(){
		
		addMouseListener(this);
		addKeyListener(this);
		
		setSize(790,735); 
		setBackground(Color.black);
		
		nombreNivel = "nivel3.txt";
		ren = 1;
		col = 0;
		
		offsetMaxX = WORLD_SIZE_X - VIEWPORT_SIZE_X;
		offsetMaxY = WORLD_SIZE_Y - VIEWPORT_SIZE_Y;
		offsetMinX = 0;
		offsetMinY = 0;
		
		recoilAtaque = 0;
		
		monstruos = new LinkedList<Monstruo>();
		messages = new LinkedList<String>();
		
		actualizoJugador = false;
		
		
		
	}
	
	public void actualizaMessages()
	{
		if(messageTimer <= 0)
		{
			messageTimer = 5;
		}
		else
		{
			messageTimer ++;
			if(messageTimer >= 5)
			{
				if(messages.size() != 0)
				{
					messages.pop();
				}
				messageTimer = 0;
			}
		}
		
		
	}
	
	public void reset(String nomNivel)
	{
		monstruos = null;
		jugador = null; 
		mapa = null;
		nombreNivel = nomNivel;
		
		try	
		{
			leeNivel();
		}
		catch (IOException e)
		{
			System.out.println("Error al cargar nivel +");
		}
	}
	
	public void rollCombat(int indiceEnemigo, boolean ataque)
	{
		Monstruo auxMonstruo = monstruos.get(indiceEnemigo);
		
		if(ataque)
		{
			//Dodge part
			int rollDodge = randomWithRange(1, 12);
			if(rollDodge >= 12-auxMonstruo.getDefense())
			{
				messages.add("The enemy dodged your attack");
				System.out.println("The enemy dodged your attack");
				return;
			}
			else
			{
				//Damage part
				int rollAtaque = randomWithRange(1, 6);
				if(rollAtaque == 1)
				{
					messages.add("Your attack missed");
					System.out.println("Your attack missed");
					return;
				}
				else if(rollAtaque == 2)
				{
					messages.add("You landa a weak attack for " + jugador.getWeapon() + " damage");
					System.out.println("You landa a weak attack for " + jugador.getWeapon() + " damage");
					auxMonstruo.setVida(auxMonstruo.getVida()-jugador.getWeapon());
				}
				else if(  3 <= rollAtaque &&  rollAtaque <= 5 )
				{
					messages.add("You landa a strong attack for " + jugador.getWeapon()+jugador.getStrength()/2 + " damage");
					System.out.println("You landa a strong attack for " + jugador.getWeapon()+jugador.getStrength()/2 + " damage");
					auxMonstruo.setVida(auxMonstruo.getVida()-(jugador.getWeapon()+jugador.getStrength()/2));
				}
				else if( rollAtaque == 6)
				{
					messages.add("You landa a critical hit! " + jugador.getWeapon()+jugador.getStrength() + " damage");
					System.out.println("You landa a critical hit! " + jugador.getWeapon()+jugador.getStrength() + " damage");
					auxMonstruo.setVida(auxMonstruo.getVida()-(jugador.getWeapon()+jugador.getStrength()));
					return;
				}
				
				//Absorb part
				int rollDefense = randomWithRange(1, 12);
				if(rollDefense >= 12-auxMonstruo.getDefense())
				{
					messages.add("The enemy defended for " + auxMonstruo.getArmor() + " hit point");
					System.out.println("The enemy defended for " + auxMonstruo.getArmor() + " hit point");
					auxMonstruo.setVida(auxMonstruo.getVida()+auxMonstruo.getArmor());
				}
				else
				{
					messages.add("The enemy failed to defend!");
					System.out.println("The enemy failed to defend!");
					return;
				}
			}
		}
		else
		{
			//Dodge part
			int rollDodge = randomWithRange(1, 12);
			if(rollDodge >= 12-jugador.getDefense())
			{
				messages.add("You dodged the enemy attack!");
				System.out.println("You dodged the enemy attack!");
				return;
			}
			else
			{
				//Damage part
				int rollAtaque = randomWithRange(1, 6);
				if(rollAtaque == 1)
				{
					messages.add("The enemy attack missed");
					System.out.println("The enemy attack missed");
					return;
				}
				else if(rollAtaque == 2)
				{
					messages.add("The enemy landed a weak attack for " + auxMonstruo.getWeapon() + " damage");
					System.out.println("The enemy landed a weak attack for " + auxMonstruo.getWeapon() + " damage");
					jugador.setVida(jugador.getVida()-auxMonstruo.getWeapon());
				}
				else if(  3 <= rollAtaque &&  rollAtaque <= 5 )
				{
					messages.add("The enemy landed a strong attack for " + auxMonstruo.getWeapon()+auxMonstruo.getStrength()/2 + " damage");
					System.out.println("The enemy landed a strong attack for " + auxMonstruo.getWeapon()+auxMonstruo.getStrength()/2 + " damage");
					jugador.setVida(jugador.getVida()-(auxMonstruo.getWeapon()+auxMonstruo.getStrength()/2));
				}
				else if( rollAtaque == 6)
				{
					messages.add("Then enemy landed a critical hit! " + auxMonstruo.getWeapon()+auxMonstruo.getStrength() + " damage");
					System.out.println("Then enemy landed a critical hit! " + auxMonstruo.getWeapon()+auxMonstruo.getStrength() + " damage");
					jugador.setVida(jugador.getVida()-(auxMonstruo.getWeapon()+auxMonstruo.getStrength()));
					return;
				}
				
				//Absorb part
				int rollDefense = randomWithRange(1, 12);
				if(rollDefense >= 12-auxMonstruo.getDefense())
				{
					messages.add("You manage to defended " + jugador.getArmor() + " hit point");
					System.out.println("You manage to defended " + jugador.getArmor() + " hit point");
					jugador.setVida(jugador.getVida()+jugador.getArmor());
				}
				else
				{
					messages.add("You failed to defend...");
					System.out.println("You failed to defend...");
					return;
				}
			}
		}
	}
	
	int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
	
	
	
	public void leeNivel() throws IOException 
	{
		if(monstruos == null)
		{
			monstruos = new LinkedList<Monstruo>();
		}
		char currentChar = 0;  //This will store the current character
		char currentChar2 = 0;  //This will store the current character
		
		
		
		//Create file reader
		FileReader inputStream = new FileReader(nombreNivel);	
		while (currentChar != (char)-1)  // This is key!
		{
			currentChar = (char)inputStream.read(); // Cast to a char
			if(currentChar == '\n')
			{
				ren++;
				col = col -2;
				//System.out.println("enter");
			}
		
		col++;
		
		}
		col = col -1;
		col = col/ren;
		//System.out.println(ren + " " + col);
	
		mapa = new char[ren][col];
		
		
		FileReader inputStream2 = new FileReader(nombreNivel);
		
		int auxI = 0;
		int auxJ = 0;
				
		while (currentChar2 != (char)-1)  // This is key!
		{
			currentChar2 = (char)inputStream2.read(); // Cast to a char
			
			if(currentChar2 == '\n')
			{
				auxI++;
				auxJ = 0;
				currentChar2 = (char)inputStream2.read();
			}
			
			if(currentChar2 == '@')
			{
				jugador = new Jugador(0, 0, tileJugador1, auxI, auxJ);
//				System.out.println("cree jugador");
//				System.out.println(jugador.getPosRen() + " " + jugador.getPosCol());
//				System.out.println(jugador.getPosX() + " " + jugador.getPosY());
				jugador.setVida(100);
				jugador.setStrength(2);
				jugador.setSpeed(2);
				jugador.setDefense(3);
				jugador.setWeapon(2);
				jugador.setArmor(2);
			}
			
			if(currentChar2 == 'X')
			{
				
				Monstruo auxMonstruo;
				auxMonstruo = new Monstruo(0, 0, tileEnemigo4Tipo7, auxI, auxJ);
				
				int aux = randomWithRange(1, 12);
				if(aux == 1)
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo1);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(1);
					auxMonstruo.setVida(8);
					auxMonstruo.setStrength(2);
					auxMonstruo.setSpeed(3);
					auxMonstruo.setDefense(1);
					auxMonstruo.setWeapon(2);
					auxMonstruo.setArmor(1);
				}
				else if(aux == 2)
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo2);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(2);
					auxMonstruo.setVida(15);
					auxMonstruo.setStrength(1);
					auxMonstruo.setSpeed(1);
					auxMonstruo.setDefense(4);
					auxMonstruo.setWeapon(1);
					auxMonstruo.setArmor(1);
				}	
				else if(aux == 3)
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo3);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(3);
					auxMonstruo.setVida(5);
					auxMonstruo.setStrength(2);
					auxMonstruo.setSpeed(3);
					auxMonstruo.setDefense(1);
					auxMonstruo.setWeapon(1);
					auxMonstruo.setArmor(1);
				}
				else if(aux == 4)
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo4);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(4);
					auxMonstruo.setVida(10);
					auxMonstruo.setStrength(2);
					auxMonstruo.setSpeed(1);
					auxMonstruo.setDefense(1);
					auxMonstruo.setWeapon(1);
					auxMonstruo.setArmor(3);
				}
				else if(aux == 5)
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo5);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(5);
					auxMonstruo.setVida(20);
					auxMonstruo.setStrength(1);
					auxMonstruo.setSpeed(1);
					auxMonstruo.setDefense(2);
					auxMonstruo.setWeapon(2);
					auxMonstruo.setArmor(5);
				}
				else if(aux == 6)
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo6);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(6);
					auxMonstruo.setVida(10);
					auxMonstruo.setStrength(2);
					auxMonstruo.setSpeed(3);
					auxMonstruo.setDefense(2);
					auxMonstruo.setWeapon(1);
					auxMonstruo.setArmor(1);
				}
				else if(aux == 7)
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo7);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(7);
					auxMonstruo.setVida(8);
					auxMonstruo.setStrength(1);
					auxMonstruo.setSpeed(3);
					auxMonstruo.setDefense(1);
					auxMonstruo.setWeapon(1);
					auxMonstruo.setArmor(1);
				}
				else if(aux == 8)
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo8);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(8);
					auxMonstruo.setVida(2);
					auxMonstruo.setStrength(6);
					auxMonstruo.setSpeed(6);
					auxMonstruo.setDefense(1);
					auxMonstruo.setWeapon(1);
					auxMonstruo.setArmor(1);
				}
				else if(aux == 9)
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo9);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(9);
					auxMonstruo.setVida(1);
					auxMonstruo.setStrength(20);
					auxMonstruo.setSpeed(1);
					auxMonstruo.setDefense(1);
					auxMonstruo.setWeapon(20);
					auxMonstruo.setArmor(1);
				}
				else if(aux == 10)
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo10);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(10);
					auxMonstruo.setVida(8);
					auxMonstruo.setStrength(1);
					auxMonstruo.setSpeed(3);
					auxMonstruo.setDefense(1);
					auxMonstruo.setWeapon(1);
					auxMonstruo.setArmor(1);
				}
				else if(aux == 11)
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo11);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(11);
					auxMonstruo.setVida(6);
					auxMonstruo.setStrength(3);
					auxMonstruo.setSpeed(1);
					auxMonstruo.setDefense(4);
					auxMonstruo.setWeapon(2);
					auxMonstruo.setArmor(2);
				}
				else if(aux == 12)
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo12);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(12);
					auxMonstruo.setVida(8);
					auxMonstruo.setStrength(1);
					auxMonstruo.setSpeed(9);
					auxMonstruo.setDefense(1);
					auxMonstruo.setWeapon(1);
					auxMonstruo.setArmor(1);
				}
				else
				{
					ImageIcon auxImageIcon = new ImageIcon(tileEnemigo4Tipo4);
					auxMonstruo.setImageIcon(auxImageIcon);
					auxMonstruo.setTipoMonstruo(4);
					auxMonstruo.setVida(8);
					auxMonstruo.setStrength(1);
					auxMonstruo.setSpeed(3);
					auxMonstruo.setDefense(1);
					auxMonstruo.setWeapon(1);
					auxMonstruo.setArmor(1);
				}
				
				//System.out.println("Cree monstruo " + " en " + auxMonstruo.getPosRen() + " " + auxMonstruo.getPosCol());
				monstruos.add(auxMonstruo);
				//System.out.println("Tam" + monstruos.size());
				
				
			}
			
			if(auxJ < col)
			{
				//System.out.println(auxI + " " + auxJ + " " + currentChar2);
				mapa[auxI][auxJ] = currentChar2;
			}
			
			auxJ++;
		}
	}
	
	public JFrameCrawl() {
		
		init();
		try {
			leeNivel();
		}
		catch (IOException e){
			System.out.println("Error al cargar nivel +");
		}
		
		// Dezzzclaras un hilo
		Thread th = new Thread(this);
		// Empieza el hilo
		th.start();
		
	}
	
	public void orientaJugador()
	{
		if(jugador.getOrientacion() == 1)
		{
			ImageIcon auxIcon;
			auxIcon = new ImageIcon(tileJugador1);
			jugador.setImageIcon(auxIcon);
		}
		else if(jugador.getOrientacion() == 2)
		{
			ImageIcon auxIcon;
			auxIcon = new ImageIcon(tileJugador2);
			jugador.setImageIcon(auxIcon);
		}
		else if(jugador.getOrientacion() == 3)
		{
			ImageIcon auxIcon;
			auxIcon = new ImageIcon(tileJugador3);
			jugador.setImageIcon(auxIcon);
		}
		else if(jugador.getOrientacion() == 4)
		{
			ImageIcon auxIcon;
			auxIcon = new ImageIcon(tileJugador4);
			jugador.setImageIcon(auxIcon);
		}
	}
	
	public void orientaAtaque()
	{
		if(jugador.getOrientacion() == 1)
		{
			tileAtaqueDireccion = tileAtaque1;
			auxAtaqueDireccionY = jugador.getPosY() - tamTile + BORDESUP;
			auxAtaqueDireccionX = jugador.getPosX() + BORDEINF;
		}
		else if(jugador.getOrientacion() == 2)
		{
			tileAtaqueDireccion = tileAtaque2;
			auxAtaqueDireccionY = jugador.getPosY()+BORDESUP;
			auxAtaqueDireccionX = jugador.getPosX() + tamTile + BORDEINF;
			
		}
		else if(jugador.getOrientacion() == 3)
		{
			tileAtaqueDireccion = tileAtaque3;
			auxAtaqueDireccionY = jugador.getPosY()+ tamTile + BORDESUP ;
			auxAtaqueDireccionX = jugador.getPosX() + BORDEINF  ;
		}
		else if(jugador.getOrientacion() == 4)
		{
			tileAtaqueDireccion = tileAtaque4;
			auxAtaqueDireccionY = jugador.getPosY() + BORDESUP;
			auxAtaqueDireccionX = jugador.getPosX() - tamTile + BORDEINF;
		}
	}
	
	public void orientaMonstruo()
	{
		for(int k=0; k<monstruos.size(); k++)
		{
			Monstruo auxMonstruo = monstruos.get(k);
			
			if(auxMonstruo.getTipoMonstruo() == 1)
			{
				if(auxMonstruo.getOrientacion() == 1)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo1Tipo1);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 2)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo2Tipo1);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 3)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo3Tipo1);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 4)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo4Tipo1);
					auxMonstruo.setImageIcon(auxIcon);
				}
			}
			else if(auxMonstruo.getTipoMonstruo() == 2)
			{
				if(auxMonstruo.getOrientacion() == 1)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo1Tipo2);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 2)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo2Tipo2);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 3)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo3Tipo2);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 4)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo4Tipo2);
					auxMonstruo.setImageIcon(auxIcon);
				}
			}
			else if(auxMonstruo.getTipoMonstruo() == 3)
			{
				if(auxMonstruo.getOrientacion() == 1)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo1Tipo3);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 2)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo2Tipo3);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 3)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo3Tipo3);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 4)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo4Tipo3);
					auxMonstruo.setImageIcon(auxIcon);
				}
			}
			else if(auxMonstruo.getTipoMonstruo() == 4)
			{
				if(auxMonstruo.getOrientacion() == 1)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo1Tipo4);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 2)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo2Tipo4);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 3)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo3Tipo4);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 4)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo4Tipo4);
					auxMonstruo.setImageIcon(auxIcon);
				}
			}
			else if(auxMonstruo.getTipoMonstruo() == 5)
			{
				if(auxMonstruo.getOrientacion() == 1)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo1Tipo5);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 2)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo2Tipo5);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 3)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo3Tipo5);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 4)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo4Tipo5);
					auxMonstruo.setImageIcon(auxIcon);
				}
			}
			else if(auxMonstruo.getTipoMonstruo() == 6)
			{
				if(auxMonstruo.getOrientacion() == 1)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo1Tipo6);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 2)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo2Tipo6);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 3)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo3Tipo6);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 4)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo4Tipo6);
					auxMonstruo.setImageIcon(auxIcon);
				}
			}
			else if(auxMonstruo.getTipoMonstruo() == 7)
			{
				if(auxMonstruo.getOrientacion() == 1)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo1Tipo7);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 2)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo2Tipo7);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 3)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo3Tipo7);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 4)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo4Tipo7);
					auxMonstruo.setImageIcon(auxIcon);
				}
			}
			else if(auxMonstruo.getTipoMonstruo() == 8)
			{
				if(auxMonstruo.getOrientacion() == 1)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo1Tipo8);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 2)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo2Tipo8);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 3)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo3Tipo8);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 4)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo4Tipo8);
					auxMonstruo.setImageIcon(auxIcon);
				}
			}
			else if(auxMonstruo.getTipoMonstruo() == 9)
			{
				if(auxMonstruo.getOrientacion() == 1)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo1Tipo9);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 2)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo2Tipo9);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 3)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo3Tipo9);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 4)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo4Tipo9);
					auxMonstruo.setImageIcon(auxIcon);
				}
			}
			else if(auxMonstruo.getTipoMonstruo() == 10)
			{
				if(auxMonstruo.getOrientacion() == 1)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo1Tipo10);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 2)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo2Tipo10);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 3)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo3Tipo10);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 4)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo4Tipo10);
					auxMonstruo.setImageIcon(auxIcon);
				}
			}
			else if(auxMonstruo.getTipoMonstruo() == 11)
			{
				if(auxMonstruo.getOrientacion() == 1)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo1Tipo11);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 2)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo2Tipo11);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 3)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo3Tipo11);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 4)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo4Tipo11);
					auxMonstruo.setImageIcon(auxIcon);
				}
			}
			else if(auxMonstruo.getTipoMonstruo() == 12)
			{
				if(auxMonstruo.getOrientacion() == 1)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo1Tipo12);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 2)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo2Tipo12);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 3)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo3Tipo12);
					auxMonstruo.setImageIcon(auxIcon);
				}
				else if(auxMonstruo.getOrientacion() == 4)
				{
					ImageIcon auxIcon;
					auxIcon = new ImageIcon(tileEnemigo4Tipo12);
					auxMonstruo.setImageIcon(auxIcon);
				}
			}
		}
	}
	
	public void actualizaJugador(){
		if(recoilAtaque <= 0)
		{
			if(presionaIzquierda)
			{
				if(jugador.getOrientacion() == 4)
				{
					if(mapa[jugador.getPosRen()][jugador.getPosCol()-1] == '.')
					{
						mapa[jugador.getPosRen()][jugador.getPosCol()] = mapa[jugador.getPosRen()][jugador.getPosCol()-1];
						jugador.setPosCol(jugador.getPosCol()-1);
						mapa[jugador.getPosRen()][jugador.getPosCol()] = '@';
						jugador.setOrientacion(4);
					}
					
					if(mapa[jugador.getPosRen()][jugador.getPosCol()-1] == '^')
					{
						escaleras = true;
						nivelActual++;
						if(nivelActual > 8)
						{
							nivelActual = 1;
						}
					}
					
					if(mapa[jugador.getPosRen()][jugador.getPosCol()-1] == 'V')
					{
						escaleras = true;
						nivelActual--;
						if(nivelActual <= 0)
						{
							nivelActual = 5;
						}
					}
				}
				else
				{
					jugador.setOrientacion(4);
				}
				
				presionaIzquierda = false;
			}
			else if(presionaDerecha)
			{
				if(jugador.getOrientacion() == 2)
				{
					if(mapa[jugador.getPosRen()][jugador.getPosCol()+1] == '.')
					{
						mapa[jugador.getPosRen()][jugador.getPosCol()] = mapa[jugador.getPosRen()][jugador.getPosCol()+1];
						jugador.setPosCol(jugador.getPosCol()+1);
						mapa[jugador.getPosRen()][jugador.getPosCol()] = '@';
						jugador.setOrientacion(2);
					}
					
					if(mapa[jugador.getPosRen()][jugador.getPosCol()+1] == '^')
					{
						
						escaleras = true;
						nivelActual++;
						if(nivelActual > 8)
						{
							nivelActual = 1;
						}
					}
					
					if(mapa[jugador.getPosRen()][jugador.getPosCol()+1] == 'V')
					{
						escaleras = true;
						escaleras = true;
						nivelActual--;
						if(nivelActual <= 0)
						{
							nivelActual = 5;
						}
					}
				}
				else
				{
					jugador.setOrientacion(2);
				}
				

				presionaDerecha = false;
			}
			else if(presionaArriba)
			{
				if(jugador.getOrientacion() == 1)
				{
					if(mapa[jugador.getPosRen()-1][jugador.getPosCol()] == '.')
					{
						mapa[jugador.getPosRen()][jugador.getPosCol()] = mapa[jugador.getPosRen() - 1][jugador.getPosCol()];
						jugador.setPosRen(jugador.getPosRen() - 1);
						mapa[jugador.getPosRen()][jugador.getPosCol()] = '@';
						jugador.setOrientacion(1);
					}
					
					if(mapa[jugador.getPosRen()-1][jugador.getPosCol()] == '^')
					{
						escaleras = true;
						escaleras = true;
						nivelActual++;
						if(nivelActual > 8)
						{
							nivelActual = 1;
						}
					}
					
					if(mapa[jugador.getPosRen()-1][jugador.getPosCol()] == 'V')
					{
						escaleras = true;
						nivelActual--;
						if(nivelActual <= 0)
						{
							nivelActual = 5;
						}
					}
				}
				else
				{
					jugador.setOrientacion(1);
				}
				
				presionaArriba = false;
			}
			else if(presionaAbajo)
			{
				if(jugador.getOrientacion() == 3)
				{
					if(mapa[jugador.getPosRen()+1][jugador.getPosCol()] == '.')
					{
						mapa[jugador.getPosRen()][jugador.getPosCol()] = mapa[jugador.getPosRen()+1][jugador.getPosCol()];
						jugador.setPosRen(jugador.getPosRen()+1);
						mapa[jugador.getPosRen()][jugador.getPosCol()] = '@';
						jugador.setOrientacion(3);
					}
					
					if(mapa[jugador.getPosRen()+1][jugador.getPosCol()] == '^')
					{
						escaleras = true;
					}
					
					if(mapa[jugador.getPosRen()+1][jugador.getPosCol()] == 'V')
					{
						escaleras = true;
					}
				}
				else
				{
					jugador.setOrientacion(3);
				}
				
				presionaAbajo = false;
			}

			if(presionaAtaque)
			{
				recoilAtaque = 15;
				orientaAtaque();
				
				if(jugador.getOrientacion() == 1)
				{
					if(mapa[jugador.getPosRen()-1][jugador.getPosCol()] == 'X')
					{
							for (int k=0; k<monstruos.size();k++)
							{
								Monstruo auxMonstruo = monstruos.get(k);
								if(auxMonstruo.getPosRen() == jugador.getPosRen()-1 && auxMonstruo.getPosCol() == jugador.getPosCol())
								{
									rollCombat(k, true);
									
									if(auxMonstruo.getVida() <= 0)
									{
										mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = '.';
										monstruos.remove(k);
										jugador.setXp(jugador.getXp()+1);
									}
								}
							}
					}
				}
				else if(jugador.getOrientacion() == 2)
				{
					if(mapa[jugador.getPosRen()][jugador.getPosCol()+1] == 'X')
					{
							for (int k=0; k<monstruos.size();k++)
							{
								Monstruo auxMonstruo = monstruos.get(k);
								if(auxMonstruo.getPosRen() == jugador.getPosRen() && auxMonstruo.getPosCol() == jugador.getPosCol()+1)
								{
									rollCombat(k, true);
									
									if(auxMonstruo.getVida() <= 0)
									{
										mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = '.';
										monstruos.remove(k);
										jugador.setXp(jugador.getXp()+1);
									}
								}
							}
					}
				}
				else if(jugador.getOrientacion() == 3)
				{
					if(mapa[jugador.getPosRen()+1][jugador.getPosCol()] == 'X')
					{
							for (int k=0; k<monstruos.size();k++)
							{
								Monstruo auxMonstruo = monstruos.get(k);
								if(auxMonstruo.getPosRen() == jugador.getPosRen()+1 && auxMonstruo.getPosCol() == jugador.getPosCol())
								{
									rollCombat(k, true);
									
									if(auxMonstruo.getVida() <= 0)
									{
										mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = '.';
										monstruos.remove(k);
										jugador.setXp(jugador.getXp()+1);
									}
								}
							}
					}
				}
				else if(jugador.getOrientacion() == 4)
				{
					if(mapa[jugador.getPosRen()][jugador.getPosCol()-1] == 'X')
					{
							for (int k=0; k<monstruos.size();k++)
							{
								Monstruo auxMonstruo = monstruos.get(k);
								if(auxMonstruo.getPosRen() == jugador.getPosRen() && auxMonstruo.getPosCol() == jugador.getPosCol()-1)
								{
									rollCombat(k, true);
									
									if(auxMonstruo.getVida() <= 0)
									{
										mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = '.';
										monstruos.remove(k);
										jugador.setXp(jugador.getXp()+1);
									}
								}
							}
					}
				}
				
				presionaAtaque = false;
			}
			orientaJugador();
		}
		else
		{
			
		}
		
		
	}
	
	public void actualizaMonstruos()
	{
		if(!muerto)
		for(int k=0; k<monstruos.size(); k++)
		{
			Monstruo auxMonstruo = monstruos.get(k);
			
			boolean gastoTurno = false;
			boolean jugadorArriba = mapa[auxMonstruo.getPosRen()-1][auxMonstruo.getPosCol()] == '@';
			boolean jugadorAbajo = mapa[auxMonstruo.getPosRen()+1][auxMonstruo.getPosCol()] == '@';
			boolean jugadorIzquierda = mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()-1] == '@';
			boolean jugadorDerecha = mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()+1] == '@';
			//boolean 
					
			int aggroRenInf, aggroRenSup;
			int aggroColInf, aggroColSup;
			
			aggroRenInf = auxMonstruo.getPosRen()-2;
			aggroRenSup = auxMonstruo.getPosRen()+2;
			
			aggroColInf = auxMonstruo.getPosCol()-2;
			aggroColSup = auxMonstruo.getPosCol()+2;
			
			if( (aggroRenInf <= jugador.getPosRen() && jugador.getPosRen() <= aggroRenSup) && (aggroColSup >= jugador.getPosCol() && jugador.getPosCol() >= aggroColInf) )
			{	
				int rollAggro = randomWithRange(1 , 10);
				if(jugadorArriba || jugadorAbajo || jugadorIzquierda || jugadorDerecha)
				{
					int rollFlee = randomWithRange(1, 10);
					if(jugadorArriba && (auxMonstruo.getOrientacion() == 1))
					{
						rollCombat(k, false);
						
					}
					else if(jugadorAbajo && (auxMonstruo.getOrientacion() == 3))
					{
						rollCombat(k, false);
					}
					else if(jugadorDerecha && (auxMonstruo.getOrientacion() == 2))
					{
						rollCombat(k, false);
					}
					else if(jugadorIzquierda && (auxMonstruo.getOrientacion() == 4))
					{
				
						rollCombat(k, false);
					
					}
					else
					{
						if(jugadorArriba)
						{
							auxMonstruo.setOrientacion(1);
						}
						else if(jugadorAbajo)
						{
							auxMonstruo.setOrientacion(3);
						}
						else if(jugadorDerecha)
						{
							auxMonstruo.setOrientacion(2);
						}
						else if(jugadorIzquierda)
						{
							auxMonstruo.setOrientacion(2);
						}
					}
					
					if(jugador.getVida() <= 0)
					{
						muerto = true;
					}
				}
				else if(rollAggro <= 8)
				{
					if(auxMonstruo.getPosRen() < jugador.getPosRen() && !gastoTurno)
					{
						if(mapa[auxMonstruo.getPosRen()+1][auxMonstruo.getPosCol()] == '.')
						{
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = mapa[auxMonstruo.getPosRen()+1][auxMonstruo.getPosCol()];
							auxMonstruo.setPosRen(auxMonstruo.getPosRen()+1);
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = 'X';
							auxMonstruo.setOrientacion(3);
							gastoTurno = true;

						}

					}

					if(auxMonstruo.getPosRen() > jugador.getPosRen() && !gastoTurno)
					{
						if(mapa[auxMonstruo.getPosRen()-1][auxMonstruo.getPosCol()] == '.')
						{
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = mapa[auxMonstruo.getPosRen()-1][auxMonstruo.getPosCol()];
							auxMonstruo.setPosRen(auxMonstruo.getPosRen()-1);
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = 'X';
							auxMonstruo.setOrientacion(1);
							gastoTurno = true;
						}

					}

					if(auxMonstruo.getPosCol() < jugador.getPosCol() && !gastoTurno)
					{
						if(mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()+1] == '.')
						{
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()+1];
							auxMonstruo.setPosCol(auxMonstruo.getPosCol()+1);
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = 'X';
							auxMonstruo.setOrientacion(2);
							gastoTurno = true;
						}
					}

					if(auxMonstruo.getPosCol() > jugador.getPosCol() && !gastoTurno)
					{
						if(mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()-1] == '.')
						{
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()-1];
							auxMonstruo.setPosCol(auxMonstruo.getPosCol()-1);
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = 'X';
							auxMonstruo.setOrientacion(4);
							gastoTurno = true;
						}
					}
				}
				else
				{
					orientaMonstruo();
					return;
				}
				
			}
			else
			{
				int rollStay = randomWithRange(1, 4);
				int rollMueve = randomWithRange(1, 4) ;
				if(rollStay == 1)
				{
					orientaMonstruo();
					return;
				}
				else
				{
					if(rollMueve == 1)
					{
						//System.out.println("Voy a mover monstruos en " +  auxMonstruo.getPosRen() + " " + auxMonstruo.getPosCol());
						if(mapa[auxMonstruo.getPosRen()-1][auxMonstruo.getPosCol()] == '.')
						{
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = mapa[auxMonstruo.getPosRen()-1][auxMonstruo.getPosCol()];
							auxMonstruo.setPosRen(auxMonstruo.getPosRen()-1);
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = 'X';
							auxMonstruo.setOrientacion(1);
						}

					}
					else if(rollMueve == 2)
					{
						if(mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()+1] == '.')
						{
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()+1];
							auxMonstruo.setPosCol(auxMonstruo.getPosCol()+1);
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = 'X';
							auxMonstruo.setOrientacion(2);
						}
					}
					else if(rollMueve == 3)
					{
						if(mapa[auxMonstruo.getPosRen()+1][auxMonstruo.getPosCol()] == '.')
						{
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = mapa[auxMonstruo.getPosRen()+1][auxMonstruo.getPosCol()];
							auxMonstruo.setPosRen(auxMonstruo.getPosRen()+1);
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = 'X';
							auxMonstruo.setOrientacion(3);
						}
					}
					else if(rollMueve == 4)
					{
						if(mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()-1] == '.')
						{
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()-1];
							auxMonstruo.setPosCol(auxMonstruo.getPosCol()-1);
							mapa[auxMonstruo.getPosRen()][auxMonstruo.getPosCol()] = 'X';
							auxMonstruo.setOrientacion(4);
						}
					}
				}
			}
		}
		orientaMonstruo();
	}
	
	public void subeNivel()
	{
		if((jugador.getXp()%5) == 0)
		{
			int rollSubir = randomWithRange(1, 4);
			if(rollSubir == 1)
			{
				jugador.setStrength(jugador.getStrength()+1);
			}
			else if(rollSubir == 2)
			{
				jugador.setDefense(jugador.getDefense()+1);
			}
			else if(rollSubir == 3)
			{
				jugador.setSpeed(jugador.getSpeed()+1);
			}
			else if(rollSubir == 4)
			{
				jugador.setStrength(jugador.getStrength()+2);
				jugador.setVida(jugador.getVida()-1);
			}
			
		}
	}
	
	/*
	 * Metodo actualiza
	 * actualiza el juego cada frame
	 */
	public void actualiza() {
		if(actualizoJugador)
		{
			//System.out.println("Turno jugador");
			actualizaJugador();
			//System.out.println("Turno monstruos");
			actualizaMonstruos();
			actualizaMessages();
			
			actualizoJugador = false;
			
		}
		if(messages.size() > 10)
		{
			messages.pop();
		}
		
		recoilAtaque--;
		
	}
	
	
	/*
	 * metodo checaColision
	 * checa colisiones entre los objetos del juego
	 */
	public void checaColision() {
			
	}
		
	/**
	* Metodo keyPressed
	* @param KeyEvent e
	*/
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			presionaIzquierda = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			presionaDerecha = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) 
		{
			presionaArriba = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) 
		{
			presionaAbajo = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_Z) 
		{
			presionaAtaque = true;
		}
		
		actualizoJugador = true;
	}
	
	/**
	* Metodo keyTyped
	* @param KeyEvent e
	*/
	public void keyTyped(KeyEvent e) {
	
	}
	
	/**
	* Metodo keyReleased
	* @param KeyEvent e
	*/
	public void keyReleased(KeyEvent e) {
		
	}
	
	/**
	* Metodo mousePressed
	* @param MouseEvent e
	*/
	public void mousePressed(MouseEvent e) {
		
	}
		
	/**
	* Metodo mousePressed
	* @param MouseEvent e
	*/
	public void mouseReleased(MouseEvent e) {
	
	}
	
	/**
	* Metodo mousePressed
	* @param MouseEvent e
	*/
	public void mouseMoved(MouseEvent e){
	
	}
	
	/**
	* Metodo mousePressed
	* @param MouseEvent e
	*/
	public void mouseEntered(MouseEvent e){
	
	}
	
	/**
	* Metodo mousePressed
	* @param MouseEvent e
	*/
	public void mouseExited(MouseEvent e){
	
	}
	
	/**
	* Metodo mousePressed
	* @param MouseEvent e
	*/
	public void mouseClicked(MouseEvent e){
	
	}
	/*
	 * Metodo paint
	 * paint para hacer double buffering, se dibuja a un buffer
	 * @param Graphics g
	 */
	public void paint (Graphics g) {
		// Inicializan el DoubleBuffer                
		dbImage = createImage (this.getSize().width, this.getSize().height);
		dbg = dbImage.getGraphics ();
		
		// Actualiza la imagen de fondo.
		dbg.setColor(getBackground ());
		dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		// Actualiza el Foreground.
		dbg.setColor(getForeground());
		paint1(dbg);
		
		// Dibuja la imagen actualizada
		g.drawImage (dbImage, 0, 0, this);
	}
	/*
	 * Metodo paint1
	 * paint1 para pintar la pantalla
	 * @param Graphics g
	 */
	public void paint1 (Graphics g)
	{                                     
		
		if(g != null) 
		{ //Si ya se creo el objeto grafico g
			g.translate(-camX, -camY);
			for(int i=0; i<ren; i++)
				for(int j=0; j<col; j++)
				{
					char auxChar = mapa[i][j];
					if(mapa[i][j] == '#')
					{
							g.drawImage(tilePared1, j*tamTile+BORDEINF, i*tamTile+BORDESUP, this);
					}
					
					if(mapa[i][j] == '.')
					{
						g.drawImage(tilePiso, j*tamTile+BORDEINF, i*tamTile+BORDESUP, this);
					}
					
					if(mapa[i][j] == '0')
					{
						g.drawImage(tileVacio, j*tamTile+BORDEINF, i*tamTile+BORDESUP, this);
					}
					
					if(mapa[i][j] == '@')
					{
						//System.out.println( jugador.getPosX() + " " + jugador.getPosY());
						g.drawImage(tilePiso, j*tamTile+BORDEINF, i*tamTile+BORDESUP, this);
						g.drawImage(jugador.getImagenI(), jugador.getPosX()+BORDEINF, jugador.getPosY()+BORDESUP, this);
					}
					if(mapa[i][j] == 'X')
					{
						
						for(int k=0; k<monstruos.size(); k++)
						{
							Monstruo auxMonstruo = monstruos.get(k);
							
							if(auxMonstruo.getPosRen() == i && auxMonstruo.getPosCol() == j)
							{
								g.drawImage(tilePiso, j*tamTile+BORDEINF, i*tamTile+BORDESUP, this);
								g.drawImage(auxMonstruo.getImagenI(), j*tamTile+BORDEINF, i*tamTile+BORDESUP, this);
						
							}
						}	
					}
					if(mapa[i][j] == '^')
					{
						g.drawImage(tileEscaleraArriba, j*tamTile+BORDEINF, i*tamTile+BORDESUP, this);
					}
					if(mapa[i][j] == 'V')
					{
						g.drawImage(tileEscaleraAbajo, j*tamTile+BORDEINF, i*tamTile+BORDESUP, this);
					}
					if(recoilAtaque > 0)
					{
						//System.out.println( auxAtaqueDireccionX + " " + auxAtaqueDireccionY);
						g.drawImage(tileAtaqueDireccion, auxAtaqueDireccionX, auxAtaqueDireccionY, this);
					}
					
				}
			g.translate(camX, camY);
			
			g.drawImage(messageLog, 0, 600, this);
			if(messages != null)
			{
				for(int l=0; l<messages.size(); l++)
				{
					String auxMessage = messages.get(l);
					g.setColor(Color.yellow);
					g.drawString(">>"+auxMessage, 400+BORDEINF, (l*12)+580+BORDESUP);
				}
			}
			g.setColor(Color.yellow);
			g.drawString("Level"+":"+jugador.getXp()/5, 6+BORDEINF, 580+BORDESUP);
			g.drawString("XP"+":"+jugador.getXp(), 6+BORDEINF, 20+580+BORDESUP);
			g.drawString("Hitpoints"+":"+jugador.getVida(), 6+BORDEINF, 40+580+BORDESUP);
			g.drawString("Strength"+":"+jugador.getStrength(), 6+BORDEINF, 60+580+BORDESUP);
			g.drawString("Defense"+":"+jugador.getDefense(), 6+BORDEINF, 80+580+BORDESUP);
			g.drawString("Speed"+":"+jugador.getSpeed(), 6+BORDEINF, 100+580+BORDESUP);
			
		}
		else
		{
			System.out.println("cargando");
		}
	}
	
	public void ajustarCamara()
	{
		camX = jugador.getPosX()+BORDEINF - VIEWPORT_SIZE_X / 2;
		if(camX > offsetMaxX)
		{
			camX = offsetMaxX;
		}
		else if(camX < offsetMinX)
		{
			camX = 0;
		}
		camY = jugador.getPosY()+BORDESUP - VIEWPORT_SIZE_Y / 2;
		if(camY > offsetMaxY)
		{
			camY = offsetMaxY;
		}
		else if(camY < offsetMinY)
		{
			camY = 0;
		}
	}
	
	/*
	 * Metodo run
	 * corre el juego y duerme el thread
	 */
	public void run() {
		while(true) 
		{
			if(!muerto && !escaleras)
			{
				actualiza(); //Se actualiza y checa colision
				checaColision();
				ajustarCamara();
				repaint();
			}
			else if(muerto)
			{
				init();
				reset("nivel" + nivelActual +".txt");
				muerto = false;
			}
			else if(escaleras)
			{
				init();
				reset("nivel" + nivelActual +".txt");
				escaleras = false;
			}
			try
			{
				Thread.sleep(16); //Se duerme el juego 1 frame (1/60 de segundo es 1 frame)
			}
			catch(InterruptedException ex)
			{
				System.out.println("Error en " + ex.toString());
			}
		}		
	}
	
	/*
	 * Metodo main
	 * metodo principal del juego
	 * @param String[] args
	 */
	public static void main(String[] args) {
	
		JFrameCrawl crawl = new JFrameCrawl(); //Se crea un objeto JFrameCesta
		crawl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Despliega la ventana en pantalla al hacerla visible
		crawl.setVisible(true); //Se hace visible
	
	}
}
