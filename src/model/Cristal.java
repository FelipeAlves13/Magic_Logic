package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cristal extends Elemento{
	private BufferedImage imagem;
	private boolean aparecerCristal,pegouCristal;
	
	public Cristal(int x, int y, int altura, int largura,String caminho) {
		super(x, y, altura, largura);
		try {
			imagem = ImageIO.read(new File(caminho));
		} catch (IOException e) {  
			e.printStackTrace();
		}
	}

	@Override
	public void setX(int x) {
		this.x=x;
		
	}

	@Override
	public void setY(int y) {
		this.y=y;
		
	}

	public boolean isApareceuCristal() {
		return aparecerCristal;
	}

	public void setApareceuCristal(boolean pegouCristal) {
		this.aparecerCristal = pegouCristal;
	}

	public BufferedImage getImagem() {
		return imagem;
	}

	public boolean isPegouCristal() {
		return pegouCristal;
	}

	public void setPegouCristal(boolean pegouCristal) {
		this.pegouCristal = pegouCristal;
	}

	
	
}
