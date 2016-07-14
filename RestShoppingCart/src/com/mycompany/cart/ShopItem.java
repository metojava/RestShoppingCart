package com.mycompany.cart;

import com.mycompany.entities.Product;

public class ShopItem {

	private String product;
	private int quantity;

	public ShopItem(String product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public ShopItem() {

	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
