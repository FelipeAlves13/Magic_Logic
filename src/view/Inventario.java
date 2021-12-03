package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.Dados;

public class Inventario extends JPanel {
	private JProgressBar vida;
	private JLabel nomeDoJogador, comandosLabel, movimentarLabel, interrogacaoLabel, vidalabel, objetivoLabel,
				   botaoLabel, fecharLabel, sairLabel, objetivoSecundario,objetivoPrincipal, rotuloSecundario, rotuloPrincipal;
	private ArrayList<JLabel> comandos,qtdComandos;
	private JPanel quadroPrincipal, quadroFuncao, quadroComandos;
	private JProgressBar lifeBar;
	private JComboBox<String> metodo;
	private JLabel magiaTeleporte, magiaAtaque;
	
	public Inventario() {
		
		this.setLayout(null);
		this.setBackground(new Color(47,79,79));
		this.setPreferredSize(new Dimension(200, 720));
		this.setOpaque(true);
//		fecharLabel = new JLabel(new ImageIcon("imagens\\fechar.png"));
//		fecharLabel.setFont(new Font("Serif",Font.BOLD, 14));
//		fecharLabel.setBounds(288, 0, 33, 33);
//		fecharLabel.setForeground(Color.WHITE);
//		add(fecharLabel);
//		
		this.nomeDoJogador = new JLabel("Jogador: Felipe Antonio");
		this.nomeDoJogador.setFont(new Font("Arial",Font.BOLD, 13));
		this.nomeDoJogador.setBounds(75, 33, 270, 40);
		this.nomeDoJogador.setForeground(Color.WHITE);
		this.add(this.nomeDoJogador);
		
		this.magiaTeleporte = new JLabel(new ImageIcon("imagens\\circulomagico.png"));
		this.magiaTeleporte.setBounds(126, 79, 40, 40);
		this.magiaTeleporte.setVisible(false);
		add(this.magiaTeleporte);
		
		this.magiaAtaque = new JLabel(new ImageIcon("imagens\\magiaMenor.png"));
		this.magiaAtaque.setBounds(166, 79, 40, 40);
		this.magiaAtaque.setVisible(false);
		add(this.magiaAtaque);
		
		this.vidalabel = new JLabel(new ImageIcon("imagens\\life.png"));
		this.vidalabel.setBounds(0,15,250,100);
		this.add(this.vidalabel);
		
		
		
		this.lifeBar = new JProgressBar();
		this.lifeBar.setValue(100);
		this.lifeBar.setForeground(Color.GREEN);
		this.lifeBar.setBounds(50, 62, 180, 21);
		this.add(this.lifeBar);
		
		
//		
		this.objetivoLabel = new JLabel("Objetivos");
		this.objetivoLabel.setFont(new Font("Arial",Font.BOLD, 15));
		this.objetivoLabel.setBounds(90, 120, 270, 40);
		this.objetivoLabel.setForeground(Color.WHITE);
		this.add(this.objetivoLabel);
		
		this.rotuloSecundario = new JLabel(0+"/"+Dados.QTD_LIVROS_FASE1);
		this.rotuloSecundario.setFont(new Font("Arial",Font.BOLD, 14));
		this.rotuloSecundario.setBackground(null);
		this.rotuloSecundario.setForeground(Color.LIGHT_GRAY);
		//this.rotuloSecundario.setOpaque(true);
		this.rotuloSecundario.setBounds(40, 215, 30, 20);
		this.add(this.rotuloSecundario);
		
		
		
		this.objetivoSecundario= new JLabel(new ImageIcon("imagens\\LivroMagia.png"));
		this.objetivoSecundario.setFont(new Font("Arial",Font.BOLD, 18));
		this.objetivoSecundario.setBounds(20, 155, 65, 70);
		this.objetivoSecundario.setForeground(Color.WHITE);
		this.add(this.objetivoSecundario);
		
		this.rotuloPrincipal = new JLabel(" 0/4");
		this.rotuloPrincipal.setFont(new Font("Arial",Font.BOLD, 14));
		this.rotuloPrincipal.setBackground(null);
		this.rotuloPrincipal.setForeground(Color.LIGHT_GRAY);
		this.rotuloPrincipal.setBounds(190, 215, 30, 20);
		this.add(this.rotuloPrincipal);
		
		this.objetivoPrincipal= new JLabel(new ImageIcon("imagens\\Cristal.png"));
		this.objetivoPrincipal.setFont(new Font("Arial",Font.BOLD, 18));
		this.objetivoPrincipal.setBounds(170, 155, 65, 70);
		this.objetivoPrincipal.setForeground(Color.WHITE);
		this.add(this.objetivoPrincipal);
		
		this.comandosLabel = new JLabel("Comandos");
		this.comandosLabel.setFont(new Font("Arial",Font.BOLD, 15));
		this.comandosLabel.setBounds(88, 225, 270, 40);
		this.comandosLabel.setForeground(Color.WHITE);
		this.add(this.comandosLabel);
				
		this.quadroComandos = new JPanel(null);
		this.quadroComandos.setBounds(0, 260,300,165);
		this.quadroComandos.setBackground(null);
		this.qtdComandos = new ArrayList<JLabel>();
		this.comandos = new ArrayList<JLabel>();
		
		preencherComandos(false);
		
		this.add(quadroComandos);
		
		this.interrogacaoLabel = new JLabel(new ImageIcon("imagens\\interrogacao.png"));
		this.interrogacaoLabel.setBounds(215,0, 30, 30);
		this.add(this.interrogacaoLabel);
		
		this.sairLabel = new JLabel(new ImageIcon("imagens\\sair.png"));
		//sairLabel.setFont(new Font("Serif",Font.BOLD, 20));
		this.sairLabel.setBounds(170,0, 30, 30);
	//	sairLabel.setForeground(Color.WHITE);
		this.add(this.sairLabel);
		
	}
	
	public void preencherComandos(boolean visivel) {
		this.qtdComandos.removeAll(this.qtdComandos);
		this.comandos.removeAll(this.comandos);
		this.quadroComandos.removeAll();
		
		if(Dados.fase1Visivel) {
			
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_FRENTE_FASE1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_COSTA_FASE1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_DIREITA_FASE1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_ESQUERDA_FASE1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_LOOP_FASE1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_PEGAR_FASE1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_PEGAR_FASE1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_PEGAR_FASE1));
			
			this.comandos.add(new JLabel(new ImageIcon("imagens\\baixo.png")));//0
			this.comandos.add(new JLabel(new ImageIcon("imagens\\cima.png")));//1
			this.comandos.add(new JLabel(new ImageIcon("imagens\\direita.png")));//2
			this.comandos.add(new JLabel(new ImageIcon("imagens\\esquerda.png")));//3
			this.comandos.add(new JLabel(new ImageIcon("imagens\\loop.png")));//4
			this.comandos.add(new JLabel(new ImageIcon("imagens\\pegar.png")));//5
			this.comandos.add(new JLabel(new ImageIcon("imagens\\funcao.png")));//6
			this.comandos.add(new JLabel(new ImageIcon("imagens\\teleporte.png")));//7
		}else if(Dados.desafio1fase1Visivel){
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_FRENTE_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_COSTA_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_DIREITA_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_ESQUERDA_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_FUNCAO_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_FUNCAO_DESAFIO1));
			
			this.comandos.add(new JLabel(new ImageIcon("imagens\\baixo.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\cima.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\direita.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\esquerda.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\teleporte.png")));
			//comandos.add(new JLabel(new ImageIcon("imagens\\loop.png")));
			//comandos.add(new JLabel(new ImageIcon("imagens\\pegar.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\funcao.png")));
		}else if(Dados.desafio2Fase1visivel){
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_FRENTE_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_COSTA_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_DIREITA_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_ESQUERDA_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_FUNCAO_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_FUNCAO_FASE3_DESAFIO2));
			
			this.comandos.add(new JLabel(new ImageIcon("imagens\\baixo.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\cima.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\direita.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\esquerda.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\teleporte.png")));
			//comandos.add(new JLabel(new ImageIcon("imagens\\loop.png")));
			//comandos.add(new JLabel(new ImageIcon("imagens\\pegar.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\funcao.png")));
		}else if(Dados.fase2Visivel) {
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_FRENTE_FASE2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_COSTA_FASE2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_DIREITA_FASE2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_ESQUERDA_FASE2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_LOOP_FASE2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_PEGAR_FASE2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_PEGAR_FASE2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_PEGAR_FASE3));
			
			this.comandos.add(new JLabel(new ImageIcon("imagens\\baixo.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\cima.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\direita.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\esquerda.png")));

			this.comandos.add(new JLabel(new ImageIcon("imagens\\loop.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\pegar.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\funcao.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\teleporte.png")));
		}else if(Dados.desafio1Fase2Visivel){
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_FRENTE_FASE2_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_COSTA_FASE2_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_DIREITA_FASE2_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_ESQUERDA_FASE2_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_FUNCAO_FASE2_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_FUNCAO_FASE2_DESAFIO1));
			
			this.comandos.add(new JLabel(new ImageIcon("imagens\\baixo.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\cima.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\direita.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\esquerda.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\teleporte.png")));
			//comandos.add(new JLabel(new ImageIcon("imagens\\loop.png")));
			//comandos.add(new JLabel(new ImageIcon("imagens\\pegar.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\funcao.png")));
		}else if(Dados.desafio2Fase2Visivel){
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_FRENTE_FASE2_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_COSTA_FASE2_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_DIREITA_FASE2_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_ESQUERDA_FASE2_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_FUNCAO_FASE2_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_FUNCAO_FASE2_DESAFIO1));
			
			this.comandos.add(new JLabel(new ImageIcon("imagens\\baixo.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\cima.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\direita.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\esquerda.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\teleporte.png")));
			
			//comandos.add(new JLabel(new ImageIcon("imagens\\loop.png")));
			//comandos.add(new JLabel(new ImageIcon("imagens\\pegar.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\funcao.png")));
		}else if(Dados.fase3Visivel) {
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_FRENTE_FASE3));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_COSTA_FASE3));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_DIREITA_FASE3));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_ESQUERDA_FASE3));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_LOOP_FASE3));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_PEGAR_FASE3));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_PEGAR_FASE3));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_PEGAR_FASE3));
			
			this.comandos.add(new JLabel(new ImageIcon("imagens\\baixo.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\cima.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\direita.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\esquerda.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\loop.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\pegar.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\funcao.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\teleporte.png")));
		}else if(Dados.desafio1Fase3Visivel){
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_FRENTE_FASE3_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_COSTA_FASE3_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_DIREITA_FASE3_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_ESQUERDA_FASE3_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_FUNCAO_FASE3_DESAFIO1));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_FUNCAO_FASE2_DESAFIO1));
			
			this.comandos.add(new JLabel(new ImageIcon("imagens\\baixo.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\cima.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\esquerda.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\direita.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\teleporte.png")));
			//comandos.add(new JLabel(new ImageIcon("imagens\\loop.png")));
			//comandos.add(new JLabel(new ImageIcon("imagens\\pegar.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\funcao.png")));
		}else if(Dados.desafio2Fase3Visivel){
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_FRENTE_FASE3_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_COSTA_FASE3_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_DIREITA_FASE3_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_ANDAR_ESQUERDA_FASE3_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_FUNCAO_FASE3_DESAFIO2));
			this.qtdComandos.add(new JLabel(" "+Dados.QTD_FUNCAO_FASE3_DESAFIO2));
			
			this.comandos.add(new JLabel(new ImageIcon("imagens\\baixo.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\cima.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\direita.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\esquerda.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\teleporte.png")));
			//comandos.add(new JLabel(new ImageIcon("imagens\\loop.png")));
			//comandos.add(new JLabel(new ImageIcon("imagens\\pegar.png")));
			this.comandos.add(new JLabel(new ImageIcon("imagens\\funcao.png")));
			
		}

		
		
		int w=19,z=19;
		int cont=0;
		for(JLabel jl:qtdComandos) {
			if(cont>=5) {
				jl.setBounds(z,110, 17, 15);
				z+=50;
			}else {
				jl.setBounds(w, 46, 17, 15);
				
			}
			jl.setOpaque(true);
			jl.setFont(new Font("Arial",Font.BOLD, 14));
			jl.setBackground(null);
			
			jl.setForeground(Color.green);
			if(cont==7) {
				jl.setVisible(visivel);
			}
			cont++;
			quadroComandos.add(jl);
			w+=50;
		}
		
		
		int x=5,y=5;
		cont=0;
		for(JLabel jl:comandos) {
			jl.setBackground(null);
			if(cont>=5) {
				jl.setBounds(y, 67, 42, 46);
				y+=50;
			}else {
				jl.setBounds(x, 0, 42, 46);
				
			}
			if(cont==7) {
				jl.setVisible(visivel);
			}
			cont++;

			quadroComandos.add(jl);
			x+=50;
		}
	}
	
	public JProgressBar getVida() {
		return vida;
	}

	public JLabel getNomeDoJogador() {
		return nomeDoJogador;
	}

	public JLabel getComandosLabel() {
		return comandosLabel;
	}

	public JLabel getMovimentarLabel() {
		return movimentarLabel;
	}

	public JLabel getInterrogacaoLabel() {
		return interrogacaoLabel;
	}

	public JLabel getVidalabel() {
		return vidalabel;
	}

	public JLabel getObjetivoLabel() {
		return objetivoLabel;
	}

	public JLabel getBotaoLabel() {
		return botaoLabel;
	}

	public JLabel getFecharLabel() {
		return fecharLabel;
	}

	public JLabel getSairLabel() {
		return sairLabel;
	}

	public JLabel getObjetivoSecundario() {
		return objetivoSecundario;
	}

	public JLabel getObjetivoPrincipal() {
		return objetivoPrincipal;
	}

	public ArrayList<JLabel> getComandos() {
		return comandos;
	}

	public JPanel getQuadroPrincipal() {
		return quadroPrincipal;
	}

	public JPanel getQuadroFuncao() {
		return quadroFuncao;
	}

	public JProgressBar getLifeBar() {
		return lifeBar;
	}

	public JLabel getRotuloSecundario() {
		return rotuloSecundario;
	}

	public JLabel getRotuloPrincipal() {
		return rotuloPrincipal;
	}

	public ArrayList<JLabel> getQtdComandos() {
		return qtdComandos;
	}

	public JComboBox<String> getMetodo() {
		return metodo;
	}

	public JPanel getQuadroComandos() {
		return quadroComandos;
	}

	public JLabel getMagiaTeleporte() {
		return magiaTeleporte;
	}

	public JLabel getMagiaAtaque() {
		return magiaAtaque;
	}
	
	
	
	
}
