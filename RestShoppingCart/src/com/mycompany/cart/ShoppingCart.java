package com.mycompany.cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mycompany.entities.Product;

public class ShoppingCart {

	private List<ShopItem> cartItems;
	private float sum;

	public ShoppingCart() {
		this.cartItems = new ArrayList<>();
		this.sum = 0.0f;
	}

	public void addtocart(String product, int quantity) {
		boolean hav = false;
		if (cartItems.size() == 0)
			cartItems.add(new ShopItem(product, quantity));
		else if (!itemExists(product))
			cartItems.add(new ShopItem(product, quantity));
		else {
			for (Iterator iterator = cartItems.iterator(); iterator.hasNext();) {
				ShopItem item = (ShopItem) iterator.next();
				if (itemExists(item.getProduct())) {
					item.setQuantity(item.getQuantity() + quantity);
					getTotal();
					return;
				}
			}
		}

	}

	public boolean itemExists(String product) {
		boolean exists = false;
		for (Iterator iterator = cartItems.iterator(); iterator.hasNext();) {
			ShopItem item = (ShopItem) iterator.next();
			if (item.getProduct().equals(product))
				exists = true;
		}
		return exists;
	}

	public void updateCart(String product, int quantity) {
		ShopItem itm = null;
		for (Iterator iterator = cartItems.iterator(); iterator.hasNext();) {
			ShopItem item = (ShopItem) iterator.next();
			if (item.getProduct().equals(product)) {
				itm = item;
				break;
			}
		}
		itm.setQuantity(quantity);
		getTotal();
	}

	public float getTotal() {
		sum = 0.0f;
		if (cartItems.size() > 0)
			for (Iterator iterator = cartItems.iterator(); iterator.hasNext();) {
				ShopItem item = (ShopItem) iterator.next();
				String productId = item.getProduct();
				float price = ProductUtil.getProductPrice(productId);
				sum += price * item.getQuantity();

			}
		return sum;
	}

	public boolean delete(Product product) {
		boolean removed = false;
		for (Iterator iterator = cartItems.iterator(); iterator.hasNext();) {
			ShopItem item = (ShopItem) iterator.next();
			cartItems.remove(item);
		}
		getTotal();
		return removed;
	}
}
