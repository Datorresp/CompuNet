package icesi.edu.co.DAO;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
	
	public void save(T t);
	
	public void update(T t);

	public List<T> getAll();
	
	public Optional<T> getByInt(Integer id);
    
	public Optional<T> getByLong(long id);
	
}
