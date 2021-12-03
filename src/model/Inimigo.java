package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Inimigo extends Elemento{
	private BufferedImage imagem;
	private String direcao;
	private Rectangle[] retangulos = new Rectangle[4];
	private int aparencia,contador;
	public BufferedImage[] sprites;
	
	public Inimigo(BufferedImage[] sprites,int aparencia,int x, int y, int altura, int largura) {
		super(x, y, altura, largura);
		this.sprites = sprites;
		this.aparencia = aparencia;
		//JOptionPane.showMessageDialog(null, sprites.length+"");
		this.direcao="direita";
//		try {
//			imagem = ImageIO.read(new File(camin));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void areaDeAtuacao(int X1,int Y1, int X2, int Y2,int X3, int Y3, int X4, int Y4) {
		this.retangulos[0]=new Rectangle(X1, Y1, 20, 140);
		this.retangulos[1]=new Rectangle(X2, Y2, 40, 20);
		this.retangulos[2]=new Rectangle(X3, Y3, 20, 60);
		this.retangulos[3]=new Rectangle(X4, Y4, 20, 20);
	}
	
	public boolean checarColisao(Rectangle r1){
		Rectangle inimigo = new Rectangle(this.x , this.y,this.largura,this.altura);
		if(inimigo.intersects(r1)){
			return true;
		}else{
			return false;
		}
	}
	
	public void perseguir(int x, int y){
	
		if(this.getX() > x){
			this.direcao = "esquerda";
		}else if(this.getX() < x){
			this.direcao = "direita";
		}else if(this.getY() > y){
			this.direcao = "acima";
		}else if(this.getY() < y){
			this.direcao = "abaixo";

		}
		
	}

	public void coordenadas(Personagem personagem,String direcao){
		if(personagem.checarColisao(this.retangulos[0])||personagem.checarColisao(this.retangulos[1])||personagem.checarColisao(this.retangulos[2])||personagem.checarColisao(this.retangulos[3])){
			this.perseguir(personagem.getX(),personagem.getY());
		}else{
			if(this.contador==0){
				this.direcao = direcao;
				this.contador++;
			}else if(this.checarColisao(this.retangulos[1])){
				this.direcao="direita";
			}else if(this.checarColisao(this.retangulos[0])){	
				this.direcao="acima";
			}else if(this.checarColisao(this.retangulos[2])) {
				this.direcao="abaixo";
			}else if(this.checarColisao(this.retangulos[3])) {
				this.direcao="esquerda";
			}
		}
	}
	
	public void andar(Personagem personagem){
		coordenadas(personagem, this.direcao);
		if(this.direcao!=null) {
			if(this.direcao.equals("esquerda")){
				//this.aparencia = 1;
				this.setX(this.getX()-2);
			}else if(this.direcao.equals("direita")){
				//this.aparencia = 0;
				this.setX(this.getX()+2);
			}else if(this.direcao.equals("abaixo")){
				//this.aparencia = 1;
				this.setY(this.getY()+2);
			}else if(this.direcao.equals("acima")){
				//this.aparencia = 0;
				this.setY(this.getY()-2);
			}
		}
		
	}
	
	@Override
	public void setX(int x) {
		this.x=x;
		
	}

	@Override
	public void setY(int y) {
		this.y=y;
		
	}

	public String getDirecao() {
		return direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}

	public int getAparencia() {
		return aparencia;
	}

	public void setAparencia(int aparencia) {
		this.aparencia = aparencia;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public BufferedImage getImagem() {
		return imagem;
	}

	public Rectangle[] getRetangulos() {
		return retangulos;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

}
