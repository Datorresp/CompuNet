package icesi.edu.co.DAO;

import java.util.List;
import java.util.Optional;

import icesi.edu.co.person.Address;

public interface Dao<T> {
	
	public void save(T t);
	
	public void update(T t);

	public List<T> getAll();
	
	public T getByInt(Integer id);
    

	void delete(T entity);
	
}
