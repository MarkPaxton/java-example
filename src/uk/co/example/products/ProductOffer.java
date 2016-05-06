package uk.co.example.products;

public class ProductOffer {
	private String _product = null;
	
	private Integer _purchaseAmount = null;
	
	private Integer _freeAmount = null;
	
	/***
	 * Create a product offer, where when 'buy' number of products is bought, 'free' number extra are available
	 * @param product
	 * @param buy
	 * @param free
	 */
	public ProductOffer(String product, Integer buy, Integer free) {
		_product = product;
		_purchaseAmount = buy;
		_freeAmount = free;
	}

	public String Product() {
		return _product;
	}

	public Integer PurchaseAmount() {
		return _purchaseAmount;
	}

	public Integer FreeAmount() {
		return _freeAmount;
	}
	
	
}
