/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.estebarb.wsdlgeneratormaven;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esteban
 */
public class WSDLGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	if (args.length != 2) {
	    System.out.println("El uso correcto es:\nWSDLGenerator clase archivoSalida\n");
	}
	try {
	    Class<?> c = Class.forName(args[0]);
	    System.out.format("Generando para: %s\n", args[0]);
	    System.out.format("Salida en: %s\n", args[1]);

	    export(c.getMethods(), "Methods");
	    printClasses(c);
	} catch (ClassNotFoundException ex) {
	    Logger.getLogger(WSDLGenerator.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private static void export(Member[] mbrs, String s) {
	System.out.format("%s:%n", s);
	for (Member mbr : mbrs) {
	    if (mbr instanceof Field) {
		System.out.format("  %s%n", ((Field) mbr).toGenericString());
	    } else if (mbr instanceof Constructor) {
		System.out.format("  %s%n", ((Constructor) mbr).toGenericString());
	    } else if (mbr instanceof Method) {
		System.out.format("  %s%n", ((Method) mbr).toGenericString());
	    }
	}
	if (mbrs.length == 0) {
	    System.out.format("  -- No %s --%n", s);
	}
	System.out.format("%n");
    }

    private static void printClasses(Class<?> c) {
	System.out.format("Classes:%n");
	Class<?>[] clss = c.getClasses();
	for (Class<?> cls : clss) {
	    System.out.format("  %s%n", cls.getCanonicalName());
	}
	if (clss.length == 0) {
	    System.out.format("  -- No member interfaces, classes, or enums --%n");
	}
	System.out.format("%n");
    }

}
