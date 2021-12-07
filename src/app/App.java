package app;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import Controller.Controle;
import model.Camada;
import model.Cristal;
import model.Inimigo;
import model.Livro;
import model.Magia;
import model.Personagem;
import model.PortalDesafio;
import model.Sprite;
import view.DesafioPanel;
import view.ExibirMensagem;
import view.Fase1;
import view.Fase2;
import view.Fase3;
import view.TelaJogo;

public class App {
	
	public static void main(String[] args) {
		 try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {//modifica o designer das telinhas e seus componentes
		        if ("Nimbus".equals(info.getName())) {         
					UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
			UIManager.getLookAndFeelDefaults().put("nimbusOrange",(Color.GREEN));
			Personagem p=new Personagem(new Sprite("imagens\\Bruxinhaverde.png",12,1,34,39).sprites, 0, 150, 120);
			p.setMagiaTeleporte(true);
			ArrayList<Camada> camadasFase1 = new ArrayList<Camada>();
			ArrayList<Camada> camadasFase2 = new ArrayList<Camada>();
			ArrayList<Camada> camadasFase3 = new ArrayList<Camada>();
			ArrayList<Camada> camadasFase4 = new ArrayList<Camada>();
			
			ArrayList<Camada> camadasDesafio1Fase1 = new ArrayList<Camada>();
			ArrayList<Camada> camadasDesafio2Fase1 = new ArrayList<Camada>();
			ArrayList<Camada> camadasDesafio1Fase2 = new ArrayList<Camada>();
			ArrayList<Camada> camadasDesafio2Fase2 = new ArrayList<Camada>();
			ArrayList<Camada> camadasDesafio1Fase3 = new ArrayList<Camada>();
			ArrayList<Camada> camadasDesafio2Fase3 = new ArrayList<Camada>();
			
			ArrayList<DesafioPanel> desafiosFase1= new ArrayList<DesafioPanel>();
			ArrayList<DesafioPanel> desafiosFase2=new ArrayList<DesafioPanel>();
			ArrayList<DesafioPanel> desafiosFase3=new ArrayList<DesafioPanel>();
			
			ArrayList<Livro> livros = new ArrayList<Livro>(); 
			ArrayList<Livro> livros2 = new ArrayList<Livro>(); 
			ArrayList<Livro> livros3 = new ArrayList<Livro>(); 
			
			//CAMADAS DA FASE 1
			
			camadasFase1.add(new Camada(30, 30, 20, 20, "imagens\\chao.png", "camadas\\camada1.txt"));
			camadasFase1.add(new Camada(30, 30, 20, 20, "imagens\\portal.png", "camadas\\camada4.txt"));
			camadasFase1.add(new Camada(30,30, 20, 20, "imagens\\objetos.png", "camadas\\camada2.txt"));
			camadasFase1.add(new Camada(30,30, 20, 20, "imagens\\objetos.png", "camadas\\camada3.txt"));
			
			//OBJETIVO SECUNDARIO DA FASE 1
			
			livros.add(new Livro(490, 260,"imagens\\LivroMagiamenor.png"));
			livros.add(new Livro(150, 370,"imagens\\LivroMagiamenor.png"));
			//new ExibirMensagem("", "","");
			Magia magia = new Magia("imagens\\magia.png",285, 355);
 			
			camadasDesafio1Fase1.add(new Camada(30, 30, 20, 20, "imagens\\chao.png", "camadas\\desafiocamada1.txt"));
			camadasDesafio1Fase1.add(new Camada(30, 30, 20, 20, "imagens\\c.png", "camadas\\desafiocamada2.txt"));
			camadasDesafio1Fase1.add(new Camada(30, 30, 20, 20, "imagens\\percurso.png", "camadas\\camada3Desafio1Fase1.txt"));
			
			camadasDesafio2Fase1.add(new Camada(30, 30, 20, 20, "imagens\\chao.png", "camadas\\desafiocamada1.txt"));
			camadasDesafio2Fase1.add(new Camada(30, 30, 20, 20, "imagens\\c.png", "camadas\\camada2Desafio2Fase1.txt"));
			//camadasDesafio2Fase1.add(new Camada(30, 30, 20, 20, "imagens\\percurso.png", "arquivoTxt\\camada3Desafio2Fase1.txt"));
			
			//DESAFIOS
			//new Magia("imagens\\magia.png",205, 360)
			desafiosFase1.add(new DesafioPanel(camadasDesafio1Fase1, new Personagem(new Sprite("imagens\\Bruxinhaverde.png",12,1,34,39).sprites, 0, 285, 345)));
			desafiosFase1.add(new DesafioPanel(camadasDesafio2Fase1, new Personagem(new Sprite("imagens\\Bruxinhaverde.png",12,1,34,39).sprites, 0, 205, 350)));
			
			desafiosFase1.get(0).setInicio(new PortalDesafio(278, 355, 46, 42, "imagens\\teleporte.png"));
			desafiosFase1.get(0).setFim(new PortalDesafio(220, 317, 46, 42, "imagens\\teleporteVerde.png"));
			
			desafiosFase1.get(1).setInicio(new PortalDesafio(201, 354, 46, 42, "imagens\\teleporte.png"));
			desafiosFase1.get(1).setFim(new PortalDesafio(260, 275, 46, 42, "imagens\\teleporteVerde.png"));
			
			ArrayList<Inimigo> inimigosFase1 = new ArrayList<Inimigo>();
			ArrayList<Rectangle> retangulosInimigo1 = new ArrayList<Rectangle>();
			retangulosInimigo1.add(new Rectangle(0,200,20,60));
			retangulosInimigo1.add(new Rectangle(340,200,20,60));
			retangulosInimigo1.add(new Rectangle(0,0,0,0));
			retangulosInimigo1.add(new Rectangle(0,0,0,0));
			retangulosInimigo1.add(new Rectangle(20, 200 , 320, 60));
			inimigosFase1.add(new Inimigo(new Sprite("imagens\\inimigo1.png", 12, 1,50,40).sprites, 9, 310, 200, 50, 40,retangulosInimigo1,"esquerda"));
			
			ArrayList<Rectangle> retangulosinimigo2fase1 = new ArrayList<Rectangle>();
			retangulosinimigo2fase1.add(new Rectangle(400,160,20,20));
			retangulosinimigo2fase1.add(new Rectangle(480,130,20,20));
			retangulosinimigo2fase1.add(new Rectangle(500,160,20,20));
			retangulosinimigo2fase1.add(new Rectangle(480,240,20,20));
			retangulosinimigo2fase1.add(new Rectangle(420, 120 , 180, 220));
			inimigosFase1.add(new Inimigo(new Sprite("imagens\\inimigo1.png", 12, 1,50,40).sprites, 0, 480, 200, 50, 40,retangulosinimigo2fase1,"acima"));
			//inimigosFase1.add(new Inimigo(new Sprite("imagens\\inimigo1.png", 12, 1,40,40).sprites, 1, 400, 400, 40, 40));
			
			Fase1 fase1 = new Fase1(camadasFase1,livros,p,inimigosFase1, new Cristal(426, 33, 30, 30, "imagens\\CristalMenor.png"));
			
			//CAMADAS DA FASE 2
			
			camadasFase2.add(new Camada(30, 30, 20, 20, "imagens\\chao2.png", "camadas\\fase2camada1.txt"));
			camadasFase2.add(new Camada(30,30, 20, 20, "imagens\\lacuna.png", "camadas\\fase2camada2.txt"));
			camadasFase2.add(new Camada(30, 30, 20, 20, "imagens\\portal.png", "camadas\\fase2camada4.txt"));
			camadasFase2.add(new Camada(30,30, 20, 20, "imagens\\lacuna.png", "camadas\\fase2camada3.txt"));
			
			
			camadasDesafio1Fase2.add(new Camada(30, 30, 20, 20, "imagens\\caminho.png", "camadas\\camada1Desafio1Fase2.txt"));
			camadasDesafio1Fase2.add(new Camada(30, 30, 20, 20, "imagens\\c.png", "camadas\\camada2Desafio2Fase2.txt"));
			//camadasDesafio1Fase2.add(new Camada(30, 30, 20, 20, "imagens\\percurso.png", "arquivoTxt\\camada3Desafio1Fase1.txt"));
			
			camadasDesafio2Fase2.add(new Camada(30, 30, 20, 20, "imagens\\caminho.png", "camadas\\camada1Desafio2Fase2.txt"));
			camadasDesafio2Fase2.add(new Camada(30, 30, 20, 20, "imagens\\c.png", "camadas\\camada2Desafio1Fase2.txt"));
		//	camadasDesafio2Fase2.add(new Camada(30, 30, 20, 20, "imagens\\percurso.png", "arquivoTxt\\.txt"));
			
			livros2.add(new Livro(440, 40,"imagens\\LivroMagiamenor.png"));
			livros2.add(new Livro(200, 360,"imagens\\LivroMagiamenor.png"));
			
			//DESAFIOS//FALTA AJUSTAR A POSIÇÃO
			//new Magia("imagens\\magia.png",286, 376)
			//new Magia("imagens\\magia.png",272, 353)
			desafiosFase2.add(new DesafioPanel(camadasDesafio1Fase2,new Personagem(new Sprite("imagens\\Bruxinhaverde.png",12,1,34,39).sprites, 0, 286, 366)));
			desafiosFase2.add(new DesafioPanel(camadasDesafio2Fase2,new Personagem(new Sprite("imagens\\Bruxinhaverde.png",12,1,34,39).sprites, 0, 272, 343) ));
			
			//SETAR OS PORTAOS NO DESAFIO
			desafiosFase2.get(0).setInicio(new PortalDesafio(279, 374, 46, 42, "imagens\\teleporte.png"));
			desafiosFase2.get(0).setFim(new PortalDesafio(349, 333, 46, 42, "imagens\\teleporteVerde.png"));
			
			desafiosFase2.get(1).setInicio(new PortalDesafio(269, 351, 46, 42, "imagens\\teleporte.png"));
			desafiosFase2.get(1).setFim(new PortalDesafio(329, 351, 46, 42, "imagens\\teleporteVerde.png"));
			
			//------------------------------------------------------------//
			ArrayList<Inimigo> inimigosFase2 = new ArrayList<Inimigo>();
			ArrayList<Rectangle> retangulosInimigo1Fase2 = new ArrayList<Rectangle>();
			retangulosInimigo1Fase2.add(new Rectangle(380,120,80,30));
			retangulosInimigo1Fase2.add(new Rectangle(520,310,60,20));
			retangulosInimigo1Fase2.add(new Rectangle(560,140,20,20));
			retangulosInimigo1Fase2.add(new Rectangle(380,300,20,20));
			retangulosInimigo1Fase2.add(new Rectangle(380,140,200,180));
			
			ArrayList<Rectangle> retangulosInimigo2Fase2 = new ArrayList<Rectangle>();
			retangulosInimigo2Fase2.add(new Rectangle(5,140,20,20));
			retangulosInimigo2Fase2.add(new Rectangle(360,140,20,40));
			retangulosInimigo2Fase2.add(new Rectangle(0,0,0,0));
			retangulosInimigo2Fase2.add(new Rectangle(0,0,0,0));
			retangulosInimigo2Fase2.add(new Rectangle(20,120,260,60));
			
			//INIMIGOS FASE 2
			inimigosFase2.add(new Inimigo(new Sprite("imagens\\inimigo1.png", 12, 1,50,40).sprites, 3, 380, 140, 50, 40,retangulosInimigo1Fase2,"direita"));
			inimigosFase2.add(new Inimigo(new Sprite("imagens\\inimigo1.png", 12, 1,50,40).sprites, 3, 20,135, 50, 40,retangulosInimigo2Fase2,"direita"));
			//inimigosFase2.add(new Inimigo(new Sprite("imagens\\inimigo1.png", 12, 1,40,40).sprites, 1, 400, 400, 40, 40));
			
			//fase
			Fase2 fase2 = new Fase2(camadasFase2, livros2, p, inimigosFase2,new Cristal(288, 42, 30, 30, "imagens\\CristalMenor.png"));
			fase2.setVisible(false);
			
			//CAMADAS DA FASE 3 
			
			camadasFase3.add(new Camada(30, 30, 20, 20, "imagens\\chao2.png", "camadas\\fase3camada1.txt"));
			camadasFase3.add(new Camada(30, 30, 20, 20, "imagens\\portal.png", "camadas\\fase3camada4.txt"));
			camadasFase3.add(new Camada(30,30, 20, 20, "imagens\\objetos.png", "camadas\\fase3camada2.txt"));
			camadasFase3.add(new Camada(30,30, 20, 20, "imagens\\objetos.png", "camadas\\fase3camada3.txt"));
			
			camadasDesafio1Fase3.add(new Camada(30, 30, 20, 20, "imagens\\chao2.png", "camadas\\camada1Desafio2Fase3.txt"));
			camadasDesafio1Fase3.add(new Camada(30, 30, 20, 20, "imagens\\caminho.png", "camadas\\camada2Desafio2Fase3.txt"));
			
			camadasDesafio2Fase3.add(new Camada(30, 30, 20, 20, "imagens\\chao2.png", "camadas\\camada1Dasafio1Fase3.txt"));
			camadasDesafio2Fase3.add(new Camada(30, 30, 20, 20, "imagens\\caminho.png", "camadas\\camada2Desafio1Fase3.txt"));
			//camadasDesafio2Fase1.add(new Camada(30, 30, 20, 
			
			livros3.add(new Livro(300, 150,"imagens\\LivroMagiamenor.png"));
			livros3.add(new Livro(10, 380,"imagens\\LivroMagiamenor.png"));
			
//			falta os desafios
			//new Magia("imagens\\magia.png",92, 123)
			//new Magia("imagens\\magia.png",99, 129)
			desafiosFase3.add(new DesafioPanel(camadasDesafio1Fase3,new Personagem(new Sprite("imagens\\Bruxinhaverde.png",12,1,34,39).sprites, 0, 92, 113) ));
			desafiosFase3.add(new DesafioPanel(camadasDesafio2Fase3,new Personagem(new Sprite("imagens\\Bruxinhaverde.png",12,1,34,39).sprites, 0, 285, 360)));
			
			desafiosFase3.get(1).setInicio(new PortalDesafio(279, 374, 46, 42, "imagens\\teleporte.png"));
			desafiosFase3.get(1).setFim(new PortalDesafio(355, 335, 46, 42, "imagens\\teleporteVerde.png"));
			
			desafiosFase3.get(0).setInicio(new PortalDesafio(89, 119, 46, 42, "imagens\\teleporte.png"));
			desafiosFase3.get(0).setFim(new PortalDesafio(89, 180, 46, 42, "imagens\\teleporteVerde.png"));
			
			ArrayList<Inimigo> inimigosFase3 = new ArrayList<Inimigo>();
			ArrayList<Rectangle> retangulosInimigo1Fase3 = new ArrayList<Rectangle>();
			retangulosInimigo1Fase3.add(new Rectangle(140,280,30,80));
			retangulosInimigo1Fase3.add(new Rectangle(280,330,60,20));
			retangulosInimigo1Fase3.add(new Rectangle(300,200,40,10));
			retangulosInimigo1Fase3.add(new Rectangle(340,220,20,120));
			retangulosInimigo1Fase3.add(new Rectangle(160,140,200,200));
			
			ArrayList<Rectangle> retangulosInimigo2Fase3 = new ArrayList<Rectangle>();
			retangulosInimigo2Fase3.add(new Rectangle(145,380,20,60));
			retangulosInimigo2Fase3.add(new Rectangle(340,365,60,20));
			retangulosInimigo2Fase3.add(new Rectangle(390,380,20,40));
			retangulosInimigo2Fase3.add(new Rectangle(340,495,40,5));
			retangulosInimigo2Fase3.add(new Rectangle(160,380,320,120));
			
			//AJUSTAR POSIÇÕES
			inimigosFase3.add(new Inimigo(new Sprite("imagens\\inimigo1.png", 12, 1,50,40).sprites, 0, 300, 290, 50, 40,retangulosInimigo1Fase3,"acima"));
			inimigosFase3.add(new Inimigo(new Sprite("imagens\\inimigo1.png", 12, 1,50,40).sprites, 9, 340, 380, 50, 40,retangulosInimigo2Fase3,"esquerda"));
		//	inimigosFase3.add(new Inimigo(new Sprite("imagens\\inimigo1.png", 12, 1,40,40).sprites, 1, 400, 400, 40, 40));
			
			ArrayList<Cristal> cristais  =new ArrayList<Cristal>();
			cristais.add(new Cristal(26, 15, 30, 30, "imagens\\CristalMenor.png"));
			cristais.add(new Cristal(506, 415, 30, 30, "imagens\\CristalMenor.png"));
			
			//fase
			Fase3 fase3 = new Fase3(camadasFase3, livros3, p,inimigosFase3,cristais);
			fase3.setVisible(false);
						
			fase1.setVisible(false);
			
			for(DesafioPanel desafioPanel:desafiosFase1) {
				desafioPanel.setVisible(false);
			}
			
			for(DesafioPanel desafioPanel:desafiosFase2) {
				desafioPanel.setVisible(false);
			}
			
			for(DesafioPanel desafioPanel:desafiosFase3) {
				desafioPanel.setVisible(false);
			}
			
			ArrayList<ArrayList<DesafioPanel>> desafios = new ArrayList<ArrayList<DesafioPanel>>();
			desafios.add(desafiosFase1);
			desafios.add(desafiosFase2);
			desafios.add(desafiosFase3);
			
			new Controle(new TelaJogo(fase1,fase2,fase3,desafios)).start();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
