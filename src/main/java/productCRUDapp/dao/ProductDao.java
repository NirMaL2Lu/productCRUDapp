package productCRUDapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import productCRUDapp.model.Product;

@Repository
public class ProductDao {
	
	Logger logger=LoggerFactory.getLogger(ProductDao.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	//create products
	@Transactional
	public void createProduct(Product product) {
		
		logger.info("Method : createProduct starts");
		
		this.hibernateTemplate.saveOrUpdate(product);
		
		logger.info("Method : createProduct ends");
	}
	
	//get all products
	
	public List<Product> getProducts(){
		List<Product> products = this.hibernateTemplate.loadAll(Product.class);
		return products;
	}
	//delete a product
	@Transactional
	public void deleteProduct(int pid) {
		
		logger.info("Method : deleteProduct starts");
		
		Product p = this.hibernateTemplate.load(Product.class, pid);
		this.hibernateTemplate.delete(p);
		
		logger.info("Method : deleteProduct ends");
	}
	
	//get a single product
	public Product getProduct(int pid) {
		
		logger.info("Method : getProduct starts");
		
		Product p = this.hibernateTemplate.get(Product.class, pid);
		
		logger.info("Method : getProduct ends");
		return p;
		
	}
}
