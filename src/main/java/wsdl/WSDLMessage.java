/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wsdl;

import java.lang.reflect.Method;

/**
 *
 * @author esteban
 */
class WSDLMessage {
    String xml;

    WSDLMessage(Method m, String response) {
	String identifier = m.getName()+response;
	StringBuilder sb = new StringBuilder();
	sb.append("<message name=\"").append(identifier).append("\">\n");
	sb.append("  <part name=\"parameters\" element=\"tns:")
		.append(identifier).append("\"/>\n");
	sb.append("</message>\n");
	xml = sb.toString();
    }
    
    public String getXML(){
	
	return xml;
    }
    
}
