package knn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;
public class Main {

	private static final Object[] String = null;





	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int k = 7;
		//abertura do arquivo
		BufferedReader reader =
				   new BufferedReader(new FileReader("BeaNbea.arff"));
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
		int acertos = 0;
		int erros = 0;
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
			String []particao = x[i].split(","); // quebra a string cada vez que encontrar um espaco 
			double[] split = new double[particao.length];//criar um vetor com o tamanhoLinhasLinhas da quantidade de elementos			
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
		//copiar todo vetor de base para conseguir definir os maiores valores, fazendo normalização por coluna
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
		double[] classe = new double[tamTreino]; 
		double total = 0.0;
		
		//calcular as distancias pegando cada valor de teste e comparando com todos valores de treino
		
		for(int i = 0;i<tamTeste;i++)
		{
			for(int j = 0;j<tamTreino;j++){
				double somaDeAtributos = 0;
				for(int l = 0;l<tamanhoColunas-1;l++){
					somaDeAtributos = somaDeAtributos + (Math.pow(teste[i][l]-treino[j][l], 2));
				}
				euclidiana[j] = Math.sqrt(somaDeAtributos);
				classe[j] = treino[j][tamanhoColunas-1];
			}
			
			//ordenação pela menor distancia
			for(int b=(tamTreino-1); b >= 1; b--)
			{
				for(int c = 0;c<b;c++){
					if(euclidiana[c] > euclidiana[c+1]){
						double aux1 = euclidiana[c];
						double aux2 = classe[c];
						euclidiana[c] = euclidiana[c+1];
						classe[c] = classe[c+1];
						euclidiana[c+1] = aux1;
						classe[c+1] = aux2;
					}
				}
				
			}
			
			//k menores distancias
			System.out.println(i+1);
			
			
			int[] vetorArmazenamento = new int[nrClasses];
			for(int zero = 0;zero<nrClasses;zero++){
				vetorArmazenamento[zero] = 0;
			}
			for(int n = 0;n<k;n++){
				//total = total + classe[n];
				for(int m = 0;m<nrClasses;m++){
					if(classe[n]== vetorDeClassificacoes[m]){
						vetorArmazenamento[m]++;
					}
				}
				System.out.println(euclidiana[n]+","+classe[n]);
			}
			total = total/k;
			
			
			int classification = 0; //inicializar com um valor qualquer
			for (int y = 1; y < nrClasses; y++){
				   int newnumber = vetorArmazenamento[y];
				   if ((newnumber > vetorArmazenamento[classification])){
				   classification = y;
				  }
			}
			
		    System.out.println((double)classification + "\n\n");
		    
		    if(Double.compare(teste[i][tamanhoColunas-1], (double)classification) == 0){
		    		acertos++;
		    	}
		    	
		}
		double taxa = ( (100.0*acertos) / (tamanhoLinhas/3.0));
		System.out.println(taxa + "% de acerto, total de acertos: " + acertos +" de " + tamanhoLinhas/3 + " para todo k = "+ k);
		
	}
	

	

}
