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
class WSDLOperation {
    
    private static String ServiceURL = "http://servicioweb";

    public static String getServiceURL() {
	return ServiceURL;
    }

    public static void setServiceURL(String ServiceURL) {
	WSDLOperation.ServiceURL = ServiceURL;
    }
    
    private String xml;

    WSDLOperation(Method m) {
	String id = m.getName();
	StringBuilder sb = new StringBuilder();
	sb.append("    <operation name=\"").append(id).append("\">\n");
	sb.append("        <input wsam:Action=\"").append(getServiceURL()).append("/").append(id).append("Request\" message=\"tns:").append(id).append("Request\"/>\n");
	sb.append("        <output wsam:Action=\"").append(getServiceURL()).append("/").append(id).append("Response\" message=\"tns:").append(id).append("Response\"/>\n");
	sb.append("    </operation>\n");
	xml = sb.toString();
    }
    
    public String getXML(){
	return xml;
    }
    
}
