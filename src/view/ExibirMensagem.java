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
	private JButton okButon, okAviso, jogarButton, cancelarButton, jogarNovamente, naoJogar, okParabens,okInvalida;
	private JLabel mensagem,subMensagem,tituloLabel, mensagemFimJogo, mensagemParabens,mensagemSubParabens;
	private JLabel mensagemAviso, subMenssagemAviso, portal;
	private JLabel gameover,mensagemInvalida,subInvalida;
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
		tituloLabel.setBounds(140,20, 400, 20);
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
		jogarNovamente.setBounds(120,90, 60, 30);
		
		naoJogar = new JButton("Não");
		naoJogar.setBackground(new Color(47,79,79));
		naoJogar.setForeground(Color.WHITE);
		naoJogar.setFont(new Font("Arial",Font.BOLD,15));
		naoJogar.setBounds(220,90, 60, 30);
		
		add(tituloLabel);
		add(mensagemFimJogo);
		add(jogarNovamente);
		add(naoJogar);
		add(fundoImg);
		show(morreu);
	}
	
	public ExibirMensagem(String aviso,String avisoSub,String continuacao) {
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
		
		mensagemAviso = new JLabel("Para desistir do desafio use o           no");
		mensagemAviso.setFont(new Font("Arial",Font.BOLD,13));
		mensagemAviso.setBounds(58,30, 400, 20);
		mensagemAviso.setForeground(Color.black);
		mensagemAviso.setFont(new Font("Arial",Font.BOLD,15));
		
		portal = new JLabel(new ImageIcon("imagens\\teleporte.png"));
		portal.setFont(new Font("Arial",Font.BOLD,13));
		portal.setBounds(268,20, 42, 46);
		portal.setForeground(Color.black);
		portal.setFont(new Font("Arial",Font.BOLD,15));
		
		
		subMenssagemAviso = new JLabel("  portal da mesma cor");
		subMenssagemAviso.setFont(new Font("Arial",Font.BOLD,13));
		subMenssagemAviso.setBounds(110,60, 400, 20);
		subMenssagemAviso.setForeground(Color.black);
		subMenssagemAviso.setFont(new Font("Arial",Font.BOLD,15));
		
		fundoImg.setBackground(null);
		okAviso=new JButton("Ok");
		okAviso.setBackground(new Color(47,79,79));
		okAviso.setForeground(Color.WHITE);
		okAviso.setFont(new Font("Arial",Font.BOLD,15));
		okAviso.setBounds(170,90, 50, 30);
		add(mensagemAviso);
		add(portal);
		add(subMenssagemAviso);
		add(okAviso);
		add(fundoImg);
		show(false);
	}
	
	public ExibirMensagem(boolean visivel, String nome) {
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
		
		mensagemParabens = new JLabel("PARABENS!!");
		mensagemParabens.setFont(new Font("Arial",Font.BOLD,13));
		mensagemParabens.setBounds(68,30, 400, 20);
		mensagemParabens.setForeground(Color.black);
		mensagemParabens.setFont(new Font("Arial",Font.BOLD,15));
		
		mensagemSubParabens = new JLabel("A irmã da bruxinha foi salva!");
		mensagemSubParabens.setFont(new Font("Arial",Font.BOLD,13));
		mensagemSubParabens.setBounds(100,50, 400, 20);
		mensagemSubParabens.setForeground(Color.black);
		mensagemSubParabens.setFont(new Font("Arial",Font.BOLD,15));
		
		fundoImg.setBackground(null);
		okParabens=new JButton("Ok");
		okParabens.setBackground(new Color(47,79,79));
		okParabens.setForeground(Color.WHITE);
		okParabens.setFont(new Font("Arial",Font.BOLD,15));
		okParabens.setBounds(170,90, 50, 30);
		add(mensagemParabens);
		add(mensagemSubParabens);
		add(okParabens);
		add(fundoImg);
		show(false);
	}
	
	public ExibirMensagem(boolean exibir,boolean exibirLogo) {
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
		
		mensagemInvalida = new JLabel("Movimento Invalido!!");
		mensagemInvalida.setFont(new Font("Arial",Font.BOLD,13));
		mensagemInvalida.setBounds(120,30, 400, 20);
		mensagemInvalida.setForeground(Color.black);
		mensagemInvalida.setFont(new Font("Arial",Font.BOLD,15));
		
		subInvalida = new JLabel("Percorra todo o trajeto!!");
		subInvalida.setFont(new Font("Arial",Font.BOLD,13));
		subInvalida.setBounds(110,50, 400, 20);
		subInvalida.setForeground(Color.black);
		subInvalida.setFont(new Font("Arial",Font.BOLD,15));
		
		fundoImg.setBackground(null);
		okInvalida=new JButton("Ok");
		okInvalida.setBackground(new Color(47,79,79));
		okInvalida.setForeground(Color.WHITE);
		okInvalida.setFont(new Font("Arial",Font.BOLD,15));
		okInvalida.setBounds(170,90, 50, 30);
		add(mensagemInvalida);
		add(subInvalida);
		add(okInvalida);
		add(fundoImg);
		show(false);
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


	public JButton getOkAviso() {
		return okAviso;
	}


	public JLabel getMensagemAviso() {
		return mensagemAviso;
	}


	public JLabel getSubMenssagemAviso() {
		return subMenssagemAviso;
	}


	public JLabel getPortal() {
		return portal;
	}


	public JButton getOkParabens() {
		return okParabens;
	}


	public JLabel getMensagemParabens() {
		return mensagemParabens;
	}


	public JLabel getMensagemSubParabens() {
		return mensagemSubParabens;
	}


	public JLabel getMensagemInvalida() {
		return mensagemInvalida;
	}


	public JLabel getSubInvalida() {
		return subInvalida;
	}


	public JButton getOkInvalida() {
		return okInvalida;
	}
	
	
	
	
}
