package br.com.facility.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public class GenericService<E, T extends CrudRepository> implements IGenericService<E>, Serializable {

	@Autowired
	private T repository;

	@Override
	public E save(E entity) {
		return (E) repository.save(entity);
	}

	@Override
	public List<E> findAll() {
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
