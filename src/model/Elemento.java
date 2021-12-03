package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public abstract class Elemento{
	protected int x,y;
	protected int altura,largura;
	
	public Elemento(int x, int y, int altura, int largura) {
		super();
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.largura = largura;
	}
	public Elemento(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	

//	public abstract void desenhar(Graphics g);
//	public abstract void atualizar();

	public int getX() {
		return x;
	}

	public abstract void setX(int x);

	public int getY() {
		return y;
	}

	public abstract void setY(int y);
	
	public int getAltura() {
		return altura;
	}

	public int getLargura() {
		return largura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public void setLargura(int largura) {
		this.largura = largura;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(this.x, this.y, this.largura, this.altura);
	}
	
}
