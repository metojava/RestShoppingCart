package com.mycompany.test;

import java.util.List;

import com.mycompany.entities.Product;
import com.mycompany.service.ProductFacadeREST;

public class TestTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ProductFacadeREST pfr = new ProductFacadeREST();
		List<Product> products = pfr.findAll();
		System.out.println(products.size());
		System.out.println(products.get(0).getName() + " - "+products.get(0).getDescription());
	}

}
