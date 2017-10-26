package genericdao.vista.jesus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ListController implements Initializable, IControlPantallas {

    //Atributos
    private Main menuVentanas;
    private AlumnoDao alumnoDao;
    private CursoDao cursoDao;

    @FXML
    private TableView<Alumno> tvAlumnos;
    @FXML
    private TableColumn<Alumno, Integer> tcMatricula;
    @FXML
    private TableColumn<Alumno, String> tcNombre;
    @FXML
    private TableColumn<Alumno, String> tcApellido01;
    @FXML
    private TableColumn<Alumno, String> tcApellido02;
    @FXML
    private TableColumn<Alumno, Integer> tcCurso;

    @FXML
    private TableView<Curso> tvCursos;
    @FXML
    private TableColumn<Curso, String> tcCodCurso;
    @FXML
    private TableColumn<Curso, String> tcDescripcion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Menú listado cargado.");
        alumnoDao = new AlumnoDao();
        cursoDao = new CursoDao();
        listAlumnos();
        listCursos();

    }

    @Override
    public void setMainApp(Main mainApp) {
        menuVentanas = mainApp;
    }

    public void listAlumnos() {
        tvAlumnos.refresh();
        tcMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcApellido01.setCellValueFactory(new PropertyValueFactory<>("apellido01"));
        tcApellido02.setCellValueFactory(new PropertyValueFactory<>("apellido02"));
        tcCurso.setCellValueFactory(new PropertyValueFactory<>("idCurso"));

        tvAlumnos.setItems(FXCollections.observableArrayList(alumnoDao.listAll()));

    }

    public void listCursos() {
        tvCursos.refresh();
        tcCodCurso.setCellValueFactory(new PropertyValueFactory<>("codCurso"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        
        tvCursos.setItems(FXCollections.observableArrayList(cursoDao.listAll()));

    }

}
