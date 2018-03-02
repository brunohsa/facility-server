package br.com.facility.test.fixture;

import java.util.List;

public interface IFixture<T> {

	List<T> getAll();
}
