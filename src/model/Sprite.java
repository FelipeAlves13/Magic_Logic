package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	BufferedImage spriteSheet; //ARMAZENA O FOLHETO  
	public BufferedImage[] sprites;//FOLHETO DIVIDIDO EM FIGURAS
	
	public Sprite(String diretorio,int columns, int rows,int largura,int altura) throws IOException{
		try {
			BufferedImage spriteSheet = ImageIO.read(new File(diretorio)); 
	
			sprites = new BufferedImage[columns * rows];
			
			for(int i=0; i < columns;i++){
				for(int j = 0; j < rows; j++) {
					sprites[(i * rows) + j] = spriteSheet.getSubimage(i * largura, j * altura, largura,altura);
				}
			}
		}catch(javax.imageio.IIOException e) {
			
		}
		
	}
	
	//ESPELHAR IMAGEM DO PERSONAGEM
	public BufferedImage imageFlip(BufferedImage img) {  
		int w = img.getWidth();  
		int h = img.getHeight();  
		BufferedImage dimg = new BufferedImage(w, h, img.getType());  
		Graphics2D g = dimg.createGraphics();  
		g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);  
		g.dispose();  
		return dimg;
	}
}
