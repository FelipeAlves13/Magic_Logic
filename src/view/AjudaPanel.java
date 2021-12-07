package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class AjudaPanel extends JDialog {
	
	private JLabel andarCimaLabel,andarBaixoLabel,andarEsquerdaLabel,andarDireitaLabel, funcaoLabel, loopLabel, pegarLabel, teleporteLabel;
	private JLabel atirarMagiaLabel, instrucoesTituloLabel, comandosLabel, fecharLabel, linhaComandoInfLabel,linhaComandoSupLabel;
	private JLabel linhaInstrucaoInfLabel,linhaInstruçãoSupLabel, observacoes, observacoes2, observacoes3, observacoes4;
	
	
	
	public AjudaPanel() {
		super();
		setLayout(null);
		setSize(700,600);
		setLocationRelativeTo(null);
		setModal(true);
		setAlwaysOnTop(true);
		
		setUndecorated(true);
		
		//getContentPane().setBackground(new Color(0,0,0,0));
		setBackground(Color.BLUE);
		
		fecharLabel = new JLabel(new ImageIcon("Imagens\\fechar.png"));
		fecharLabel.setBounds(651, 10, 40, 40);
		add(fecharLabel);
		
		linhaComandoSupLabel=new JLabel("________________________________________________________________");
		linhaComandoSupLabel.setFont(new Font("Arial",Font.BOLD,18));
		linhaComandoSupLabel.setForeground(Color.WHITE);
		linhaComandoSupLabel.setBounds(30, 45, 655, 30);
		add(linhaComandoSupLabel);
		
		comandosLabel = new JLabel("Comandos");
		comandosLabel.setFont(new Font("Arial",Font.BOLD,18));
		comandosLabel.setForeground(Color.WHITE);
		comandosLabel.setBounds(295, 75, 120, 30);
		add(comandosLabel);
		
		linhaComandoInfLabel=new JLabel("________________________________________________________________");
		linhaComandoInfLabel.setFont(new Font("Arial",Font.BOLD,18));
		linhaComandoInfLabel.setForeground(Color.WHITE);
		linhaComandoInfLabel.setBounds(30, 90, 655, 30);
		add(linhaComandoInfLabel);
		
		andarCimaLabel = new JLabel("Andar para frente",new ImageIcon("imagens\\cima.png"), JLabel.LEFT);
		andarCimaLabel.setFont(new Font("Arial",Font.BOLD,16));
		andarCimaLabel.setForeground(Color.WHITE);
		andarCimaLabel.setBounds(30, 130, 300, 46);
		add(andarCimaLabel);
		
		andarBaixoLabel = new JLabel("Andar para trás",new ImageIcon("imagens\\baixo.png"), JLabel.LEFT);
		andarBaixoLabel.setFont(new Font("Arial",Font.BOLD,16));
		andarBaixoLabel.setForeground(Color.WHITE);
		andarBaixoLabel.setBounds(30, 180, 300, 46);
		add(andarBaixoLabel);
		
		andarEsquerdaLabel = new JLabel("Andar para esquerda",new ImageIcon("imagens\\esquerda.png"), JLabel.LEFT);
		andarEsquerdaLabel.setFont(new Font("Arial",Font.BOLD,16));
		andarEsquerdaLabel.setForeground(Color.WHITE);
		andarEsquerdaLabel.setBounds(30, 230, 300, 46);
		add(andarEsquerdaLabel);
		
		andarDireitaLabel = new JLabel("Andar para direita",new ImageIcon("imagens\\direita.png"), JLabel.LEFT); 
		andarDireitaLabel.setFont(new Font("Arial",Font.BOLD,16));
		andarDireitaLabel.setForeground(Color.WHITE);
		andarDireitaLabel.setBounds(30, 280, 300, 46);
		add(andarDireitaLabel);
		
		atirarMagiaLabel = new JLabel("Captura inimigo",new ImageIcon("imagens\\congelar.png"), JLabel.LEFT); 
		atirarMagiaLabel.setFont(new Font("Arial",Font.BOLD,16));
		atirarMagiaLabel.setForeground(Color.WHITE);
		atirarMagiaLabel.setBounds(30, 326, 300, 46);
		add(atirarMagiaLabel);
		
		funcaoLabel = new JLabel("Executa os comandos no campo função",new ImageIcon("imagens\\funcao.png"), JLabel.LEFT);
		funcaoLabel.setFont(new Font("Arial",Font.BOLD,16));
		funcaoLabel.setForeground(Color.WHITE);
		funcaoLabel.setBounds(320, 130, 405, 46);
		add(funcaoLabel);
		
		loopLabel = new JLabel("Repete os comandos anteriores",new ImageIcon("imagens\\loop.png"), JLabel.LEFT);
		loopLabel.setFont(new Font("Arial",Font.BOLD,16));
		loopLabel.setForeground(Color.WHITE);
		loopLabel.setBounds(320, 180, 300, 46);
		add(loopLabel);
		
		pegarLabel = new JLabel("Pega livros ou cristais",new ImageIcon("imagens\\pegar.png"), JLabel.LEFT);
		pegarLabel.setFont(new Font("Arial",Font.BOLD,16));
		pegarLabel.setForeground(Color.WHITE);
		pegarLabel.setBounds(320, 230, 300, 46);
		add(pegarLabel);
		
		teleporteLabel = new JLabel("Teleporta para outro reino", new ImageIcon("imagens\\teleporte.png"), JLabel.LEFT);
		teleporteLabel.setFont(new Font("Arial",Font.BOLD,16));
		teleporteLabel.setForeground(Color.WHITE);
		teleporteLabel.setBounds(320, 280, 300, 46);
		add(teleporteLabel);
		
		linhaInstruçãoSupLabel=new JLabel("________________________________________________________________");
		linhaInstruçãoSupLabel.setFont(new Font("Arial",Font.BOLD,18));
		linhaInstruçãoSupLabel.setForeground(Color.WHITE);
		linhaInstruçãoSupLabel.setBounds(30, 386, 655, 30);
		add(linhaInstruçãoSupLabel);
		
		instrucoesTituloLabel= new JLabel("Instruções");
		instrucoesTituloLabel.setFont(new Font("Arial",Font.BOLD,18));
		instrucoesTituloLabel.setForeground(Color.WHITE);
		instrucoesTituloLabel.setBounds(295, 416, 200, 30);
		add(instrucoesTituloLabel);
		
		linhaInstrucaoInfLabel=new JLabel("________________________________________________________________");
		linhaInstrucaoInfLabel.setFont(new Font("Arial",Font.BOLD,18));
		linhaInstrucaoInfLabel.setForeground(Color.WHITE);
		linhaInstrucaoInfLabel.setBounds(30, 436, 655, 30);
		add(linhaInstrucaoInfLabel);
		
		
		observacoes =  new JLabel("Seu objetivo principal é salvar a irmâ da bruxinha, para isso é necessario coletar,");
		observacoes.setFont(new Font("Arial",Font.BOLD,16));
		observacoes.setForeground(Color.WHITE);
		observacoes.setBounds(30, 470, 655, 30);
		add(observacoes);
		
		observacoes2 = new JLabel("4 cristais espalhados pelas 3 fases, contudo o cristal da primeira fase so podera ser");
		observacoes2.setFont(new Font("Arial",Font.BOLD,16));
		observacoes2.setForeground(Color.WHITE);
		observacoes2.setBounds(30,500, 655, 30);
		add(observacoes2);
		
		observacoes3  = new JLabel("pego quando você tiver os outros tres cristais. Os cristais so aparecem apos coletar");
		observacoes3.setFont(new Font("Arial",Font.BOLD,16));
		observacoes3.setForeground(Color.WHITE);
		observacoes3.setBounds(30,530, 655, 30);
		add(observacoes3);
		
		observacoes4 = new JLabel("a quantidade de livros mostrada no inventario. Apos ter os cristais vá ate a casa.");
		observacoes4.setFont(new Font("Arial",Font.BOLD,16));
		observacoes4.setForeground(Color.WHITE);
		observacoes4.setBounds(30,560, 655, 30);
		add(observacoes4);
		
		JLabel fundoImg = new JLabel(new ImageIcon("imagens\\fundo.png"));
		fundoImg.setBounds(10, 10, 680, 580);
		fundoImg.setBackground(new Color(0,0,0,0));
		
		add(fundoImg);
		show(false);
	}



	public JLabel getFecharLabel() {
		return fecharLabel;
	}
	
	
}
