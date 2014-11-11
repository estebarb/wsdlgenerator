package wsdl;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author esteban
 */
public class WSDLDocument {

    List<WSDLType> types = new ArrayList<>();
    List<WSDLMessage> messages = new ArrayList<>();
    List<WSDLOperation> portType = new ArrayList<>();
    List<WSDLBinding> bindingOps = new ArrayList<>();

    public WSDLDocument(String serviceURL, String WSname) {
	setServiceURL(serviceURL);
	setWebServiceName(WSname);
    }

    private String ServiceURL;

    public String getServiceURL() {
	return ServiceURL;
    }

    public void setServiceURL(String ServiceURL) {
	this.ServiceURL = ServiceURL;
    }

    private String WebServiceName;

    public String getWebServiceName() {
	return WebServiceName;
    }

    public void setWebServiceName(String WebServiceName) {
	this.WebServiceName = WebServiceName;
    }

    public void AddMethod(Member member) {
	if (member instanceof Method) {
	    Method m = (Method) member;
	    GenerateTypes(m);
	    GenerateMessages(m);
	    GenerateOperations(m);
	    GenerateBinding(m);
	}
    }

    public String GenerateXML() {
	StringBuilder sb = new StringBuilder();
	// Encabezado
	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
	sb.append("<definitions targetNamespace=\"").append(getServiceURL())
		.append("\" name=\"")
		.append(getWebServiceName())
		.append("\" xmlns=\"http://schemas.xmlsoap.org/wsdl/\" xmlns:wsp=\"http://www.w3.org/ns/ws-policy\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" xmlns:wsp1_2=\"http://schemas.xmlsoap.org/ws/2004/09/policy\" xmlns:tns=\"")
		.append(getServiceURL())
		.append("\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/wsdl/soap/\" xmlns:wsam=\"http://www.w3.org/2007/05/addressing/metadata\">\n");
	
	// Tipos
	sb.append("<types><xsd:schema>\n");
	for(WSDLType t : types){
	    sb.append(t.getXML());
	}
	sb.append("</xsd:schema></types>\n");
	
	
	// Mensajes
	for(WSDLMessage m : messages){
	    sb.append(m.getXML());
	}
	
	// Port Type
	sb.append("<portType name=\"").append(getWebServiceName()).append("\">\n");
	for(WSDLOperation op : portType){
	    sb.append(op.getXML());
	}
	sb.append("</portType>\n");
		
	// Binding
	sb.append("<binding name=\""+getWebServiceName()+"PortBinding\" type=\"tns:"+getWebServiceName()+"\">\n" +
"    <soap:binding transport=\"http://schemas.xmlsoap.org/soap/http\" style=\"document\"/>");
	for(WSDLBinding binding : bindingOps){
	    sb.append(binding.getXML());
	}
	sb.append("</binding>\n");
	
	// Definición del servicio:
	sb.append("<service name=\""+getWebServiceName()+"\">\n" +
"    <port name=\""+getWebServiceName()+"Port\" binding=\"tns:"+getWebServiceName()+"PortBinding\">\n" +
"      <soap:address location=\""+getServiceURL()+"\"/>\n" +
"    </port>\n" +
"  </service>\n");
	
	// Finalización
	sb.append("</definitions>");
	return sb.toString();
    }

    private void GenerateTypes(Method m) {
	// Se genera un tipo para los argumentos
	// y otro para el retorno.
	if (m.getParameterCount() > 0) {
	    types.add(new WSDLType(m, "Request", m.getParameterTypes()));
	}
	types.add(new WSDLType(m, "Response", m.getReturnType()));
    }

    private void GenerateMessages(Method m) {
	messages.add(new WSDLMessage(m, "Request"));
	messages.add(new WSDLMessage(m, "Response"));
    }

    private void GenerateOperations(Method m) {
	portType.add(new WSDLOperation(m));
    }

    private void GenerateBinding(Method m) {
	bindingOps.add(new WSDLBinding(m));
    }
}
