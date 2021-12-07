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

public class Fase3 extends Fase{
	private ArrayList<Cristal> cristais;
	
	public Fase3(ArrayList<Camada> camadas, ArrayList<Livro> livros, Personagem p,ArrayList<Inimigo> inimigo, ArrayList<Cristal> cristais) {
		super(camadas, livros, p,inimigo,cristais.get(0));
		this.cristais = cristais;
		int cont=0;
		for(Livro l:this.getLivros()) {
			
			this.getRectangleLivros().add(new Rectangle(l.getX()+((l.getImagem().getWidth()/3)), l.getY()+((l.getImagem().getHeight()/3)), (l.getImagem().getWidth()/6), (l.getImagem().getHeight()/4)));
			
			cont++;
		}
		inicializarCoordenadasDoPercurso();
		repaint();
	}
	
	public void inicializarCoordenadasDoPercurso() {
		//VERTICAL ACIMA
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(500,20,40,20));//0
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(140,70,40,20));
		this.getRetangulosPercursoVerticalCima().add(new Rectangle(300,120,40,20));
		//VERTICAL BAIXO
		
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(500,190,40,20));
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(300,320,40,20));
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(180,420,40,20));//5
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(140,190,20,20));
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(160,180,20,20));
		this.getRetangulosPercursoVerticalBaixo().add(new Rectangle(20,110,20,20));
		
		//HORIZONTAL DIREITA
		//CAMPO ABERTO
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(220,400,20,40));
		
		
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(380,400,20,20));
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(540,160,20,20));
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(520,460,20,20));
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(340,300,20,40));
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(180,100,20,20));
		this.getRetangulosPercursoHorizontalDireita().add(new Rectangle(500,20,20,40));
		//HORIZONTAL ESQUERDA
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(160,400,20,40));
		
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(140,160,20,40));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(20,90,20,20));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(160,300,30,20));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(140,160,20,40));
		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(500,20,20,40));
//		this.getRetangulosPercursoHorizontalEsquerda().add(new Rectangle());
	}
	
	public void paint(Graphics g) {
		Graphics2D desenhar = (Graphics2D) g;

		int cont=3;
		for(Camada c: this.getCamadas()) {
			if(cont==4) {
				if(!this.getLivros().get(cont-4).isPegouLivro()) {
					desenhar.drawImage(this.getLivros().get(cont-4).getImagem(),this.getLivros().get(cont-4).getX(),this.getLivros().get(cont-4).getY() , null);
			
				}
				if(!this.getLivros().get(cont-3).isPegouLivro()) {
					desenhar.drawImage(this.getLivros().get(cont-3).getImagem(),this.getLivros().get(cont-3).getX(),this.getLivros().get(cont-3).getY() , null);
				}
				
				desenhar.drawImage(c.getCamada(), 0, 0, null);
				for(Inimigo i:this.getInimigo()) {
					if(!i.isAparecer()) {
						desenhar.drawImage(i.getSprites()[i.getAparencia()],i.getX(),i.getY(),null);
					}
					
				}
				
			}else {
				desenhar.drawImage(this.getPersonagem().getSprites()[this.getPersonagem().getAparencia()],this.getPersonagem().getX(),this.getPersonagem().getY(),null);
				desenhar.drawImage(c.getCamada(), 0, 0, null);
			}
			cont++;
		}
		if(this.getCristais().get(0).isApareceuCristal()) {
			desenhar.drawImage(this.getCristais().get(0).getImagem(),this.getCristais().get(0).getX(),this.getCristais().get(0).getY(),null);
			
		}
		if(this.getCristais().get(1).isApareceuCristal()) {
			desenhar.drawImage(this.getCristais().get(1).getImagem(),this.getCristais().get(1).getX(),this.getCristais().get(1).getY(),null);
		}
		
		
		//desenhar.drawRect(getLivros().get(0).getX()+((getLivros().get(0).getImagem().getWidth()/3)), getLivros().get(0).getY()+(getLivros().get(0).getImagem().getHeight()/3));
		desenhar.dispose();

	}

	public ArrayList<Cristal> getCristais() {
		return cristais;
	}

	public void setCristais(ArrayList<Cristal> cristais) {
		this.cristais = cristais;
	}
	
	
	
	//sobrescrevr metodo paint
}
