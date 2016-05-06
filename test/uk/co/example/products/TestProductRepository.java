package uk.co.example.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestProductRepository {
	private static double delta = 0.000001d;
	
	@Test
	public void testPopulateData() {
		ProductRepository repo = new ProductRepository();
		repo.Populate("data/test-products.json");
		
		Product p1 = repo.Get("1");
		assertNotNull(p1);
		Product p2 = repo.Get("2");
		assertNotNull(p2);
		
		assertEquals(p1.ID(), "1");
		assertEquals(p2.ID(), "2");
		assertEquals(p1.Name(), "test1");
		assertEquals(p2.Name(), "test2");
		assertEquals(2.0, p1.Price(), delta);
		assertEquals(0.2, p2.Price(), delta);
	}

	@Test
	public void testPopulateOffers() {
		ProductRepository repo = new ProductRepository();
		repo.PopulateOffers("data/test-products.json", "data/test-offers.json");

		Product p1 = repo.Get("1");
		assertNotNull(p1);
		Product p2 = repo.Get("2");
		assertNotNull(p2);
		
		assertEquals(p1.ID(), "1");
		assertEquals(p2.ID(), "2");
		assertEquals(p1.Name(), "test1");
		assertEquals(p2.Name(), "test2");
		assertEquals(2.0, p1.Price(), delta);
		assertEquals(0.2, p2.Price(), delta);
		
		ProductOffer o1 = repo.GetOffer("1");
		assertNotNull(o1);
		ProductOffer o2 = repo.GetOffer("2");
		assertNotNull(o2);
		
		assertEquals(o1.Product(), "1");
		assertEquals(o2.Product(), "2");
		assertEquals(1, o1.PurchaseAmount(), delta);
		assertEquals(5, o2.PurchaseAmount(), delta);
		assertEquals(1, o1.FreeAmount(), delta);
		assertEquals(1, o2.FreeAmount(), delta);
	}
	
	@Test
	public void testGet() {
		ProductRepository repo = new ProductRepository();

		Product p1 = repo.Get("1");
		assertNull(p1);		
		repo.Populate("data/test-products.json");
		p1 = repo.Get("1");
		assertNotNull(p1);
	}

}
