package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class InventarioHorizontal extends JPanel{
	
	private JPanel quadroPrincipal, quadroFuncao;
	private JLabel apagarFunção, apagarMain;
	
	private int posInicialMain, posInicialFuncao;
	
	
	public InventarioHorizontal() {
			
			setLayout(null);
			setBackground(new Color(255,140,2));
			//setBackground(Color.WHITE);
			setPreferredSize(new Dimension(800, 300));
			
			//usados para colocar os comandos no local correto do main ou da função
			this.posInicialFuncao=15;
			this.posInicialMain=15;
			
			apagarMain = new JLabel(new ImageIcon("imagens\\lixeira.png"));
			apagarMain.setBounds(585, 29, 40, 40);
			add(apagarMain);
			
			apagarFunção = new JLabel(new ImageIcon("imagens\\lixeira.png"));
			apagarFunção.setBounds(585, 105, 40, 40);
			add(apagarFunção);
			
			quadroPrincipal = new JPanel(null);
			quadroPrincipal.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black,5,true), "MAIN",TitledBorder.LEFT , TitledBorder.TOP,new Font("Serif",Font.BOLD, 18),Color.black));
			quadroPrincipal.setBounds(1, 0, 618, 70);
			quadroPrincipal.setBackground(null);
			quadroPrincipal.setFocusable(true);
			add(quadroPrincipal);
			
			quadroFuncao = new JPanel(null);
			quadroFuncao.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE,5,true), "FUNÇÃO",TitledBorder.LEFT , TitledBorder.TOP,new Font("Serif",Font.BOLD, 18),Color.WHITE));
			quadroFuncao.setBounds(1, 75, 618, 70);
			quadroFuncao.setBackground(null);
			add(quadroFuncao);
			
		
	}

	public JPanel getQuadroPrincipal() {
		return quadroPrincipal;
	}

	public JPanel getQuadroFuncao() {
		return quadroFuncao;
	}

	public JLabel getApagarFunção() {
		return apagarFunção;
	}

	public void setApagarFunção(JLabel apagarFunção) {
		this.apagarFunção = apagarFunção;
	}

	public JLabel getApagarMain() {
		return apagarMain;
	}

	public void setApagarMain(JLabel apagarMain) {
		this.apagarMain = apagarMain;
	}

	public void setQuadroPrincipal(JPanel quadroPrincipal) {
		this.quadroPrincipal = quadroPrincipal;
	}

	public void setQuadroFuncao(JPanel quadroFuncao) {
		this.quadroFuncao = quadroFuncao;
	}

	public int getPosInicialMain() {
		return posInicialMain;
	}

	public void setPosInicialMain(int posInicialMain) {
		this.posInicialMain = posInicialMain;
	}

	public int getPosInicialFuncao() {
		return posInicialFuncao;
	}

	public void setPosInicialFuncao(int posInicialFuncao) {
		this.posInicialFuncao = posInicialFuncao;
	}
	
	
	
	
}
