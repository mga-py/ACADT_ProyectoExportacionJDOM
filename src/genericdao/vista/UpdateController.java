package genericdao.vista;

import acadt_proyectoexportjdom.ACADT_ProyectoExportJDOM;
import genericdao.modelo.dao.AlumnosDao;
import genericdao.modelo.dao.CursosDao;
import genericdao.modelo.entities.Alumno;
import genericdao.modelo.entities.Curso;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Jesús González <jesusgonzalezmerin@gmail.com>
 */
public class UpdateController implements Initializable, IControlPantallas {

    //Atributos
    private ACADT_ProyectoExportJDOM menuVentanas;
    private AlumnosDao alumnoDao;
    private CursosDao cursoDao;

    @FXML
    private TextField tfMatricula;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellido01;
    @FXML
    private TextField tfApellido02;
    @FXML
    private TextField tfCurso;
    @FXML
    private TextField tfCodigoCurso;
    @FXML
    private TextField tfDescripcionCurso;
    @FXML
    private TextField tfFiltro;
    @FXML
    private Label lbMatricula;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbApellido1;
    @FXML
    private Label lbApellido2;
    @FXML
    private Label lbIdCurso;
    @FXML
    private Label lbCodigoCurso;
    @FXML
    private Label lbDescripcion;
    @FXML
    private TextField tfFiltroCurso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Menú actualizar cargado.");

    }

    @Override
    public void setMainApp(ACADT_ProyectoExportJDOM mainApp) {
        menuVentanas = mainApp;
    }

    @FXML
    private void updateAlumno(ActionEvent event) {
        Alumno alumno=new Alumno();
        //alumno.setId(Integer.valueOf(tfFiltro.getText()));
        alumno.setMatricula(Integer.valueOf(tfMatricula.getText()));
        alumno.setNombre(tfNombre.getText());
        alumno.setApellido1(tfApellido01.getText());
        alumno.setApellido2(tfApellido02.getText());
        alumnoDao.update(alumno);
        
        tfNombre.clear();
        tfMatricula.clear();
        tfApellido01.clear();
        tfApellido02.clear();
        tfCodigoCurso.clear();
        tfFiltro.clear();
        
        //System.out.println(alumno);
    }

    @FXML
    private void updateCourse(ActionEvent event) {
        Curso curso=new Curso();
        //curso.setId(Integer.parseInt(tfFiltro.getText()));
        curso.setCodCurso(tfCodigoCurso.getText());
        curso.setDescripcion(tfDescripcionCurso.getText());
        cursoDao.update(curso);
        
        tfCodigoCurso.clear();
        tfDescripcionCurso.clear();
        tfFiltroCurso.clear();
        
        //System.out.println(curso);
    }

    @FXML
    private void back(ActionEvent event) {
        menuVentanas.cambiarContenido("/genericdao/vista/List.fxml");
    }

    @FXML
    private void findAlumno(ActionEvent event) {
        alumnoDao = new AlumnosDao();
        Alumno alumno = new Alumno();
        alumno.setMatricula(Integer.valueOf(tfFiltro.getText()));
        if (alumnoDao.exist(alumno)) {
            System.out.println("EXISTE");
            //Asignamos campos de alumno para modificar
            alumno = alumnoDao.get(Integer.valueOf(tfFiltro.getText()));
            tfMatricula.setText(String.valueOf(alumno.getMatricula()));
            tfCurso.setText("");
            tfNombre.setText(alumno.getNombre());
            tfApellido01.setText(alumno.getApellido1());
            tfApellido02.setText(alumno.getApellido2());
            
        } else {
            System.out.println("NO EXISTE");
        }

    }

    @FXML
    private void findCourse(ActionEvent event) {
        cursoDao = new CursosDao();
        Curso cursoTemp = new Curso();
        cursoTemp.setCodCurso(tfFiltroCurso.getText());
        if (cursoDao.exist(cursoTemp)) {
            System.out.println("EXISTE");
            //Asignamos campos de curso para modificar
            cursoTemp = cursoDao.get(cursoTemp);
            tfCodigoCurso.setText(cursoTemp.getCodCurso());
            tfDescripcionCurso.setText(cursoTemp.getDescripcion());
            
        } else {
            System.out.println("NO EXISTE");
        }
    }

}
