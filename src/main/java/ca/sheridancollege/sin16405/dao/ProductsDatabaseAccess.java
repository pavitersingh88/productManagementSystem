package ca.sheridancollege.sin16405.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.sin16405.model.Product;

@Repository
public class ProductsDatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public long addProduct(Product product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String insert="INSERT INTO product(productCode, productBrand, productQuantity, productPrice)" + 
					"VALUES (:productCode, :productBrand, :productQuantity, :productPrice);";
		namedParameters.addValue("productCode", product.getProductCode());
		namedParameters.addValue("productBrand", product.getProductBrand());
		namedParameters.addValue("productQuantity", product.getProductQuantity());
		namedParameters.addValue("productPrice", product.getProductPrice());
		long rowsAffected = jdbc.update(insert, namedParameters);
		return rowsAffected;
	}
	
	public List<Product> selectProducts(){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM product";
		List<Product> products = jdbc.query(query, namedParameters,
		new BeanPropertyRowMapper<Product>(Product.class));
		return products;
	}
	
	public List<Product> selectProductsByBrand(String brand) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query =
		"SELECT * FROM product where productBrand = :brand";
		namedParameters.addValue("brand", brand);
		List<Product> products =jdbc.query(query, namedParameters,
		new BeanPropertyRowMapper<Product>(Product.class));
		return products;
	}
	
	public List<Product> selectProductsByQuantity(int threshold) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	    String query = "SELECT * FROM Product WHERE productQuantity < :threshold";
	    namedParameters.addValue("threshold", threshold);
	    List<Product> products =jdbc.query(query, namedParameters,
	    new BeanPropertyRowMapper<Product>(Product.class));
	    return products;
	}
	
	public Product selectProductById(int id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		Product product;
		String query = "SELECT * FROM product where id = :id";
		namedParameters.addValue("id", id);
		List<Product> products =jdbc.query(query, namedParameters,
									new BeanPropertyRowMapper<Product>(Product.class));
		product = products.get(0);
		return product;
	}
	
	public long updateProductById(int productId, Product product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query =
		"update product Set productCode = :productCode, "
		+ "productBrand = :productBrand , productQuantity = :productQuantity, productPrice = :productPrice"
		+ " where id = :productId;";
		namedParameters.addValue("productId", productId);
		namedParameters.addValue("productCode", product.getProductCode());
		namedParameters.addValue("productBrand", product.getProductBrand());
		namedParameters.addValue("productQuantity", product.getProductQuantity());
		namedParameters.addValue("productPrice", product.getProductPrice());
		long rowsUpdated = jdbc.update(query, namedParameters);
		return rowsUpdated;
	}
	
	public long deleteProductById(int productId, Product product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "delete from product where id = :productId;";
		namedParameters.addValue("productId", productId);
		long rowsDeleted = jdbc.update(query, namedParameters);
		return rowsDeleted;
	}

}
