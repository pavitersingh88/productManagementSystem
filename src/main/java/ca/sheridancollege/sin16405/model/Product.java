package ca.sheridancollege.sin16405.model;


/*
* Product Catalog Management Application
* Author: Paviter Singh
* Date: June 11, 2025
*/

public class Product {
	
	
	// Properties of the Product object
	
	private int id;
	private int productCode;
	private String productBrand;
	private int productQuantity;
	private double productPrice;
	
	// No-arg constructor
	
	public Product() {
		
	}
	
	
	// Setters for the Product properties
	
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	public void setProductPrice (double productPrice) {
		this.productPrice = productPrice;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	
	// Getters for the Product properties
	
	public int getProductCode() {
		return this.productCode;
	}
	
	public String getProductBrand() {
		return this.productBrand;
	}
	
	public int getProductQuantity() {
		return this.productQuantity;
	}
	
	public double getProductPrice() {
		return this.productPrice;
	}
	
	public int getId() {
		return this.id;
	}
	
}
