package com.mycompany.test;

import com.mycompany.service.ProductFacadeREST;

public class TestTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ProductFacadeREST pfr = new ProductFacadeREST();
		System.out.println(pfr.findAll().size());
	}

}
