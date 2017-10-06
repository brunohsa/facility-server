package br.com.facility.service;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public class GenericService<E> implements IGenericService<E>, Serializable {

	private final CrudRepository repository;

	public GenericService(CrudRepository repository) {
		this.repository = repository;
	}

	@Override
	public E save(E entity) {
		return (E) repository.save(entity);
	}

	@Override
	public List<E> getAll() {
		return (List<E>) repository.findAll();
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public E findById(Long id) {
		return (E) repository.findOne(id);
	}
}
