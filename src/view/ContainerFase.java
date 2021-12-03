package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContainerFase extends JPanel{
	
	private Fase1 fase1;
	private Fase2 fase2;
	private Fase3 fase3;
	private ArrayList<DesafioPanel> desafiosFase1, desafiosFase2, desafiosFase3;
	
	private Inventario inventarioVertical;
	private InventarioHorizontal inventarioHorizontal;
	
	private JLabel jogarLabel, iniciarLabel;
	
	public ContainerFase(Fase1 fase1,Fase2 fase2, Fase3 fase3, ArrayList<ArrayList<DesafioPanel>> desafios){
		setLayout(null);
		setBounds(0, 0, 850, 650);
		
		this.desafiosFase1=desafios.get(0);
		this.desafiosFase2=desafios.get(1);
		this.desafiosFase3=desafios.get(2);
		
		for(DesafioPanel d:this.desafiosFase1) {
			d.setBounds(0, 0, 600, 500);
			add(d);
		}
		
		for(DesafioPanel d:this.desafiosFase2) {
			d.setBounds(0, 0, 600, 500);
			add(d);
		}
		
		for(DesafioPanel d:this.desafiosFase3) {
			d.setBounds(0, 0, 600, 500);
			add(d);
		}
		this.iniciarLabel=new JLabel("");
		this.iniciarLabel.setBounds(680, 550, 140, 100);
		this.iniciarLabel.setBackground(new Color(0,0,0,0));
		add(iniciarLabel);
		
		this.jogarLabel=new JLabel(new ImageIcon("imagens\\caldeirao.png"));
		this.jogarLabel.setBounds(610, 410, 230, 240);
		add(jogarLabel);
		//INICIALIZANDO ATRIBUTOS DAS FASES
		this.fase1=fase1;
		this.fase1.setBounds(0, 0, 600, 500);
		add(this.fase1);
		
		this.fase2=fase2;
		this.fase2.setBounds(0, 0, 600, 500);
		this.fase2.setVisible(false);
		add(this.fase2);
		
		this.fase3=fase3;
		this.fase3.setBounds(0, 0, 600, 500);
		this.fase3.setVisible(false);
		add(this.fase3);
		
		this.inventarioVertical= new Inventario();
		this.inventarioVertical.setBounds(600, 0, 250, 500);
		add(this.inventarioVertical);
		
		this.inventarioHorizontal = new InventarioHorizontal();
		this.inventarioHorizontal.setBounds(0, 500, 850, 150);
		add(this.inventarioHorizontal);
		
	}

	public Fase1 getFase1() {
		return fase1;
	}

	public Fase2 getFase2() {
		return fase2;
	}

	public Fase3 getFase3() {
		return fase3;
	}

	public Inventario getInventarioVertical() {
		return inventarioVertical;
	}

	public InventarioHorizontal getInventarioHorizontal() {
		return inventarioHorizontal;
	}

	public JLabel getJogarLabel() {
		return jogarLabel;
	}

	public ArrayList<DesafioPanel> getDesafiosFase1() {
		return desafiosFase1;
	}

	public ArrayList<DesafioPanel> getDesafiosFase2() {
		return desafiosFase2;
	}

	public ArrayList<DesafioPanel> getDesafiosFase3() {
		return desafiosFase3;
	}

	public JLabel getIniciarLabel() {
		return iniciarLabel;
	}
	
	
}
