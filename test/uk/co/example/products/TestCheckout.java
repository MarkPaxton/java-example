package uk.co.example.products;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.co.example.checkout.Checkout;

public class TestCheckout {
	private static double delta = 0.000001d;
	@Test
	public void testCheckout() {
		ProductRepository products = new ProductRepository();
		products.Populate("data/products.json");
		
		Checkout checkout = new Checkout(products);
		
		Double total = checkout.Scan("apple\napple\norange\napple");
		
		assertEquals(2.05, total, delta);
	}

}
