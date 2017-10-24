package genericdao.modelo.entities;

import java.util.List;

/**
 * Esta seria la clase curso con sus atributos correspondientes
 * @author Baltasar Rangel Pinilla  <mga-py>----<baltasarrangel93@gmail.com">
 */
public class Curso {

    private Integer id;
    private String codCurso;
    private String descripcion;
    private List<Alumno> alumnos;

    public Curso() {
    }

    public Curso(Integer id, String codCurso, String descripcion) {
        this.id = id;
        this.codCurso = codCurso;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", codCurso=" + codCurso + ", descripcion=" + descripcion + ", alumnos=" + alumnos + '}';
    }
    
    
}
