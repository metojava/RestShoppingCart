package com.mycompany.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SingletonEntityFactory {

	public static EntityManagerFactory factory;

	private SingletonEntityFactory() {

	}

	public static EntityManagerFactory getEManagerFactory() {
		if (factory == null)
			synchronized (SingletonEntityFactory.class) {
				if (factory == null) {
					factory = Persistence
							.createEntityManagerFactory("elink_JPA");
				}
			}
		return factory;
	}

}
