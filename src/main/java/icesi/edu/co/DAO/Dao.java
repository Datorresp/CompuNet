package icesi.edu.co.DAO;

import java.util.List;

public interface Dao<T> {
	
	public T save(T t);
	
	public T update(T t);

	public List<T> getAll();
	
	public T getByInt(Integer id);
    

	void delete(T entity);
	
}
