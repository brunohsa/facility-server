package br.com.facility.service;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public class GenericService implements Serializable {

	private final CrudRepository repository;

	public GenericService(CrudRepository repository) {
		this.repository = repository;
	}

	public <E> E save(E entity) {
		return (E) repository.save(entity);
	}

	public <T> T findById(Long id, Class<T> tClass) {
		return (T) repository.findOne(id);
	}

	public <T> List<T> getAll(Class<T> tClass) {
		return (List<T>) repository.findAll();
	}

	public void delete(Long id) {
		repository.delete(id);
	}
}
