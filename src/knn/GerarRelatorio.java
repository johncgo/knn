package knn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;



public class GerarRelatorio implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String dados;
	private static GerarRelatorio instancia;
	
	if (instancia == null) {
		return;
	}
	File out = new File("repositorioConta.dat");
	FileOutputStream fos = null;
	ObjectOutputStream oos = null;

	try {
		fos = new FileOutputStream(out);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(instancia);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (oos != null) {
			try {
				oos.close();
			} catch (IOException e) {
			}
		}
	}

}	
}

