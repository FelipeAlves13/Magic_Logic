package view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
	private JLabel fundo, jogarLabel,sobreLabel, creditosLabel,sairLabel;
	
	public MenuPanel() {
		
			setLayout(null);
			setBounds(0,0,850,650);
			
			jogarLabel = new JLabel("JOGAR");
			jogarLabel.setBounds(270, 232, 300, 70);
			jogarLabel.setFont(new Font("Serif",Font.BOLD, 54));
			jogarLabel.setHorizontalAlignment(JLabel.CENTER);
			add(jogarLabel);
			
			sobreLabel = new JLabel("SOBRE");
			sobreLabel.setBounds(270, 327, 300, 70);
			sobreLabel.setFont(new Font("Serif",Font.BOLD, 54));
			sobreLabel.setHorizontalAlignment(JLabel.CENTER);
			add(sobreLabel);
			
			creditosLabel = new JLabel("CRÉDITOS");
			creditosLabel.setBounds(270, 422, 300, 70);
			creditosLabel.setFont(new Font("Serif",Font.BOLD, 54));
			creditosLabel.setHorizontalAlignment(JLabel.CENTER);
			add(creditosLabel);
			
			sairLabel = new JLabel("SAIR");
			sairLabel.setBounds(270, 515, 300, 70);
			sairLabel.setFont(new Font("Serif",Font.BOLD, 54));
			sairLabel.setHorizontalAlignment(JLabel.CENTER);
			add(sairLabel);
			
			fundo = new JLabel(new ImageIcon("imagens\\menu.png"));
			fundo.setBounds(0,0,850,650);
			add(fundo);
	}

	public JLabel getJogarLabel() {
		return jogarLabel;
	}

	public JLabel getSobreJLabel() {
		return sobreLabel;
	}

	public JLabel getCreditosLabel() {
		return creditosLabel;
	}

	public JLabel getSairLabel() {
		return sairLabel;
	}
	
	
}
