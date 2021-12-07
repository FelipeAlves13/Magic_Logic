package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import model.Camada;
import model.Cristal;
import model.Inimigo;
import model.Livro;
import model.Personagem;

@SuppressWarnings("serial")
public class Fase1 extends Fase{
	
	
	public Fase1(ArrayList<Camada> camadas, ArrayList<Livro> livros, Personagem p,ArrayList<Inimigo> inimigo, Cristal cristal) {
		super(camadas, livros, p,inimigo,cristal);
		for(Livro l:this.getLivros()) {
			getRectangleLivros().add(new Rectangle(l.getX()+((l.getImagem().getWidth()/3)), l.getY()+((l.getImagem().getHeight()/3)), l.getImagem().getWidth()/4, l.getImagem().getHeight()/4));
		}
		inicializarCoordenadasDoPercurso();
		repaint();
	}
	
	public void inicializarCoordenadasDoPercurso() {
		
		//VERTICAL CIMA
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(160,300,20,20));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(300,200,20,20));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(40,200,20,20));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(480,140,40,20));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(160,100,20,30));
		
		//VERTICAL BAIXO
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(160, 240, 20, 5));
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(300, 305, 20, 5));
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(300, 480, 20, 20));
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(140, 460, 40, 20));
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(40, 400, 40, 20));
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(500, 460, 40, 20));
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(420, 200, 40, 20));
		
		//HORIZONTAL DIREITA
		//CAMPO ABERTO
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(170, 200, 20,40));
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(380, 460, 20,20));//1
		//ESTREMIDADE
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(320, 220, 20,20));
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(180, 380, 20,20));
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(510, 140, 20,20));
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(510, 460, 20,20));
		//HORIZONTAL ESQUERDA
		//CAMPO ABERTO
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(140, 200, 30, 40));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(320,460,20,20));//1
		//ESTREMIDADE
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(20, 220, 20, 20));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(20, 380, 20, 20));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(280, 280, 20, 20));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(400, 160, 30, 20));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(140, 440, 20, 20));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(400, 140, 20, 20));
		
	}

	public void paint(Graphics g) {
		Graphics2D desenhar = (Graphics2D) g;

		int cont=1;
		for(Camada c: this.getCamadas()) {
			if(cont==3) {
				
				desenhar.drawImage(c.getCamada(), 0, 0, null);
								
				if(!this.getLivros().get(cont-3).isPegouLivro()) {
					desenhar.drawImage(this.getLivros().get(cont-3).getImagem(),this.getLivros().get(cont-3).getX(),this.getLivros().get(cont-3).getY() , null);
				}				
				if(!this.getLivros().get(cont-2).isPegouLivro()) {
					desenhar.drawImage(this.getLivros().get(cont-2).getImagem(),this.getLivros().get(cont-2).getX(),this.getLivros().get(cont-2).getY() , null);
				}
				for(Inimigo i:this.getInimigo()) {
					if(!i.isAparecer()) {
						desenhar.drawImage(i.getSprites()[i.getAparencia()],i.getX(),i.getY(),null);
					}
					
				}
				desenhar.drawImage(this.getPersonagem().getSprites()[this.getPersonagem().getAparencia()],this.getPersonagem().getX(),this.getPersonagem().getY(),null);
			}else if(cont!=3) {
				desenhar.drawImage(c.getCamada(), 0, 0, null);
			}
		
			cont++;
		}
		if(this.getCristal().isApareceuCristal()) {//TIRAR A NEGAÇÃO QUANDO AJEITAR
			desenhar.drawImage(this.getCristal().getImagem(),this.getCristal().getX(),this.getCristal().getY(),null);
		}
		//desenhar.drawRect(300, 305, 20, 5);
		desenhar.dispose();

	}
	

	
	
}
