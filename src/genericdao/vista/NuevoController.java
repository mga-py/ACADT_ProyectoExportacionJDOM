/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericdao.vista;

import acadt_proyectoexportjdom.ACADT_ProyectoExportJDOM;
import genericdao.modelo.exportacionJDOM.JDOM;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Baltasar Rangel Pinilla  <mga-py>----<baltasarrangel93@gmail.com">
 */
public class NuevoController implements Initializable, IControlPantallas {

    private ACADT_ProyectoExportJDOM menuVentanas;
    @FXML
    private TextField tfNombreDirectorio;
    @FXML
    private Button btElegirDirectorio;
    @FXML
    private RadioButton rbTXT;
    @FXML
    private RadioButton rbLOG;
    @FXML
    private RadioButton rbCONF;
    @FXML
    private TextField tfRutaCompletaArchivo;

    private String rutaCompleta;
    private String nombreArchivo;
    private String rutaDirectorio;
    private String extensionArchivo;
    private File fichero;

    @FXML
    private Button btCrearArchivo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rutaDirectorio="";
        extensionArchivo=".xml";
    }

    @Override
    public void setMainApp(ACADT_ProyectoExportJDOM mainApp) {
        menuVentanas = mainApp;
    }

    @FXML
    private void elegirDirectorio(ActionEvent event) {
        DirectoryChooser directorio = new DirectoryChooser();
        directorio.setTitle("Elegir carpeta de guardado");

        File defaultDirectory = new File(System.getProperty("user.dir") + File.separator);
        directorio.setInitialDirectory(defaultDirectory);
        File selectedDirectory = directorio.showDialog(null);

        String path;
        if (selectedDirectory != null) {

            rutaDirectorio = selectedDirectory.getPath();
        } else {
            //default return value
            path = null;
        }

        completarRuta();
        tfRutaCompletaArchivo.setText(rutaCompleta);

    }

    public void completarRuta() {
        if (!tfNombreDirectorio.getText().equals("")) {
            nombreArchivo = tfNombreDirectorio.getText();
            rutaCompleta = rutaDirectorio + File.separator + nombreArchivo + extensionArchivo;
            tfRutaCompletaArchivo.setText(rutaCompleta);

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Falta el nombre del archivo");
            alert.setContentText("Debe ingresar el nombre del archivo en el campo correspondiente");

            alert.showAndWait();
        }

    }

    @FXML
    private void tipoTXT(ActionEvent event) {
        extensionArchivo = ".txt";
        rbCONF.setSelected(false);
        rbLOG.setSelected(false);

        completarRuta();
        tfRutaCompletaArchivo.setText(rutaCompleta);
    }

    @FXML
    private void tipoLOG(ActionEvent event) {
        extensionArchivo = ".log";
        rbCONF.setSelected(false);
        rbTXT.setSelected(false);

        completarRuta();
        tfRutaCompletaArchivo.setText(rutaCompleta);
    }

    @FXML
    private void tipoCONF(ActionEvent event) {
        extensionArchivo = ".conf";
        rbTXT.setSelected(false);
        rbLOG.setSelected(false);

        completarRuta();
        tfRutaCompletaArchivo.setText(rutaCompleta);
    }

    @FXML
    private void crearArchivo(ActionEvent event) throws FileNotFoundException, ParseException {
        completarRuta();
        fichero = new File(rutaCompleta);
        if (!rutaDirectorio.equals("")) {
             if (!fichero.exists()) {
            
                //fichero.createNewFile();
                
                 JDOM jdom = new JDOM();
        jdom.exportarXml(rutaCompleta);
                
                
                
                menuVentanas.cambiarContenido("/genericdao/vista/List.fxml");
               // menuVentanas.cambiaContenidoFichero("Listado.fxml",rutaCompleta);
                
                
                
           
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("El fichero ya existe");
            alert.setContentText("Debe ingresar otro nombre de archivo");

            alert.showAndWait();
        }
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("La ruta de almacenamiento del fichero no es correcta");
            alert.setContentText("Debe elegir el directorio donde se va a guardar");

            alert.showAndWait();
        }
       
    }

   

}
