package genericdao.modelo.conexion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Baltasar Rangel Pinilla  <mga-py>----<baltasarrangel93@gmail.com">
 * @param <T> (Objeto)entity
 * @param <PK> (Primary key)id
 */
public abstract class GenericDao<T, PK extends Serializable>
        implements IGenericDao<T, PK> {

    protected ConectaBD conectaBD;
    protected Connection con;
    protected boolean error;
    protected Statement stmt;

    /**
     * Obtiene las conexiones si estaban creadas y si no las genera y a su vez
     * las deja a las clases que extienden de esta para que no tengan que estar
     * iniciando una conexion cada vez que una clase diferente quiera acceder a
     * la base de datos
     */
    protected GenericDao() {
        error = false;
        try {
            conectaBD = ConectaBD.getConectaBD();
            con = conectaBD.getConnection();
            stmt = conectaBD.getConnection().createStatement();
        } catch (SQLException ex) {
            error = true;
            System.err.println("Error en la conexion con la BD.");
        } catch (ExceptionDataBase ex) {
            error = true;
            System.err.println(ex.toString());
        }
    }

    @Override
    public boolean isError() {
        return error;
    }

}
