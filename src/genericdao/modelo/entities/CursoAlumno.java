package genericdao.modelo.entities;

/**
 * Esta clase la funcion que tiene es relacionar 
 * el id del curso con el id del alumno
 * @author Baltasar Rangel Pinilla  <mga-py>----<baltasarrangel93@gmail.com">
 */
public class CursoAlumno {

    private int idCurso;
    private int idAlumno;
    
    public CursoAlumno() {
    }

    public CursoAlumno(int idCurso, int idAlumno) {
        this.idCurso = idCurso;
        this.idAlumno = idAlumno;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public String toString() {
        return "CursoAlumno{" + "idCurso=" + idCurso + ", idAlumno=" + idAlumno + '}';
    }

    
}
