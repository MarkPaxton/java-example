package uk.co.example.checkout;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import uk.co.example.products.Product;
import uk.co.example.products.ProductOffer;
import uk.co.example.products.ProductRepository;

public class Checkout {
	private ProductRepository productRepo = null;
	
	public Checkout(ProductRepository r) {
		productRepo = r;
	}
	
	private Map<Product, Long> productCounts(String items) {
		BufferedReader r = new BufferedReader(new StringReader(items));
		return r.lines()
			.collect(Collectors.groupingBy(item -> item, Collectors.counting()))
			.entrySet().stream()
			.collect(Collectors.<Entry<String, Long>, Product, Long>toMap(
				e -> productRepo.Get(e.getKey()),
				e -> e.getValue())
			);
	}
	
	/**
	 * Scan takes a string of scanned items separated by newlines e.g apple\norange\nappple
	 * and returns the total price
	 * @param items
	 * @return
	 */
	public double Scan(String items) {
		return productCounts(items).entrySet().stream()
			.mapToDouble(e -> {
				return e.getKey().Price() * e.getValue();
			}).sum();
	}
	
	/**
	 * Scan takes a string of scanned items separated by newlines e.g apple\norange\nappple
	 * and returns the total price, including applying offers
	 * 
	 * @param items
	 * @return
	 */
	public double ScanWithOffers(String items) {
		return productCounts(items).entrySet().stream()
			.mapToDouble(e -> {
				Long payCount = null;					
				ProductOffer offer = productRepo.GetOffer(e.getKey().ID());
				if (offer != null) {
					payCount = e.getValue() - (offer.FreeAmount() * Math.floorDiv(e.getValue(), offer.PurchaseAmount() + offer.FreeAmount()) );
				} else {
					payCount =e.getValue();
				}
				return payCount * e.getKey().Price();
			})
			.sum();		
	}
}
