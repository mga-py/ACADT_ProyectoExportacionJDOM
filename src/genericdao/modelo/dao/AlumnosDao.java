package genericdao.modelo.dao;

import genericdao.modelo.conexion.GenericDao;
import genericdao.modelo.entities.Alumno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Baltasar Rangel Pinilla  <mga-py>----<baltasarrangel93@gmail.com">
 */
public class AlumnosDao extends GenericDao<Alumno, Integer> {

    private int matriculaAnterior;

    public AlumnosDao() {
        super();
        matriculaAnterior = 0;
    }

    @Override
    public Alumno get(Integer id) {
        Alumno alumno = null;
        error = false;
        String sql = "SELECT id, "
                + "matricula, nombre, apellido1, apellido2 "
                + "FROM alumnos WHERE id =  "
                + id;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId(rs.getInt("id"));
                alumno.setMatricula(rs.getInt("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido1(rs.getString("apellido1"));
                alumno.setApellido2(rs.getString("apellido2"));
            }
            rs.close();
        } catch (SQLException ex) {
            alumno = null;
            error = true;
        }
        return alumno;
    }

    /**
     * Realiza una consulta segun los campos completados
     *
     * @param entity
     * @return devuelve el primer registro que cumpla la condicion
     */
    @Override
    public Alumno get(Alumno entity) {
        Alumno alumno = null;
        error = false;
        String condicion = "";
        if (entity.getId() != null) {
            if (condicion.length() > 0) {
                condicion += " and ";
            }
            condicion += "id = " + entity.getId() + " ";
        }

        if (entity.getMatricula() != 0) {
            if (condicion.length() > 0) {
                condicion += " and ";
            }
            condicion += "matricula = " + entity.getMatricula() + " ";
        }
        if (entity.getNombre() != null) {
            if (condicion.length() > 0) {
                condicion += " and ";
            }
            condicion += "nombre = \"" + entity.getNombre() + "\" ";
        }
        if (entity.getApellido1() != null) {
            if (condicion.length() > 0) {
                condicion += " and ";
            }
            condicion += "apellido1 = \"" + entity.getApellido1() + "\" ";
        }
        if (entity.getApellido2() != null) {
            if (condicion.length() > 0) {
                condicion += " and ";
            }
            condicion += "apellido2= \"" + entity.getApellido2() + "\" ";
        }

        String sql = "SELECT id, "
                + "matricula, nombre, apellido1, apellido2 "
                + "FROM alumnos WHERE "
                + condicion;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId(rs.getInt("id"));
                alumno.setMatricula(rs.getInt("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido1(rs.getString("apellido1"));
                alumno.setApellido2(rs.getString("apellido2"));
            }
            rs.close();
        } catch (SQLException ex) {
            alumno = null;
            error = true;
        }
        return alumno;
    }

    private Integer _add(Alumno entity) throws SQLException {
        Integer id;
        String sql = "INSERT INTO alumnos "
                + "(id, matricula, nombre, apellido1, apellido2) VALUES "
                + "(null, ?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, entity.getMatricula());
        ps.setString(2, entity.getNombre());
        ps.setString(3, entity.getApellido1());
        ps.setString(4, entity.getApellido2());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        } else {
            id = -1; // Si se ha añadido pero no se ha devuelto un id
        }

        return id;
    }

    @Override
    public Integer add(Alumno entity) {
        Integer id;
        error = false;

        try {
            id = _add(entity);
            con.commit();

        } catch (SQLException ex) {
            id = null;
            error = true;
            try {
                con.rollback();
            } catch (SQLException ex1) {
            }
        }
        return id;
    }

    @Override
    public boolean add(List<Alumno> list) {
        boolean correcto = true;
        error = false;

        try {
            for (Alumno entity : list) {
                _add(entity);
            }
            con.commit();

        } catch (SQLException ex) {
            correcto = false;
            error = true;
            try {
                con.rollback();
            } catch (SQLException ex1) {
            }
        }
        return correcto;
    }

    /**
     * Actualiza el registro que tiene el id
     *
     * @param entity
     * @return
     */
    @Override
    public boolean update(Alumno entity) {
        boolean correcto = true;
        String sql = "UPDATE alumnos SET "
                + "matricula=?, nombre=?, apellido1=?, apellido2=? "
                + "WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entity.getMatricula());
            ps.setString(2, entity.getNombre());
            ps.setString(3, entity.getApellido1());
            ps.setString(4, entity.getApellido2());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            correcto = false;
            error = true;
            try {
                con.rollback();
            } catch (SQLException ex1) {
            }
        }
        return correcto;
    }

    @Override
    public boolean delete(Integer id) {
        boolean correcto = true;
        String sql = "DELETE FROM alumnos "
                + "WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            correcto = false;
            error = true;
            try {
                con.rollback();
            } catch (SQLException ex1) {
            }
        }
        return correcto;
    }

    @Override
    public List<Alumno> listAll() {
        ArrayList<Alumno> alumnos = new ArrayList();
        Alumno alumno;
        error = false;
        String sql = "SELECT id, "
                + "matricula, nombre, apellido1, apellido2 "
                + "FROM alumnos ORDER BY matricula ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                alumno = new Alumno();
                alumno.setId(rs.getInt("id"));
                alumno.setMatricula(rs.getInt("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido1(rs.getString("apellido1"));
                alumno.setApellido2(rs.getString("apellido2"));
                alumnos.add(alumno);
            }
            rs.close();
        } catch (SQLException ex) {
            error = true;
        }
        return alumnos;
    }

    @Override
    public List<Alumno> listNext(int rows) {
        ArrayList<Alumno> alumnos = new ArrayList();
        Alumno alumno;
        error = false;
        String sql = "SELECT id, "
                + "matricula, nombre, apellido1, apellido2 "
                + "FROM alumnos "
                + "WHERE    "
                + "matricula > " + matriculaAnterior + " "
                + "ORDER BY matricula ASC LIMIT " + rows;

        try {
            int matriculaRegistro = matriculaAnterior;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                alumno = new Alumno();
                alumno.setId(rs.getInt("id"));
                alumno.setMatricula(rs.getInt("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido1(rs.getString("apellido1"));
                alumno.setApellido2(rs.getString("apellido2"));
                alumnos.add(alumno);
                matriculaRegistro = alumno.getMatricula();
            }
            rs.close();
            matriculaAnterior = matriculaRegistro;
        } catch (SQLException ex) {
            error = true;
        }
        return alumnos;
    }

    @Override
    public boolean exist(Alumno entity) {
        error = false;
        boolean encontrado = false;
        String sql = "SELECT "
                + "id "
                + "FROM alumnos WHERE matricula =  " + entity.getMatricula();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            encontrado = rs.next();
            rs.close();
        } catch (SQLException ex) {
            error = true;
        }
        return encontrado;
    }

    public List<Alumno> listAllJoinCursos() {
        ArrayList<Alumno> alumnos = new ArrayList();
        Alumno alumno;
        error = false;
        String sql = "SELECT id, "
                + "matricula, nombre, apellido1, apellido2 "
                + "FROM alumnos ORDER BY matricula ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            CursosAlumnoDao cursosAlumnoDao = new CursosAlumnoDao();
            while (rs.next()) {
                alumno = new Alumno();
                alumno.setId(rs.getInt("id"));
                alumno.setMatricula(rs.getInt("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido1(rs.getString("apellido1"));
                alumno.setApellido2(rs.getString("apellido2"));
                alumno.setCursos(cursosAlumnoDao.listCursosAlumno(alumno.getId()));
                alumnos.add(alumno);
            }
            rs.close();
        } catch (SQLException ex) {
            error = true;
        }
        return alumnos;
    }
}
