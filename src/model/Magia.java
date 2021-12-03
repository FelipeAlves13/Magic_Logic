package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Magia extends Elemento {
	private BufferedImage imagem;
	
	public Magia(String caminho,int x, int y) {
		super(x, y);
		try {
			imagem = ImageIO.read(new File(caminho));
			this.setLargura(imagem.getWidth());
			this.setAltura(imagem.getHeight());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

	public BufferedImage getImagem() {
		return imagem;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}

	
}
