package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class Personagem  extends Elemento{

	private int aparencia;
	private int vida = 100;
	private boolean pulando;
	public static int indice;
	private boolean magiaTeleporte,magiaAtaque;
	
	public BufferedImage[] sprites;
		
	public Personagem(){
		super(0,0);
	}
	
	public Personagem(BufferedImage[] sprites,int aparencia,int x,int y ){
		super(x, y);
		this.sprites = sprites;
		this.aparencia = aparencia;
		init();
	}
	private void init(){
		this.setAltura(sprites[5].getHeight());
		this.setLargura(sprites[5].getWidth());
		
	}
	
	public boolean checarColisao(Rectangle r1){
		Rectangle personagem = new Rectangle(this.x , this.y,this.getSprites()[0].getWidth(),this.getSprites()[0].getHeight());
		if(personagem.intersects(r1)){
			return true;
		}else{
			return false;
		}
	}
	
	ArrayList<Rectangle> rentanguloColidir=new ArrayList<Rectangle>();
	
	
	public void setRentanguloColidir(ArrayList<Rectangle> rentanguloColidir) {
		this.rentanguloColidir = rentanguloColidir;
	}
	

	public boolean collision(ArrayList<Rectangle> tmp, int x,int y) {
		Rectangle personagem=new Rectangle(this.getX()+x, this.getY()+y, this.getLargura(), this.getAltura());
		for (Rectangle rectangle : tmp) {
			if(rectangle.intersects(personagem)){
//				System.out.println("colidiu");
				return true;
			}
			
				
		}
		return false;

	}
	

	public int getAparencia() {
		return aparencia;
	}

	public void setAparencia(int aparencia) {
		this.aparencia = aparencia;
	}

	
	public BufferedImage[] getSprites() {
		return sprites;
	}
	
	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}
	public int getLargura() {
		return largura;
	}
	public void setLargura(int largura) {
		this.largura = largura;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}

	
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	
	
	@Override
	public void setX(int x) {
		this.x = x;
	}


	@Override
	public void setY(int y) {
		this.y=y;
	}

	public boolean isMagiaTeleporte() {
		return magiaTeleporte;
	}

	public void setMagiaTeleporte(boolean magiaTeleporte) {
		this.magiaTeleporte = magiaTeleporte;
	}

	public boolean isMagiaAtaque() {
		return magiaAtaque;
	}

	public void setMagiaAtaque(boolean magiaAtaque) {
		this.magiaAtaque = magiaAtaque;
	}

	
	
}
