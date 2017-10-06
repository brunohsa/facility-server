package br.com.facility.service;

import java.util.List;

public interface IGenericService<E> {

	public E findById(Long id);

	public E save(E entity);

	public List<E> getAll();

	public void delete(Long id);
}
