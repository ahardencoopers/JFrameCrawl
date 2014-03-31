package com.example;


import java.awt.Image;

/**
 *
 * @author Alberto Harden Cooper & Ana Paula
 */
public class Secundarios extends Personajes {

	protected double velocidadX;//velocidadX
	protected double velocidadY;//velocidadY

	/**
	* Constructor de Secundarios
	* @param posX es la posicion en x
	* @param posY es la posicion en y
	* @param image es la imagen del objeto
	*/
    public Secundarios(int posX, int posY, Image image){
        super(posX,posY,image);//se llama a constructor de la super clase
    }
	
	/**
	* setter de velocidadX
	* @param vX es la velocidad en x
	*/
	public void setVelocidadX(double vX) {
		velocidadX = vX;
	}
	
	/**
	* setter de velocidadY
	* @param vY es la velocidad en x
	*/
	public void setVelocidadY(double vY) {
		velocidadY = vY;
	}
	/**
	* getter de velocidadX
	*/
	public double getVelocidadX() {
		return velocidadX;
	}
	
	/**
	* getter de velocidadY
	*/
	public double getVelocidadY() {
		return velocidadY;
	}
	
	/**
	* Metodo mueveX
	* mueve el objeto de acuerdo a su velocidadX en el eje x cada frame que se llama
	* 
	*/
	public void mueveX() {
		double aux;
		aux = posX + velocidadX; 
		posX = (int) Math.ceil(aux);
	}
	
	/**
	* Metodo mueveY
	* mueve el objeto de acuerdo a su velocidadX en el eje y cada frame que se llama tomando en cuenta una desaceleracion por gravedad
	* @param aceleracion
	*/
	public void mueveY(double aceleracion) {
		double aux;
		aux = posY - velocidadY;//Se mueve el objeto hacia arriba
		velocidadY = velocidadY - aceleracion; //Se desacelera como sucederia en la realidad
		posY = (int) Math.ceil(aux); //Se actualiza la posicion
	}
}
