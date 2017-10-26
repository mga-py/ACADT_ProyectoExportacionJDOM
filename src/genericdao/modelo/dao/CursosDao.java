package genericdao.modelo.dao;

import genericdao.modelo.conexion.GenericDao;
import genericdao.modelo.entities.Curso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CursosDao extends GenericDao<Curso, Integer> {

    private String codCursoAnterior;

    public CursosDao() {
        super();
        codCursoAnterior = "";
    }

    @Override
    public Curso get(Integer id) {
        Curso curso = null;
        error = false;
        String sql = "SELECT id, "
                + "codCurso, descripcion "
                + "FROM cursos WHERE id =  "
                + id;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setCodCurso(rs.getString("codCurso"));
                curso.setDescripcion(rs.getString("descripcion"));
            }
            rs.close();
        } catch (SQLException ex) {
            curso = null;
            error = true;
        }
        return curso;
    }

    /**
     * Realiza una consulta segun los campos completados
     *
     * @param entity
     * @return devuelve el primer registro que cumpla la condicion
     */
    @Override
    public Curso get(Curso entity) {
        Curso curso = null;
        error = false;
        String condicion = "";
        if (entity.getId() != null) {
            if (condicion.length() > 0) {
                condicion += " and ";
            }
            condicion += "id = " + entity.getId() + " ";
        }

        if (entity.getCodCurso()!= null) {
            if (condicion.length() > 0) {
                condicion += " and ";
            }
            condicion += "codCurso = \"" + entity.getCodCurso() + "\" ";
        }
        if (entity.getDescripcion() != null) {
            if (condicion.length() > 0) {
                condicion += " and ";
            }
            condicion += "descripcion = \"" + entity.getDescripcion() + "\" ";
        }

        String sql = "SELECT id, "
                + "codCurso, descripcion "
                + "FROM cursos WHERE "
                + condicion;
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setCodCurso(rs.getString("codCurso"));
                curso.setDescripcion(rs.getString("descripcion"));          
            }
            rs.close();
        } catch (SQLException ex) {
            curso = null;
            error = true;
        }
        return curso;
    }

    private Integer _add(Curso entity) throws SQLException {
        Integer id;
        String sql = "INSERT INTO cursos "
                + "(id, codCurso, descripcion) VALUES "
                + "(null, ?,?)";

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, entity.getCodCurso());
        ps.setString(2, entity.getDescripcion());
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
    public Integer add(Curso entity) {
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
    public boolean add(List<Curso> list) {
        boolean correcto = true;
        error = false;

        try {
            for (Curso entity : list) {
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
    public boolean update(Curso entity) {
        boolean correcto = true;
        String sql = "UPDATE cursos SET "
                + "codCurso=?, descripcion=?) "
                + "WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getCodCurso());
            ps.setString(2, entity.getDescripcion());
            ps.setInt(3, entity.getId());
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
        String sql = "DELETE FROM cursos "
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
    public List<Curso> listAll() {
        
        
        error = false;
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT id, codCurso, descripcion FROM cursos ORDER BY codCurso ASC;";

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Curso curso=new Curso();
                curso.setId(rs.getInt("id"));
                curso.setCodCurso(rs.getString("codCurso"));
                curso.setDescripcion(rs.getString("descripcion"));
 
                lista.add(curso);
            }
            con.commit();

        } catch (SQLException ex) {
            error = true;
        }
        System.out.println("Lista leida.");
        return lista;
        
        
        
        
        
        
        
        
//        ArrayList<Curso> cursos = new ArrayList();
//        //Curso curso;
//        error = false;
//        String sql = "SELECT id, "
//                + "codCurso, descripcion "
//                + "FROM cursos ORDER BY codCurso ASC;";
//        try {
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                Curso curso = new Curso();
//                curso.setId(rs.getInt("id"));
//                curso.setCodCurso(rs.getString("codCurso"));
//                curso.setDescripcion(rs.getString("decripcion"));
//                cursos.add(curso);
//            }
//            con.commit();
//            //rs.close();
//        } catch (SQLException ex) {
//            error = true;
//        }
//        System.out.println(cursos);
//        return cursos;
    }

    @Override
    public List<Curso> listNext(int rows) {
        ArrayList<Curso> cursos=  new ArrayList();
        Curso curso;
        error = false;
        String sql = "SELECT id, "
                + "codCurso, descripcion "
                + "FROM cursos "
                + "WHERE    "
                + "codCurso > " + codCursoAnterior + " "
                + "ORDER BY codCurso ASC LIMIT " + rows;

        try {
            String codCursoRegistro = codCursoAnterior;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setCodCurso(rs.getString("codCurso"));
                curso.setDescripcion(rs.getString("descripcion"));
                cursos.add(curso);
                codCursoRegistro = curso.getCodCurso();
            }
            rs.close();
            codCursoAnterior = codCursoRegistro;
        } catch (SQLException ex) {
            error = true;
        }
        return cursos;
    }

    @Override
    public boolean exist(Curso entity) {
        error = false;
        boolean encontrado = false;
        String sql = "SELECT "
                + "id "
                + "FROM cursos WHERE codCurso =  " + entity.getCodCurso();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            encontrado = rs.next();
            rs.close();
        } catch (SQLException ex) {
            error = true;
        }
        return encontrado;
    }

    public List<Curso> listAllJoinAlumnos() {
        ArrayList<Curso> cursos = new ArrayList();
        Curso curso;
        error = false;
        String sql = "SELECT id, "
                + "codCurso, descripcion "
                + "FROM cursos ORDER BY codCurso ASC";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            CursosAlumnoDao cursosAlumnoDao = new CursosAlumnoDao();
            while (rs.next()) {
                curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setCodCurso(rs.getString("codCurso"));
                curso.setDescripcion(rs.getString("descripcion"));
                curso.setAlumnos(cursosAlumnoDao.listAlumnosCurso(curso.getId()));
                cursos.add(curso);
            }
            rs.close();
        } catch (SQLException ex) {
            error = true;
        }
        return cursos;
    }    
}
