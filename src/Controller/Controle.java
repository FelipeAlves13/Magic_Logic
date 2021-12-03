package Controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import model.Cristal;
import model.Dados;
import model.Livro;
import model.Magia;
import model.Personagem;
import view.AjudaPanel;
import view.CreditosPanel;
import view.DesafioPanel;
import view.ExibirMensagem;
import view.Fase1;
import view.Fase2;
import view.Fase3;
import view.Inventario;
import view.InventarioHorizontal;
import view.MapaDasFasesPanel;
import view.MenuPanel;
import view.SobrePanel;
import view.TelaJogo;

public class Controle extends Thread implements MouseListener, ActionListener{
	private MenuPanel menuPanel;
	private SobrePanel sobrePanel;
	private CreditosPanel creditosPanel;
	
	private TelaJogo telaJogo;
	
	private ExibirMensagem iniciarJogo, exibirMensagens,exibirMensagenLonga,exibirGameOver;
	private AjudaPanel ajudaPanel;
	
	private MapaDasFasesPanel mapaDasFasesPanel;
	
	private Fase1 fase1;
	private Fase2 fase2;
	private Fase3 fase3;
	private boolean resetou;
	private Inventario inventario;
	private InventarioHorizontal inventarioHorizontal;
	
	private int index, yFase1=350,yFase2=260,yFase3=370, pontoChegadaDesafio1=-1, pontoChegadaDesafio2=-1;
	private int indice=-1, posicao=-1;
	private int frente,direita=3, esquerda=6, costa=9, numeroDesafio=-1;
	private boolean subir,subirfase2, subirfase3;
	private boolean mainSelecionado, executando,repetir;
	private boolean baixoAndando,acimaAndando, direitaAndando, esquerdaAndando;
	private Color corLetra;
	private int indiceColisaoDireitaOuEsquerda,indiceColisaoAcimaOuAbaixo;
	private ArrayList<Boolean> retangulosAcimaColidiuFase2,retangulosAbaixoColidiuFase2,retangulosEsquerdaColidiuFase2,retangulosDireitaColidiuFase2;
	private ArrayList<Boolean> retangulosEsquerdaColidiuFase1, retangulosDireitaColidiuFase1, retangulosEsquerdaColidiuFase3, retangulosDireitaColidiuFase3;
	private boolean opostoEsquerda,opostoDireita, opostoDireitaAcima, opostoEsquerdaAcima, opostoAcima,opostoAbaixo;
	//private Rectangle retanguloTemporario;
	private boolean querJogar,concluiuDesafio=true;
	
	private JLabel cdLabel;
	
	
	public Controle(TelaJogo telaJogo) {
		this.telaJogo=telaJogo;
		this.menuPanel=telaJogo.getMenuPanel();//MENU
		this.sobrePanel=telaJogo.getSobrePanel();
		this.creditosPanel=telaJogo.getCreditosPanel();
		
		retangulosAcimaColidiuFase2= new ArrayList<Boolean>();
		retangulosAbaixoColidiuFase2= new ArrayList<Boolean>();
		retangulosEsquerdaColidiuFase2= new ArrayList<Boolean>();
		retangulosDireitaColidiuFase2= new ArrayList<Boolean>();
		
		this.retangulosDireitaColidiuFase1= new ArrayList<Boolean>();
		this.retangulosEsquerdaColidiuFase1=new ArrayList<Boolean>();
		
		this.retangulosDireitaColidiuFase3= new ArrayList<Boolean>();
		this.retangulosEsquerdaColidiuFase3=new ArrayList<Boolean>();
		
		for(int i=0;i<2;i++) {
			if(i<1) {
				this.retangulosDireitaColidiuFase3.add(false);
				this.retangulosEsquerdaColidiuFase3.add(false);
			}
			this.retangulosDireitaColidiuFase1.add(false);
			this.retangulosEsquerdaColidiuFase1.add(false);
		}
		
		
		for(int i=0;i<9;i++) {
			
			if(i<5) {
				this.retangulosAbaixoColidiuFase2.add(false);
				this.retangulosAcimaColidiuFase2.add(false);
			}
			
			this.retangulosDireitaColidiuFase2.add(false);
			this.retangulosEsquerdaColidiuFase2.add(false);
		}
		
		this.mapaDasFasesPanel=telaJogo.getMapaDasFasesPanel();
		
		this.fase1=telaJogo.getContainerFase().getFase1();
		this.fase2=telaJogo.getContainerFase().getFase2();
		this.fase3=telaJogo.getContainerFase().getFase3();
		
		this.inventario=telaJogo.getContainerFase().getInventarioVertical();
		this.inventarioHorizontal=telaJogo.getContainerFase().getInventarioHorizontal();
		mainSelecionado=true;
		//MENU
		this.corLetra =this.menuPanel.getJogarLabel().getForeground();
		this.telaJogo.exibirCampoDePreenchimento();
		this.telaJogo.exibirAvisoSimples();
		this.telaJogo.exibirAvisoLongo();
		this.telaJogo.exibirAjuda();
		this.telaJogo.exibirMsgGameOver();
		
		this.exibirGameOver=this.telaJogo.getExibirGameOver();
		this.iniciarJogo = this.telaJogo.getExibirMensagem();
		this.exibirMensagens=this.telaJogo.getExibirAviso();
		this.exibirMensagenLonga=this.telaJogo.getExibirAvisoLongo();
		this.ajudaPanel=this.telaJogo.getAjudaPanel();
		
		this.exibirGameOver.getJogarNovamente().addActionListener(this);
		this.exibirGameOver.getNaoJogar().addActionListener(this);
		
		this.ajudaPanel.getFecharLabel().addMouseListener(this);
		//EVENTOS DO MENU DO JOGO
		this.iniciarJogo.getCancelarButton().addActionListener(this);
		this.iniciarJogo.getJogarButton().addActionListener(this);
		this.exibirMensagens.getOkButon().addActionListener(this);
		this.exibirMensagenLonga.getOkButon().addActionListener(this);
		//this.iniciarJogo.getNomeField().addFocusListener(this);
		
		this.menuPanel.getJogarLabel().addMouseListener(this);
		this.menuPanel.getSobreJLabel().addMouseListener(this);
		this.menuPanel.getCreditosLabel().addMouseListener(this);
		this.menuPanel.getSairLabel().addMouseListener(this);
		
		//EVENTO TELA SOBRE PANEL
		this.sobrePanel.getVoltarLabel().addMouseListener(this);
		
		//EVENTO TELA CREDITOS
		this.creditosPanel.getVoltarLabel().addMouseListener(this);
		
		
		//EVENTO TELA MAPA DAS FASES
		this.mapaDasFasesPanel.getVoltarLabel().addMouseListener(this);
		this.mapaDasFasesPanel.getFase1IconPanel().addMouseListener(this);
		this.mapaDasFasesPanel.getFase2IconPanel().addMouseListener(this);
		this.mapaDasFasesPanel.getFase3IconPanel().addMouseListener(this);
		this.mapaDasFasesPanel.getInterrogacaoLabel().addMouseListener(this);
		//EVENTO DO INVENTARIO
	//	ativarEventosDosComandosDoInventario();//COMANDOS ATIVADOS
		this.inventario.getInterrogacaoLabel().addMouseListener(this);
		this.inventario.getSairLabel().addMouseListener(this);
		this.inventarioHorizontal.getQuadroFuncao().addMouseListener(this);
		this.inventarioHorizontal.getQuadroPrincipal().addMouseListener(this);
		this.inventarioHorizontal.getApagarFunção().addMouseListener(this);
		this.inventarioHorizontal.getApagarMain().addMouseListener(this);
		this.telaJogo.getContainerFase().getIniciarLabel().addMouseListener(this);
		
		
	}

	public void run() {
		while(true) {
			try {
				if(this.inventario.getLifeBar().getValue()<=0&(!resetou)) {
					executando=false;
					this.exibirGameOver.show(true);
				}else if(executando) {
					while(index<this.inventarioHorizontal.getQuadroPrincipal().getComponentCount()) {
						JLabel jl=(JLabel) this.inventarioHorizontal.getQuadroPrincipal().getComponent(index);
						
						if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("baixo")) {
							baixoAndando=true;
							
							if(fase2.isVisible()) {
								for(Boolean b:retangulosAcimaColidiuFase2) {
									b=true;
								}
								
								
							}
							
							while(baixoAndando) {
								if(Dados.desafioAtivado) {
									if(percursoVertical("baixo")) {
										if(Dados.desafio1fase1Visivel||Dados.desafio2Fase1visivel) {
											this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getMagia().setY(this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getMagia().getY()+20);
											this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).repaint();
											Thread.sleep(200);
										}else if(Dados.desafio1Fase2Visivel||Dados.desafio2Fase2Visivel) {
											this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getMagia().setY(this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getMagia().getY()+20);
											this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).repaint();
											Thread.sleep(200);
										}else if(Dados.desafio1Fase3Visivel||Dados.desafio2Fase3Visivel) {
											this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getMagia().setY(this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getMagia().getY()+20);
											this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).repaint();
											Thread.sleep(200);
										}
										
									
									}else {
										finalizouDesafio();
										baixoAndando=false;
									}
									
								}else if(fase1.isVisible()) {
									//FASE 1
									if(percursoVertical("baixo")&(!checarColisaoLivros())) {
										mudarAparencia(this.fase1.getPersonagem(),"baixo");
										
									}else {
										baixoAndando=false;
									}
									this.fase1.repaint();
									Thread.sleep(200);
								}else if(fase2.isVisible()) {
									//FASE 2
									if(percursoVertical("baixo")) {
										mudarAparencia(this.fase2.getPersonagem(),"baixo");
										
									}else {
										baixoAndando=false;
									}
									this.fase2.repaint();
									Thread.sleep(200);
									
								}else if(fase3.isVisible()) {
									//FASE 3
									if(percursoVertical("baixo")) {
										mudarAparencia(this.fase3.getPersonagem(),"baixo");
										
									}else {
										baixoAndando=false;
									}
									this.fase3.repaint();
									Thread.sleep(200);
								}
							}
							frente=0;
							if(indiceColisaoAcimaOuAbaixo==6) {
								retangulosDireitaColidiuFase2.set(4, true);
								retangulosEsquerdaColidiuFase2.set(6, true);
							}else if(indiceColisaoAcimaOuAbaixo==5) {
								retangulosDireitaColidiuFase2.set(1, true);
							}else if(indiceColisaoAcimaOuAbaixo==10) {
								retangulosDireitaColidiuFase2.set(3, true);
								retangulosDireitaColidiuFase2.set(5, false);
								retangulosEsquerdaColidiuFase2.set(1, true);
								retangulosEsquerdaColidiuFase2.set(2, false);
							}else if(indiceColisaoAcimaOuAbaixo==3) {
								retangulosEsquerdaColidiuFase2.set(2, true);
								retangulosEsquerdaColidiuFase2.set(4, false);
								retangulosDireitaColidiuFase2.set(3, false);
							}
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("esquerda")) {
							esquerdaAndando=true;
							opostoDireita=true;
							
							if(fase1.isVisible()) {
								retangulosDireitaColidiuFase1.set(0,true);
								retangulosDireitaColidiuFase1.set(1,true);
							}else if(fase2.isVisible()) {
								for(Boolean r:retangulosDireitaColidiuFase2) {
									r=true;
								}
								for(Boolean r:retangulosAcimaColidiuFase2) {
									r=true;
								}
								for(Boolean r:retangulosAbaixoColidiuFase2) {
									r=true;
								}
								
							}
							else if(fase3.isVisible()) {
								retangulosDireitaColidiuFase3.set(0,true);
							
							}
							while(esquerdaAndando) {
								if(Dados.desafioAtivado) {
									if(percursoHorizontal("esquerda")) {
										if(Dados.desafio1fase1Visivel||Dados.desafio2Fase1visivel) {
											this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getMagia().setX(this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getMagia().getX()-20);
											this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).repaint();
											Thread.sleep(200);
										}else if(Dados.desafio1Fase2Visivel||Dados.desafio2Fase2Visivel) {
											this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getMagia().setX(this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getMagia().getX()-20);
											this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).repaint();
											Thread.sleep(200);
										}else if(Dados.desafio1Fase3Visivel||Dados.desafio2Fase3Visivel) {
											this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getMagia().setX(this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getMagia().getX()-20);
											this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).repaint();
											Thread.sleep(200);
										}
										
									}else {
										finalizouDesafio();
										esquerdaAndando=false;
									}
//									if(numeroDesafio>=0) {
//										
						//			}
								}else if(fase1.isVisible()) {
									if(percursoHorizontal("esquerda")&(!checarColisaoLivros())&(!colidiuCristal())) {
										mudarAparencia(this.fase1.getPersonagem(),"esquerda");
									}else {
										//finalizouDesafio();
										esquerdaAndando=false;
									}
									this.fase1.repaint();
									Thread.sleep(200);
								}else if(fase2.isVisible()) {
									System.out.println("olaaa");
									if(percursoHorizontal("esquerda")&(!checarColisaoLivros())&(!colidiuCristal())) {
										mudarAparencia(this.fase2.getPersonagem(),"esquerda");
									}else {
										//finalizouDesafio();
										esquerdaAndando=false;
									}
									this.fase2.repaint();
									Thread.sleep(200);
								}else if(fase3.isVisible()) {
									if(percursoHorizontal("esquerda")&(!checarColisaoLivros())&(!colidiuCristal())) {
										mudarAparencia(this.fase3.getPersonagem(),"esquerda");
									}else {
										//finalizouDesafio();
										esquerdaAndando=false;
									}
									this.fase3.repaint();
									Thread.sleep(200);
								}
							}
							esquerda=6;
							if(indiceColisaoDireitaOuEsquerda==8) {
								retangulosAbaixoColidiuFase2.set(2, false);
								retangulosAcimaColidiuFase2.set(3, false);
							}else if(indiceColisaoDireitaOuEsquerda==4) {
								retangulosEsquerdaColidiuFase2.set(2, true);
								retangulosAcimaColidiuFase2.set(1, false);
								retangulosAcimaColidiuFase2.set(2, false);
								retangulosAbaixoColidiuFase2.set(1, false);
								retangulosAbaixoColidiuFase2.set(3, false);
								retangulosAbaixoColidiuFase2.set(0, true);
							}else if(indiceColisaoDireitaOuEsquerda==1) {
								retangulosDireitaColidiuFase2.set(7, true);
								
							}else if(indiceColisaoDireitaOuEsquerda==7) {
								retangulosEsquerdaColidiuFase2.set(1, true);
							}
							else if(indiceColisaoDireitaOuEsquerda==2) {
								//retangulosEsquerdaColidiuFase2.set(7, true);
								retangulosDireitaColidiuFase2.set(7, true);
									
							}
							else {
								for(int i=0;i<5;i++) {
									retangulosAbaixoColidiuFase2.set(i,true);
									retangulosAcimaColidiuFase2.set(i, true);
								}
							}
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("direita")) {
							direitaAndando=true;
							opostoEsquerda=true;
							
							//USADO PARA A COLISÃO EM CAMPOS ABERTOS
							if(fase1.isVisible()) {
								retangulosEsquerdaColidiuFase1.set(0,true);
								retangulosEsquerdaColidiuFase1.set(1,true);
							}else if(fase2.isVisible()) {
								for(Boolean r:retangulosEsquerdaColidiuFase2) {
									r=true;
								}
								for(Boolean r:retangulosAcimaColidiuFase2) {
									r=true;
								}
								for(Boolean r:retangulosAbaixoColidiuFase2) {
									r=true;
								}
							}
							else if(fase3.isVisible()) {
								retangulosEsquerdaColidiuFase3.set(0,true);
								
							}
							
							while(direitaAndando) {
								if(Dados.desafioAtivado) {
									if(percursoHorizontal("direita")) {
										if(Dados.desafio1fase1Visivel||Dados.desafio2Fase1visivel) {
											this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getMagia().setX(this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getMagia().getX()+20);
											this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).repaint();
											Thread.sleep(200);
										}else if(Dados.desafio1Fase2Visivel||Dados.desafio2Fase2Visivel) {
											this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getMagia().setX(this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getMagia().getX()+20);
											this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).repaint();
											Thread.sleep(200);
										}else if(Dados.desafio1Fase3Visivel||Dados.desafio2Fase3Visivel) {
											this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getMagia().setX(this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getMagia().getX()+20);
											this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).repaint();
											Thread.sleep(200);
										}
										
									}else {
										finalizouDesafio();
										direitaAndando=false;
									}
									
								}else if(fase1.isVisible()) {
									if(percursoHorizontal("direita")&(!checarColisaoLivros())&(!colidiuCristal())) {
										mudarAparencia(this.fase1.getPersonagem(),"direita");
									
									}else {
										direitaAndando=false;
									}
									this.fase1.repaint();
									Thread.sleep(200);
								}else if(fase2.isVisible()) {
									if(percursoHorizontal("direita")&(!checarColisaoLivros())&(!colidiuCristal())) {
										mudarAparencia(this.fase2.getPersonagem(),"direita");
									
									}else {
										direitaAndando=false;
									}
									this.fase2.repaint();
									Thread.sleep(200);
								}else if(fase3.isVisible()) {
									if(percursoHorizontal("direita")&(!checarColisaoLivros())&(!colidiuCristal())) {
										mudarAparencia(this.fase3.getPersonagem(),"direita");
									
									}else {
										direitaAndando=false;
									}
									this.fase3.repaint();
									Thread.sleep(200);
								}
							}
							direita=3;
							if(indiceColisaoDireitaOuEsquerda==11||indiceColisaoDireitaOuEsquerda==3) {
								retangulosAcimaColidiuFase2.set(1, false);
								retangulosAcimaColidiuFase2.set(2, false);
								retangulosAbaixoColidiuFase2.set(1, false);
								retangulosAbaixoColidiuFase2.set(3, false);
								retangulosAbaixoColidiuFase2.set(0, true);
							}else if(indiceColisaoDireitaOuEsquerda==9) {
								retangulosAcimaColidiuFase2.set(1, true);
								retangulosAcimaColidiuFase2.set(0, false);
								retangulosAbaixoColidiuFase2.set(0, false);
							}else if(indiceColisaoDireitaOuEsquerda==1) {
								retangulosDireitaColidiuFase2.set(2, true);
							}else if(indiceColisaoDireitaOuEsquerda==2) {
								retangulosDireitaColidiuFase2.set(4, true);
							}
							else if(indiceColisaoDireitaOuEsquerda==7) {
								retangulosDireitaColidiuFase2.set(5, true);
								
							}else if(indiceColisaoDireitaOuEsquerda==5) {
								retangulosDireitaColidiuFase2.set(3, true);
							}
							else {
								for(int i=0;i<4;i++) {
									retangulosAcimaColidiuFase2.set(i, true);
									retangulosAbaixoColidiuFase2.set(i, true);
								}
							}
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("cima")) {
							acimaAndando=true;
							if(fase2.isVisible()) {
								for(Boolean b:retangulosAbaixoColidiuFase2) {
									b=true;
								}
							}
							opostoDireitaAcima=true;
							opostoEsquerdaAcima=true;
							if(fase2.isVisible()) {
								retangulosEsquerdaColidiuFase2.set(2, true);

								retangulosDireitaColidiuFase2.set(2, true);
								retangulosDireitaColidiuFase2.set(4, true);
								
								
							}
							while(acimaAndando) {
								if(Dados.desafioAtivado) {
									
									if(percursoVertical("cima")) {
										if(Dados.desafio1fase1Visivel||Dados.desafio2Fase1visivel) {
											this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getMagia().setY(this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getMagia().getY()-20);
											this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).repaint();
											Thread.sleep(200);
										}else if(Dados.desafio1Fase2Visivel||Dados.desafio2Fase2Visivel) {
											this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getMagia().setY(this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getMagia().getY()-20);
											this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).repaint();
											Thread.sleep(200);
										}
										else if(Dados.desafio2Fase3Visivel||Dados.desafio1Fase3Visivel) {
											this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getMagia().setY(this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getMagia().getY()-20);
											this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).repaint();
											Thread.sleep(200);
										}
										
									}else {
										finalizouDesafio();
										acimaAndando=false;
									}
									
									
								}else if(fase1.isVisible()) {
									if(percursoVertical("cima")&(!checarColisaoLivros())&(!colidiuCristal())) {
										mudarAparencia(this.fase1.getPersonagem(),"cima");
									}else {
										acimaAndando=false;
									}
									this.fase1.repaint();
									Thread.sleep(200);
								}else if(fase2.isVisible()) {
									if(percursoVertical("cima")&(!checarColisaoLivros())&(!colidiuCristal())) {
										mudarAparencia(this.fase2.getPersonagem(),"cima");
									}else {
										acimaAndando=false;
									}
									this.fase2.repaint();
									Thread.sleep(200);
								}else if(fase3.isVisible()) {
									if(percursoVertical("cima")&(!checarColisaoLivros())) {
										mudarAparencia(this.fase3.getPersonagem(),"cima");
									}else {
										acimaAndando=false;
									}
									this.fase3.repaint();
									Thread.sleep(200);
								}
							}
							costa=9;
							if(indiceColisaoAcimaOuAbaixo==2) {
								retangulosDireitaColidiuFase2.set(3, false);
								retangulosEsquerdaColidiuFase2.set(4, false);
								retangulosEsquerdaColidiuFase2.set(2, true);
							}else if(indiceColisaoAcimaOuAbaixo==6) {
								retangulosEsquerdaColidiuFase2.set(1, true);
								retangulosEsquerdaColidiuFase2.set(7, false);
								retangulosDireitaColidiuFase2.set(7, false);
								retangulosDireitaColidiuFase2.set(5, true);
							}
							
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("loop")) {
							if(!repetir) {
								index=-1;
								repetir=true;

							}else {
								
								repetir=false;
								if(index+1==this.inventarioHorizontal.getQuadroPrincipal().getComponentCount()) {
									break;
								}
							}
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("pegar")) {
							if(fase1.isVisible()) {
								
									if(numeroDesafio>=0) {//SIGNIFICA QUE O PERSONAGEM PEGOU UM LIVRO
										if(!fase1.getLivros().get(numeroDesafio).isPegouLivro()) {
											this.fase1.setVisible(false);				
											
											//INFORMAR QUE UM DESAFIO ESTA VISIVEL E MUDAR OS RETANGULOS DE COLISÃO
											Dados.desafioAtivado=true;
	
											this.inventario.getQuadroComandos().removeAll();
											this.inventario.getComandos().removeAll(this.inventario.getComandos());
											this.inventario.getQtdComandos().removeAll(this.inventario.getQtdComandos());
											this.inventarioHorizontal.getQuadroPrincipal().removeAll();
											
											this.inventarioHorizontal.setPosInicialFuncao(15);
											this.inventarioHorizontal.setPosInicialMain(15);
											
											//UTILIZADOS PARA EXIBIR A QUANTIDADE DE COMANDOS NECESSARIOS PARA CADA FASE OU DESAFIO
											Dados.fase1Visivel=false;//
											if(numeroDesafio==0) {
												Dados.desafio1fase1Visivel=true;
											}else {
												Dados.desafio2Fase1visivel=true;
											}
											
											
											this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).setVisible(true);
											
											this.inventario.preencherComandos(true);
											ativarEventosDosComandosDoInventario();
											
											this.telaJogo.getContainerFase().repaint();
										}
									}else if(fase1.getCristal().isPegouCristal()) {//pegarCristal
										fase1.getCristal().setApareceuCristal(false);
										//atualizar inventario
									}
									
								
							}else if(fase2.isVisible()) {
								if(numeroDesafio>=0) {//SIGNIFICA QUE O PERSONAGEM PEGOU UM LIVRO
									if(!fase2.getLivros().get(numeroDesafio).isPegouLivro()) {
										this.fase2.setVisible(false);				
										
										//INFORMAR QUE UM DESAFIO ESTA VISIVEL E MUDAR OS RETANGULOS DE COLISÃO
										Dados.desafioAtivado=true;
	
										this.inventario.getQuadroComandos().removeAll();
										this.inventario.getComandos().removeAll(this.inventario.getComandos());
										this.inventario.getQtdComandos().removeAll(this.inventario.getQtdComandos());
										this.inventarioHorizontal.getQuadroPrincipal().removeAll();
										
										this.inventarioHorizontal.setPosInicialFuncao(15);
										this.inventarioHorizontal.setPosInicialMain(15);
										
										//UTILIZADOS PARA EXIBIR A QUANTIDADE DE COMANDOS NECESSARIOS PARA CADA FASE OU DESAFIO
										Dados.fase2Visivel=false;//
										
										if(numeroDesafio==0) {
											Dados.desafio1Fase2Visivel=true;
										}else {
											Dados.desafio2Fase2Visivel=true;
										}
										
										
										this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).setVisible(true);
										
										this.inventario.preencherComandos(true);
										ativarEventosDosComandosDoInventario();
										
										this.telaJogo.getContainerFase().repaint();
									}
								}else if(this.fase2.getCristal().isPegouCristal()) {
									fase2.getCristal().setApareceuCristal(false);
									//atualizar inventario

								}
							}else if(fase3.isVisible()) {
								if(numeroDesafio>=0) {//SIGNIFICA QUE O PERSONAGEM PEGOU UM LIVRO
									if(!fase3.getLivros().get(numeroDesafio).isPegouLivro()) {
										
										this.fase3.setVisible(false);				
										
										//INFORMAR QUE UM DESAFIO ESTA VISIVEL E MUDAR OS RETANGULOS DE COLISÃO
										Dados.desafioAtivado=true;
	
										this.inventario.getQuadroComandos().removeAll();
										this.inventario.getComandos().removeAll(this.inventario.getComandos());
										this.inventario.getQtdComandos().removeAll(this.inventario.getQtdComandos());
										this.inventarioHorizontal.getQuadroPrincipal().removeAll();
										
										this.inventarioHorizontal.setPosInicialFuncao(15);
										this.inventarioHorizontal.setPosInicialMain(15);
										
										//UTILIZADOS PARA EXIBIR A QUANTIDADE DE COMANDOS NECESSARIOS PARA CADA FASE OU DESAFIO
										Dados.fase3Visivel=false;//
										
										if(numeroDesafio==0) {
											System.out.println("peganooooo");
											Dados.desafio2Fase3Visivel=true;
										}else {
											Dados.desafio1Fase3Visivel=true;
										}
										
										
										this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).setVisible(true);
										
										this.inventario.preencherComandos(true);
										ativarEventosDosComandosDoInventario();
										
										this.telaJogo.getContainerFase().repaint();
									}
								}
								for(Cristal c: fase3.getCristais()) {
									if(this.fase1.getPersonagem().getRectangle().intersects(c.getRectangle())) {
										c.setApareceuCristal(true);
										//atualizar inventario
									}
								}
							}
							inicializarCoordenadasDoPercurso();
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("teleporte")) {
							//verifucar desafios
							if(Dados.desafioAtivado) {
								Rectangle magia = new Rectangle();
								Rectangle portalInicio = new Rectangle();
								if(Dados.desafio1fase1Visivel||Dados.desafio2Fase1visivel) {
									magia=this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getMagia().getRectangle();
									portalInicio = this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getInicio().getRectangle();
									if(magia.intersects(portalInicio)) {
										Dados.desafioAtivado=false;
										if(numeroDesafio==0) {
											Dados.desafio1fase1Visivel=false;
										}else {
											Dados.desafio2Fase1visivel=false;
										}
										Dados.fase1Visivel=true;
										
										this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).setVisible(false);
										this.fase1.setVisible(true);
										this.inventario.getQuadroComandos().removeAll();
										this.inventario.getQtdComandos().removeAll(this.inventario.getQtdComandos());
										this.inventario.getComandos().removeAll(this.inventario.getComandos());
										this.inventarioHorizontal.getQuadroPrincipal().removeAll();
										this.inventarioHorizontal.getQuadroFuncao().removeAll();
										this.inventarioHorizontal.setPosInicialMain(15);
										this.inventarioHorizontal.setPosInicialFuncao(15);
										this.inventario.preencherComandos(true);
										ativarEventosDosComandosDoInventario();
										this.telaJogo.getContainerFase().repaint();
										
									}
								}else if(Dados.desafio1Fase2Visivel||Dados.desafio2Fase2Visivel) {
									magia=this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getMagia().getRectangle();
									portalInicio = this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getInicio().getRectangle();
									if(magia.intersects(portalInicio)) {
										Dados.desafioAtivado=false;
										if(numeroDesafio==0) {
											Dados.desafio1Fase2Visivel=false;
										}else {
											Dados.desafio2Fase2Visivel=false;
										}
										Dados.fase2Visivel=true;
										this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).setVisible(false);
										this.fase2.setVisible(true);
										this.inventario.getQuadroComandos().removeAll();
										this.inventario.getQtdComandos().removeAll(this.inventario.getQtdComandos());
										this.inventario.getComandos().removeAll(this.inventario.getComandos());
										this.inventarioHorizontal.getQuadroPrincipal().removeAll();
										this.inventarioHorizontal.getQuadroFuncao().removeAll();
										this.inventarioHorizontal.setPosInicialMain(15);
										this.inventarioHorizontal.setPosInicialFuncao(15);
										this.inventario.preencherComandos(true);
										ativarEventosDosComandosDoInventario();
										
										this.telaJogo.getContainerFase().repaint();
									}
								
								}else if(Dados.desafio1Fase3Visivel||Dados.desafio2Fase3Visivel) {
										System.out.println("aaaaaaa");
										magia=this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getMagia().getRectangle();
										portalInicio = this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getInicio().getRectangle();
										if(magia.intersects(portalInicio)) {
											Dados.desafioAtivado=false;
											if(numeroDesafio==0) {
												Dados.desafio2Fase3Visivel=false;
											}else {
												Dados.desafio1Fase3Visivel=false;
											}
											Dados.fase3Visivel=true;
											this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).setVisible(false);
											this.fase3.setVisible(true);
											this.inventario.getQuadroComandos().removeAll();
											this.inventario.getQtdComandos().removeAll(this.inventario.getQtdComandos());
											this.inventario.getComandos().removeAll(this.inventario.getComandos());
											this.inventarioHorizontal.getQuadroPrincipal().removeAll();
											this.inventarioHorizontal.getQuadroFuncao().removeAll();
											this.inventarioHorizontal.setPosInicialMain(15);
											this.inventarioHorizontal.setPosInicialFuncao(15);
											this.inventario.preencherComandos(true);
											ativarEventosDosComandosDoInventario();
											
											this.telaJogo.getContainerFase().repaint();
										}
								}
							}
								
							else if(fase1.getPersonagem().isMagiaTeleporte()) {
								if(fase1.isVisible()) {
									this.mapaDasFasesPanel.getBackFase().setBounds(18, 430, 219,200);
									checarColisao(new Rectangle(320,440,80,40));
								}else if(fase2.isVisible()) {
									this.mapaDasFasesPanel.getBackFase().setBounds(314, 330, 219, 200);
									checarColisao(new Rectangle(260,460,80,40));
								}else if(fase3.isVisible()) {
									this.mapaDasFasesPanel.getBackFase().setBounds(622, 430, 219, 200);
									checarColisao(new Rectangle(480,30,80,40));
								}
								
								
							}
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("funcao")) {
							
						}
						index++;
					}
					this.inventarioHorizontal.getQuadroPrincipal().removeAll();
					this.inventarioHorizontal.getQuadroFuncao().removeAll();
					this.inventarioHorizontal.setPosInicialMain(15);
					this.inventarioHorizontal.setPosInicialFuncao(15);
					this.inventario.preencherComandos(this.fase1.getPersonagem().isMagiaTeleporte());
					ativarEventosDosComandosDoInventario();
					this.executando=false;
					this.telaJogo.getContainerFase().getJogarLabel().setIcon(new ImageIcon("imagens\\caldeirao.png"));
					this.index=0;
					this.telaJogo.getContainerFase().repaint();
				}else if(Dados.desafioAtivado&(!concluiuDesafio)) {//PERDER VIDA CASO NÃO CONCLUA O DESAFIO
					if(numeroDesafio>=0) {
						concluiuDesafio=true;
						this.inventario.getLifeBar().setValue(this.inventario.getLifeBar().getValue()-10);
					}
				}
				else if(this.iniciarJogo.isVisible()) {//EVITA QUE O NOME DO JOGADOR FIQUE CONGELADO
					this.iniciarJogo.repaint();
				}else if(this.mapaDasFasesPanel.isVisible()) {
					movimentarReinos();
				}else if(this.fase1.isVisible()) {
					this.fase1.repaint();
					Thread.sleep(500);
				}else if(this.fase2.isVisible()) {
					this.fase2.repaint();
				}else if(this.fase3.isVisible()) {
					this.fase3.repaint();
				}
				Thread.sleep(50);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void inicializarCoordenadasDoPercurso() {
		this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoVerticalCima().removeAll(this.telaJogo.getContainerFase().getDesafiosFase1().get(0).getRetangulosPercursoVerticalCima());
		this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().removeAll(this.telaJogo.getContainerFase().getDesafiosFase1().get(0).getRetangulosPercursoVerticalBaixo());
		this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().removeAll(this.telaJogo.getContainerFase().getDesafiosFase1().get(0).getRetangulosPercursoHorizontalDireita());
		this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().removeAll(this.telaJogo.getContainerFase().getDesafiosFase1().get(0).getRetangulosPercursoHorizontalEsquerda());
		
		this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalCima().removeAll(this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalCima());
		this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().removeAll(this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalBaixo());
		this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().removeAll(this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalDireita());
		this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().removeAll(this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda());
		
		this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().removeAll(this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima());
		this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().removeAll(this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo());
		this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().removeAll(this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita());
		this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().removeAll(this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda());
		
		
		if(Dados.desafio1fase1Visivel) {
			//VERTICAL ACIMA
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(280, 320, 20, 20));
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(480, 220, 20, 20));
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(320, 160, 20, 20));
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(220, 160, 20, 20));
			
			//VERTICAL ABAIXO
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(240,240,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(100,340,20,20));
			
			//HORIZONTAL DIREITA
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(485, 330, 10,40));
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(240, 320, 10, 20));
			//HORIZONTAL ESQUERDA
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(320, 220, 20, 20));
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(220, 160, 20, 20));
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(80, 220, 20, 40));
	
		}else if(Dados.desafio2Fase1visivel) {
			//VERTICAL ACIMA
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(200, 140, 20, 20));
			
			//VERTICAL ABAIXO
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(360,290,20,20));
			
			//HORIZONTAL DIREITA
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(380, 160, 10,40));
			
			//HORIZONTAL ESQUERDA
			this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(255, 280, 20, 20));
		}else if(Dados.desafio1Fase2Visivel) {
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(280,320,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(100,140,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(480,140,20,20));
			
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(480,360,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(280,420,40,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(100,360,20,20));
			
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(500,160,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(320,380,20,20));
			
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(80,340,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(340,340,20,20));//FINALIZA
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(260,380,20,40));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(80,160,20,20));
			
	
		}else if(Dados.desafio2Fase2Visivel) {
			
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(80,220,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(300,340,40,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(160,140,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(260,140,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(360,140,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(460,140,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(500,240,20,20));
			
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(80,380,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(300,380,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(160,260,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(260,260,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(360,260,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(460,280,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(500,380,20,20));
			
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(330,360,20,20));	
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(180,240,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(280,160,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(380,240,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(480,160,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(520,260,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(520,360,20,20));
			
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(320,360,20,20));//finaliza
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(60,360,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(60,240,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(140,160,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(240,240,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(340,160,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(440,260,20,20));
			
		
		}else if(Dados.desafio1Fase3Visivel) {
			
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(280,320,40,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(80,220,40,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(220,140,40,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(340,140,40,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(400,100,40,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(480,100,40,20));
			
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(280,420,40,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(80,360,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(220,260,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(340,260,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(480,360,40,20));
			
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(320,380,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(240,240,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(360,160,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(440,240,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(520,120,20,40));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(520,340,20,40));
			
			
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(260,380,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(300,340,20,40));//finaliza
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(60,340,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(60,240,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(200,160,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(320,240,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(380,120,20,40));
			
			
		}else if(Dados.desafio2Fase3Visivel) {
			
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(100,180,20,20));//finaliza
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(100,140,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(360,160,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(420,220,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(280,320,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(480,300,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalCima().add(new Rectangle(180,200,20,20));
			
			
			
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(100,160,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(240,165,40,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(360,230,40,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(420,340,40,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(480,420,40,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoVerticalBaixo().add(new Rectangle(280,420,40,20));
			
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(260,130,20,10));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(380,160,20,40));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(440,220,20,40));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(500,320,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(300,340,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalDireita().add(new Rectangle(200,240,20,20));
			
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(80,140,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(220,180,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(340,220,20,40));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(400,320,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(500,400,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(260,400,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(160,340,20,20));
			this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda().add(new Rectangle(80,220,20,20));
		}
		
	}

	
	public boolean rotinaColisaoLivros(Rectangle personagem, ArrayList<Rectangle> retangulosLivros,ArrayList<Livro> livros) {
		int indice=0;
		for(Rectangle r: retangulosLivros) {
			if(personagem.intersects(r)&(!livros.get(indice).isColidiu())){
				this.indice=indice;
				numeroDesafio=indice;
				livros.get(indice).setColidiu(true);
				return true;
			}else if(livros.get(indice).isColidiu()&(personagem.getY()>(livros.get(indice).getImagem().getHeight()+livros.get(indice).getY()))) {
				livros.get(indice).setColidiu(false);
			}else if(livros.get(indice).isColidiu()&(personagem.getX()>(livros.get(indice).getImagem().getWidth()+livros.get(indice).getX()))) {
				System.out.println("oiiiiaaa");
				livros.get(indice).setColidiu(false);
			}else if(livros.get(indice).isColidiu()&(personagem.getX()+personagem.getWidth())<livros.get(indice).getX()) {
				livros.get(indice).setColidiu(false);
			}
			numeroDesafio=-1;
			indice++;
		}
		
		
		return false;
	}
	
	public void checarColisao(Rectangle retangulo) {
		
		if(this.fase2.getPersonagem().getRectangle().intersects(retangulo)) {
			this.fase1.setVisible(false);
			this.fase2.setVisible(false);
			this.fase3.setVisible(false);
			this.telaJogo.getContainerFase().setVisible(false);
			this.mapaDasFasesPanel.setVisible(true);
			return;
		}
	}

	public boolean colidiuCristal() {
		if(fase1.isVisible()) {
			if(fase1.getPersonagem().getRectangle().intersects(fase1.getCristal().getRectangle())){
				fase1.getCristal().setPegouCristal(true);
				return true;
			}
		}else if(fase2.isVisible()) {
			if(fase2.getPersonagem().getRectangle().intersects(fase2.getCristal().getRectangle())){
				fase2.getCristal().setPegouCristal(true);
				return true;
			}
		}else if(fase3.isVisible()) {
			for(Cristal c: fase3.getCristais()) {
				if(this.fase1.getPersonagem().getRectangle().intersects(c.getRectangle())) {
					c.setPegouCristal(true);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checarColisaoLivros(){
		if(this.fase1.isVisible()) {
			return rotinaColisaoLivros(this.fase1.getPersonagem().getRectangle(),this.fase1.getRectangleLivros(),this.fase1.getLivros());
		}else if(this.fase2.isVisible()) {
			return rotinaColisaoLivros(this.fase2.getPersonagem().getRectangle(),this.fase2.getRectangleLivros(),this.fase2.getLivros());
		}else {
			return rotinaColisaoLivros(this.fase3.getPersonagem().getRectangle(),this.fase3.getRectangleLivros(),this.fase3.getLivros());
		}
		
		
	}
			
	public void mudarAparencia(Personagem personagem,String direcao) {
		if(direcao.equalsIgnoreCase("baixo")) {
			switch (frente) {
				case 0:
		
					personagem.setAparencia(0);
					break;
					
				case 1:
		
					personagem.setAparencia(1);
					break;
					
				case 2:
					
					personagem.setAparencia(2);
					frente=0;
					break;
					
				default:
					break;
			}
			frente++;
			personagem.setY(personagem.getY()+20);
			
		}else if(direcao.equalsIgnoreCase("esquerda")) {
			switch (esquerda) {
				case 6:

					personagem.setAparencia(6);
					break;
				case 7:
					
					personagem.setAparencia(7);
					break;
				case 8:

					personagem.setAparencia(8);
					esquerda=6;
					break;
				default:
					break;
			}
			esquerda++;
			personagem.setX(personagem.getX()-20);
		}else if(direcao.equalsIgnoreCase("direita")) {
			switch (direita) {
				case 3:
	
					personagem.setAparencia(3);
					break;
				case 4:
	
					personagem.setAparencia(4);
					break;
				case 5:
	
					personagem.setAparencia(5);
					direita=3;
					break;
				default:
					break;
			}
			direita++;
			personagem.setX(personagem.getX()+20);
		}else if(direcao.equalsIgnoreCase("cima")) {
			switch (costa) {
				case 9:
	
					personagem.setAparencia(9);
					break;
				case 10:
					
					personagem.setAparencia(10);	
					break;
				case 11:
	
					personagem.setAparencia(11);
					costa=9;
					break;
				default:
					break;
			}
			costa++;
			personagem.setY(personagem.getY()-20);
		}
		
	}
	
	
	public void finalizouDesafio() {
		if(pontoChegadaDesafio1==1||pontoChegadaDesafio2==0) {
			if(Dados.desafio1fase1Visivel||Dados.desafio2Fase1visivel) {

				exibirMensagenLonga.getMensagem().setText("PARABENS!!");
				exibirMensagenLonga.getMensagem().setBounds(160,30, 400, 20);
				exibirMensagenLonga.getSubMensagem().setText("Você liberou a magia de teleporte!");

				exibirMensagenLonga.setVisible(true);
				this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).setVisible(false);

				executando=false;
				baixoAndando=false;
				acimaAndando=false;
				direitaAndando=false;
				esquerdaAndando=false;

				frente=0;
				direita=3;
				esquerda=6;
				costa=9;

				//this.telaPrincipal.getFase1().setAtivarDesafio(false);
				this.fase1.setQtdObjSecundarios(this.fase1.getQtdObjSecundarios()+1);
				//this.inventario.getRotuloPrincipal().setText(this.fase1.getQtdObjSecundarios()+"/"+Dados.QTD_CRISTAIS_TOTAL);
				Dados.fase1Visivel=true;
				if(numeroDesafio==0) {
					Dados.desafio1fase1Visivel=false;
				}else {
					Dados.desafio2Fase1visivel=false;
				}
				//inventario.preencherComandos();
				colorirObjetivoSecundario(Dados.QTD_LIVROS_FASE1);
				this.fase1.getLivros().get(numeroDesafio).setPegouLivro(true);
				//this.fase1.getRectangleLivros().get(numeroDesafio).setBounds(-1, -1, 0, 0);
				this.fase1.getPersonagem().setMagiaTeleporte(true);
				this.inventario.getMagiaTeleporte().setVisible(true);
				this.fase1.setVisible(true);
				//numeroDesafio=-1;

				Dados.desafioAtivado=false;
				this.inventario.getQuadroComandos().removeAll();
				this.inventario.getComandos().removeAll(this.inventario.getComandos());
				this.inventario.getQtdComandos().removeAll(this.inventario.getQtdComandos());
				this.inventarioHorizontal.getQuadroPrincipal().removeAll();
				this.inventarioHorizontal.setPosInicialMain(15);
				this.inventarioHorizontal.setPosInicialFuncao(15);


				this.inventario.preencherComandos(true);
				
				ativarEventosDosComandosDoInventario();
				
			}else if(Dados.desafio1Fase2Visivel||Dados.desafio2Fase2Visivel) {
				//PONTOS DE CHEGADA PARA COLOCAR


				if(numeroDesafio==0) {
					exibirMensagenLonga.getMensagem().setText("PARABENS!!");
					exibirMensagenLonga.getMensagem().setBounds(160,30, 400, 20);
					exibirMensagenLonga.getSubMensagem().setText("");

					exibirMensagenLonga.setVisible(true);
					this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).setVisible(false);
					Dados.desafio1Fase2Visivel=false;
				}else {
					exibirMensagenLonga.getMensagem().setText("PARABENS!!");
					exibirMensagenLonga.getMensagem().setBounds(160,30, 400, 20);
					exibirMensagenLonga.getSubMensagem().setText("Você liberou a magia de ataque!");

					exibirMensagenLonga.setVisible(true);
					this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).setVisible(false);
					this.inventario.getMagiaAtaque().setVisible(true);
					Dados.desafio2Fase2Visivel=false;
				}

				executando=false;
				baixoAndando=false;
				acimaAndando=false;
				direitaAndando=false;
				esquerdaAndando=false;

				frente=0;
				direita=3;
				esquerda=6;
				costa=9;

				this.fase2.setQtdObjSecundarios(this.fase2.getQtdObjSecundarios()+1);
				//this.inventario.getRotuloPrincipal().setText(this.fase2.getQtdObjSecundarios()+"/"+Dados.QTD_CRISTAIS_TOTAL);
				//this.fase2.getLivros().get(numeroDesafio).setPegouLivro(true);
				Dados.fase2Visivel=true;
				colorirObjetivoSecundario(Dados.QTD_LIVROS_FASE2);
				this.fase2.getLivros().get(numeroDesafio).setPegouLivro(true);
				this.inventario.getQuadroComandos().removeAll();
				this.inventario.getComandos().removeAll(this.inventario.getComandos());
				this.inventario.getQtdComandos().removeAll(this.inventario.getQtdComandos());
				this.inventarioHorizontal.getQuadroPrincipal().removeAll();
				this.inventarioHorizontal.setPosInicialMain(15);
				this.inventarioHorizontal.setPosInicialFuncao(15);
				inventario.preencherComandos(true);
				ativarEventosDosComandosDoInventario();
				this.fase2.setVisible(true);

			}else if(Dados.desafio1Fase3Visivel||Dados.desafio2Fase3Visivel) {
				this.fase3.getLivros().get(numeroDesafio).setPegouLivro(true);
				Dados.fase3Visivel=true;
				if(numeroDesafio==0) {
					Dados.desafio1Fase3Visivel=false;
				}else {
					Dados.desafio2Fase3Visivel=false;
				}
				colorirObjetivoSecundario(Dados.QTD_LIVROS_FASE3);
				inventario.preencherComandos(true);
				ativarEventosDosComandosDoInventario();
				this.fase3.setVisible(true);

			}
			pontoChegadaDesafio1=-1;
			pontoChegadaDesafio2=-1;
			numeroDesafio=-1;
			concluiuDesafio=true;
			if(Dados.fase2Visivel||Dados.fase1Visivel||Dados.fase3Visivel) {
				
				
				this.telaJogo.getContainerFase().repaint();
			}
			//variavel utilizada para identificar q a locomoção e verificação das colisões sera mudada

		}else {
			concluiuDesafio=false;
		}
		
		
		

		
	}
	
	public boolean percursoVertical(String direcao) {
		if(fase1.isVisible()) {
			
			if(direcao.equalsIgnoreCase("baixo")) {
				for(Rectangle r:fase1.getRetangulosPercursoVerticalBaixo()) {
					if(this.fase1.getPersonagem().getRectangle().intersects(r)) {
						return false;
					}
				}
				return true;
			}else if(direcao.equalsIgnoreCase("cima")) {
				for(Rectangle r:fase1.getRetangulosPercursoVerticalCima()) {
					if(this.fase1.getPersonagem().getRectangle().intersects(r)) {
						return false;
					}
				}
				return true;
			}
			
		}else if(fase2.isVisible()) {
//			if(posicao==1||posicao==0||posicao==8) {
//				this.posicao=-1;
//			}
			if(direcao.equalsIgnoreCase("baixo")) {
				int indice=0;
				for(Rectangle r:fase2.getRetangulosPercursoVerticalBaixo()) {
					if(this.fase2.getPersonagem().getRectangle().intersects(r)) {
						indiceColisaoAcimaOuAbaixo=indice;
//						if(indice==8) {
//							this.posicao=8;
//						}
						if(indice<5) {
							if(retangulosAbaixoColidiuFase2.get(indice)) {
								for(int i=0;i<5;i++) {
									retangulosAbaixoColidiuFase2.set(indice,false);
									retangulosAcimaColidiuFase2.set(indice, false);
								}
								return false;
							}
							return true;
				
						}else {
							return false;
						}
			
						
					}
					indice++;
				}
				return true;
			}else if(direcao.equalsIgnoreCase("cima")) {
				int indice =0;
				for(Rectangle r:fase2.getRetangulosPercursoVerticalCima()) {
					if(this.fase2.getPersonagem().getRectangle().intersects(r)) {
						indiceColisaoAcimaOuAbaixo=indice;
						if(indice<5) {
							if(retangulosAcimaColidiuFase2.get(indice)) {
								for(int i=0;i<5;i++) {
									retangulosAbaixoColidiuFase2.set(indice,false);
									retangulosAcimaColidiuFase2.set(indice, false);
								}
								return false;
							}
							return true;
						}else {
							return false;
						}
						
					}
					indice++;
				}
				return true;
			}
		}else if(fase3.isVisible()) {
//			if(posicao==1||posicao==0) {
//				this.posicao=-1;
//			}
			if(direcao.equalsIgnoreCase("baixo")) {
				
				for(Rectangle r:fase3.getRetangulosPercursoVerticalBaixo()) {
					if(this.fase3.getPersonagem().getRectangle().intersects(r)) {
						
						return false;
					}
					
				}
				return true;
			}else if(direcao.equalsIgnoreCase("cima")) {
				int indice=0;
				for(Rectangle r:fase3.getRetangulosPercursoVerticalCima()) {
					if(this.fase3.getPersonagem().getRectangle().intersects(r)) {
//						if(indice==0) {
//							this.posicao=0;
//						}
						return false;
					}
					indice++;
				}
				return true;
			}
			
		}
		else if(Dados.desafioAtivado) {
			Magia m=null;
			ArrayList<DesafioPanel> desafios=null;
			if(Dados.desafio1fase1Visivel||Dados.desafio2Fase1visivel) {
				m=this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getMagia();
				desafios=this.telaJogo.getContainerFase().getDesafiosFase1();
			}else if(Dados.desafio1Fase2Visivel||Dados.desafio2Fase2Visivel) {
				m=this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getMagia();
				desafios=this.telaJogo.getContainerFase().getDesafiosFase2();
			}else if(Dados.desafio1Fase3Visivel||Dados.desafio2Fase3Visivel) {
				m=this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getMagia();
				desafios=this.telaJogo.getContainerFase().getDesafiosFase3();
			}
			Rectangle magia = new Rectangle(m.getX(), m.getY(), m.getLargura(), m.getAltura());
			if(direcao.equalsIgnoreCase("cima")) {
				int indice=0;
				for(Rectangle r:desafios.get(numeroDesafio).getRetangulosPercursoVerticalCima()) {
					if(magia.intersects(r)) {
						if(numeroDesafio==0&Dados.desafio2Fase3Visivel) {
							System.out.println("num creioo");
							pontoChegadaDesafio1=indice;
						}
						return false;
					}
					indice++;
				}
				return true;
			}else if(direcao.equalsIgnoreCase("baixo")) {
				
				for(Rectangle r:desafios.get(numeroDesafio).getRetangulosPercursoVerticalBaixo()) {
					if(magia.intersects(r)) {
						System.out.println(r.getY());
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean percursoHorizontal(String direcao) {
		if(fase1.isVisible()) {
//			if(posicao==1||posicao==0) {
//				this.posicao=-1;
//			}
			if(direcao.equalsIgnoreCase("direita")) {
				
				int indice=0;
				for(Rectangle r:fase1.getRetangulosPercursoHorizontalDireita()) {
					if(this.fase1.getPersonagem().getRectangle().intersects(r)) {
						System.out.println("Direita:"+retangulosDireitaColidiuFase1);
						
						if(indice<2) {
							if(retangulosDireitaColidiuFase1.get(indice)) {
							//	this.posicao=1;
								retangulosDireitaColidiuFase1.set(0,false);
								retangulosDireitaColidiuFase1.set(1,false);
								retangulosEsquerdaColidiuFase1.set(0,false);
								retangulosEsquerdaColidiuFase1.set(1,false);
								return false;
							}
							return true;
						}
						return false;
					}
					indice++;
				}
				return true;
			}else if(direcao.equalsIgnoreCase("esquerda")){
				
				int indice=0;
				for(Rectangle r:fase1.getRetangulosPercursoHorizontalEsquerda()) {
					if(this.fase1.getPersonagem().getRectangle().intersects(r)) {
						
						if(indice<2) {
							if(retangulosEsquerdaColidiuFase1.get(indice)) {
							//	posicao=1;
								retangulosDireitaColidiuFase1.set(0,false);
								retangulosDireitaColidiuFase1.set(1,false);
								retangulosEsquerdaColidiuFase1.set(0,false);
								retangulosEsquerdaColidiuFase1.set(1,false);
								return false;
							}
							return true;
						}
						return false;
					}
					indice++;
				}
				return true;
			}
		}else if(fase2.isVisible()) {
			System.out.println(direcao);
//			if(posicao==1||posicao==0) {
//				this.posicao=-1;
//			}
			if(direcao.equalsIgnoreCase("direita")) {
				
				int indice=0;
				for(Rectangle r:fase2.getRetangulosPercursoHorizontalDireita()) {
					
					if(this.fase2.getPersonagem().getRectangle().intersects(r)) {
						indiceColisaoDireitaOuEsquerda=indice;
						System.out.println("index:"+indice);
						if(indice<8) {
//							if(indice==0) {
//								this.posicao=0;
//							}
//							if(indice==4) {
//								if(opostoDireitaAcima) {
//									return false;
//								}
//								return true;							}
//							}else if(this.fase2.getPersonagem().getX()>(r.getX()+r.getWidth())) {
//								
//								retangulosDireitaColidiuFase2.set(indice,true);
////								if(opostoDireita) {
////									opostoDireita=false;
////									return false;
////								}
							if(retangulosDireitaColidiuFase2.get(indice)) {
								
								retangulosDireitaColidiuFase2.set(0,false);
								retangulosDireitaColidiuFase2.set(1,false);
								retangulosDireitaColidiuFase2.set(2,false);
								retangulosDireitaColidiuFase2.set(3,false);
								retangulosDireitaColidiuFase2.set(4,false);
								retangulosDireitaColidiuFase2.set(5,false);
								retangulosDireitaColidiuFase2.set(6,false);
								retangulosDireitaColidiuFase2.set(7,false);
								retangulosDireitaColidiuFase2.set(8,false);
								retangulosEsquerdaColidiuFase2.set(0, false);
								retangulosEsquerdaColidiuFase2.set(1, false);
								retangulosEsquerdaColidiuFase2.set(2, false);
								retangulosEsquerdaColidiuFase2.set(3, false);
								retangulosEsquerdaColidiuFase2.set(4, false);
								retangulosEsquerdaColidiuFase2.set(5, false);
								retangulosEsquerdaColidiuFase2.set(6, false);
								retangulosEsquerdaColidiuFase2.set(7, false);
								return false;
							}
							
							return true;
						}else {
							return false;
						}
						
					}	
					indice++;
						
				}
				return true;	
			}else if(direcao.equalsIgnoreCase("esquerda")){
					int indice=0;
					System.out.println("affffffffff");
					for(Rectangle r:fase2.getRetangulosPercursoHorizontalEsquerda()) {
						if(this.fase2.getPersonagem().getRectangle().intersects(r)) {
							indiceColisaoDireitaOuEsquerda=indice;
							System.out.println("aaaaaaaaaaaaaaaaa");
							if(indice<7) {
//								if(indice==1) {
//									this.posicao=1;
//								}
//								System.out.println("P:"+(this.fase2.getPersonagem().getX()+this.fase2.getPersonagem().getLargura())+" R:"+r.getX());
//								if(indice==2) {
//									if(opostoEsquerdaAcima) {
//										opostoEsquerdaAcima=false;
//										return false;
//									}
//									 
//									return true;
//								}else if((this.fase2.getPersonagem().getX()+this.fase2.getPersonagem().getLargura())<r.getX()) {
//									retangulosEsquerdaColidiuFase2.set(indice,true);
//									
//								}else 
								if(retangulosEsquerdaColidiuFase2.get(indice)) {
									retangulosDireitaColidiuFase2.set(0,false);
									retangulosDireitaColidiuFase2.set(1,false);
									retangulosDireitaColidiuFase2.set(2,false);
									retangulosDireitaColidiuFase2.set(3,false);
									retangulosDireitaColidiuFase2.set(4,false);
									retangulosDireitaColidiuFase2.set(5,false);
									retangulosDireitaColidiuFase2.set(6,false);
									retangulosDireitaColidiuFase2.set(7,false);
									retangulosDireitaColidiuFase2.set(8,false);
									retangulosEsquerdaColidiuFase2.set(0, false);
									retangulosEsquerdaColidiuFase2.set(1, false);
									retangulosEsquerdaColidiuFase2.set(2, false);
									retangulosEsquerdaColidiuFase2.set(3, false);
									retangulosEsquerdaColidiuFase2.set(4, false);
									retangulosEsquerdaColidiuFase2.set(5, false);
									retangulosEsquerdaColidiuFase2.set(6, false);
									retangulosEsquerdaColidiuFase2.set(7, false);			
									return false;
								}
								return true;
								
							}else {
								return false;
							}
							
						}
						indice++;
					}
					return true;
				
			}
				return true;
			}
		else if(fase3.isVisible()) {
			if(direcao.equalsIgnoreCase("direita")) {
				int indice=0;
				for(Rectangle r:fase3.getRetangulosPercursoHorizontalDireita()) {
					if(this.fase3.getPersonagem().getRectangle().intersects(r)) {
						//System.out.println("Direita:"+retangulosDireitaColidiuFase1);
						if(indice<1) {
							if(retangulosDireitaColidiuFase3.get(indice)) {
								retangulosDireitaColidiuFase3.set(0,false);
								retangulosEsquerdaColidiuFase3.set(0,false);
								return false;
							}
							return true;
						}
						return false;
					}
					indice++;
				}
				return true;
			}else if(direcao.equalsIgnoreCase("esquerda")){
				int indice=0;
				for(Rectangle r:fase3.getRetangulosPercursoHorizontalEsquerda()) {
					if(this.fase3.getPersonagem().getRectangle().intersects(r)) {
						if(indice<1) {
							if(retangulosEsquerdaColidiuFase3.get(indice)) {
								retangulosDireitaColidiuFase3.set(0,false);
								retangulosEsquerdaColidiuFase3.set(0,false);
								return false;
							}
							return true;
						}
						return false;
					}
					indice++;
				}
				return true;
			}
		}
		else if(Dados.desafioAtivado) {
			Magia m=null;
			ArrayList<DesafioPanel> desafios=null;
			if(Dados.desafio1fase1Visivel||Dados.desafio2Fase1visivel) {
				m=this.telaJogo.getContainerFase().getDesafiosFase1().get(numeroDesafio).getMagia();
				desafios=this.telaJogo.getContainerFase().getDesafiosFase1();
			}else if(Dados.desafio1Fase2Visivel||Dados.desafio2Fase2Visivel) {
				m=this.telaJogo.getContainerFase().getDesafiosFase2().get(numeroDesafio).getMagia();
				desafios=this.telaJogo.getContainerFase().getDesafiosFase2();
			}else if(Dados.desafio1Fase3Visivel||Dados.desafio2Fase3Visivel) {
				System.out.println("auuuuuuuuuuuuuuuu");
				m=this.telaJogo.getContainerFase().getDesafiosFase3().get(numeroDesafio).getMagia();
				desafios=this.telaJogo.getContainerFase().getDesafiosFase3();
			}
			Rectangle magia = new Rectangle(m.getX(), m.getY(), m.getLargura(), m.getAltura());
			if(direcao.equalsIgnoreCase("direita")) {
				int indice=0;
				for(Rectangle r:desafios.get(numeroDesafio).getRetangulosPercursoHorizontalDireita()) {
					if(magia.intersects(r)) {
						System.out.println(r.getX()+" "+r.getY());
						if(numeroDesafio==1&(!Dados.desafio2Fase1visivel)&(!Dados.desafio2Fase3Visivel)) {
							pontoChegadaDesafio2=indice;
						}else if(numeroDesafio==0&(Dados.desafio1fase1Visivel)) {
							pontoChegadaDesafio1=indice;
						}
						return false;
					}
					indice++;
				}
				return true;
			}else if(direcao.equalsIgnoreCase("esquerda")) {
				int indice=0;
				System.out.println("oiiiiiiiiii");
				for(Rectangle r:desafios.get(numeroDesafio).getRetangulosPercursoHorizontalEsquerda()) {
					if(magia.intersects(r)) {
						if(numeroDesafio==1) {
							pontoChegadaDesafio2=indice;
						}else if(numeroDesafio==0&(!Dados.desafio1fase1Visivel)) {
							pontoChegadaDesafio1=indice;
						}
						return false;
					}
					indice++;
				}
				return true;
			}
		}
		return false;
	}
	
	public void colorirObjetivoSecundario(int livro) {
		this.inventario.getRotuloSecundario().setText((Integer.parseInt(this.inventario.getRotuloSecundario().getText().substring(0,1))+1)+"/"+livro);
		if((Integer.parseInt(this.inventario.getRotuloSecundario().getText().substring(0,1)))==livro){
			this.inventario.getRotuloSecundario().setForeground(Color.GREEN);
			//FAZ COM QUE O CRISTAL AAPREÇA NA FASE CORRESPONDENTE
			if(Dados.fase1Visivel) {
				this.fase1.getCristal().setApareceuCristal(true);
			}else if(Dados.fase2Visivel) {
				this.fase2.getCristal().setApareceuCristal(true);
			}else if(Dados.fase3Visivel) {
				this.fase3.getCristais().get(0).setApareceuCristal(true);
				this.fase3.getCristais().get(1).setApareceuCristal(true);
			}
		}
	}
	
	public void colorirObjetivoPrincipal(int cristais) {
		this.inventario.getRotuloPrincipal().setText((Integer.parseInt(this.inventario.getRotuloPrincipal().getText().substring(0,1))+1)+"/"+cristais);
		if((Integer.parseInt(this.inventario.getRotuloPrincipal().getText().substring(0,1)))==cristais){
			this.inventario.getRotuloPrincipal().setForeground(Color.GREEN);
		}
	}
	
	public void AvisoComandoLimitado() {
		this.exibirMensagens.getMensagem().setText("Este comando está no limite!!");
		this.exibirMensagens.setVisible(true);
	}
	
	public  void ativarEventosDosComandosDoInventario() {
		for(JLabel j: this.inventario.getComandos()) {
			j.addMouseListener(this);
		}
	}
	
	public void movimentarReinos() {
		if(this.mapaDasFasesPanel.isVisible()) {
			if(this.mapaDasFasesPanel.getFase1IconPanel().getBounds().getY()>340 && subir) {
				this.yFase1--;
				this.mapaDasFasesPanel.getFase1IconPanel().setBounds(30,this.yFase1, 187, 158);
				if(this.mapaDasFasesPanel.getFase1IconPanel().getBounds().getY()==340) {
					subir=false;
				
				}
			}else {
				if(this.mapaDasFasesPanel.getFase1IconPanel().getBounds().getY()==350) {
					subir=true;
				}else {
					this.yFase1++;
					this.mapaDasFasesPanel.getFase1IconPanel().setBounds(this.mapaDasFasesPanel.getFase1IconPanel().getX(),this.yFase1, 187, 158);
				}
			}
			
			if(this.mapaDasFasesPanel.getFase2IconPanel().getY()<270 && !subirfase2) {
				this.yFase2++;
				this.mapaDasFasesPanel.getFase2IconPanel().setBounds(this.mapaDasFasesPanel.getFase2IconPanel().getX(),this.yFase2, 187, 158);
				if(this.mapaDasFasesPanel.getFase2IconPanel().getBounds().getY()==270) {
					subirfase2=true;
				}
			}else {
				if(this.mapaDasFasesPanel.getFase2IconPanel().getBounds().getY()==260) {
					subirfase2=false;
				}else {
					this.yFase2--;
					this.mapaDasFasesPanel.getFase2IconPanel().setBounds(this.mapaDasFasesPanel.getFase2IconPanel().getX(),this.yFase2, 187, 158);
					
				}
				
			}
			
			
			if(this.mapaDasFasesPanel.getFase3IconPanel().getY()>360 && subirfase3) {
				this.yFase3--;
				this.mapaDasFasesPanel.getFase3IconPanel().setBounds(this.mapaDasFasesPanel.getFase3IconPanel().getX(),this.yFase3, 187, 158);
				if(this.mapaDasFasesPanel.getFase3IconPanel().getBounds().getY()==360) {
					subirfase3=false;
				
				}
			}else {
				if(this.mapaDasFasesPanel.getFase3IconPanel().getBounds().getY()==370) {
					subirfase3=true;
				}else {
					this.yFase3++;
					this.mapaDasFasesPanel.getFase3IconPanel().setBounds(this.mapaDasFasesPanel.getFase3IconPanel().getX(),this.yFase3, 187, 158);
				}
			}
		}
		
	}
	
// INICIO DO METODO COLORIR	
	public void colorir(Color c,MouseEvent e, boolean dentro){
		if(this.menuPanel.isVisible()) {
			if(this.menuPanel.getJogarLabel()==e.getSource()) {
				
				if(dentro) {
					this.menuPanel.getJogarLabel().setCursor(new Cursor(Cursor.HAND_CURSOR));
				}else {
					this.menuPanel.getJogarLabel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
				this.menuPanel.getJogarLabel().setForeground(c);
				
			}else if(this.menuPanel.getSobreJLabel()==e.getSource()) {
				
				if(dentro) {
					this.menuPanel.getSobreJLabel().setCursor(new Cursor(Cursor.HAND_CURSOR));
				}else {
					this.menuPanel.getSobreJLabel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
				this.menuPanel.getSobreJLabel().setForeground(c);
				
			}else if(this.menuPanel.getCreditosLabel()==e.getSource()) {
				
				if(dentro) {
					this.menuPanel.getCreditosLabel().setCursor(new Cursor(Cursor.HAND_CURSOR));
				}else {
					this.menuPanel.getCreditosLabel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
				this.menuPanel.getCreditosLabel().setForeground(c);
				
			}else {
				
				if(dentro) {
					this.menuPanel.getSairLabel().setCursor(new Cursor(Cursor.HAND_CURSOR));
				}else {
					this.menuPanel.getSairLabel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
				this.menuPanel.getSairLabel().setForeground(c);
				
			}
		}else if(this.sobrePanel.isVisible()) {
			if(dentro) {
				this.sobrePanel.getVoltarLabel().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}else {
				this.sobrePanel.getVoltarLabel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			this.sobrePanel.getVoltarLabel().setForeground(c);
		}else if(this.creditosPanel.isVisible()) {
			if(dentro) {
				this.creditosPanel.getVoltarLabel().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}else {
				this.creditosPanel.getVoltarLabel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			this.creditosPanel.getVoltarLabel().setForeground(c);
		}
	}
	
// FIM DO METODO COLORIR	
	
	public void atualizarComandos() {
		if(cdLabel.getIcon().toString().substring(8,cdLabel.getIcon().toString().indexOf(".")).equals("cima")) {
			this.inventario.getQtdComandos().get(1).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(1).getText().substring(1))+1));
			this.inventario.getQtdComandos().get(1).setForeground(Color.GREEN);
		}else if(cdLabel.getIcon().toString().substring(8,cdLabel.getIcon().toString().indexOf(".")).equals("baixo")) {
			this.inventario.getQtdComandos().get(0).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(0).getText().substring(1))+1));
			this.inventario.getQtdComandos().get(0).setForeground(Color.GREEN);
		}else if(cdLabel.getIcon().toString().substring(8,cdLabel.getIcon().toString().indexOf(".")).equals("direita")) {
			this.inventario.getQtdComandos().get(2).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(2).getText().substring(1))+1));
			this.inventario.getQtdComandos().get(2).setForeground(Color.GREEN);
		}else if(cdLabel.getIcon().toString().substring(8,cdLabel.getIcon().toString().indexOf(".")).equals("esquerda")) {
			this.inventario.getQtdComandos().get(3).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(3).getText().substring(1))+1));
			this.inventario.getQtdComandos().get(3).setForeground(Color.GREEN);
		}else if(cdLabel.getIcon().toString().substring(8,cdLabel.getIcon().toString().indexOf(".")).equals("pegar")) {
			this.inventario.getQtdComandos().get(5).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(5).getText().substring(1))+1));
			this.inventario.getQtdComandos().get(5).setForeground(Color.GREEN);
		}else if(cdLabel.getIcon().toString().substring(8,cdLabel.getIcon().toString().indexOf(".")).equals("loop")) {
			this.inventario.getQtdComandos().get(4).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(4).getText().substring(1))+1));
			this.inventario.getQtdComandos().get(4).setForeground(Color.GREEN);
		}
		
	}
	
	public void resetar() {
		this.resetou=true;
		this.fase1.getPersonagem().setX(150);
		this.fase1.getPersonagem().setY(120);
		this.fase1.getPersonagem().setMagiaTeleporte(false);
		this.fase1.getPersonagem().setMagiaAtaque(false);
		this.inventario.getRotuloSecundario().setText("0/"+Dados.QTD_LIVROS_FASE1);
		this.inventario.getRotuloPrincipal().setText("0/"+Dados.QTD_LIVROS_FASE1);
		this.inventario.getRotuloSecundario().setForeground(Color.LIGHT_GRAY);
		//falta atualizar os inimigos
		
		for(int i=0;i<2;i++) {
			if(i==0) {
				this.fase1.getCristal().setApareceuCristal(false);
				this.fase2.getCristal().setApareceuCristal(false);
			}
			this.fase3.getCristais().get(i).setApareceuCristal(false);
			
			this.fase1.getLivros().get(i).setPegouLivro(false);
			this.fase2.getLivros().get(i).setPegouLivro(false);
			this.fase3.getLivros().get(i).setPegouLivro(false);
		}
		posicao=-1;
		pontoChegadaDesafio1=-1;
		pontoChegadaDesafio2=-1;
		this.inventario.getLifeBar().setValue(100);
		
		this.iniciarJogo.getNomeField().setText("");
		this.inventarioHorizontal.setPosInicialFuncao(15);
		this.inventarioHorizontal.setPosInicialMain(15);
		this.inventarioHorizontal.getQuadroFuncao().removeAll();
		this.inventarioHorizontal.getQuadroPrincipal().removeAll();
		this.inventario.getLifeBar().setValue(100);
		Dados.fase1Visivel=true;
		Dados.fase2Visivel=false;
		Dados.fase3Visivel=false;
		Dados.desafio1fase1Visivel=false;
		Dados.desafio1Fase2Visivel=false;
		Dados.desafio1Fase3Visivel=false;
		Dados.desafio2Fase1visivel=false;
		Dados.desafio2Fase2Visivel=false;
		Dados.desafio2Fase3Visivel=false;
		
		
		this.inventario.preencherComandos(false);
		
		
		if(this.querJogar) {
			this.fase1.setVisible(true);
		}
		
		
		this.fase2.setVisible(false);
		this.fase3.setVisible(false);
		ativarEventosDosComandosDoInventario();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.menuPanel.isVisible()) {//TELA HOME VISIVEL
			
			if(this.menuPanel.getJogarLabel()==e.getSource()) {
				this.iniciarJogo.show(true);
			}else if(this.menuPanel.getSobreJLabel()==e.getSource()) {
				this.menuPanel.setVisible(false);
				this.sobrePanel.setVisible(true);
			}else if(this.menuPanel.getCreditosLabel()==e.getSource()) {
				this.menuPanel.setVisible(false);
				this.creditosPanel.setVisible(true);
			}else if(this.menuPanel.getSairLabel()==e.getSource()) {
				System.exit(0);
			}
			
		}else if(this.sobrePanel.isVisible()) {//TELA SOBRE VISIVEL
			this.sobrePanel.setVisible(false);
			this.menuPanel.setVisible(true);
		}else if(this.creditosPanel.isVisible()) {//TELA DE CREDITOS VISIVEL
			this.creditosPanel.setVisible(false);
			this.menuPanel.setVisible(true);
		}else if(this.ajudaPanel.getFecharLabel()==e.getSource()) {
			this.ajudaPanel.dispose();
		}else if(this.mapaDasFasesPanel.isVisible()) {
			
			if(this.mapaDasFasesPanel.getVoltarLabel()==e.getSource()) {
				resetar();
				this.mapaDasFasesPanel.setVisible(false);
				this.menuPanel.setVisible(true);
				
			}else if(this.mapaDasFasesPanel.getInterrogacaoLabel()==e.getSource()) {
				this.ajudaPanel.show(true);
			}else if(this.mapaDasFasesPanel.getFase1IconPanel()==e.getSource()) {

				this.mapaDasFasesPanel.setVisible(false);

				Dados.fase1Visivel=true;
				Dados.fase2Visivel=false;
				Dados.fase3Visivel=false;

				this.inventario.preencherComandos(this.fase1.getPersonagem().isMagiaTeleporte());
				ativarEventosDosComandosDoInventario();
				this.inventario.getRotuloSecundario().setText(0+"/"+Dados.QTD_LIVROS_FASE1);

				this.fase1.setVisible(true);
				this.telaJogo.getContainerFase().setVisible(true);

				//FALTA O RESTO
			}else if(this.mapaDasFasesPanel.getFase2IconPanel()==e.getSource()) {
			//	System.out.println(this.fase1.getPersonagem().isMagiaTeleporte());
				if(this.fase1.getPersonagem().isMagiaTeleporte()) {
					this.fase2.getPersonagem().setX(280);
					this.fase2.getPersonagem().setY(440);
					this.mapaDasFasesPanel.setVisible(false);
					
					Dados.fase1Visivel=false;
					Dados.fase2Visivel=true;
					Dados.fase3Visivel=false;
					
					this.inventario.preencherComandos(true);
					this.inventario.getComandos().get(7).setVisible(true);
					this.inventario.getQtdComandos().get(7).setVisible(true);
					ativarEventosDosComandosDoInventario();
					this.inventario.getRotuloSecundario().setText(0+"/"+Dados.QTD_LIVROS_FASE2);
					this.fase2.setVisible(true);
					this.telaJogo.getContainerFase().setVisible(true);
				}else {
					exibirMensagenLonga.getMensagem().setText("Você não pode se teleportar pra este reino!");
					exibirMensagenLonga.getMensagem().setBounds(58,30, 400, 20);
					exibirMensagenLonga.getSubMensagem().setText("Não possui magia de teleporte!!");
					exibirMensagenLonga.show(true);;
				}
				
			}else if(this.mapaDasFasesPanel.getFase3IconPanel()==e.getSource()) {
				if(this.fase1.getPersonagem().isMagiaTeleporte()) {
					this.fase3.getPersonagem().setX(500);
					this.fase3.getPersonagem().setY(20);
					this.mapaDasFasesPanel.setVisible(false);
					
					Dados.fase1Visivel=false;
					Dados.fase2Visivel=false;
					Dados.fase3Visivel=true;
					
					this.inventario.preencherComandos(true);
					this.inventario.getComandos().get(7).setVisible(true);
					this.inventario.getQtdComandos().get(7).setVisible(true);
					ativarEventosDosComandosDoInventario();
			
					this.inventario.getRotuloSecundario().setText(0+"/"+Dados.QTD_LIVROS_FASE3);
					this.fase3.setVisible(true);
					this.telaJogo.getContainerFase().setVisible(true);
				}else {
					exibirMensagenLonga.getMensagem().setText("Você não pode se teleportar pra este reino!");
					exibirMensagenLonga.getMensagem().setBounds(58,30, 400, 20);
					exibirMensagenLonga.getSubMensagem().setText("Não possui magia de teleporte!!");
					exibirMensagenLonga.show(true);
				}
			}
	
		}else {
			if(this.telaJogo.getContainerFase().getIniciarLabel()==e.getSource()) {
				if(this.inventarioHorizontal.getQuadroPrincipal().getComponentCount()>0) {
					this.executando=true;
					this.telaJogo.getContainerFase().getJogarLabel().setIcon(new ImageIcon("imagens\\caldeirao2.png"));
					System.out.println("Entreii");
				}else {
					this.exibirMensagens.getMensagem().setText("Erro!! Insirá comandos no main!");
					this.exibirMensagens.show(true);
				}
				
			}else if(this.inventario.getSairLabel()==e.getSource()) {
				resetar();
				this.telaJogo.getContainerFase().setVisible(false);
				this.menuPanel.setVisible(true);
			
			}else if(this.inventario.getInterrogacaoLabel()==e.getSource()) {//FALTA FAZER
				this.ajudaPanel.show(true);
			}else if(this.inventarioHorizontal.getQuadroPrincipal()==e.getSource()) {
				
				this.inventarioHorizontal.getQuadroFuncao().setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,5,true), "Função",TitledBorder.LEFT , TitledBorder.TOP,new Font("Arial",Font.BOLD, 18),Color.WHITE));
				this.inventarioHorizontal.getQuadroPrincipal().setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black,5,true), "Main",TitledBorder.LEFT , TitledBorder.TOP,new Font("Arial",Font.BOLD, 18),Color.black));
				this.mainSelecionado=true;
				this.telaJogo.getContainerFase().repaint();
				
			}else if(this.inventarioHorizontal.getQuadroFuncao()==e.getSource()) {
				
				this.inventarioHorizontal.getQuadroPrincipal().setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,5,true), "Main",TitledBorder.LEFT , TitledBorder.TOP,new Font("Arial",Font.BOLD, 18),Color.WHITE));
				this.inventarioHorizontal.getQuadroFuncao().setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black,5,true), "Função",TitledBorder.LEFT , TitledBorder.TOP,new Font("Arial",Font.BOLD, 18),Color.black));
				this.mainSelecionado=false;
				this.telaJogo.repaint();
				
			}else if(this.inventarioHorizontal.getApagarMain()==e.getSource()) {
				
				int indice = this.inventarioHorizontal.getQuadroPrincipal().getComponentCount()-1;
				if(!(indice<0)) {
					if(indice==0) {
						this.inventarioHorizontal.setPosInicialMain(15);//INSERIR COMANDO NO INCIO
					}else {
						this.inventarioHorizontal.setPosInicialMain(this.inventarioHorizontal.getPosInicialMain()-43);
					}
					
					cdLabel=((JLabel)this.inventarioHorizontal.getQuadroPrincipal().getComponent(indice));
					atualizarComandos();
					this.inventarioHorizontal.getQuadroPrincipal().remove(indice);
					
					this.telaJogo.repaint();
					
				}else {
					this.exibirMensagens.getMensagem().setText("Erro!! Não á comandos no main!");
					this.exibirMensagens.show(true);
				}
				
			}else if(this.inventarioHorizontal.getApagarFunção()==e.getSource()) {
				int indice = this.inventarioHorizontal.getQuadroFuncao().getComponentCount()-1;
				if(!(indice<0)) {
					if(indice==0) {
						this.inventarioHorizontal.setPosInicialFuncao(15);//INSERIR COMANDO NO INCIO
					}else {
						this.inventarioHorizontal.setPosInicialFuncao(this.inventarioHorizontal.getPosInicialFuncao()-43);
					}
					
					cdLabel=((JLabel)this.inventarioHorizontal.getQuadroFuncao().getComponent(indice));
					atualizarComandos();
					this.inventarioHorizontal.getQuadroFuncao().remove(indice);
					
					this.telaJogo.repaint();
				}else {
					this.exibirMensagens.getMensagem().setText("Erro!! Não á comandos na função!");
					this.exibirMensagens.show(true);
				}
			}
			
			int indice=0;
			for(JLabel jl: this.inventario.getComandos()) {
				if(jl==e.getSource()) {
					if(this.inventarioHorizontal.getQuadroPrincipal().getComponentCount()==13) {
						exibirMensagens.getMensagem().setText("Erro!! Limite maximo atingido!");
					}else {
						System.out.println("imagens\\"+jl.getIcon().toString().substring(8));
						if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("baixo")) {
							
							if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))==0)){
								AvisoComandoLimitado();
							}else {
								if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1)==0) {
									this.inventario.getQtdComandos().get(0).setForeground(Color.LIGHT_GRAY);
								}
								this.inventario.getQtdComandos().get(indice).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1));
								JLabel temp=new JLabel(new ImageIcon("imagens\\"+jl.getIcon().toString().substring(8)));
								
								if(this.mainSelecionado) {
									temp.setBounds(this.inventarioHorizontal.getPosInicialMain(),18, 42, 46);
									this.inventarioHorizontal.getQuadroPrincipal().add(temp);
									this.inventarioHorizontal.setPosInicialMain(this.inventarioHorizontal.getPosInicialMain()+43);
								}else {
									temp.setBounds(this.inventarioHorizontal.getPosInicialFuncao(),18, 42, 46);
									this.inventarioHorizontal.getQuadroFuncao().add(temp);
									this.inventarioHorizontal.setPosInicialFuncao(this.inventarioHorizontal.getPosInicialFuncao()+43);
								}
								
								
							}
							
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("cima")) {
							if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))==0)){
								AvisoComandoLimitado();
							}else {
								if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1)==0) {
									this.inventario.getQtdComandos().get(1).setForeground(Color.LIGHT_GRAY);
								}
								this.inventario.getQtdComandos().get(indice).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1));
								JLabel temp=new JLabel(new ImageIcon("imagens\\"+jl.getIcon().toString().substring(8)));
								if(this.inventarioHorizontal.getQuadroPrincipal().isFocusable()) {
									temp.setBounds(this.inventarioHorizontal.getPosInicialMain(),18, 42, 46);
									this.inventarioHorizontal.getQuadroPrincipal().add(temp);
									this.inventarioHorizontal.setPosInicialMain(this.inventarioHorizontal.getPosInicialMain()+43);
								}else {
									temp.setBounds(this.inventarioHorizontal.getPosInicialFuncao(),18, 42, 46);
									this.inventarioHorizontal.getQuadroFuncao().add(temp);
									this.inventarioHorizontal.setPosInicialFuncao(this.inventarioHorizontal.getPosInicialFuncao()+43);
								}
							}
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("esquerda")) {
							
							if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))==0)){
								AvisoComandoLimitado();
							}else {
								if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1)==0) {
									this.inventario.getQtdComandos().get(3).setForeground(Color.LIGHT_GRAY);
								}
								this.inventario.getQtdComandos().get(indice).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1));
								JLabel temp=new JLabel(new ImageIcon("imagens\\"+jl.getIcon().toString().substring(8)));
								if(this.mainSelecionado) {
									temp.setBounds(this.inventarioHorizontal.getPosInicialMain(),18, 42, 46);
									this.inventarioHorizontal.getQuadroPrincipal().add(temp);
									this.inventarioHorizontal.setPosInicialMain(this.inventarioHorizontal.getPosInicialMain()+43);
								}else {
									temp.setBounds(this.inventarioHorizontal.getPosInicialFuncao(),18, 42, 46);
									this.inventarioHorizontal.getQuadroFuncao().add(temp);
									this.inventarioHorizontal.setPosInicialFuncao(this.inventarioHorizontal.getPosInicialFuncao()+43);
								}
							}
							
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("direita")) {
							
							if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))==0)){
								AvisoComandoLimitado();
							}else {
								if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1)==0) {
									this.inventario.getQtdComandos().get(2).setForeground(Color.LIGHT_GRAY);
								}
								this.inventario.getQtdComandos().get(2).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(2).getText().substring(1))-1));
								JLabel temp=new JLabel(new ImageIcon("imagens\\"+jl.getIcon().toString().substring(8)));
								if(this.mainSelecionado) {
									temp.setBounds(this.inventarioHorizontal.getPosInicialMain(),18, 42, 46);
									this.inventarioHorizontal.getQuadroPrincipal().add(temp);
									this.inventarioHorizontal.setPosInicialMain(this.inventarioHorizontal.getPosInicialMain()+43);
								}else {
									temp.setBounds(this.inventarioHorizontal.getPosInicialFuncao(),18, 42, 46);
									this.inventarioHorizontal.getQuadroFuncao().add(temp);
									this.inventarioHorizontal.setPosInicialFuncao(this.inventarioHorizontal.getPosInicialFuncao()+43);
								}
							}
							
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("loop")) {
							if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))==0)){
								AvisoComandoLimitado();
							}else {
								if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1)==0) {
									this.inventario.getQtdComandos().get(4).setForeground(Color.LIGHT_GRAY);
								}
								this.inventario.getQtdComandos().get(indice).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1));
								JLabel temp=new JLabel(new ImageIcon("imagens\\"+jl.getIcon().toString().substring(8)));
								if(this.mainSelecionado) {
									temp.setBounds(this.inventarioHorizontal.getPosInicialMain(),18, 42, 46);
									this.inventarioHorizontal.getQuadroPrincipal().add(temp);
									this.inventarioHorizontal.setPosInicialMain(this.inventarioHorizontal.getPosInicialMain()+43);
								}else {
									temp.setBounds(this.inventarioHorizontal.getPosInicialFuncao(),18, 42, 46);
									this.inventarioHorizontal.getQuadroFuncao().add(temp);
									this.inventarioHorizontal.setPosInicialFuncao(this.inventarioHorizontal.getPosInicialFuncao()+43);
								}
							}
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("pegar")) {
							if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))==0)){
								AvisoComandoLimitado();
							}else {
								if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1)==0) {
									this.inventario.getQtdComandos().get(5).setForeground(Color.LIGHT_GRAY);
								}
								this.inventario.getQtdComandos().get(indice).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1));
								JLabel temp=new JLabel(new ImageIcon("imagens\\"+jl.getIcon().toString().substring(8)));
								if(this.mainSelecionado) {
									temp.setBounds(this.inventarioHorizontal.getPosInicialMain(),18, 42, 46);
									this.inventarioHorizontal.getQuadroPrincipal().add(temp);
									this.inventarioHorizontal.setPosInicialMain(this.inventarioHorizontal.getPosInicialMain()+43);
								}else {
									temp.setBounds(this.inventarioHorizontal.getPosInicialFuncao(),18, 42, 46);
									this.inventarioHorizontal.getQuadroFuncao().add(temp);
									this.inventarioHorizontal.setPosInicialFuncao(this.inventarioHorizontal.getPosInicialFuncao()+43);
								}
							}
							
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("teleporte")) {
							if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))==0)){
								AvisoComandoLimitado();
							}else {
								if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1)==0) {
									this.inventario.getQtdComandos().get(indice).setForeground(Color.LIGHT_GRAY);
								}
								this.inventario.getQtdComandos().get(indice).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1));
								JLabel temp=new JLabel(new ImageIcon("imagens\\"+jl.getIcon().toString().substring(8)));
								if(this.mainSelecionado) {
									temp.setBounds(this.inventarioHorizontal.getPosInicialMain(),18, 42, 46);
									this.inventarioHorizontal.getQuadroPrincipal().add(temp);
									this.inventarioHorizontal.setPosInicialMain(this.inventarioHorizontal.getPosInicialMain()+43);
								}else {
									temp.setBounds(this.inventarioHorizontal.getPosInicialFuncao(),18, 42, 46);
									this.inventarioHorizontal.getQuadroFuncao().add(temp);
									this.inventarioHorizontal.setPosInicialFuncao(this.inventarioHorizontal.getPosInicialFuncao()+43);
								}
							}
						}else if(jl.getIcon().toString().substring(8,jl.getIcon().toString().indexOf(".")).equals("funcao")) {
							if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))==0)){
								AvisoComandoLimitado();
							}else if(mainSelecionado){
								if((Integer.parseInt(this.inventario.getQtdComandos().get(indice).getText().substring(1))-1)==0) {
									this.inventario.getQtdComandos().get(indice).setForeground(Color.LIGHT_GRAY);
								}
								this.inventario.getQtdComandos().get(6).setText(" "+(Integer.parseInt(this.inventario.getQtdComandos().get(6).getText().substring(1))-1));
								JLabel temp=new JLabel(new ImageIcon("imagens\\"+jl.getIcon().toString().substring(8)));
								
								temp.setBounds(this.inventarioHorizontal.getPosInicialMain(),18, 42, 46);
								this.inventarioHorizontal.getQuadroPrincipal().add(temp);
								this.inventarioHorizontal.setPosInicialMain(this.inventarioHorizontal.getPosInicialMain()+43);
								
							}else {
								//AVISO
								this.exibirMensagenLonga.getMensagem().setText("Erro! Esta instrução não é permitida!");
								this.exibirMensagenLonga.getSubMensagem().setText("Dentro do campo de Função!");
								this.exibirMensagenLonga.show(true);
							}
						}
						
						this.telaJogo.getContainerFase().repaint();
						
					}
					break;
				}
				indice++;
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(this.menuPanel.isVisible()||this.sobrePanel.isVisible()||this.creditosPanel.isVisible()) {
			colorir(Color.WHITE, e, true);
		}else if(this.mapaDasFasesPanel.isVisible()) {
			if(this.mapaDasFasesPanel.getFase1IconPanel()==e.getSource()) {
				this.mapaDasFasesPanel.getBackFase().setBounds(18, 430, 219,200);
				this.mapaDasFasesPanel.getFase1IconPanel().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}else if(this.mapaDasFasesPanel.getFase2IconPanel()==e.getSource()) {
				this.mapaDasFasesPanel.getBackFase().setBounds(314, 330, 219, 200);
				this.mapaDasFasesPanel.getFase2IconPanel().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}else if(this.mapaDasFasesPanel.getFase3IconPanel()==e.getSource()) {
				this.mapaDasFasesPanel.getBackFase().setBounds(622, 430, 219, 200);
				this.mapaDasFasesPanel.getFase3IconPanel().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}else if(this.telaJogo.getContainerFase().isVisible()) {
			
			if(this.inventarioHorizontal.getApagarMain()==e.getSource()) {
				this.inventarioHorizontal.getApagarMain().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}else if(this.inventarioHorizontal.getApagarFunção()==e.getSource()) {
				this.inventarioHorizontal.getApagarFunção().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}else if(this.telaJogo.getContainerFase().getIniciarLabel()==e.getSource()) {
				this.telaJogo.getContainerFase().getJogarLabel().setIcon(new ImageIcon("imagens\\caldeirao2.png"));
				this.telaJogo.getContainerFase().getIniciarLabel().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			for(JLabel jl: this.inventario.getComandos()) {
				if(jl==e.getSource()) {
					jl.setCursor(new Cursor(Cursor.HAND_CURSOR));
					break;						
				}
					
			}
		}else if(this.ajudaPanel.getFecharLabel()==e.getSource()) {
			this.ajudaPanel.getFecharLabel().setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(this.menuPanel.isVisible()||this.sobrePanel.isVisible()||this.creditosPanel.isVisible()) {
			colorir(corLetra, e, true);
		}else if(this.mapaDasFasesPanel.isVisible()) {
			if(this.mapaDasFasesPanel.getFase1IconPanel()==e.getSource()) {
				this.mapaDasFasesPanel.getFase1IconPanel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}else if(this.mapaDasFasesPanel.getFase2IconPanel()==e.getSource()) {
				this.mapaDasFasesPanel.getFase2IconPanel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}else if(this.mapaDasFasesPanel.getFase3IconPanel()==e.getSource()) {
				this.mapaDasFasesPanel.getFase3IconPanel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}else if(this.telaJogo.getContainerFase().isVisible()) {
			
			if(this.inventarioHorizontal.getApagarMain()==e.getSource()) {
				this.inventarioHorizontal.getApagarMain().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}else if(this.inventarioHorizontal.getApagarFunção()==e.getSource()) {
				this.inventarioHorizontal.getApagarFunção().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}else if(this.telaJogo.getContainerFase().getIniciarLabel()==e.getSource()) {
				if(!executando) {
					telaJogo.getContainerFase().getJogarLabel().setIcon(new ImageIcon("imagens\\caldeirao.png"));
				}
				this.telaJogo.getContainerFase().getIniciarLabel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			for(JLabel jl: this.inventario.getComandos()) {
				if(jl==e.getSource()) {
					jl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					break;
				}
					
			}
			
		}else if(this.ajudaPanel.getFecharLabel()==e.getSource()) {
			this.ajudaPanel.getFecharLabel().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(this.iniciarJogo.getJogarButton()==e.getSource()) {//informar nome do jogador
			
			if(!(this.iniciarJogo.getNomeField().getText().trim().length()==0)) {
				this.menuPanel.setFocusable(false);
				this.menuPanel.getJogarLabel().setFocusable(false);
				this.menuPanel.setVisible(false);
				this.mapaDasFasesPanel.getNomeDoJogador().setText("Jogador: "+iniciarJogo.getNomeField().getText());
				this.iniciarJogo.dispose();
				this.inventario.getNomeDoJogador().setText("Jogador: "+iniciarJogo.getNomeField().getText());
				this.mapaDasFasesPanel.setVisible(true);
	
			}else {
				this.exibirMensagens.getMensagem().setText("Erro!! Informe seu nome para jogar!");
				this.exibirMensagens.show(true);
			}
		}else if(this.iniciarJogo.getCancelarButton()==e.getSource()) {
			iniciarJogo.getNomeField().setText("");
			this.iniciarJogo.dispose();
		}
		else if(this.exibirMensagens.getOkButon()==e.getSource()) {
			this.exibirMensagens.dispose();
		}else if(this.exibirMensagenLonga.getOkButon()==e.getSource()) {
			this.exibirMensagenLonga.dispose();
		}else if(this.exibirGameOver.getJogarNovamente()==e.getSource()) {//JOGAR NOVAMENTE
			this.exibirGameOver.dispose();
			this.querJogar=true;
			resetar();
		}else if(this.exibirGameOver.getNaoJogar()==e.getSource()) {//NÃO QUER JOGAR
			this.exibirGameOver.dispose();
			this.querJogar=false;
			resetar();
			this.telaJogo.getContainerFase().setVisible(false);
			this.menuPanel.setVisible(true);
					
		}
	}
	
	
}
