package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Livro extends Elemento {
	private BufferedImage imagem;
	private boolean pegouLivro;
	private boolean colidiu;
	
	public Livro(int x, int y, String diretorio) {
		super(x, y);
		try {
			this.imagem= ImageIO.read(new File(diretorio));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setX(int x) {
		this.x+= x;		
	}

	@Override
	public void setY(int y) {
		this.y = y;
		
	}

	public BufferedImage getImagem() {
		return imagem;
	}

	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}

	public boolean isPegouLivro() {
		return pegouLivro;
	}

	public void setPegouLivro(boolean pegouLivro) {
		this.pegouLivro = pegouLivro;
	}

	public boolean isColidiu() {
		return colidiu;
	}

	public void setColidiu(boolean colidiu) {
		this.colidiu = colidiu;
	}
	
	

}
