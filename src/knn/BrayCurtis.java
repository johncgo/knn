package knn;

import java.io.IOException;

public class BrayCurtis {
	private static String dados;
	public static void brayCurtis(int tamTeste, int tamTreino, double[] brayCurtis,
 			double[] classeBray, int tamanhoColunas, double[][] treino, double[][] teste,
 			int nrClasses, int[] vetorDeClassificacoes, int k, int acertosBray, int tamanhoLinhas) throws IOException{
		
		
		for(int i = 0;i<tamTeste;i++)
		{
			for(int j = 0;j<tamTreino;j++){
				double somaDeAtributosBray = 0;
				for(int l = 0;l<tamanhoColunas-1;l++){
					somaDeAtributosBray = somaDeAtributosBray + (Math.abs(teste[i][l]-treino[j][l]));
				}
				brayCurtis[j] = somaDeAtributosBray;
				classeBray[j] = treino[j][tamanhoColunas-1];
			}
			
			//ordenação pela menor distancia 
			for(int b=(tamTreino-1); b >= 1; b--)
			{
				for(int c = 0;c<b;c++){
					if(brayCurtis[c] > brayCurtis[c+1]){
						double aux1 = brayCurtis[c];
						double aux2 = classeBray[c];
						brayCurtis[c] = brayCurtis[c+1];
						classeBray[c] = classeBray[c+1];
						brayCurtis[c+1] = aux1;
						classeBray[c+1] = aux2;
					}
				}
				
			}

			//k menores distancias
			System.out.println((i+1) + " Bray Curtis\n");
			
			
			int[] vetorArmazenamentoBray = new int[nrClasses];
			
			for(int zero = 0;zero<nrClasses;zero++){
				vetorArmazenamentoBray[zero] = 0;
			}
			for(int n = 0;n<k;n++){
				for(int m = 0;m<nrClasses;m++){
					if(classeBray[n]== vetorDeClassificacoes[m]){
						vetorArmazenamentoBray[m]++;
					}
					
				}
				System.out.println(brayCurtis[n]+","+classeBray[n]);
				
			}
			int classificationBray = 0; //inicializar com um valor qualquer
			for (int y = 1; y < nrClasses; y++){
				   int newnumber = vetorArmazenamentoBray[y];
				   if ((newnumber > vetorArmazenamentoBray[classificationBray])){
				   classificationBray = y;
				   }
			}
			System.out.println((double)classificationBray + "->"+ teste[i][tamanhoColunas-1]+"\n\n");
			double cRotulo = teste[i][tamanhoColunas-1];
			int rotulo = (int) cRotulo;
			
			MatrizConfusão.MatConf(rotulo , classificationBray);
			
		    if(Double.compare(teste[i][tamanhoColunas-1], (double)classificationBray) == 0){
		    		acertosBray++;
		    	}
		   
		}
		
		double taxaEuc = ((100.0*acertosBray) / (tamanhoLinhas/3.0));
		dados = taxaEuc + "% de acerto, total de acertos: " + acertosBray +
				" de " + tamanhoLinhas/3.0 + " para k = "+ k + 
				" Usando distancia de Bray Curtis\n\n";
		
		System.out.println(dados);
		
		
		System.out.println("Matriz de Confusão:");
		System.out.println(MatrizConfusão.contadorZeroZero + "\t" + 
							MatrizConfusão.contadorZeroUm + "\t" +
							MatrizConfusão.contadorZeroDois);
		
		System.out.println(MatrizConfusão.contadorUmZero + "\t" + 
				MatrizConfusão.contadorUmUm + "\t" +
				MatrizConfusão.contadorUmDois);
		
		System.out.println(MatrizConfusão.contadorDoisZero + "\t" + 
				MatrizConfusão.contadorDoisUm + "\t" +
				MatrizConfusão.contadorDoisDois);

	}
	static String getDados() {
		return dados;
	}
	private static void setDados(String dados) {
		BrayCurtis.dados = dados;
	}
}
