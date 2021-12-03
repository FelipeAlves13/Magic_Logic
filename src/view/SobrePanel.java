package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SobrePanel extends JPanel{
	private JLabel fundoLabel,voltarLabel, informa��oLabel;
	private JLabel pergaminho;
	private JTextArea observa��es;
	public SobrePanel() {
		setLayout(null);
		setBounds(0,0,850,650);
		observa��es=new JTextArea("O Magic Logic � um jogo cujo objetivo � salvar a vida da irm� da bruxinha por meio de 4 cristas espalhados pelas Fases. Para conseguir os cristais o jogador precisa completar os desafios e coletar os cristais. A intera��o com o jogo se da por meio da programa��o em bloco."
				+ "                                                                                                               "+
				"                                                                                                                       "
				+ "Logo abaixo se encontra um link que ir� lhe direcionar para o tutorial do jogo!");
		observa��es.setLineWrap(true);
		//observa��es.set
		observa��es.setBackground(new Color(0,0,0,0));
		observa��es.setFont(new Font("Arial",Font.BOLD,18));
		observa��es.setForeground(Color.WHITE);
		observa��es.enable();
		observa��es.setBorder(null);
		observa��es.setBounds(175, 180, 510, 330);
		add(observa��es);
		pergaminho=new JLabel(new ImageIcon("imagens\\pergaminho.png"));
		pergaminho.setBounds(225, 400, 400, 150);
		add(pergaminho);
		voltarLabel = new JLabel("VOLTAR");
		voltarLabel.setFont(new Font("Serif",Font.BOLD, 37));
		voltarLabel.setBounds(360, 600, 170, 50);
		add(voltarLabel);
		
		fundoLabel = new JLabel(new ImageIcon("imagens\\ranking.png"));
		fundoLabel.setBounds(0,0,850,650);
		add(fundoLabel);
	}

	public JLabel getFundoLabel() {
		return fundoLabel;
	}

	public JLabel getVoltarLabel() {
		return voltarLabel;
	}
	
	
}
