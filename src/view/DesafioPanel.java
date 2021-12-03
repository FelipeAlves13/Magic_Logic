package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Camada;
import model.Livro;
import model.Magia;
import model.PortalDesafio;

public class DesafioPanel extends JPanel{
	private ArrayList<Camada> camadas = new ArrayList<Camada>();
	private ArrayList<Rectangle>  retangulosPercursoVerticalBaixo,retangulosPercursoVerticalCima;
	private ArrayList<Rectangle> retangulosPercursoHorizontalEsquerda,retangulosPercursoHorizontalDireita;
	private Magia magia;
	private Livro livro;
	private PortalDesafio inicio, fim;
	private BufferedImage imagem;
	boolean comecou;
	
	
	public DesafioPanel(ArrayList<Camada> camadas, Magia magia) {
		this.retangulosPercursoVerticalBaixo = new ArrayList<Rectangle>();
		this.retangulosPercursoVerticalCima = new ArrayList<Rectangle>();
		this.retangulosPercursoHorizontalEsquerda=new ArrayList<Rectangle>();
		this.retangulosPercursoHorizontalDireita=new ArrayList<Rectangle>();
	//	inicializarCoordenadasDoPercurso();
		this.camadas=camadas;
		this.magia=magia;
		this.livro = new Livro(0, 0, "imagens\\desafio1.png");
		
		try {
			
			this.imagem= ImageIO.read(new File("imagens\\titulo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int cont=0;
		for(Camada c:this.camadas) {
			if(!comecou &cont<2) {
				c.montarMapa(600,500);
			}
		}

		repaint();
		
	}

	public void paint(Graphics g) {
		int cont=0;
		Graphics2D desenhar = (Graphics2D) g;
		
		
		for(Camada c:this.camadas) {
			System.out.println(cont);
			if(!this.comecou&cont==2) {
				break;
			}
			else {
				if(cont==1) {
					desenhar.drawImage(livro.getImagem(),livro.getX(),90 , null);
					
				}
				desenhar.drawImage(c.getCamada(), 0, 0, null);
				cont++;
			}
		}
		desenhar.drawImage(this.inicio.getImagem(), this.inicio.getX(), this.inicio.getY(),null);
		desenhar.drawImage(this.fim.getImagem(), this.fim.getX(), this.fim.getY(),null);
		desenhar.drawImage(this.magia.getImagem(),this.magia.getX(),this.magia.getY() , null);
		desenhar.drawImage(imagem,60,20,null);
//		desenhar.drawRect(280, 320, 20, 20);
//		desenhar.drawRect(this.magia.getX(), this.magia.getY(), this.magia.getLargura(), this.magia.getAltura());
	}

	
	
	public boolean isComecou() {
		return comecou;
	}

	public void setComecou(boolean comecou) {
		this.comecou = comecou;
	}

	public ArrayList<Camada> getCamadas() {
		return camadas;
	}


	public void setCamadas(ArrayList<Camada> camadas) {
		this.camadas = camadas;
	}


	public Magia getMagia() {
		return magia;
	}


	public void setMagia(Magia magia) {
		this.magia = magia;
	}

	public ArrayList<Rectangle> getRetangulosPercursoVerticalBaixo() {
		return retangulosPercursoVerticalBaixo;
	}

	public ArrayList<Rectangle> getRetangulosPercursoVerticalCima() {
		return retangulosPercursoVerticalCima;
	}

	public ArrayList<Rectangle> getRetangulosPercursoHorizontalEsquerda() {
		return retangulosPercursoHorizontalEsquerda;
	}

	public ArrayList<Rectangle> getRetangulosPercursoHorizontalDireita() {
		return retangulosPercursoHorizontalDireita;
	}

	public Livro getLivro() {
		return livro;
	}

	public BufferedImage getImagem() {
		return imagem;
	}

	public PortalDesafio getInicio() {
		return inicio;
	}

	public void setInicio(PortalDesafio inicio) {
		this.inicio = inicio;
	}

	public PortalDesafio getFim() {
		return fim;
	}

	public void setFim(PortalDesafio fim) {
		this.fim = fim;
	}
	
	

}
