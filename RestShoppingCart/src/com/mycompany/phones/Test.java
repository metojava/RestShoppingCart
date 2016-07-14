package com.mycompany.phones;

import java.util.ArrayList;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PhonesService ps = new PhonesService();
		ArrayList<Phone> phones = ps.readPhones();
		System.out.println(phones.size());

	}

}
