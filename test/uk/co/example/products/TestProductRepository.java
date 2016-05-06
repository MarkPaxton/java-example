package uk.co.example.products;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestProductRepository {
	private static double delta = 0.000001d;
	
	@Test
	public void testPopulate() {
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
	public void testGet() {
		ProductRepository repo = new ProductRepository();

		Product p1 = repo.Get("1");
		assertNull(p1);		
		repo.Populate("data/test-products.json");
		p1 = repo.Get("1");
		assertNotNull(p1);
	}

}
