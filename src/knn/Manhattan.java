package knn;

import java.io.IOException;

public class Manhattan {
		 public static void manhattan(int tamTeste, int tamTreino, double[] manhatan,
				 			double[] classeMan, int tamanhoColunas, double[][] treino, double[][] teste,
				 			int nrClasses, int[] vetorDeClassificacoes, int k, int acertosMan, int tamanhoLinhas) throws IOException{
			 
			 for(int i = 0;i<tamTeste;i++)
				{
					for(int j = 0;j<tamTreino;j++){
						double somaDeAtributosEuc = 0;
						double somaDeAtributosMan = 0;
						for(int l = 0;l<tamanhoColunas-1;l++){
							somaDeAtributosEuc= somaDeAtributosEuc + (Math.pow(teste[i][l]-treino[j][l], 2));
							somaDeAtributosMan = somaDeAtributosMan + (Math.sqrt(Math.pow(teste[i][l]-treino[j][l], 2)));
						}
						
						manhatan[j] = somaDeAtributosMan;
						classeMan[j] = treino[j][tamanhoColunas-1];
					}
					
					//ordenação pela menor distancia 
					
					for(int b=(tamTreino-1); b >= 1; b--)
					{
						for(int c = 0;c<b;c++){
							if(manhatan[c] > manhatan[c+1]){
								double aux1 = manhatan[c];
								double aux2 = classeMan[c];
								manhatan[c] = manhatan[c+1];
								classeMan[c] = classeMan[c+1];
								manhatan[c+1] = aux1;
								classeMan[c+1] = aux2;
							}
						}
						
					}
					
					//k menores distancias

					int[] vetorArmazenamentoMan = new int[nrClasses];
					
					for(int zero = 0;zero<nrClasses;zero++){
						vetorArmazenamentoMan[zero] = 0;
					}
					
					System.out.println((i+1) + " Manhattan\n");
					
					for(int n = 0;n<k;n++){
						for(int m = 0;m<nrClasses;m++){
							
							if(classeMan[n] == vetorDeClassificacoes[m]){
								vetorArmazenamentoMan[m]++;
							}
						}
						System.out.println(manhatan[n]+ ","+classeMan[n]);
					}

					int classificationMan = 0;
					
					for (int y = 1; y < nrClasses; y++){				   
						   int newnumber2 = vetorArmazenamentoMan[y];
						   if((newnumber2 > vetorArmazenamentoMan[classificationMan])){
							   classificationMan = y;
						   }
					}
					
					
				    
				    System.out.println((double)classificationMan + "->" + teste[i][tamanhoColunas-1]+ "\n\n");
				    
				    double cRotulo = teste[i][tamanhoColunas-1];
					int rotulo = (int) cRotulo;
					
					MatrizConfusão.MatConf(rotulo , classificationMan);
				   if(Double.compare(teste[i][tamanhoColunas-1], (double)classificationMan) == 0){
				    	acertosMan++;
				    }
				}
				
				double taxaMan = ((100.0*acertosMan) / (tamanhoLinhas/3.0));
				String dados = taxaMan + "% de acerto, total de acertos: " + acertosMan +
						" de " + tamanhoLinhas/3 + " para k = " + k +
						" Usando distancia de Manhatan\n\n";
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
