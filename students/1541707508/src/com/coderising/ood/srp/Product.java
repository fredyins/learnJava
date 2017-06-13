package com.coderising.ood.srp;


/**
 * @author 1541707508
 *
 */
public class Product {
	private String productID = ConfigurationKeys.NULL_STR;
	private String productDesc = ConfigurationKeys.NULL_STR;
	
	public Product() {
		
	}

	public Product(String productID, String productDesc) {
		this.productID = productID;
		this.productDesc = productDesc;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	@Override
	public String toString() {
		return "产品id：" + productID + "；产品描述：" + productDesc + "；";
	}
	
	
	
}
