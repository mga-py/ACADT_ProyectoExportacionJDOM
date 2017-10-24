package genericdao.modelo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class GenericDao<T, PK extends Serializable>
        implements IGenericDao<T, PK> {

    protected ConectaBD conectaBD;
    protected Connection con;
    protected boolean error;
    protected Statement stmt;

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
