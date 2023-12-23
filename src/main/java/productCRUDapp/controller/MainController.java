package productCRUDapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productCRUDapp.dao.ProductDao;
import productCRUDapp.model.Product;

@Controller
public class MainController {
	
	Logger logger=LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private ProductDao productDao;
	//home page handler
	@RequestMapping("/")
	public String home(Model m) {
		
		logger.info("Method : home starts");
		
		List<Product> products = productDao.getProducts();
		m.addAttribute("products",products);
		
		logger.info("Method : home ends");
		
		return "index";
	}
	//show addproduct form handler
	@RequestMapping("/addProduct")
	public String addProduct(Model m) {
		
		logger.info("Method : addProduct starts");
		
		m.addAttribute("title","Add Product");
		
		logger.info("Method : addProduct ends");
		
		return "add_product_form";
	}
	//add product from handler
	@RequestMapping(value = "/handle-product",method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product,HttpServletRequest request ) {
		
		logger.info("Method : handleProduct starts");
		
		System.out.println(product);
		productDao.createProduct(product);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		
		logger.info("Method : handleProduct ends");
		
		return redirectView;
		
	}
	
	//delete handler
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int pId,HttpServletRequest request) {
		
		logger.info("Method : deleteProduct starts");
		
		productDao.deleteProduct(pId);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		
		logger.info("Method : deleteProduct ends");
		
		return redirectView;
	}
	
	//update product
	@RequestMapping("/update/{productId}")
	public String updateProduct(@PathVariable("productId") int pId,Model m) {
		
		logger.info("Method : updateProduct starts");
		
		Product product = productDao.getProduct(pId);
		m.addAttribute("product",product);
		
		logger.info("Method : updateProduct ends");
		
		return "updateform";
	}
}
