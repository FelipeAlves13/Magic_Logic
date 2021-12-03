package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import util.Limitador;

public class ExibirMensagem extends JDialog{
	private JButton okButon, jogarButton, cancelarButton, jogarNovamente, naoJogar;
	private JLabel mensagem,subMensagem,tituloLabel, mensagemFimJogo;
	private JLabel gameover;
	private JTextField nomeField;
	
	
	public ExibirMensagem() {
		super();
		setLayout(null);
		setSize(400,150);
		setLocationRelativeTo(null);
		setModal(true);
		setAlwaysOnTop(true);
		
		setUndecorated(true);
		getRootPane().setOpaque(false);
		getContentPane().setBackground(new Color(0,0,0,0));
		setBackground(new Color(0,0,0,0));
		JLabel fundoImg = new JLabel(new ImageIcon("imagens\\pergaminho.png"));
		fundoImg.setBounds(0, 0, 400, 150);
		
		tituloLabel = new JLabel("Informe seu nome para jogar");
		tituloLabel.setBounds(80,20, 400, 20);
		tituloLabel.setForeground(new Color(0,0,0));
		tituloLabel.setFont(new Font("Arial",Font.BOLD,18));
		
		nomeField = new JTextField();
		nomeField.setDocument(new Limitador(14));
		nomeField.setBounds(100, 50, 200, 30);
		nomeField.setBackground(new Color(0, 0, 0, 0));
		nomeField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		nomeField.setFont(new Font("Arial", Font.BOLD,13));
		
		fundoImg.setBackground(null);
		jogarButton=new JButton("Jogar");
		jogarButton.setBackground(new Color(47,79,79));
		jogarButton.setForeground(Color.WHITE);
		jogarButton.setFont(new Font("Arial",Font.BOLD,15));
		jogarButton.setBounds(100,90, 80, 30);
		
		cancelarButton = new JButton("Cancelar");
		cancelarButton.setBackground(new Color(47,79,79));
		cancelarButton.setForeground(Color.WHITE);
		cancelarButton.setFont(new Font("Arial",Font.BOLD,15));
		cancelarButton.setBounds(200,90, 100, 30);
		
		add(tituloLabel);
		add(nomeField);
		add(jogarButton);
		add(cancelarButton);
		add(fundoImg);
		show(false);
	}
	
	
	public ExibirMensagem(String msg) {
		super();
		setLayout(null);
		setSize(400,150);
		setLocationRelativeTo(null);
		setModal(true);
		setAlwaysOnTop(true);
		
		setUndecorated(true);
		getRootPane().setOpaque(false);
		getContentPane().setBackground(new Color(0,0,0,0));
		setBackground(new Color(0,0,0,0));
		JLabel fundoImg = new JLabel(new ImageIcon("imagens\\pergaminho.png"));
		fundoImg.setBounds(0, 0, 400, 150);
		
		mensagem = new JLabel(msg);
		mensagem.setFont(new Font("Arial",Font.BOLD,13));
		mensagem.setBounds(90,30, 400, 20);
		mensagem.setForeground(Color.black);
		mensagem.setFont(new Font("Arial",Font.BOLD,15));
		
		
		fundoImg.setBackground(null);
		okButon=new JButton("Ok");
		okButon.setBackground(new Color(47,79,79));
		okButon.setForeground(Color.WHITE);
		okButon.setFont(new Font("Arial",Font.BOLD,15));
		okButon.setBounds(170,90, 50, 30);
		add(mensagem);
		add(okButon);
		add(fundoImg);
		show(false);
	}
	
	public ExibirMensagem(String msg, String subMsg) {
		super();
		setLayout(null);
		setSize(400,150);
		setLocationRelativeTo(null);
		setModal(true);
		setAlwaysOnTop(true);
		
		setUndecorated(true);
		getRootPane().setOpaque(false);
		getContentPane().setBackground(new Color(0,0,0,0));
		setBackground(new Color(0,0,0,0));
		JLabel fundoImg = new JLabel(new ImageIcon("imagens\\pergaminho.png"));
		fundoImg.setBounds(0, 0, 400, 150);
		
		mensagem = new JLabel(msg);
		mensagem.setFont(new Font("Arial",Font.BOLD,13));
		mensagem.setBounds(68,30, 400, 20);
		mensagem.setForeground(Color.black);
		mensagem.setFont(new Font("Arial",Font.BOLD,15));
		
		subMensagem = new JLabel(subMsg);
		subMensagem.setFont(new Font("Arial",Font.BOLD,13));
		subMensagem.setBounds(100,50, 400, 20);
		subMensagem.setForeground(Color.black);
		subMensagem.setFont(new Font("Arial",Font.BOLD,15));
		
		fundoImg.setBackground(null);
		okButon=new JButton("Ok");
		okButon.setBackground(new Color(47,79,79));
		okButon.setForeground(Color.WHITE);
		okButon.setFont(new Font("Arial",Font.BOLD,15));
		okButon.setBounds(170,90, 50, 30);
		add(mensagem);
		add(subMensagem);
		add(okButon);
		add(fundoImg);
		show(false);
	}
	
	public ExibirMensagem(boolean morreu){
		super();
		setLayout(null);
		setSize(400,150);
		setLocationRelativeTo(null);
		setModal(true);
		setAlwaysOnTop(true);
		
		setUndecorated(true);
		getRootPane().setOpaque(false);
		getContentPane().setBackground(new Color(0,0,0,0));
		setBackground(new Color(0,0,0,0));
		JLabel fundoImg = new JLabel(new ImageIcon("imagens\\pergaminho.png"));
		fundoImg.setBounds(0, 0, 400, 150);
		
		tituloLabel = new JLabel("GAME OVER");
		tituloLabel.setBounds(80,20, 400, 20);
		tituloLabel.setForeground(new Color(0,0,0));
		tituloLabel.setFont(new Font("Arial",Font.BOLD,18));
		
		mensagemFimJogo = new JLabel("Deseja Jogar novamente?");
		mensagemFimJogo.setBounds(120, 60, 200, 30);
		mensagemFimJogo.setFont(new Font("Arial", Font.BOLD,13));
		
		fundoImg.setBackground(null);
		jogarNovamente=new JButton("Sim");
		jogarNovamente.setBackground(new Color(47,79,79));
		jogarNovamente.setForeground(Color.WHITE);
		jogarNovamente.setFont(new Font("Arial",Font.BOLD,15));
		jogarNovamente.setBounds(100,90, 60, 30);
		
		naoJogar = new JButton("Não");
		naoJogar.setBackground(new Color(47,79,79));
		naoJogar.setForeground(Color.WHITE);
		naoJogar.setFont(new Font("Arial",Font.BOLD,15));
		naoJogar.setBounds(200,90, 60, 30);
		
		add(tituloLabel);
		add(mensagemFimJogo);
		add(jogarNovamente);
		add(naoJogar);
		add(fundoImg);
		show(morreu);
	}
	
	public JLabel getSubMensagem() {
		return subMensagem;
	}

	public  JButton getOkButon() {
		return okButon;
	}
	
	public JLabel getMensagem() {
		return mensagem;
	}

	public JTextField getNomeField() {
		return nomeField;
	}

	public JButton getCancelarButton() {
		return cancelarButton;
	}


	public JButton getJogarButton() {
		return jogarButton;
	}


	public JLabel getTituloLabel() {
		return tituloLabel;
	}


	public JButton getJogarNovamente() {
		return jogarNovamente;
	}


	public JButton getNaoJogar() {
		return naoJogar;
	}


	public JLabel getMensagemFimJogo() {
		return mensagemFimJogo;
	}


	public JLabel getGameover() {
		return gameover;
	}
	
	
	
	
}
