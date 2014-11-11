/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsdl;

import java.lang.reflect.Method;
import static wsdl.WSDLOperation.getServiceURL;

/**
 *
 * @author esteban
 */
class WSDLBinding {

    private String xml;

    WSDLBinding(Method m) {
	String id = m.getName();
	StringBuilder sb = new StringBuilder();
	sb.append("    <operation name=\"").append(id).append("\">\n");
	sb.append("      <soap:operation soapAction=\"\"/>\n");
	sb.append("      <input>\n");
	sb.append("        <soap:body use=\"literal\"/>\n");
	sb.append("      </input>\n");
	sb.append("      <output>\n");
	sb.append("        <soap:body use=\"literal\"/>\n");
	sb.append("      </output>\n");
	sb.append("    </operation>\n");
	xml = sb.toString();
    }

    public String getXML() {
	return xml;
    }
}
