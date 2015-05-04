package ecommerce.model;

public class PurchasedProduct extends Product {
	public PurchasedProduct(Product p)
	{
		this.setCategory_name(p.getCategory_name());
		this.setName(p.getName());
		this.setPrice(p.getPrice());
		this.setSku(p.getSku());
	}
}
