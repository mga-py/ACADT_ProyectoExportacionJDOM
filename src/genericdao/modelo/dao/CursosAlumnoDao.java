package genericdao.modelo.dao;

import genericdao.modelo.conexion.GenericDao;
import genericdao.modelo.entities.Alumno;
import genericdao.modelo.entities.Curso;
import genericdao.modelo.entities.CursoAlumno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CursosAlumnoDao extends GenericDao<CursoAlumno, Integer> {

    private int ultimoRegistro;

    public CursosAlumnoDao() {
        super();
        ultimoRegistro = 0;
    }

    @Override
    public CursoAlumno get(Integer id) {
        throw new UnsupportedOperationException("Método no soportado");
    }

    public CursoAlumno get(int idCurso, int idAlumno) {
        CursoAlumno cursoAlumno = null;
        error = false;
        String sql = "SELECT "
                + "idCurso, idAlumno "
                + "FROM cursos_alumno WHERE "
                + "idCurso =  " + idCurso + " and "
                + "idAlumno = " + idAlumno;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cursoAlumno = new CursoAlumno();
                cursoAlumno.setIdCurso(rs.getInt("idCurso"));
                cursoAlumno.setIdAlumno(rs.getInt("idAlumno"));
            }
            rs.close();
        } catch (SQLException ex) {
            cursoAlumno = null;
            error = true;
        }
        return cursoAlumno;
    }

    /**
     * Realiza una consulta segun los campos completados
     *
     * @param entity
     * @return devuelve el primer registro que cumpla la condicion
     */
    @Override
    public CursoAlumno get(CursoAlumno entity) {
        CursoAlumno cursoAlumno = null;
        error = false;
        String condicion = "";
        if (entity.getIdCurso() != 0) {
            if (condicion.length() > 0) {
                condicion += " and ";
            }
            condicion += "idCurso = " + entity.getIdCurso() + " ";
        }

        if (entity.getIdAlumno() != 0) {
            if (condicion.length() > 0) {
                condicion += " and ";
            }
            condicion += "idAlumno = " + entity.getIdAlumno() + " ";
        }

        String sql = "SELECT idCurso, idAlumno "
                + "FROM cursos_alumno WHERE "
                + condicion;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                cursoAlumno = new CursoAlumno();
                cursoAlumno.setIdCurso(rs.getInt("idCurso"));
                cursoAlumno.setIdAlumno(rs.getInt("idAlumno"));
            }
            rs.close();
        } catch (SQLException ex) {
            cursoAlumno = null;
            error = true;
        }
        return cursoAlumno;
    }

    private Integer _add(CursoAlumno entity) throws SQLException {
        Integer id;
        String sql = "INSERT INTO cursos_alumno "
                + "(idCurso, idAlumno) VALUES "
                + "(?,?)";

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, entity.getIdCurso());
        ps.setInt(2, entity.getIdAlumno());
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
    public Integer add(CursoAlumno entity) {
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
    public boolean add(List<CursoAlumno> list) {
        boolean correcto = true;
        error = false;

        try {
            for (CursoAlumno entity : list) {
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
    public boolean update(CursoAlumno entity) {
        throw new UnsupportedOperationException("Método no soportado");
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Método no soportado");
    }

    public boolean delete(int idCurso, int idAlumno) {
        boolean correcto = true;
        String sql = "DELETE FROM cursos_alumno "
                + "WHERE idCurso=? and idAlumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idCurso);
            ps.setInt(2, idAlumno);
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
    public List<CursoAlumno> listAll() {
        ArrayList<CursoAlumno> cursosAlumno = new ArrayList();
        CursoAlumno cursoAlumno;
        error = false;
        String sql = "SELECT idCurso, idAlumno "
                + "FROM cursos_alumno ORDER BY idCurso ASC, idAlumno ASC";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cursoAlumno = new CursoAlumno();
                cursoAlumno.setIdCurso(rs.getInt("idCurso"));
                cursoAlumno.setIdAlumno(rs.getInt("idAlumno"));
                cursosAlumno.add(cursoAlumno);
            }
            rs.close();
        } catch (SQLException ex) {
            error = true;
        }
        return cursosAlumno;
    }

    @Override
    public List<CursoAlumno> listNext(int rows) {
        ArrayList<CursoAlumno> cursosAlumno = new ArrayList();
        CursoAlumno cursoAlumno;
        error = false;
        String sql = "SELECT idCurso, idAlumno "
                + "FROM cursos_alumno "
                + "ORDER BY idCurso ASC, idAlumno ASC LIMIT " + rows
                + " OFFSET " + ultimoRegistro;

        try {
            int posRegistro = ultimoRegistro;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cursoAlumno = new CursoAlumno();
                cursoAlumno.setIdCurso(rs.getInt("idCurso"));
                cursoAlumno.setIdAlumno(rs.getInt("idAlumno"));
                cursosAlumno.add(cursoAlumno);
                posRegistro++;
            }
            rs.close();
            ultimoRegistro = posRegistro;
        } catch (SQLException ex) {
            error = true;
        }
        return cursosAlumno;
    }

    @Override
    public boolean exist(CursoAlumno entity) {
        error = false;
        boolean encontrado = false;
        String sql = "SELECT "
                + "id "
                + "FROM cursos_alumno WHERE "
                + "idCurso =  " + entity.getIdCurso() + " and "
                + "idAlumno =  " + entity.getIdAlumno();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            encontrado = rs.next();
            rs.close();
        } catch (SQLException ex) {
            error = true;
        }
        return encontrado;
    }

    public List<Alumno> listAlumnosCurso(int idCurso) {
        ArrayList<Alumno> alumnos = new ArrayList();
        Alumno alumno;
        error = false;
        String sql = "SELECT "
                + "al.id, al.matricula, "
                + "al.nombre, al.apellido1, al.apellido2 "
                + "FROM cursos_alumno cu "
                + "LEFT JOIN alumnos al ON cu.idAlumno=al.id "
                + "WHERE cu.idCurso=? "
                + "ORDER BY al.apellido1, al.apellido2, al.nombre";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCurso);
            ResultSet rs = ps.executeQuery();
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

    public List<Curso> listCursosAlumno(int idAlumno) {
        ArrayList<Curso> cursos = new ArrayList();
        Curso curso;
        error = false;
        String sql = "SELECT "
                + "curso.id, curso.codCurso, curso.descripcion "
                + "FROM cursos curso "
                + "LEFT JOIN cursos_alumno cursoAl ON curso.id=cursoAl.idCurso "
                + "WHERE cursoAl.idAlumno=? "
                + "ORDER BY curso.codCurso";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setCodCurso(rs.getString("codCurso"));
                curso.setDescripcion(rs.getString("descripcion"));
                cursos.add(curso);
            }
            rs.close();
        } catch (SQLException ex) {
            error = true;
        }
        return cursos;
    }
    

}
