/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.service;

import java.util.Collection;

/**
 *
 * @author kavan
 */
public abstract interface AbstractCrudService<T> {

	public T create(T entity);
	
	public void update(T entity);
	
	public Collection<T> loadAll();
	
	public T loadById(String id);
	
	public void delete (T entity);
	
	public static final String UNIQUE_EXCEPTION_ENTITY_ALREADY_EXISTS = "localized.exception.entity.already.exists";

	public static final String UNIQUE_EXCEPTION_ENTITY_DOES_NOT_EXIST = "localized.exception.entity.does.not.exist";

	public boolean contains(T entity);

}
