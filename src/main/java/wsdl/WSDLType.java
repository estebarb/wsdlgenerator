/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsdl;

import java.lang.reflect.*;

/**
 *
 * @author esteban
 */
class WSDLType {

    String typeName;
    String typeSeq;
    String kind;

    WSDLType(Method m, String request, Class<?>[] parameterTypes) {
	kind = request;
	typeName = m.getName() + request;
	typeSeq = GenerateType(m.getParameters());
    }

    WSDLType(Method m, String response, Class<?> returnType) {
	kind = response;
	typeName = m.getName() + response;
	typeSeq = ReturnType(m.getReturnType());
    }

    public String getXML() {
	StringBuilder sb = new StringBuilder();
	sb.append("<xs:element name=\"").append(typeName).append("\">\n");
	sb.append(typeSeq);
	sb.append("</xs:element>\n");
	return sb.toString();
    }

    private String ReturnType(Class<?> clase) {
	StringBuilder sb = new StringBuilder();
	sb.append("<xs:sequence>\n");
	switch (clase.getSimpleName()) {
	    case "void":
		return "  <xs:sequence/>\n";
	    case "Void":
		return "<xs:sequence/>";
	    case "int":
		sb.append("    <xs:element name=\"")
			.append("return")
			.append("\" type=\"xs:integer\"/>\n");
		break;
	    case "Integer":
		sb.append("    <xs:element name=\"")
			.append("return")
			.append("\" type=\"xs:integer\"/>\n");
		break;
	    case "Double":
		sb.append("    <xs:element name=\"")
			.append("return")
			.append("\" type=\"xs:decimal\"/>\n");
		break;
	    case "boolean":
		sb.append("    <xs:element name=\"")
			.append("return")
			.append("\" type=\"xs:boolean\"/>\n");
		break;
	    case "Boolean":
		sb.append("    <xs:element name=\"")
			.append("return")
			.append("\" type=\"xs:boolean\"/>\n");
		break;
	    default:
		sb.append("    <xs:element name=\"")
			.append("return")
			.append("\" type=\"xs:string\"/>\n");
		break;
	}
	sb.append("</xs:sequence>\n");
	return sb.toString();
    }

    private String GenerateType(Parameter[] parameterTypes) {
	String beginType = "";
	String endType = "";
	if (parameterTypes.length > 1) {
	    beginType = "  <xs:sequence>\n";
	    endType = "  </xs:sequence>\n";
	}
	if (parameterTypes.length == 0) {
	    return "<xs:sequence/>";
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
		    case "void":
			sb.append("<xs:sequence/>");
			break;
		    case "Void":
			sb.append("<xs:sequence/>");
			break;
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
