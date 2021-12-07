package model;

import java.util.ArrayList;

public abstract class Dados {
	
	public static boolean desafioAtivado=false;
	
	//QUANTIDADE DE DADOS 1° FASE: OBEJTIVO SECUNDARIO
	public static final int QTD_LIVROS_FASE1 = 1;
	
	//QUANTIDADE DE DADOS 1° FASE: OBEJTIVO SECUNDARIO
	public static final int QTD_LIVROS_FASE2 = 2;
	
	//QUANTIDADE DE DADOS 1° FASE: OBEJTIVO SECUNDARIO
	public static final int QTD_LIVROS_FASE3 = 2;
	
	//TODOS OS CRISTAIS ENCONTRADOS EM TODAS AS FASES - > OBJETIVO PRINCIPAL DO JOGO
	public static final int QTD_CRISTAIS_FASE1 = 1;
	
	public static final int QTD_CRISTAIS_FASE2 = 1;
	
	public static final int QTD_CRISTAIS_FASE3 = 2;
	
	//QUANTIDADE DE DADOS 1° FASE: COMANDOS
	public static final int QTD_ANDAR_FRENTE_FASE1 = 3;
	public static final int QTD_ANDAR_COSTA_FASE1 = 2;
	public static final int QTD_ANDAR_ESQUERDA_FASE1 = 2;
	public static final int QTD_ANDAR_DIREITA_FASE1 = 2;
	public static final int QTD_PEGAR_FASE1 = 1;
	public static final int QTD_LOOP_FASE1=1;
	public static final int QTD_FUNCAO_FASE1=1;
	
	public static boolean fase1Visivel=true,fase2Visivel=false,fase3Visivel=false,desafio1fase1Visivel=false,desafio2Fase3Visivel=false;
	public static boolean desafio2Fase1visivel=false,desafio1Fase2Visivel=false, desafio2Fase2Visivel=false, desafio1Fase3Visivel=false;
	//QUANTIDADE DE DADOS 1° FASE DESAFIO 1: COMANDOS
	
	public static final int QTD_ANDAR_FRENTE_DESAFIO1 = 2;
	public static final int QTD_ANDAR_COSTA_DESAFIO1 = 3;
	public static final int QTD_ANDAR_ESQUERDA_DESAFIO1 = 3;
	public static final int QTD_ANDAR_DIREITA_DESAFIO1 = 2;
	public static final int QTD_FUNCAO_DESAFIO1=2;
	
	//QUANTIDADE DE DADOS 1° FASE DESAFIO 2: COMANDOS
	public static final int QTD_ANDAR_FRENTE_DESAFIO2 = 2;
	public static final int QTD_ANDAR_COSTA_DESAFIO2 = 3;
	public static final int QTD_ANDAR_ESQUERDA_DESAFIO2 = 3;
	public static final int QTD_ANDAR_DIREITA_DESAFIO2 = 2;
	public static final int QTD_FUNCAO_DESAFIO2=2;
	
	//ARRAYS QUE CONTERAM A QUANTIDADE DE DADOS REFERENTE A CADA FASE
//	public static ArrayList<Integer> objetivos = new ArrayList<Integer>();
//	public static ArrayList<Integer> comandos = new ArrayList<Integer>();
	
	//QUANTIDADE DE DADOS 2° FASE: COMANDOS
	public static final int QTD_ANDAR_FRENTE_FASE2 = 3;
	public static final int QTD_ANDAR_COSTA_FASE2 = 3;
	public static final int QTD_ANDAR_ESQUERDA_FASE2 = 3;
	public static final int QTD_ANDAR_DIREITA_FASE2 = 2;
	public static final int QTD_PEGAR_FASE2 = 1;
	public static final int QTD_LOOP_FASE2=1;
	public static final int QTD_FUNCAO_FASE2=2;
	
	//QUANTIDADE DE DADOS 2° FASE DESAFIO 1: COMANDOS
	public static final int QTD_ANDAR_FRENTE_FASE2_DESAFIO1 = 2;
	public static final int QTD_ANDAR_COSTA_FASE2_DESAFIO1 = 3;
	public static final int QTD_ANDAR_ESQUERDA_FASE2_DESAFIO1 = 3;
	public static final int QTD_ANDAR_DIREITA_FASE2_DESAFIO1 = 2;
	public static final int QTD_FUNCAO_FASE2_DESAFIO1=1;
	
	//QUANTIDADE DE DADOS 2° FASE DESAFIO 2: COMANDOS
	public static final int QTD_ANDAR_FRENTE_FASE2_DESAFIO2 = 2;
	public static final int QTD_ANDAR_COSTA_FASE2_DESAFIO2 = 3;
	public static final int QTD_ANDAR_ESQUERDA_FASE2_DESAFIO2 = 3;
	public static final int QTD_ANDAR_DIREITA_FASE2_DESAFIO2 = 2;
	public static final int QTD_FUNCAO_FASE2_DESAFIO2=1;
	
	//QUANTIDADE DE DADOS 3° FASE: COMANDOS
	public static final int QTD_ANDAR_FRENTE_FASE3 = 3;
	public static final int QTD_ANDAR_COSTA_FASE3 = 2;
	public static final int QTD_ANDAR_ESQUERDA_FASE3 = 3;
	public static final int QTD_ANDAR_DIREITA_FASE3 = 2;
	public static final int QTD_PEGAR_FASE3 = 1;
	public static final int QTD_LOOP_FASE3=1;
	public static final int QTD_FUNCAO_FASE3=2;
	
	//QUANTIDADE DE DADOS 3° FASE DESAFIO 1: COMANDOS
	public static final int QTD_ANDAR_FRENTE_FASE3_DESAFIO1 = 2;
	public static final int QTD_ANDAR_COSTA_FASE3_DESAFIO1 = 3;
	public static final int QTD_ANDAR_ESQUERDA_FASE3_DESAFIO1 = 3;
	public static final int QTD_ANDAR_DIREITA_FASE3_DESAFIO1 = 3;
	public static final int QTD_FUNCAO_FASE3_DESAFIO1=2;
	
	//QUANTIDADE DE DADOS 3° FASE DESAFIO 2: COMANDOS
	public static final int QTD_ANDAR_FRENTE_FASE3_DESAFIO2 = 2;
	public static final int QTD_ANDAR_COSTA_FASE3_DESAFIO2 = 3;
	public static final int QTD_ANDAR_ESQUERDA_FASE3_DESAFIO2 = 3;
	public static final int QTD_ANDAR_DIREITA_FASE3_DESAFIO2 = 2;
	public static final int QTD_FUNCAO_FASE3_DESAFIO2=4;
}
