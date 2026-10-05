package pl.edu.wat.wcy.cop.app.client.jaxb;
// Defines the contract for jaxb parser factory.

public interface JAXBParserFactory<T> {

    pl.edu.wat.wcy.cop.app.client.jaxb.JAXBParser<T> create();

}
