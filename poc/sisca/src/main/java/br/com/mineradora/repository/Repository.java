package br.com.mineradora.repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 * @param <T>
 */
public interface Repository<T extends Serializable> extends Serializable {

	void save(T t);

	void update(T t);

	void delete(T t);

	T findById(BigInteger id);

	List<T> findAll();

}
