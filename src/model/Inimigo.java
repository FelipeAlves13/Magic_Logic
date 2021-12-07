package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Inimigo extends Elemento{
	private BufferedImage imagem;
	public String direcao;
	private Rectangle[] retangulos = new Rectangle[5];
	public int aparencia,contador;
	public BufferedImage[] sprites;
	private boolean perseguir;
	public boolean colidiuRet1, colidiuRet2, colidiuRet3, colidiuRet4,aparecer;
	public Inimigo(BufferedImage[] sprites,int aparencia,int x, int y, int altura, int largura, ArrayList<Rectangle> retangulos,String direcao) {
		super(x, y, altura, largura);
		this.sprites = sprites;
		this.aparencia = aparencia;
		this.direcao=direcao;
		areaDeAtuacao(retangulos);

	}
	
	public void areaDeAtuacao(ArrayList<Rectangle> retangulos) {
		this.retangulos[0]=retangulos.get(0);
		this.retangulos[1]=retangulos.get(1);
		this.retangulos[2]=retangulos.get(2);
		this.retangulos[3]=retangulos.get(3);
		//OS QUATRO PRIMEIROS SERVEM APENAS PRA O INIMIGO MUDAR A DIREÇÃO
		this.retangulos[4]=retangulos.get(4);//RESPONSAVEL POR TODA A ARE Q O INIMIGO PODE ANDAR
	}
	
	public boolean checarColisao(Rectangle r1){
		Rectangle inimigo = new Rectangle(this.x , this.y,this.largura,this.altura);
		if(inimigo.intersects(r1)){
			return true;
		}else{
			return false;
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

	public boolean isColidiuRet1() {
		return colidiuRet1;
	}

	public void setColidiuRet1(boolean colidiuRet1) {
		this.colidiuRet1 = colidiuRet1;
	}

	public boolean isColidiuRet2() {
		return colidiuRet2;
	}

	public void setColidiuRet2(boolean colidiuRet2) {
		this.colidiuRet2 = colidiuRet2;
	}

	public boolean isColidiuRet3() {
		return colidiuRet3;
	}

	public void setColidiuRet3(boolean colidiuRet3) {
		this.colidiuRet3 = colidiuRet3;
	}

	public boolean isColidiuRet4() {
		return colidiuRet4;
	}

	public void setColidiuRet4(boolean colidiuRet4) {
		this.colidiuRet4 = colidiuRet4;
	}

	public boolean isAparecer() {
		return aparecer;
	}

	public void setAparecer(boolean aparecer) {
		this.aparecer = aparecer;
	}

	public boolean isPerseguir() {
		return perseguir;
	}

	public void setPerseguir(boolean perseguir) {
		this.perseguir = perseguir;
	}
	
	

}
