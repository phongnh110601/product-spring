package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import product.model.Product;
import product.repository.ProductRepository;

@Controller
@RequestMapping("/deleteProduct")
public class DeleteProductController {
	private final ProductRepository productRepo;
	private Product product;

	@Autowired
	public DeleteProductController(ProductRepository productRepo) {
		this.productRepo = productRepo;
		this.product = new Product();
	}

	@GetMapping("/delete")
	public String showDeleteForm(String code, Model model) {
		product = productRepo.getById(code);
		model.addAttribute("product", product);
		return "deleteProduct";
	}

	@PostMapping
	public String DeleteProduct(Model model) {
		productRepo.delete(product);
		return "redirect:/product";
	}
}