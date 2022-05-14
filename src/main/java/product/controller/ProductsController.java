package product.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import product.model.Product;
import product.repository.ProductRepository;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductsController {
	private final ProductRepository productRepo;

	@Autowired
	public ProductsController(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@ModelAttribute
	public void addProductsToModel(Model model) {
		List<Product> products = new ArrayList<>();
		productRepo.findAll().forEach(products::add);
		model.addAttribute("products", products);
	}

	@GetMapping
	public String showProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "product";
	}

	@PostMapping
	public String processProduct(@Valid Product product, Errors errors) {
		if (errors.hasErrors()) {
			return "product";
		}
		// Save the product design...
		// We'll do this in later
		log.info("Processing products: " + product);
		return "redirect:/addProduct/add";
	}
}
