/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericdao.vista.jesus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import pkg002academia.IControlPantallas;
import pkg002academia.Main;

/**
 *
 * @author Jesús González <jesusgonzalezmerin@gmail.com>
 */
public class PrincipalController implements Initializable, IControlPantallas {

    //Atributos
    private Main menuVentanas;

    @FXML
    private Menu mAcerca;
    @FXML
    private MenuItem miInformacion;
    @FXML
    private Menu mOpciones;
    @FXML
    private MenuItem miAdd;
    @FXML
    private MenuItem miDelete;
    @FXML
    private MenuItem miUpdate;
    @FXML
    private MenuItem miList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void setMainApp(Main mainApp) {
        menuVentanas = mainApp;

    }

    @FXML
    private void add(ActionEvent event) {
        menuVentanas.cambiarContenido("interfaz/Add.fxml");
    }

    @FXML
    private void delete(ActionEvent event) {
        menuVentanas.cambiarContenido("interfaz/Delete.fxml");
    }

    @FXML
    private void update(ActionEvent event) {
        menuVentanas.cambiarContenido("interfaz/Update.fxml");
    }

    @FXML
    private void list(ActionEvent event) {
        menuVentanas.cambiarContenido("interfaz/List.fxml");
    }

    @FXML
    private void aboutUser(ActionEvent event) {
        Alert alertaAutor = new Alert(Alert.AlertType.INFORMATION);
        alertaAutor.setTitle("Información");
        alertaAutor.setHeaderText("© Copyright - Todos los derechos reservados.");
        alertaAutor.setContentText("Aplicación JDOM");
        alertaAutor.showAndWait();
    }

}
