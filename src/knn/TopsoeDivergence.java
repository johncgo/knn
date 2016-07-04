package knn;

import java.io.IOException;

public class TopsoeDivergence {
	private static String dados;
	public static void topsoeDivergence(int tamTeste, int tamTreino, double[] topsoeDivergence,
			double[] classeTop, int tamanhoColunas, double[][] treino, double[][] teste,
			int nrClasses, int[] vetorDeClassificacoes, int k, int acertosTop, int tamanhoLinhas) throws IOException{


		for(int i = 0;i<tamTeste;i++)
		{
			for(int j = 0;j<tamTreino;j++){
				double somaDeAtributosTop = 0;
				double t = 0;
				for(int l = 0;l<tamanhoColunas-1;l++){
					if(teste[i][l] != 0 && treino[j][l] != 0){
						t = teste[i][l]+treino[j][l];
						somaDeAtributosTop = somaDeAtributosTop + ((teste[i][l] * Math.log(2*teste[i][l]/t)) +
								(treino[j][l] * Math.log(2*treino[j][l]/t)));
					}

				}
				topsoeDivergence[j] = somaDeAtributosTop;
				classeTop[j] = treino[j][tamanhoColunas-1];
			}

			//ordenação pela menor distancia 
			for(int b=(tamTreino-1); b >= 1; b--)
			{
				for(int c = 0;c<b;c++){
					if(topsoeDivergence[c] > topsoeDivergence[c+1]){
						double aux1 = topsoeDivergence[c];
						double aux2 = classeTop[c];
						topsoeDivergence[c] = topsoeDivergence[c+1];
						classeTop[c] = classeTop[c+1];
						topsoeDivergence[c+1] = aux1;
						classeTop[c+1] = aux2;
					}
				}

			}

			//k menores distancias
			System.out.println((i+1) + " Topsoe Divergence\n");


			int[] vetorArmazenamentoTop = new int[nrClasses];

			for(int zero = 0;zero<nrClasses;zero++){
				vetorArmazenamentoTop[zero] = 0;
			}
			for(int n = 0;n<k;n++){
				for(int m = 0;m<nrClasses;m++){
					if(classeTop[n]== vetorDeClassificacoes[m]){
						vetorArmazenamentoTop[m]++;
					}

				}
				System.out.println(topsoeDivergence[n]+","+classeTop[n]);

			}
			int classificationTop = 0; //inicializar com um valor qualquer
			for (int y = 1; y < nrClasses; y++){
				int newnumber = vetorArmazenamentoTop[y];
				if ((newnumber > vetorArmazenamentoTop[classificationTop])){
					classificationTop = y;
				}
			}
			System.out.println((double)classificationTop + "->"+ teste[i][tamanhoColunas-1]+"\n\n");
			double cRotulo = teste[i][tamanhoColunas-1];
			int rotulo = (int) cRotulo;

			MatrizConfusão.MatConf(rotulo , classificationTop);

			if(Double.compare(teste[i][tamanhoColunas-1], (double)classificationTop) == 0){
				acertosTop++;
			}

		}
		 
		double taxaEuc = ((100.0*acertosTop) / (tamanhoLinhas/3.0));
		dados = taxaEuc + "% de acerto, total de acertos: " + acertosTop +
				" de " + tamanhoLinhas/3.0 + " para k = "+ k + 
				" Usando distancia de Topsoe Divergence\n\n";
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
		TopsoeDivergence.dados = dados;
	}
}
