package knn;

import java.util.Arrays;
import java.util.Random;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double base[] = {3.2, 1.2, 9.7, 6.2, 4.5, 0.1};
		double copia[] = base;
		int a = base.length - 1;
		
		embaralhar(base);//distribui a base aleatoriamente
		
		Arrays.sort(copia); //ordena um vetor auxiliar
		double maior = copia[a];
		double menor = copia[0];
		
		
		//normalização do vetor
		for(int i = 0;i<a;i++)
		{
			base[i] = (base[i]- menor)/(maior-menor);
		}
		//separar base de teste
		
	}
	
	public static void embaralhar(double[] a){
	    Random ran = new Random();
	    
	    for(int i = 0; i < a.length; i++){
	      int pos = ran.nextInt(a.length);
	      double temp = a[i];
	      a[i] = a[pos];
	      a[pos] = temp;
	    }
	  }

}
