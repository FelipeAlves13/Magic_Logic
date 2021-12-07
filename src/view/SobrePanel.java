package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class SobrePanel extends JPanel{
	private JLabel fundoLabel,voltarLabel, informaçãoLabel, link;
	private JLabel pergaminho;
	private JTextPane observacoes;
	public SobrePanel() {
		setLayout(null);
		setBounds(0,0,850,650);
		observacoes=new JTextPane();
		
		observacoes.setContentType("text/html");
		observacoes.setText("<html><body style='width:390px;background-color:#8f5a3a;margin:-8px;'><p style='text-align: justify;'>O Magic Logic é um jogo cujo objetivo é salvar a vida da irmã da bruxinha por meio de 4 cristas espalhados pelas Fases. Para conseguir os cristais, o jogador precisa completar os desafios e coletar os cristais. A interação com o jogo se da por meio da programação em bloco.</p>" + "<p>Logo abaixo se encontra um link que irá lhe direcionar para o tutorial do jogo!</p></body></html>");
		//observacoes.setBackground(new Color(0,0,0,0));
		observacoes.setFont(new Font("Arial",Font.BOLD,18));
		observacoes.setForeground(Color.WHITE);
		observacoes.setEditable(false);
		observacoes.setBorder(null);
		observacoes.setBounds(175, 180, 500, 200);
		add(observacoes);
		
		link=new JLabel("Clique aki");
		link.setFont(new Font("Serif",Font.BOLD, 22));
		link.setForeground(Color.BLACK);
		link.setBounds(370, 460, 170, 30);
		add(link);
		
		pergaminho=new JLabel(new ImageIcon("imagens\\pergaminho.png"));
		pergaminho.setBounds(225, 400, 400, 150);
		add(pergaminho);
		
		voltarLabel = new JLabel("VOLTAR");
		voltarLabel.setFont(new Font("Serif",Font.BOLD, 37));
		voltarLabel.setBounds(360, 600, 170, 40);
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

	public JLabel getLink() {
		return link;
	}
	
	
	
}
