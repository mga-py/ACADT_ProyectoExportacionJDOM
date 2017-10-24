
package genericdao.modelo.conexion;

import java.io.Serializable;
import java.util.List;

/**
 * Implementa estos metodos basicos para la creacion de un dao
 * @author Baltasar Rangel Pinilla  <mga-py>----<baltasarrangel93@gmail.com">
 * @param <T>(Objeto)entity
 * @param <PK> (Primary key)id
 */
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
