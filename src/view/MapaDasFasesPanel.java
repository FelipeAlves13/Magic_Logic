package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;

public class MapaDasFasesPanel extends JPanel {
	private JLabel fase1IconPanel, fase2IconPanel, fase3IconPanel;
	private JLabel titulo,sombraTitulo, voltarLabel,interrogacaoLabel;
	private JLabel bacground,nomeDoJogador,life,backFase;
	private JProgressBar lifebar;
	
	public MapaDasFasesPanel(){
		setLayout(null);
		setBounds(0,0, 850, 650);
		
		voltarLabel=new JLabel(new ImageIcon("imagens\\sair.png"));
		this.voltarLabel.setBounds(770,10, 30, 30);
		this.add(this.voltarLabel);
		
		this.interrogacaoLabel = new JLabel(new ImageIcon("imagens\\interrogacao.png"));
		this.interrogacaoLabel.setBounds(810,10, 30, 30);
		this.add(this.interrogacaoLabel);
		
		this.nomeDoJogador = new JLabel("Jogador: Felipe Antonio");
		this.nomeDoJogador.setFont(new Font("Arial",Font.BOLD, 13));
		this.nomeDoJogador.setBounds(75, 30, 270, 40);
		this.nomeDoJogador.setForeground(Color.WHITE);
		this.add(this.nomeDoJogador);
		
		this.life = new JLabel(new ImageIcon("imagens\\life.png"));
		this.life.setBounds(0,15,250,100);
		this.add(this.life);
		
		this.lifebar = new JProgressBar();
		this.lifebar.setValue(100);
		this.lifebar.setForeground(Color.GREEN);
		this.lifebar.setBounds(50, 62, 180, 21);
		this.add(this.lifebar);
		
		sombraTitulo = new JLabel("Teleporte-se para um reino");
		sombraTitulo.setFont(new Font("Arial",Font.BOLD, 30));
		sombraTitulo.setBounds(240, 150, 500, 40);
		sombraTitulo.setForeground(Color.white);
		add(sombraTitulo);
		
		JSpinner linha = new JSpinner();
		linha.setBounds(10, 190, 830, 2);
		linha.setEnabled(false);
		add(linha);
		
		
		
		fase1IconPanel = new JLabel(new ImageIcon("imagens\\fase1.png"));
		fase1IconPanel.setBounds(30, 350, 187, 158);
		add(fase1IconPanel);
		
		fase2IconPanel = new JLabel(new ImageIcon("imagens\\fase2.png"));
		fase2IconPanel.setBounds(330, 260, 187, 158);
		add(fase2IconPanel);
		
		fase3IconPanel = new JLabel(new ImageIcon("imagens\\fase3.png"));
		fase3IconPanel.setBounds(640, 370, 187, 158);
		add(fase3IconPanel);
	
		backFase =  new JLabel(new ImageIcon("imagens\\focoLuz.png"));
		//backFase.setBounds(18, 430, 219,200);//fase1
		//backFase.setBounds(314, 330, 219, 200);//fase2
		//backFase.setBounds(622, 430, 219, 200);
		
		add(backFase);
		
		bacground = new JLabel(new ImageIcon("imagens\\fundo.png"));
		bacground.setBounds(0,0, 850, 650);
		add(bacground);
		setVisible(false);
	}

	public JLabel getFase1IconPanel() {
		return fase1IconPanel;
	}


	public JLabel getFase2IconPanel() {
		return fase2IconPanel;
	}

	

	public JLabel getFase3IconPanel() {
		return fase3IconPanel;
	}

	public JLabel getTitulo() {
		return titulo;
	}

	public JLabel getSombraTitulo() {
		return sombraTitulo;
	}

	public JLabel getVoltarLabel() {
		return voltarLabel;
	}

	public JLabel getInterrogacaoLabel() {
		return interrogacaoLabel;
	}

	public JLabel getBacground() {
		return bacground;
	}

	public JLabel getNomeDoJogador() {
		return nomeDoJogador;
	}

	public JLabel getLife() {
		return life;
	}

	public JLabel getBackFase() {
		return backFase;
	}

	public JProgressBar getLifebar() {
		return lifebar;
	}

	
	
}
