package genericdao.vistajesus;

import acadt_proyectoexportjdom.ACADT_ProyectoExportJDOM;
import genericdao.modelo.dao.AlumnosDao;
import genericdao.modelo.dao.CursosDao;
import genericdao.modelo.entities.Alumno;
import genericdao.modelo.entities.Curso;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 *
 * @author Jesús González <jesusgonzalezmerin@gmail.com>
 */
public class ListController implements Initializable, IControlPantallas {

    //Atributos
    private ACADT_ProyectoExportJDOM menuVentanas;
    private AlumnosDao alumnoDao;
    private CursosDao cursoDao;

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
        alumnoDao = new AlumnosDao();
        cursoDao = new CursosDao();
        listAlumnos();
        listCursos();

    }

    @Override
    public void setMainApp(ACADT_ProyectoExportJDOM mainApp) {
        menuVentanas = mainApp;
    }

    public void listAlumnos() {
        tvAlumnos.refresh();
        tcMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcApellido01.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        tcApellido02.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        //tcCurso.setCellValueFactory(new PropertyValueFactory<>("idCurso"));
        tvAlumnos.setItems(FXCollections.observableArrayList(alumnoDao.listAll()));

    }

    public void listCursos() {
        tvCursos.refresh();
        tcCodCurso.setCellValueFactory(new PropertyValueFactory<>("codCurso"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tvCursos.setItems(FXCollections.observableArrayList(cursoDao.listAll()));

    }

}
