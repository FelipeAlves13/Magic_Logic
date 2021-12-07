package view;

import java.util.ArrayList;

import javax.swing.JFrame;

public class TelaJogo extends JFrame{
	private MenuPanel menuPanel;
	private MapaDasFasesPanel mapaDasFasesPanel;
	private ExibirMensagem exibirMensagem, exibirAviso, exibirAvisoLongo, exibirGameOver, exibirDesafio, exibirParabens,exibirInvalida;
	private SobrePanel sobrePanel;
	private CreditosPanel creditosPanel;
	private ContainerFase containerFase;
	private AjudaPanel ajudaPanel;
	
	public TelaJogo(Fase1 fase1, Fase2 fase2, Fase3 fase3, ArrayList<ArrayList<DesafioPanel>> desafios) {
		setLayout(null);
		setSize(850,650);
		//setAlwaysOnTop(true);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.menuPanel = new MenuPanel();
		this.menuPanel.setBounds(0, 0, 850, 650);

		this.mapaDasFasesPanel = new MapaDasFasesPanel();
		this.mapaDasFasesPanel.setBounds(0, 0, 850, 650);
		this.mapaDasFasesPanel.setVisible(false);
		
		this.sobrePanel = new SobrePanel();
		this.sobrePanel.setBounds(0, 0, 850, 650);
		this.sobrePanel.setVisible(false);
		
		this.creditosPanel = new CreditosPanel();
		this.creditosPanel.setBounds(0, 0, 850, 650);
		this.creditosPanel.setVisible(false);
		
		this.containerFase = new ContainerFase(fase1, fase2, fase3,desafios);
		this.containerFase.setBounds(0, 0, 850, 650);
		add(this.menuPanel);
		add(this.mapaDasFasesPanel);
		add(this.sobrePanel);
		add(this.creditosPanel);
		add(this.containerFase);
		setVisible(true);
	}
	
	public void exibirCampoDePreenchimento() {
		this.exibirMensagem=new ExibirMensagem();
	}

	public void exibirAvisoSimples() {
		this.exibirAviso=new ExibirMensagem("");
	}
	
	public void exibirAvisoLongo() {
		this.exibirAvisoLongo=new ExibirMensagem("","");
	}
	
	public void exibirAjuda() {
		this.ajudaPanel=new AjudaPanel();
	}
	
	public void exibirDesafioPanel() {
		this.exibirDesafio=new ExibirMensagem("","","");
	}
	
	public void exibirMsgGameOver() {
		this.exibirGameOver=new ExibirMensagem(false);
	}
	
	public void exibirParabens() {
		this.exibirParabens=new ExibirMensagem(true, "");
	}
	
	
	public void exibirInvalida() {
		this.exibirInvalida=new ExibirMensagem(false,false);
	}
	
	public ExibirMensagem getExibirParabens() {
		return exibirParabens;
	}

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	public MapaDasFasesPanel getMapaDasFasesPanel() {
		return mapaDasFasesPanel;
	}

	public ExibirMensagem getExibirMensagem() {
		return exibirMensagem;
	}

	public SobrePanel getSobrePanel() {
		return sobrePanel;
	}

	public CreditosPanel getCreditosPanel() {
		return creditosPanel;
	}

	public ExibirMensagem getExibirAviso() {
		return exibirAviso;
	}

	public ContainerFase getContainerFase() {
		return containerFase;
	}

	public ExibirMensagem getExibirAvisoLongo() {
		return exibirAvisoLongo;
	}

	public AjudaPanel getAjudaPanel() {
		return ajudaPanel;
	}

	public ExibirMensagem getExibirGameOver() {
		return exibirGameOver;
	}

	public ExibirMensagem getExibirDesafio() {
		return exibirDesafio;
	}

	public ExibirMensagem getExibirInvalida() {
		return exibirInvalida;
	}
	
	
	
	
	
}
