package view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Camada;
import model.Cristal;
import model.Inimigo;
import model.Livro;
import model.Personagem;

public class Fase extends JPanel{
	private ArrayList<Camada> camadas;
	private Personagem personagem;
	private ArrayList<Inimigo> inimigo;
	private ArrayList<Livro> livros;
	private ArrayList<Rectangle> rectangleLivros;
	private ArrayList<Rectangle>  retangulosPercursoVerticalBaixo,retangulosPercursoVerticalCima;
	private ArrayList<Rectangle> retangulosPercursoHorizontalEsquerda,retangulosPercursoHorizontalDireita;
	private int qtdObjSecundarios, qtdObjPrimario;
	protected BufferedImage tela;
	private boolean ativarDesafio;
	private Cristal cristal;
	
	public Fase(ArrayList<Camada> camadas, ArrayList<Livro> livros, Personagem p,ArrayList<Inimigo> inimigo, Cristal cristal) {
		this.camadas = new ArrayList<Camada>();
		this.livros= new ArrayList<Livro>();
		this.rectangleLivros = new ArrayList<Rectangle>();
		this.retangulosPercursoVerticalBaixo = new ArrayList<Rectangle>();//NECESSARIOS PARA A LOGICA DE ANDAR
		this.retangulosPercursoVerticalCima = new ArrayList<Rectangle>();
		this.retangulosPercursoHorizontalEsquerda=new ArrayList<Rectangle>();
		this.retangulosPercursoHorizontalDireita = new ArrayList<Rectangle>();
		
	for(Livro l: livros) {
		this.livros.add(l);
	}
		this.personagem=p;
		this.inimigo=inimigo;
		this.cristal = cristal;
		
		
		
		for(Camada c: camadas) {
			this.camadas.add(c);
		}
		for(Camada c: this.camadas) {
			c.montarMapa(26*30,24*30);
		}
		setPreferredSize(new Dimension(this.camadas.get(0).getLargura(),this.camadas.get(0).getAltura()));
		
	}
	public Personagem getPersonagem() {
		return personagem;
	}

	public ArrayList<Camada> getCamadas() {
		return camadas;
	}

	public ArrayList<Livro> getLivros() {
		return livros;
	}

	public ArrayList<Rectangle> getRectangleLivros() {
		return rectangleLivros;
	}
	public int getQtdObjSecundarios() {
		return qtdObjSecundarios;
	}
	public void setQtdObjSecundarios(int qtdObjSecundarios) {
		this.qtdObjSecundarios = qtdObjSecundarios;
	}
	public int getQtdObjPrimario() {
		return qtdObjPrimario;
	}
	public void setQtdObjPrimario(int qtdObjPrimario) {
		this.qtdObjPrimario = qtdObjPrimario;
	}
		
	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}
	public Cristal getCristal() {
		return cristal;
	}
	public ArrayList<Inimigo> getInimigo() {
		return inimigo;
	}
	public void setInimigo(ArrayList<Inimigo> inimigo) {
		this.inimigo = inimigo;
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
	
	
	
	
	
}
