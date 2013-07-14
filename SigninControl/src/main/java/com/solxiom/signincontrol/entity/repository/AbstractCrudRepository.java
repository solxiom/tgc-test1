/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.entity.repository;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author soleikav
 */
public interface AbstractCrudRepository<T> {

	public T create(T entity);
	
	public void update(T entity);
	
	public List<T> loadAll();
	
	public T loadById(String id);
	
	public void delete (T entity);
	
	public Collection<T> loadByText(String text);

	void dropCollection();
	
}
