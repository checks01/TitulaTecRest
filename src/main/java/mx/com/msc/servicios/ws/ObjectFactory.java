
package mx.com.msc.servicios.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.com.msc.servicios.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ValidaUsuario_QNAME = new QName("http://servicios.msc.mx.com/", "validaUsuario");
    private final static QName _ValidaUsuarioResponse_QNAME = new QName("http://servicios.msc.mx.com/", "validaUsuarioResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.msc.servicios.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidaUsuario }
     * 
     */
    public ValidaUsuario createValidaUsuario() {
        return new ValidaUsuario();
    }

    /**
     * Create an instance of {@link ValidaUsuarioResponse }
     * 
     */
    public ValidaUsuarioResponse createValidaUsuarioResponse() {
        return new ValidaUsuarioResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidaUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios.msc.mx.com/", name = "validaUsuario")
    public JAXBElement<ValidaUsuario> createValidaUsuario(ValidaUsuario value) {
        return new JAXBElement<ValidaUsuario>(_ValidaUsuario_QNAME, ValidaUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidaUsuarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios.msc.mx.com/", name = "validaUsuarioResponse")
    public JAXBElement<ValidaUsuarioResponse> createValidaUsuarioResponse(ValidaUsuarioResponse value) {
        return new JAXBElement<ValidaUsuarioResponse>(_ValidaUsuarioResponse_QNAME, ValidaUsuarioResponse.class, null, value);
    }

}
