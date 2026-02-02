package pl.edu.wat.wcy.cop.app.client.jaxb;

import com.google.gwt.xml.client.Element;
// Defines the contract for xml node factory.

public interface XMLNodeFactory<T> {

    T build(Element e);

}
