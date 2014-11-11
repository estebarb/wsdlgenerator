/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.estebarb.wsdlgeneratormaven;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import wsdl.WSDLDocument;

/**
 *
 * @author esteban
 */
public class WSDLGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
	if (args.length != 3 || args.length != 4) {
	    System.out.println("El uso correcto es:\nWSDLGenerator clase archivoSalida urlWS [wsname]\n");
	}

	Class<?> c = Class.forName(args[0]);
	System.out.format("Generando para: %s\n", args[0]);
	System.out.format("Salida en: %s\n", args[1]);

	String wsName;
	if (args.length == 3) {
	    wsName = args[0];
	} else {
	    wsName = args[3];
	}

	WSDLDocument doc = new WSDLDocument(args[2], wsName);
	for (Method m : c.getDeclaredMethods()) {
	    if (m instanceof Method) {
		System.out.format("  %s%n", ((Method) m).toGenericString());
		doc.AddMethod(m);
	    }
	}

	String salida = doc.GenerateXML();
	PrintWriter out = new PrintWriter(args[1]);
	out.println(salida);
	out.close();
    }

}
