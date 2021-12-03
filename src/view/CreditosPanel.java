package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreditosPanel extends JPanel{
	private JLabel fundoLabel,voltarLabel;
	private JLabel abreviacaoLabel, nomeFaculdadeLabel, nomeUnidadeLabel, discenteLabel, disciplinaLabel, cursoLabel, professorLabel;
	
	
	public CreditosPanel() {
		setLayout(null);
		setBounds(0,0,850,650);
		
		cursoLabel = new JLabel("Bacharelado em Sistemas de Informação");
		cursoLabel.setFont(new Font("Arial",Font.BOLD, 22));
		cursoLabel.setForeground(Color.WHITE);
		cursoLabel.setBounds(230, 150, 450, 30);
		add(cursoLabel);
		
		abreviacaoLabel=new JLabel("UAST/UFRPE");
		abreviacaoLabel.setFont(new Font("Arial",Font.BOLD, 22));
		abreviacaoLabel.setForeground(Color.WHITE);
		abreviacaoLabel.setBounds(360, 220, 150, 30);
		add(abreviacaoLabel);
		
		nomeUnidadeLabel=new JLabel("Unidade Academica de Serra Talhada");
		nomeUnidadeLabel.setFont(new Font("Arial",Font.BOLD, 22));
		nomeUnidadeLabel.setForeground(Color.WHITE);
		nomeUnidadeLabel.setBounds(240, 252, 450, 30);
		add(nomeUnidadeLabel);
		
		nomeFaculdadeLabel=new JLabel("Universidade Federal Rural de Pernambuco");
		nomeFaculdadeLabel.setFont(new Font("Arial",Font.BOLD, 22));
		nomeFaculdadeLabel.setForeground(Color.WHITE);
		nomeFaculdadeLabel.setBounds(215, 284, 450, 30);
		add(nomeFaculdadeLabel);	
		
		professorLabel = new JLabel("Richarlyson Alves D'emery");
		professorLabel.setFont(new Font("Arial",Font.BOLD, 22));
		professorLabel.setForeground(Color.WHITE);
		professorLabel.setBounds(190, 442, 450, 30);
		add(professorLabel);
		
		discenteLabel=new JLabel("Autores: Felipe Antonio Alves Leite");
		discenteLabel.setFont(new Font("Arial",Font.BOLD, 22));
		discenteLabel.setForeground(Color.WHITE);
		discenteLabel.setBounds(100, 411, 450, 30);
		add(discenteLabel);
				
		voltarLabel = new JLabel("VOLTAR");
		voltarLabel.setFont(new Font("Arial",Font.BOLD, 37));
		voltarLabel.setForeground(Color.BLACK);
		voltarLabel.setBounds(360, 600, 170, 50);
		add(voltarLabel);
		
		
		fundoLabel = new JLabel(new ImageIcon("imagens\\teladecreditos.png"));
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
