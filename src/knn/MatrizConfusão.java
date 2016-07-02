package knn;

public class MatrizConfusão {
	static int contadorZeroZero = 0;
	static int contadorZeroUm = 0;
	static int contadorZeroDois = 0;
	
	static int contadorUmZero = 0;
	static int contadorUmUm = 0;
	static int contadorUmDois = 0;
	
	static int contadorDoisZero = 0;
	static int contadorDoisUm = 0;
	static int contadorDoisDois = 0;
	
	public static void MatConf(int classeRotulada, int classeClassificada){
		
		//classe zero
		if(classeRotulada == 0 && classeClassificada == 0){
			contadorZeroZero++;
		}
		else if (classeRotulada == 0 && classeClassificada == 1){
			contadorZeroUm++;
		}
		else if (classeRotulada == 0 && classeClassificada == 2){
			contadorZeroDois++;
		}
		//classe um
		else if (classeRotulada == 1 && classeClassificada == 0){
			contadorUmZero++;
		}
		else if (classeRotulada == 1 && classeClassificada == 1){
			contadorUmUm++;
		}
		else if (classeRotulada == 1 && classeClassificada == 2){
			contadorUmDois++;
		}
		//classe dois
		else if (classeRotulada == 2 && classeClassificada == 0){
			contadorDoisZero++;
		}
		else if (classeRotulada == 2 && classeClassificada == 1){
			contadorDoisUm++;
		}
		else if (classeRotulada == 2 && classeClassificada == 2){
			contadorDoisDois++;
		}
	}
}
