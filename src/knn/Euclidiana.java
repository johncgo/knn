package knn;

import java.io.IOException;

public class Euclidiana {
	private static String dados;
	 public static void euclidiana(int tamTeste, int tamTreino, double[] euclidiana,
			 			double[] classeEucli, int tamanhoColunas, double[][] treino, double[][] teste,
			 			int nrClasses, int[] vetorDeClassificacoes, int k, int acertosEucli, int tamanhoLinhas) throws IOException{
		 
		 for(int i = 0;i<tamTeste;i++)
			{
				for(int j = 0;j<tamTreino;j++){
					double somaDeAtributosEuc = 0;
					//double somaDeAtributosMan = 0;
					for(int l = 0;l<tamanhoColunas-1;l++){
						somaDeAtributosEuc= somaDeAtributosEuc + (Math.pow(teste[i][l]-treino[j][l], 2));
						//somaDeAtributosMan = somaDeAtributosMan + (Math.sqrt(Math.pow(teste[i][l]-treino[j][l], 2)));
					}
					euclidiana[j] = Math.sqrt(somaDeAtributosEuc);
					classeEucli[j] = treino[j][tamanhoColunas-1];
				}
				
				//ordenação pela menor distancia 
				for(int b=(tamTreino-1); b >= 1; b--)
				{
					for(int c = 0;c<b;c++){
						if(euclidiana[c] > euclidiana[c+1]){
							double aux1 = euclidiana[c];
							double aux2 = classeEucli[c];
							euclidiana[c] = euclidiana[c+1];
							classeEucli[c] = classeEucli[c+1];
							euclidiana[c+1] = aux1;
							classeEucli[c+1] = aux2;
						}
					}
					
				}

				//k menores distancias
				System.out.println((i+1) + " Euclidiana\n");
				
				
				int[] vetorArmazenamentoEuc = new int[nrClasses];
				
				for(int zero = 0;zero<nrClasses;zero++){
					vetorArmazenamentoEuc[zero] = 0;
				}
				for(int n = 0;n<k;n++){
					for(int m = 0;m<nrClasses;m++){
						if(classeEucli[n]== vetorDeClassificacoes[m]){
							vetorArmazenamentoEuc[m]++;
						}
						
					}
					System.out.println(euclidiana[n]+","+classeEucli[n]);
					
				}
				int classificationEuc = 0; //inicializar com um valor qualquer
				for (int y = 1; y < nrClasses; y++){
					   int newnumber = vetorArmazenamentoEuc[y];
					   if ((newnumber > vetorArmazenamentoEuc[classificationEuc])){
					   classificationEuc = y;
					   }
				}
				System.out.println((double)classificationEuc + "->"+ teste[i][tamanhoColunas-1]+"\n\n");
				double cRotulo = teste[i][tamanhoColunas-1];
				int rotulo = (int) cRotulo;
				
				MatrizConfusão.MatConf(rotulo , classificationEuc);
				
			    if(Double.compare(teste[i][tamanhoColunas-1], (double)classificationEuc) == 0){
			    		acertosEucli++;
			    	}
			   
			}
		 	
			double taxaEuc = ((100.0*acertosEucli) / (tamanhoLinhas/3.0));
			dados = taxaEuc + "% de acerto, total de acertos: " + acertosEucli +
					" de " + tamanhoLinhas/3.0 + " para k = "+ k + 
					" Usando distancia euclidiana\n\n";
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
		Euclidiana.dados = dados;
	}
}
