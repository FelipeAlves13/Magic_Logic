package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SobrePanel extends JPanel{
	private JLabel fundoLabel,voltarLabel, informaçãoLabel;
	private JLabel pergaminho;
	private JTextArea observações;
	public SobrePanel() {
		setLayout(null);
		setBounds(0,0,850,650);
		observações=new JTextArea("O Magic Logic é um jogo cujo objetivo é salvar a vida da irmã da bruxinha por meio de 4 cristas espalhados pelas Fases. Para conseguir os cristais o jogador precisa completar os desafios e coletar os cristais. A interação com o jogo se da por meio da programação em bloco."
				+ "                                                                                                               "+
				"                                                                                                                       "
				+ "Logo abaixo se encontra um link que irá lhe direcionar para o tutorial do jogo!");
		observações.setLineWrap(true);
		//observações.set
		observações.setBackground(new Color(0,0,0,0));
		observações.setFont(new Font("Arial",Font.BOLD,18));
		observações.setForeground(Color.WHITE);
		observações.enable();
		observações.setBorder(null);
		observações.setBounds(175, 180, 510, 330);
		add(observações);
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
