package uk.co.example.products;

import static org.junit.Assert.assertEquals;

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

	@Test
	public void testCheckoutWithOffers() {
		ProductRepository productRepo = new ProductRepository();
		productRepo.PopulateOffers("data/products.json", "data/offers.json");
		
		Checkout checkout = new Checkout(productRepo);
		
		/* Buying with buy one get one free apples and buy 2 get one free oranges 
		 *  2 apples, 1 free = £0.60 + 0
		 *  2 oranges, 1 free = £0.25 + £0.25 + 0 = £0.50
		 *  total = £1.10
		 * 
		 * */
		Double total = checkout.ScanWithOffers("apple\napple\norange\norange\norange");
		
		assertEquals(1.10, total, delta);
	}
	
}
