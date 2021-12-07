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

public class Fase2 extends Fase {

	public Fase2(ArrayList<Camada> camadas, ArrayList<Livro> livros, Personagem p,ArrayList<Inimigo> inimigo, Cristal cristal) {
		super(camadas, livros, p,inimigo,cristal);
		int cont=0;
		for(Livro l:this.getLivros()) {
			
			this.getRectangleLivros().add(new Rectangle(l.getX()+((l.getImagem().getWidth()/3)), l.getY()+((l.getImagem().getHeight()/3)), l.getImagem().getWidth()/4, l.getImagem().getHeight()/4));
			
			cont++;
		}

		inicializarCoordenadasDoPercurso();
		repaint();
	}
	
	
	public void inicializarCoordenadasDoPercurso() {
		//VERTICAL BAIXO
		//CAMPO ABERTO
		//this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(260,70,40,20));//
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(520,290,80,20));//0
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(520,180,80,20));//1
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(20,170,40,20));//2
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(380,170,40,20));//3
		
		//ESTREMIDADES
		
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(180,290,40,20));//4
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(380,290,40,20));//5
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(520,470,80,20));//teleporte//6
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(80,390,40,20));//7
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(20,470,40,20));//8
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(260,170,40,30));
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(280,495,40,50));
		//---------------------------------------------------------------------------//
		
		//VERTICAL ACIMA
		//CAMPO ABERTO
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(520,240,40,30));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(520,120,40,30));//1
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(380,120,40,30));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(20,120,40,30));//3
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(260,40,40,30));
		
		//ESTREMIDADES
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(280,260,40,20));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(180,120,40,30));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(80,120,40,30));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(540,20,40,30));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(20,40,40,30));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(380,40,40,30));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(460,260,40,30));
		//----------------------------------------------------------------------------//
		
		//HORIZONTAL DIREITA
		//CAMPO ABERTO
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(300,460,30,40));//0//teleporte
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(310,280,30,20));//1
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(410,260,30,20));//2
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(420,140,30,40));//3
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(500,260,30,20));//4
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(300,140,30,40));//5
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(320,60,30,20));//6
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(220,140,30,20));//7
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(110,140,30,40));//8
		
		//ESTREMIDADES
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(560,460,20,20));//9
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(560,40,30,20));//
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(560,260,30,40));//
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(480,360,30,20));//
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(560,140,30,30));//
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(400,60,30,20));
		//----------------------------------------------------------------------------//
		
		//HORIZONTAL ESQUERDA
		//CAMPO ABERTO
		
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(260,460,30,20));//teleporte//0
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(60,140,30,40));//1
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(240,140,30,40));//2
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(380,280,30,20));//3
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(360,140,30,20));//4
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(260,60,30,20));//5
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(260,280,30,40));//6
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(160,140,30,40));//7
		//ESTREMIDADES
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(160,260,30,20));//8
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(0,140,30,20));//9
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(0,460,30,20));//10
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(60,380,30,20));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(0,60,30,40));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(440,280,30,20));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(340,360,30,40));
	}
	
	//sobrescrevr metodo paint
	public void paint(Graphics g) {
		Graphics2D desenhar = (Graphics2D) g;

		int cont=1;
		for(Camada c: this.getCamadas()) {
			if(cont>=2&&cont<4) {
				
				if(!this.getLivros().get(cont-2).isPegouLivro()) {
					desenhar.drawImage(this.getLivros().get(cont-2).getImagem(),this.getLivros().get(cont-2).getX(),this.getLivros().get(cont-2).getY() , null);
				}
			}
			
			
			
			desenhar.drawImage(c.getCamada(), 0, 0, null);
			cont++;
		}
		for(Inimigo i:this.getInimigo()) {
			if(!i.isAparecer()) {
				desenhar.drawImage(i.getSprites()[i.getAparencia()],i.getX(),i.getY(),null);
			}
			
		}
		if(this.getCristal().isApareceuCristal()) {
			desenhar.drawImage(this.getCristal().getImagem(),this.getCristal().getX(),this.getCristal().getY(),null);
		}
		desenhar.drawImage(this.getPersonagem().getSprites()[this.getPersonagem().getAparencia()],this.getPersonagem().getX(),this.getPersonagem().getY(),null);
		//desenhar.drawRect(this.getPersonagem().getX(), this.getPersonagem().getY(), this.getPersonagem().getLargura(), this.getPersonagem().getAltura());
		desenhar.dispose();

	}
}
