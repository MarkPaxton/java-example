package uk.co.example.products;

public class Product {
	
	private String _id = null;
	
	private Double _price = null;
	
	private String _name = null;
	
	public Product(String id, Double price, String name) {
		super();
		this._id = id;
		this._price = price;
		this._name = name;
	}

	public String ID() {
		return _id;
	}
	
	public Double Price() {
		return _price;
	}
	
	public String Name() {
		return _name;
	}
	
	@Override
	public String toString() {
		return String.format("%s{%s@%d}", _name, _id, _price); 
	}
}
