package genericdao.vistajesus;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Jesús González <jesusgonzalezmerin@gmail.com>
 */
public class AddController implements Initializable, IControlPantallas {

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
    private Button btAddUser;
    @FXML
    private Button btBackUser;
    @FXML
    private Button btAddCourse;
    @FXML
    private Button btBackCourse;
    @FXML
    private AnchorPane menuAdd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alumnoDao = new AlumnosDao();
        cursoDao = new CursosDao();
        System.out.println("Menú añadir cargado.");

    }

    @Override
    public void setMainApp(ACADT_ProyectoExportJDOM mainApp) {
        menuVentanas = mainApp;
    }

    @FXML
    private void addAlumno(ActionEvent event) {
        Alumno alumno = new Alumno();
        alumno.setMatricula(Integer.parseInt(tfMatricula.getText()));
        alumno.setNombre(tfNombre.getText());
        alumno.setApellido1(tfApellido01.getText());
        alumno.setApellido2(tfApellido02.getText());
        //alumno.setIdCurso(Integer.parseInt(tfCurso.getText()));
        alumnoDao.add(alumno);
    }

    @FXML
    private void addCourse(ActionEvent event) {
        Curso curso = new Curso();
        curso.setCodCurso(tfCodigoCurso.getText());
        curso.setDescripcion(tfDescripcionCurso.getText());
        cursoDao.add(curso);

    }

    @FXML
    private void back(ActionEvent event) {
        menuVentanas.cambiarContenido("/genericdao/vistajesus/List.fxml");

    }

}
