package genericdao.vista;

import acadt_proyectoexportjdom.ACADT_ProyectoExportJDOM;
import genericdao.modelo.dao.AlumnosDao;
import genericdao.modelo.dao.CursosAlumnoDao;
import genericdao.modelo.dao.CursosDao;
import genericdao.modelo.entities.Alumno;
import genericdao.modelo.entities.Curso;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
    private CursosAlumnoDao cursoAlumnoDao;

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
    private TableView<Curso> tvCursos;
    @FXML
    private TableColumn<Curso, String> tcCodCurso;
    @FXML
    private TableColumn<Curso, String> tcDescripcion;
    @FXML
    private ComboBox<Curso> cbAlumnosCurso;
    @FXML
    private Button btBorrarAlumno;
    @FXML
    private Button btBorrarCurso;
   
    @FXML
    private CheckBox ckDesbloquearAlumnos;
    @FXML
    private CheckBox ckDesbloquearCursos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Menú listado cargado.");
        alumnoDao = new AlumnosDao();
        cursoDao = new CursosDao();
        listAlumnos(alumnoDao.listAll());
        listCursos(cursoDao.listAll());
        rellenarCombo();

    }

    @Override
    public void setMainApp(ACADT_ProyectoExportJDOM mainApp) {
        menuVentanas = mainApp;
    }

    public void listAlumnos(List<Alumno> lista) {
        tvAlumnos.refresh();
        tcMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcApellido01.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        tcApellido02.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        //tcCurso.setCellValueFactory(new PropertyValueFactory<>("idCurso"));
        tvAlumnos.setItems(FXCollections.observableArrayList(lista));

    }

    public void listCursos(List<Curso> lista) {
        tvCursos.refresh();
        tcCodCurso.setCellValueFactory(new PropertyValueFactory<>("codCurso"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tvCursos.setItems(FXCollections.observableArrayList(lista));

    }

    public void rellenarCombo() {
        List<Curso> lista = cursoDao.listAll();
        cbAlumnosCurso.getItems().addAll(lista);

    }

    @FXML
    private void comprobarPulsado(ActionEvent event) {
        cursoAlumnoDao = new CursosAlumnoDao();
        Curso curso = cbAlumnosCurso.getSelectionModel().getSelectedItem();
        listAlumnos(cursoAlumnoDao.listAlumnosCurso(curso.getId()));

    }

    @FXML
    private void deleteAlumno(ActionEvent event) {
        Alumno alumno = tvAlumnos.getSelectionModel().getSelectedItem();
        alumnoDao.delete(alumno.getId());

        listAlumnos(alumnoDao.listAll());

    }

    @FXML
    private void deleteCurso(ActionEvent event) {
        Curso curso = tvCursos.getSelectionModel().getSelectedItem();
        cursoDao.delete(curso.getId());
        listCursos(cursoDao.listAll());
    }

   

    @FXML
    private void unlockCamposAlumnos(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText("Confirmación");
        alert.setContentText("¿Quiere continuar?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... Ha aceptado
            if (ckDesbloquearAlumnos.isSelected()) {
                btBorrarAlumno.setDisable(false);

               // tvAlumnos.setDisable(false);

            } else {
                btBorrarAlumno.setDisable(true);

             //   tvAlumnos.setDisable(true);

            }

        } else // ... Ha dicho que no
        {
            if (ckDesbloquearAlumnos.isSelected()) {
                ckDesbloquearAlumnos.setSelected(false);
            } else {
                ckDesbloquearAlumnos.setSelected(true);
            }
        }
    }

    @FXML
    private void unlockCamposCursos(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText("Confirmación");
        alert.setContentText("¿Quiere continuar?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... Ha aceptado
            if (ckDesbloquearCursos.isSelected()) {

                btBorrarCurso.setDisable(false);

               // tvCursos.setDisable(false);
            } else {

                btBorrarCurso.setDisable(true);

              //  tvCursos.setDisable(true);
            }

        } else // ... Ha dicho que no
        {
            if (ckDesbloquearCursos.isSelected()) {
                ckDesbloquearCursos.setSelected(false);
            } else {
                ckDesbloquearCursos.setSelected(true);
            }
        }
    }

}
