package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import product.model.Product;
import product.repository.ProductRepository;

@Slf4j
@Controller
@RequestMapping("/addProduct")
public class AddProductController {
	private final ProductRepository productRepo;

	@Autowired
	public AddProductController(ProductRepository productRepo) {

		this.productRepo = productRepo;

	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("product", new Product());
		return "addProduct";
	}

	@PostMapping
	public String addProduct(Product product, Model model) {
		if (!product.getCode().equals("")&&!product.getDescription().equals("")) {
			productRepo.save(product);
			model.addAttribute(product);
			log.info("Product saved: " + product);
		}
		return "redirect:/product";
	}
}
