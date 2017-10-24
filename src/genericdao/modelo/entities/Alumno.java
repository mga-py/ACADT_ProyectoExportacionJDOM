package genericdao.modelo.entities;

import java.util.List;

/**
 * Esta es la clase alumno con sus atributos correspondientes
 * @author Baltasar Rangel Pinilla  <mga-py>----<baltasarrangel93@gmail.com">
 */
public class Alumno {
    private Integer id;
    private int matricula;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private List<Curso> cursos;

    public Alumno() {
    }

    public Alumno(Integer id, int matricula, String nombre, String apellido1, String apellido2) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
//        this.idCurso = idCurso;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }


    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", matricula=" + matricula + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + '}';
    }
  
}
