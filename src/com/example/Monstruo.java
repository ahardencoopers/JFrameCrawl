/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Alberto Harden Cooper & Ana Paula
 */
public class Monstruo extends Actores {
	
	protected int tipoMonstruo;

	/**
	 * Metodo constructor usado para crear el objeto
	 * @param posX es la <code>posicion en x</code> del objeto.
	 * @param posY es la <code>posicion en y</code> del objeto.
	 * @param image es la <code>imagen</code> del objeto.
	 */
	public Monstruo(int posX, int posY ,Image image) {
		super(posX, posY, image);
		orientacion = 3;
		vida = 10;
		dinero = 0;
		speed = 1;
		strength = 1;
		defense = 1;
		weapon = 1;
		armor = 1;
	}
	
	/**
	 * Metodo constructor usado para crear el objeto
	 * @param posX es la <code>posicion en x</code> del objeto.
	 * @param posY es la <code>posicion en y</code> del objeto.
	 * @param image es la <code>imagen</code> del objeto.
	 * @param indX
	 * @param indY
	 */
	public Monstruo(int posX, int posY ,Image image, int posRen, int posCol) {
		super(posX, posY, image);
		this.posRen = posRen;
		this.posCol = posCol;
		
		this.posX = posCol*icono.getIconHeight();
		this.posY = posRen*icono.getIconWidth();
		orientacion = 4;
	}
	
	public void setTipoMonstruo(int tipoMonstruo)
	{
		this.tipoMonstruo = tipoMonstruo;
	}
	
	public int getTipoMonstruo()
	{
		return this.tipoMonstruo;
	}
	
	
}

