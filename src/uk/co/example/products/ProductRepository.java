package uk.co.example.products;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ProductRepository {
	private static  JSONParser Parser = new JSONParser();
	private Map<String, Product> products = new HashMap<>();
	private Map<String, ProductOffer> offers = new HashMap<>();

	public void Populate(String fileName) {
		products.clear();

		try {
			FileReader r = new FileReader(fileName);
			// Making an assumption here that the input json file is well
			// formatted and parseable.
			
			List<JSONObject> data = null;
			try {
				data = (List<JSONObject>) Parser.parse(r);
				Map<String, Product> productList = data.stream()
						.collect(Collectors.<JSONObject, String, Product>toMap(
								item -> (String) item.get("id"), 
								item -> {
									String id = (String) item.get("id");
									String name = (String) item.get("name");
									Number price = (Number) item.get("price");
									Product p = new Product(id, price.doubleValue(), name);
									return p;
								})
						);
				products.putAll(productList);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			r.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void PopulateOffers(String fileName, String offerFileName) {
		Populate(fileName);
		offers.clear();
		try {
			FileReader r = new FileReader(offerFileName);
			// Making an assumption here that the input json file is well
			// formatted and parseable.
			List<JSONObject> offersData = null;
			try {
				offersData = (List<JSONObject>) Parser.parse(r);
				Map<String, ProductOffer> offersList = offersData.stream()
						.collect(Collectors.<JSONObject, String, ProductOffer>toMap(
								item -> (String) item.get("product"), 
								item -> {
									String product = (String) item.get("product");
									Number buy = (Number) item.get("buy");
									Number free = (Number) item.get("free");
									ProductOffer o = new ProductOffer(product, buy.intValue(), free.intValue());
									return o;
								})
						);
				offers.putAll(offersList);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			r.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	
	public Product Get(String productId) {
		Product p = null;
		if (products.containsKey(productId)) {
			p = products.get(productId);
		}
		return p;
	}
	
	public ProductOffer GetOffer(String productId) {
		ProductOffer o = null;
		if (offers.containsKey(productId)) {
			o = offers.get(productId);
		}
		return o;
	}
}
