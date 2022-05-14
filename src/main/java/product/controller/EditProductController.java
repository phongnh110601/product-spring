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
@RequestMapping("/editProduct")
public class EditProductController {
	private final ProductRepository productRepo;

	@Autowired
	public EditProductController(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@GetMapping("/edit")
	public String showEditForm(String code, Model model) {
		Product product = productRepo.getById(code);
		productRepo.deleteById(code);
		model.addAttribute("product", product);
		return "editProduct";
	}

	@PostMapping
	public String EditProduct(Product product, Model model) {
		if (!product.getCode().equals("") && !product.getDescription().equals("")) {
			productRepo.save(product);
			model.addAttribute(product);
			log.info("Product saved: " + product);
		}
		return "redirect:/product";
	}
}