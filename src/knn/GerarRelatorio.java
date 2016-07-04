package knn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class GerarRelatorio {

	private static final long serialVersionUID = 1L;

	public static void salvarArquivo(String dados) throws IOException{
		
		File arquivo = new File("Relatorio.txt");
		try{
			arquivo.createNewFile();
           /*ESCREVER*/
            FileWriter fileW = new FileWriter (arquivo);//arquivo para escrita
            BufferedWriter buffW = new BufferedWriter (fileW);
            buffW.write (dados);
            buffW.newLine ();
            buffW.close ();
        } catch (IOException io)
        {
        }
    }

		

	}




