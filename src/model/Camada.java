package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class Camada {
	private  int mapa[][];
	public  BufferedImage camada;
	
	private BufferedImage tileSet;
	private int mapaWidth;
	private int mapaHeight;
	private int tileWidth;
	private int tileHeight;
	private int largura,altura;
	private ArrayList<Rectangle> colisoes=new ArrayList<>();
	
	public Camada(int mapaWidth, int mapaHeight, int tileWidth, int tileHeight, String img, String arquivo) throws FileNotFoundException {
		this.mapaWidth=mapaWidth;
		this.mapaHeight=mapaHeight;
		this.tileWidth=tileWidth;
		this.tileHeight=tileHeight;
		largura = mapaWidth * tileWidth;
		altura = mapaHeight * tileHeight;
		mapa = new int[mapaWidth][mapaHeight];
		mapa = carregaMatriz(mapa, arquivo);
		try {
			tileSet = ImageIO.read(new File(img));
		} catch (IOException e) {
			System.out.println("Erro ao tileSet.\nEncerrando aplica��o");
			System.exit(0);
		}
	}
	
	

	public int[][] carregaMatriz(int[][] matz, String arquivo) throws FileNotFoundException {
		ArrayList<String> linhasMatrizCamada = new ArrayList<String>();
		BufferedReader br = new BufferedReader (new FileReader(arquivo));   
		String linha="";
		try {

			while ((linha = br.readLine()) != null){
				//System.out.println(linha);
				linhasMatrizCamada.add(linha);
			}
			int j = 0;
			//System.out.println(linhasMatrizCamada.size());
			for (int i = 0; i < linhasMatrizCamada.size(); i++) {
				StringTokenizer token = new StringTokenizer(linhasMatrizCamada.get(i), ",");
				
				while (token.hasMoreElements()) {
					
					matz[i][j] = Integer.parseInt(token.nextToken());
					j++;
				}
				j = 0;
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("nao carregou arquivo mapa");
			System.exit(0);
		}
		catch (IOException ioException) {
			System.out.println("erro na leitura do mapa");
			System.exit(0);
		}
		return matz;
	}

	public void montarMapa(int lar, int alt) {

		camada = new BufferedImage(lar, alt, BufferedImage.TYPE_4BYTE_ABGR);
		camada.createGraphics();

		int tile;
		int tileRow;
		int tileCol;
		int colunasTileSet=tileSet.getWidth()/tileWidth;
		System.out.println(colunasTileSet);
		System.out.println(colunasTileSet);
		for (int i = 0; i < mapaWidth; i++) {
			for (int j = 0; j < mapaHeight; j++) {
				tile = (mapa[i][j] != 0) ? (mapa[i][j]-1) : 0;
				tileRow = (tile / (colunasTileSet)) | 0;
				tileCol = (tile % (colunasTileSet)) | 0;
				camada.getGraphics().drawImage(tileSet, (j * tileWidth), (i * tileHeight), (j * tileWidth) + tileWidth,
						(i * tileHeight) + tileHeight, (tileCol * tileWidth), (tileRow * tileHeight),
						(tileCol * tileWidth) + tileWidth, (tileRow * tileHeight) + tileHeight, null);
			}
		}
	}
	
	public void montarMapa(int lar, int alt,int inicialX, int inicialY, int fimX, int fimY) {

		this.camada = new BufferedImage(lar, alt, BufferedImage.TYPE_4BYTE_ABGR);
		this.camada.createGraphics();

		int tile;
		int tileRow;
		int tileCol;
		int colunasTileSet=tileSet.getWidth()/tileWidth;
		System.out.println(colunasTileSet);
		System.out.println(colunasTileSet);
		for (int i = inicialX; i > 0; i--) {
			for (int j = 0; j < this.mapaHeight; j++) {
				tile = (this.mapa[i][j] != 0) ? (this.mapa[i][j]-1) : 0;
				tileRow = (tile / (colunasTileSet)) | 0;
				tileCol = (tile % (colunasTileSet)) | 0;
				this.camada.getGraphics().drawImage(tileSet, (j * tileWidth), (i * tileHeight), (j * tileWidth) + tileWidth,
						(i * tileHeight) + tileHeight, (tileCol * tileWidth), (tileRow * tileHeight),
						(tileCol * tileWidth) + tileWidth, (tileRow * tileHeight) + tileHeight, null);
			}
		}
	}

//	public void mapa() {
//		for (int j = 0; j < mapaHeight; j++) {
//			System.out.print("["+j+"]");	
//			
//		}
//		System.out.println();
//		for (int i = 0; i < mapaWidth; i++) {
//			
//			System.out.println("["+i+"]"+"--------------------------------------------------------------");
//			
//			for (int j = 0; j < mapaHeight; j++) {
//					if(j>9) {
//						System.out.print(" |"+mapa[i][j]+"|");
//					}else {
//						System.out.print("|"+mapa[i][j]+"|");
//					}
//					
//					
//				}
//			System.out.println();
//		}
//	}
	public void montarColi() {
		
		
		for (int i = 0; i < mapaWidth; i++) {
			for (int j = 0; j < mapaHeight; j++) {
				if(mapa[i][j] != 0 & i!=22) {
					this.colisoes.add(new Rectangle( (j * tileWidth), (i * tileWidth), 20, 20));
				}
				
			}
		}
		
	}
	
	
	
	public ArrayList<Rectangle> getColisoes() {
		return colisoes;
	}



	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
	}

	public int getTileWidth() {
		return tileWidth;
	}



	public BufferedImage getCamada() {
		return camada;
	}



	public int[][] getMapa() {
		return mapa;
	}

	
	
}
