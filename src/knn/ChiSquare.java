package knn;

import java.io.IOException;

public class ChiSquare {
	public static void chiSquare(int tamTeste, int tamTreino, double[] chiSquare,
 			double[] classeChi, int tamanhoColunas, double[][] treino, double[][] teste,
 			int nrClasses, int[] vetorDeClassificacoes, int k, int acertosChi, int tamanhoLinhas) throws IOException{
		
		
		for(int i = 0;i<tamTeste;i++)
		{
			for(int j = 0;j<tamTreino;j++){
				double somaDeAtributosChi = 0;
				for(int l = 0;l<tamanhoColunas-1;l++){
					double t = teste[i][l]+treino[j][l];
					if(t !=0){
						somaDeAtributosChi = somaDeAtributosChi + (Math.pow(teste[i][l]-treino[j][l], 2)/t);
					}			
				}
				chiSquare[j] = somaDeAtributosChi * 0.5;
				classeChi[j] = treino[j][tamanhoColunas-1];
			}
			
			//ordenação pela menor distancia 
			for(int b=(tamTreino-1); b >= 1; b--)
			{
				for(int c = 0;c<b;c++){
					if(chiSquare[c] > chiSquare[c+1]){
						double aux1 = chiSquare[c];
						double aux2 = classeChi[c];
						chiSquare[c] = chiSquare[c+1];
						classeChi[c] = classeChi[c+1];
						chiSquare[c+1] = aux1;
						classeChi[c+1] = aux2;
					}
				}
				
			}

			//k menores distancias
			System.out.println((i+1) + " Chi Square\n");
			
			
			int[] vetorArmazenamentoChi = new int[nrClasses];
			
			for(int zero = 0;zero<nrClasses;zero++){
				vetorArmazenamentoChi[zero] = 0;
			}
			for(int n = 0;n<k;n++){
				for(int m = 0;m<nrClasses;m++){
					if(classeChi[n]== vetorDeClassificacoes[m]){
						vetorArmazenamentoChi[m]++;
					}
					
				}
				System.out.println(chiSquare[n]+","+classeChi[n]);
				
			}
			int classificationChi = 0; //inicializar com um valor qualquer
			for (int y = 1; y < nrClasses; y++){
				   int newnumber = vetorArmazenamentoChi[y];
				   if ((newnumber > vetorArmazenamentoChi[classificationChi])){
				   classificationChi = y;
				   }
			}
			System.out.println((double)classificationChi + "->"+ teste[i][tamanhoColunas-1]+"\n\n");
			double cRotulo = teste[i][tamanhoColunas-1];
			int rotulo = (int) cRotulo;
			
			MatrizConfusão.MatConf(rotulo , classificationChi);
			
		    if(Double.compare(teste[i][tamanhoColunas-1], (double)classificationChi) == 0){
		    		acertosChi++;
		    	}
		   
		}
		double taxaEuc = ((100.0*acertosChi) / (tamanhoLinhas/3.0));
		String dados = taxaEuc + "% de acerto, total de acertos: " + acertosChi +
				" de " + tamanhoLinhas/3 + " para k = "+ k + 
				" Usando distancia de Chi Square\n\n";
		System.out.println(dados);
		
		GerarRelatorio.salvarArquivo(dados);
		
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
}