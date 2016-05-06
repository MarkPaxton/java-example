package uk.co.example.checkout;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import uk.co.example.products.Product;
import uk.co.example.products.ProductRepository;

public class Checkout {
	private ProductRepository productRepo = null;
	
	public Checkout(ProductRepository r) {
		productRepo = r;
	}
	
	public double Scan(String items) {
		BufferedReader r = new BufferedReader(new StringReader(items));
		
		Map<String, Long> itemCounts = r.lines()
				.collect(Collectors.groupingBy(item -> item, Collectors.counting()));
		
		Map<Product, Long> productCounts = itemCounts.entrySet().stream()
				.collect(Collectors.<Entry<String, Long>, Product, Long>toMap(
					e -> productRepo.Get(e.getKey()),
					e -> e.getValue())
				);
		
		double total = productCounts.entrySet().stream()
				.mapToDouble(e -> {
					return e.getKey().Price() * e.getValue();
				}).sum();
		
		return total;
	}
}
