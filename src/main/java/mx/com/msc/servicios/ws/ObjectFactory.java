
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

    private final static QName _GeneraReporteResponse_QNAME = new QName("http://servicios.msc.mx.com/", "generaReporteResponse");
    private final static QName _GeneraReporte_QNAME = new QName("http://servicios.msc.mx.com/", "generaReporte");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.com.msc.servicios.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GeneraReporte }
     * 
     */
    public GeneraReporte createGeneraReporte() {
        return new GeneraReporte();
    }

    /**
     * Create an instance of {@link GeneraReporte.Parametros }
     * 
     */
    public GeneraReporte.Parametros createGeneraReporteParametros() {
        return new GeneraReporte.Parametros();
    }

    /**
     * Create an instance of {@link GeneraReporteResponse }
     * 
     */
    public GeneraReporteResponse createGeneraReporteResponse() {
        return new GeneraReporteResponse();
    }

    /**
     * Create an instance of {@link GeneraReporte.Parametros.Entry }
     * 
     */
    public GeneraReporte.Parametros.Entry createGeneraReporteParametrosEntry() {
        return new GeneraReporte.Parametros.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneraReporteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios.msc.mx.com/", name = "generaReporteResponse")
    public JAXBElement<GeneraReporteResponse> createGeneraReporteResponse(GeneraReporteResponse value) {
        return new JAXBElement<GeneraReporteResponse>(_GeneraReporteResponse_QNAME, GeneraReporteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneraReporte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios.msc.mx.com/", name = "generaReporte")
    public JAXBElement<GeneraReporte> createGeneraReporte(GeneraReporte value) {
        return new JAXBElement<GeneraReporte>(_GeneraReporte_QNAME, GeneraReporte.class, null, value);
    }

}
