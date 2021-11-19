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

	public void save(T t);

	public void update(T t);

	public void delete(T t);

	public T findById(BigInteger id);

	public List<T> findAll();

}
