package genericdao.vista.jesus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pkg002academia.IControlPantallas;
import pkg002academia.Main;
import pkg002academia.modelo.dao.AlumnoDao;
import pkg002academia.modelo.dao.CursoDao;
import pkg002academia.modelo.entities.Alumno;
import pkg002academia.modelo.entities.Curso;

/**
 *
 * @author Jesús González <jesusgonzalezmerin@gmail.com>
 */
public class AddController implements Initializable, IControlPantallas {

    //Atributos
    private Main menuVentanas;
    private AlumnoDao alumnoDao;
    private CursoDao cursoDao;

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
        alumnoDao = new AlumnoDao();
        cursoDao = new CursoDao();
        System.out.println("Menú añadir cargado.");

    }

    @Override
    public void setMainApp(Main mainApp) {
        menuVentanas = mainApp;
    }

    @FXML
    private void addAlumno(ActionEvent event) {
        Alumno alumno = new Alumno();
        alumno.setMatricula(Integer.parseInt(tfMatricula.getText()));
        alumno.setNombre(tfNombre.getText());
        alumno.setApellido01(tfApellido01.getText());
        alumno.setApellido02(tfApellido02.getText());
        alumno.setIdCurso(Integer.parseInt(tfCurso.getText()));
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
        menuVentanas.cambiarContenido("interfaz/List.fxml");

    }

}
