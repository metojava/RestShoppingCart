/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * 
 * @author User
 */
public abstract class AbstractFacade<T> {
	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	public void create(T entity) {
		getEntityManager().persist(entity);
	}

	public void edit(T entity) {
		getEntityManager().merge(entity);
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public T find(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	public List<T> findAll() {
		String entityName = entityClass.getSimpleName();
		Class claz = entityClass.getClass();
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery(
				"select e from " + entityName + " e");
		@SuppressWarnings("unchecked")
		List entities = new ArrayList<>(q.getResultList());
		return entities;
	}

	public List<T> findRange(int[] range) {
		String entityName = entityClass.getName();

		javax.persistence.Query q = getEntityManager().createQuery(
				"select e from " + entityName + " e");
		q.setMaxResults(range[1] - range[0] + 1);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	public int count() {
		
		return findAll().size();
	}

}
