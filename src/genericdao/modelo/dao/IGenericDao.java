
package genericdao.modelo.dao;

import java.io.Serializable;
import java.util.List;


public interface IGenericDao<T, PK extends Serializable> {
    T get(PK id);
    T get(T entity);
    PK add(T entity);    
    boolean add(List<T> list);
    boolean update(T entity);
    boolean delete(PK id);
    List<T> listAll();
    List<T> listNext(int rows);
    boolean exist(T entity);
    boolean isError();
}
