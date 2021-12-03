package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PortalDesafio extends Elemento{
	private BufferedImage imagem;
	
	public PortalDesafio(int x, int y, int altura, int largura,String diretorio) {
		super(x, y, altura, largura);
		try {
			this.imagem=ImageIO.read(new File(diretorio));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	public BufferedImage getImagem() {
		return imagem;
	}



	@Override
	public void setX(int x) {
		this.x=x;
		
	}

	@Override
	public void setY(int y) {
		this.y=y;
		
	}
	
}
