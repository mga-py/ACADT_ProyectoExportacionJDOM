package genericdao.vista.jesus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import pkg002academia.IControlPantallas;
import pkg002academia.Main;

/**
 *
 * @author Jesús González <jesusgonzalezmerin@gmail.com>
 */
public class UpdateController implements Initializable, IControlPantallas {

    //Atributos
    private Main menuVentanas;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Menú actualizar cargado.");

    }

    @Override
    public void setMainApp(Main mainApp) {
        menuVentanas = mainApp;
    }

    @FXML
    private void updateAlumno(ActionEvent event) {
    }

    @FXML
    private void updateCourse(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
        menuVentanas.cambiarContenido("interfaz/List.fxml");
    }

    @FXML
    private void find(ActionEvent event) {
        
        
    }

}
