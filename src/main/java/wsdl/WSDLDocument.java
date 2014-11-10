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
    
    public void AddMethod(Member member){
	if(member instanceof Method){
	    Method m = (Method)member;
	    GenerateTypes(m);
	    GenerateMessages(m);
	    GenerateOperations(m);
	}
    }
    
    public String GenerateXML(){
	return "";
    }

    private void GenerateTypes(Method m) {
	// Se genera un tipo para los argumentos
	// y otro para el retorno.
	if(m.getParameterCount()>0){
	types.add(new WSDLType(m, "Request", m.getParameterTypes()));
	}
	types.add(new WSDLType(m, m.getReturnType()));
    }

    private void GenerateMessages(Method m) {
	messages.add(new WSDLMessage(m, "Request"));
	messages.add(new WSDLMessage(m, "Response"));
    }

    private void GenerateOperations(Method m) {
	portType.add(new WSDLOperation(m));
    }
}
