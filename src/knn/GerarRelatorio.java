package knn;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;



public class GerarRelatorio implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void salvarArquivo(String dados) throws IOException{
		FileWriter arq = new FileWriter("Relatorio.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.printf(dados);
		arq.close();

	}


}

