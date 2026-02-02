/**
 * 
 */
package pl.edu.wat.wcy.cop.symbolservice;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
// Represents symbol service test.


public class SymbolServiceTest {
	SymbolService service;

	@Before
	public void init() {
		service = new SymbolService();
	}

	@Test
	public void test() throws Exception {
		assertNotNull(service.getImageForCode(SymbolType.APP6A, "sfgpiur---h----", 50, 50));
		assertNotNull(service.getImageForCode(SymbolType.APP6A, "sfgpixh---h----", 50, 50));
		assertNotNull(service.getImageForCode(SymbolType.APP6A, "sfgpucath-ee---", 50, 50));
	}

	@Test(expected = ImageNotFoundForCodeException.class)
	public void test2() throws Exception {
		assertNotNull(service.getImageForCode(SymbolType.APP6A, "", 50, 50));
	}

	@Test
	public void test3() throws Exception {
		assertNotNull(service.getImageForCode(SymbolType.BG, "4.1.001", 50, 50));
	}

	@Test
	public void test4() throws Exception {
		assertNotNull(service.getImageForCode(SymbolType.POLICE, "3.2.019", 50, 50));
	}

	@Test
	public void test5() throws Exception {

		for (int i = 0; i < 5; i++) {
			assertNotNull(service.getImageForCode(SymbolType.BG,
					String.format("4.1.%03d", (int) (Math.random() * 50 + 1)), 50, 50));

		}
		for (int i = 0; i < 8; i++) {
			assertNotNull(service.getImageForCode(SymbolType.POLICE,
					String.format("3.2.%03d", (int) (Math.random() * 20 + 1)), 50, 50));

		}

	}

}
