package knn;

import java.util.Random;
import java.util.StringTokenizer;

public class Utils {
	public static void embaralhar(String[] emb){
	    Random ran = new Random();
	    
	    for(int i = 0; i < emb.length; i++){
	      int pos = ran.nextInt(emb.length);
	      String temp = emb[i];
	      emb[i] = emb[pos];
	      emb[pos] = temp;
	    }
	  }
	public static String[] separaClasse(String enumeracaoClasse){
		StringTokenizer aux = new StringTokenizer(enumeracaoClasse, "{}");
		aux.nextToken();
		String semPontos = aux.nextToken();
		String[] classesAtuais = semPontos.split(",");
		return classesAtuais;
	}
}
