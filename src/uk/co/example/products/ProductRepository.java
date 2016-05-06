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
	private Map<String, Product> products = new HashMap<>();

	public void Populate(String fileName) {
		products.clear();

		try {
			FileReader r = new FileReader(fileName);
			// Making an assumption here that the input json file is well
			// formatted and parseable.
			JSONParser parser = new JSONParser();
			
			List<JSONObject> data = null;
			try {
				data = (List<JSONObject>) parser.parse(r);
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

	public Product Get(String productId) {
		Product p = null;
		if (products.containsKey(productId)) {
			p = products.get(productId);
		}
		return p;
	}
}
