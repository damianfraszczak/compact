package pl.edu.wat.wcy.cop.app.client.jaxb;
// Defines the contract for jaxb parser.

public interface JAXBParser<T> {

    T parse(String xml);

}
