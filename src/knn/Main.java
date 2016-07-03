package knn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int k = 7;
		//abertura do arquivo
		BufferedReader reader =
				   new BufferedReader(new FileReader("BoBoM.arff"));
		ArffReader arff = new ArffReader(reader, 1000);
		
		Instances data = arff.getStructure();
		data.setClassIndex(data.numAttributes() - 1);
		Instance inst;
		while ((inst = arff.readInstance(data)) != null) {
			data.add(inst);   
			}
		System.out.println("\n\n"+data.relationName()+"\n\n");
	
		int nrClasses = data.numClasses();
		//pega as classes
		Attribute a = data.classAttribute();
		String enumeracaoClasse = a.toString();
		//separação das classes
		String[] classesAtuais = Utils.separaClasse(enumeracaoClasse);
		
		//System.in.read();
		
		int tamanhoLinhas = data.numInstances();
		String[] x = new String[tamanhoLinhas];
		int tamanhoColunas;
		int acertosEucli = 0;
		int acertosMan = 0;
		int acertosBray = 0;
		int acertosChi = 0;
		int acertosTop = 0;
		int tamTeste = 0;// = tamanhoLinhas/3;
		int tamTreino = 0;//= 2*tamTeste;
		
		
		if((tamanhoLinhas/3) == 1){
			tamTeste = ((tamanhoLinhas-1)/3);
			tamTreino = (tamanhoLinhas - tamTeste);
		}
		else if((tamanhoLinhas/3) == 2){
			tamTeste = ((tamanhoLinhas-2)/3);
			tamTreino = (tamanhoLinhas - tamTeste);
		}
		else{
			tamTeste = tamanhoLinhas/3;
			tamTreino = tamanhoLinhas - tamTeste;
		}
		
		for(int i=0;i<tamanhoLinhas;i++){
			x[i] = data.instance(i).toString();
		}
		Utils.embaralhar(x); //metodo que embaralha a base
		
		tamanhoColunas = x[1].split(",").length;
		double [][] base = new double[tamanhoLinhas][tamanhoColunas];
		int[] vetorDeClassificacoes = new int[nrClasses];
		
		for(int i=0;i<tamanhoLinhas;i++){
			// quebra a string cada vez que encontrar um espaco
			String []particao = x[i].split(",");  
			//criar um vetor com o tamanhoLinhasLinhas da quantidade de elementos
			double[] split = new double[particao.length];			
			//converter todas as classes em binario
			for(int j = 0; j < split.length; j++){
				for(int u = 0;u<nrClasses;u++){
					if(particao[j].equals(classesAtuais[u])){
						vetorDeClassificacoes[u] = u;
						particao[j] = Integer.toString(u);
					}
				}
				split[j] = Double.parseDouble(particao[j]); //converter a base para double
				
			}
			for(int q = 0; q < split.length ;q++){
				base[i][q] = split[q];
			}
			
		}
		
		double []copia = new double[tamanhoLinhas];
		//copiar todo vetor de base para conseguir definir os maiores valores, fazendo 
		//normalização por coluna
		for(int i = 0; i <tamanhoColunas-1;i++){
			for (int j = 0; j < tamanhoLinhas;j++){
				copia[j] = base[j][i];
			}
			Arrays.sort(copia); //ordena um vetor auxiliar
			double maior = copia[tamanhoLinhas-1]; // maior valor da base
			double menor = copia[0]; // menor valor da base
			//normaliza a base
			for(int j = 0;j<tamanhoLinhas;j++){
				base[j][i] = ((base[j][i]- menor)/(maior-menor)); 
			}
		}
		
		
	
		//separar base de teste e treino
		  
		double[] [] teste = new double[tamTeste][tamanhoColunas]; //9 é o numero de atributos, contando com a classe
		double [][] treino = new double[tamTreino][tamanhoColunas];
		int test = (tamTeste);
		int cont = 0;
		//teste
		while(cont<test){
			for(int i=0;i<tamanhoColunas;i++){
				teste[cont][i] = base[cont][i]; 
			}
			cont++;
		}
		//treino
		int train = 0;
		while(cont<tamanhoLinhas){
			for(int i=0;i<tamanhoColunas;i++){
				treino[train][i] = base[cont][i]; 
			}
			cont++;
			train++;
		}
		//calcular distancia
		double[] euclidiana = new double[tamTreino];
		double[] classeEucli = new double[tamTreino];
		
		double[] brayCurtis = new double[tamTreino];
		double[] classeBray = new double[tamTreino];
		
		double[] manhatan = new double[tamTreino];
		double[] classeMan = new double[tamTreino];
		
		double[] chiSquare = new double[tamTreino];
		double[] classeChi = new double[tamTreino];
		
		double[] topsoeDivergence = new double[tamTreino];
		double[] classeTop = new double[tamTreino];
		
		MatrizConfusão.contadorZeroZero = 0;
		MatrizConfusão.contadorZeroUm = 0;
		MatrizConfusão.contadorZeroDois = 0;
		
		MatrizConfusão.contadorUmZero = 0;
		MatrizConfusão.contadorUmUm = 0;
		MatrizConfusão.contadorUmDois = 0;

		MatrizConfusão.contadorDoisZero = 0;
		MatrizConfusão.contadorDoisUm = 0;
		MatrizConfusão.contadorDoisDois = 0;

		Euclidiana.euclidiana(tamTeste, tamTreino, euclidiana, classeEucli, tamanhoColunas,
				treino, teste, nrClasses, vetorDeClassificacoes, k, acertosEucli, tamanhoLinhas);
		

		MatrizConfusão.contadorZeroZero = 0;
		MatrizConfusão.contadorZeroUm = 0;
		MatrizConfusão.contadorZeroDois = 0;
		
		MatrizConfusão.contadorUmZero = 0;
		MatrizConfusão.contadorUmUm = 0;
		MatrizConfusão.contadorUmDois = 0;

		MatrizConfusão.contadorDoisZero = 0;
		MatrizConfusão.contadorDoisUm = 0;
		MatrizConfusão.contadorDoisDois = 0;
		
		Manhattan.manhattan(tamTeste, tamTreino, manhatan, classeMan, tamanhoColunas, 
				treino, teste, nrClasses, vetorDeClassificacoes, k, acertosMan, tamanhoLinhas);
		
		MatrizConfusão.contadorZeroZero = 0;
		MatrizConfusão.contadorZeroUm = 0;
		MatrizConfusão.contadorZeroDois = 0;
		
		MatrizConfusão.contadorUmZero = 0;
		MatrizConfusão.contadorUmUm = 0;
		MatrizConfusão.contadorUmDois = 0;

		MatrizConfusão.contadorDoisZero = 0;
		MatrizConfusão.contadorDoisUm = 0;
		MatrizConfusão.contadorDoisDois = 0;
		
		BrayCurtis.brayCurtis(tamTeste, tamTreino, brayCurtis, classeBray, tamanhoColunas,
				treino, teste, nrClasses, vetorDeClassificacoes, k, acertosBray, tamanhoLinhas);
		
		
		MatrizConfusão.contadorZeroZero = 0;
		MatrizConfusão.contadorZeroUm = 0;
		MatrizConfusão.contadorZeroDois = 0;
		
		MatrizConfusão.contadorUmZero = 0;
		MatrizConfusão.contadorUmUm = 0;
		MatrizConfusão.contadorUmDois = 0;

		MatrizConfusão.contadorDoisZero = 0;
		MatrizConfusão.contadorDoisUm = 0;
		MatrizConfusão.contadorDoisDois = 0;
		
		ChiSquare.chiSquare(tamTeste, tamTreino, chiSquare, classeChi, tamanhoColunas, treino, teste, nrClasses, vetorDeClassificacoes, k, acertosChi, tamanhoLinhas);
		
		MatrizConfusão.contadorZeroZero = 0;
		MatrizConfusão.contadorZeroUm = 0;
		MatrizConfusão.contadorZeroDois = 0;
		
		MatrizConfusão.contadorUmZero = 0;
		MatrizConfusão.contadorUmUm = 0;
		MatrizConfusão.contadorUmDois = 0;

		MatrizConfusão.contadorDoisZero = 0;
		MatrizConfusão.contadorDoisUm = 0;
		MatrizConfusão.contadorDoisDois = 0;
		
		TopsoeDivergence.topsoeDivergence(tamTeste, tamTreino, topsoeDivergence, classeTop, tamanhoColunas, treino, teste, nrClasses, vetorDeClassificacoes, k, acertosTop, tamanhoLinhas);
		
		}
		
	

	

}
