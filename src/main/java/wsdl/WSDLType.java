/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsdl;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 *
 * @author esteban
 */
class WSDLType {

    String typeName;
    String typeSeq;

    WSDLType(Method m, String request, Class<?>[] parameterTypes) {
	typeName = m.getName() + "Request";
	typeSeq = GenerateType(m.getParameters());
    }

    WSDLType(Method m, Class<?> returnType) {
	typeName = m.getName() + "Response";
	typeSeq = GenerateType(m.getParameters());
    }

    public String getType() {
	StringBuilder sb = new StringBuilder();
	sb.append("<xs:element name=\"").append(typeName).append("\">");
	sb.append(typeSeq);
	sb.append("</xs:element>");
	return sb.toString();
    }

    private String GenerateType(Parameter[] parameterTypes) {
	String beginType = "";
	String endType = "";
	if (parameterTypes.length > 1) {
	    beginType = "  <xs:sequence>\n";
	    endType = "  </xs:sequence>\n";
	}
	StringBuilder sb = new StringBuilder();
	sb.append(beginType);
	for (Parameter p : parameterTypes) {
	    if (p.getClass().isArray()) {
		sb.append("<xs:element name=\"")
			.append(p.getName())
			.append("\" type=\"xs:anyType\" nillable=\"true\" minOccurs=\"0\" maxOccurs=\"unbounded\"/>\n");
	    } else {
		switch (p.getClass().getSimpleName()) {
		    case "int":
			sb.append("    <xs:element name=\"")
				.append(p.getName())
				.append("\" type=\"xs:integer\"/>\n");
			break;
		    case "Integer":
			sb.append("    <xs:element name=\"")
				.append(p.getName())
				.append("\" type=\"xs:integer\"/>\n");
			break;
		    case "Double":
			sb.append("    <xs:element name=\"")
				.append(p.getName())
				.append("\" type=\"xs:decimal\"/>\n");
			break;
		    case "boolean":
			sb.append("    <xs:element name=\"")
				.append(p.getName())
				.append("\" type=\"xs:boolean\"/>\n");
			break;
		    case "Boolean":
			sb.append("    <xs:element name=\"")
				.append(p.getName())
				.append("\" type=\"xs:boolean\"/>\n");
			break;
		    default:
			sb.append("    <xs:element name=\"")
				.append(p.getName())
				.append("\" type=\"xs:string\"/>\n");
			break;
		}
	    }
	}
	sb.append(endType);
	return sb.toString();
    }
}
