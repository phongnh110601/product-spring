package product.service;

import product.model.Product;

public interface ProductService {
	Product save(Product product);

	void delete(Product product);
}
