package ca.sheridancollege.sin16405.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.sin16405.dao.ProductsDatabaseAccess;
import ca.sheridancollege.sin16405.model.Product;

@Controller
public class ProductManagementController {
	
	@Autowired
	private ProductsDatabaseAccess pda;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/productDataInput")
	public String productDataInput(Model model) {
		model.addAttribute("product", new Product());
		return "productDataInput";
	}
	
	@PostMapping("/productDataInput")
	public String addProduct(Model model, @ModelAttribute Product product) {
		String message;
		long numberOfRows = pda.addProduct(product);
		if (numberOfRows > 0){
			message = "Success! The product object successfully added to the database.";
		} 
		else{
			message = "Failure --- The product object is not added to the database.";
		}
		model.addAttribute("message", message);
		return "productAddOutcome";
	}
	
	@GetMapping ("/listOfProducts")
	public String viewListOfProducts(Model model) {
		List<Product> products = pda.selectProducts();
		model.addAttribute("products", products);
		return "listOfProducts";
	}
	
	@GetMapping("/searchByBrand")
	public String seachProductByBrand() {
		return "searchByBrand";
	}
	
	@GetMapping("/productsByBrand")
	public String ListOfProductsByBrand(Model model, @RequestParam String brand) {
		String message;
		List<Product> products = pda.selectProductsByBrand(brand);
		if(products.size()> 0) {
		message = "List of products with "+ brand + " brand";
		} else {
		message = "No products with " + brand + " brand";
		}
		model.addAttribute("message", message);
		model.addAttribute("products", products);
		return "productsByBrand";
	}
	
	@GetMapping("/searchByQuantity")
	public String searchProductByQuantity() {
	    return "searchByQuantity";
	}

	@PostMapping("/productsByQuantity")
	public String ListOfProductsByQuantity(@RequestParam String productThreshold, Model model) {
		String message;
		int threshold =Integer.parseInt(productThreshold);
	    List<Product> products = pda.selectProductsByQuantity(threshold);
	    if(products.size()> 0){
	    	message = "List of products with quantity less than "+ threshold;
	    } 
	    else{
	    	message = "No products with quantity less than " + threshold;
	    }
	    model.addAttribute("message", message);
	    model.addAttribute("products", products);
	    return "productsByQuantity";
	}
	
	@GetMapping ("/editableListOfProducts")
	public String viewEditableListOfProducts(Model model) {
		List<Product> products = pda.selectProducts();
		model.addAttribute("products", products);
		return "editableListOfProducts"; 
	}
	
	@GetMapping("/editProductData/{id}")
	public String editProductData(Model model, @PathVariable("id") int id) {
		Product productById = pda.selectProductById(id);
		model.addAttribute("product", productById);
		return "editProductData";
	}
	
	@PostMapping("/updateProductData")
	public String updateProduct(Model model, @ModelAttribute Product product) {
		String message;
		int productId = product.getId();
		long rowsUpdated = pda.updateProductById(productId, product);
		if (rowsUpdated > 0){
			message = "Success! The product object successfully updated in the database.";
		} 
		else{
			message = "Failure --- The product object is not updated in the database.";
		}
		model.addAttribute("message", message);
		return "productUpdateOutcome";
	}
	
	@GetMapping ("/deletableListOfProducts")
	public String viewDeletableListOfProducts(Model model) {
		List<Product> products = pda.selectProducts();
		model.addAttribute("products", products);
		return "deletableListOfProducts"; 
	}
	
	@GetMapping ("/confirmProductDeletion/{id}")
	public String confirmProductDeletion(Model model, @PathVariable("id") int id) {
		Product productById = pda.selectProductById(id);
		model.addAttribute("product", productById);
		return "confirmProductDeletion";
	}
	
	
	@PostMapping("/deleteProductData")
	public String deleteProduct(Model model, @ModelAttribute Product product) {
		String message;
		int productId = product.getId();
		long rowsDeleted = pda.deleteProductById(productId, product);
		if (rowsDeleted > 0){
			message = "Success! The product object successfully deleted from the database.";
		}
		else{
			message = "Failure --- The product object is not delted from the database.";
		}
		model.addAttribute("message", message);
		return "productDeleteOutcome";
	}
	
	
	
}
