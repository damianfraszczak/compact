/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.signs;

import pl.edu.wat.wcy.cop.app.client.jaxb.JAXBBindings;
import pl.edu.wat.wcy.cop.app.client.jaxb.JAXBParserFactory;
import pl.edu.wat.wcy.cop.app.shared.MSWiASymbol;

@JAXBBindings(value = MSWiASymbol.class, objects = {MSWiASymbol.class})
// Defines the contract for ms wi a symbol bean parser factory.
public interface MSWiASymbolBeanParserFactory extends JAXBParserFactory<MSWiASymbol> {
}
