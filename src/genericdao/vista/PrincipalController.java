package genericdao.vista;

import acadt_proyectoexportjdom.ACADT_ProyectoExportJDOM;
import genericdao.modelo.dao.AlumnosDao;
import genericdao.modelo.dao.CursosAlumnoDao;
import genericdao.modelo.dao.CursosDao;
import genericdao.modelo.exportacionJDOM.JDOM;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public class PrincipalController implements Initializable, IControlPantallas {

    //Atributos
    private ACADT_ProyectoExportJDOM menuVentanas;

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
    @FXML
    private Menu mArchivo;
    @FXML
    private MenuItem miExport;
    @FXML
    private MenuItem miImport;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void setMainApp(ACADT_ProyectoExportJDOM mainApp) {
        menuVentanas = mainApp;

    }

    @FXML
    private void add(ActionEvent event) {
        menuVentanas.cambiarContenido("/genericdao/vista/Add.fxml");
    }

    @FXML
    private void delete(ActionEvent event) {
        menuVentanas.cambiarContenido("/genericdao/vista/Delete.fxml");
    }

    @FXML
    private void update(ActionEvent event) {
        menuVentanas.cambiarContenido("/genericdao/vista/Update.fxml");
    }

    @FXML
    private void list(ActionEvent event) {
        menuVentanas.cambiarContenido("/genericdao/vista/List.fxml");
    }

    @FXML
    private void aboutUser(ActionEvent event) {
        Alert alertaAutor = new Alert(Alert.AlertType.INFORMATION);
        alertaAutor.setTitle("Información");
        alertaAutor.setHeaderText("© Copyright - Todos los derechos reservados.");
        alertaAutor.setContentText("Aplicación JDOM");
        alertaAutor.showAndWait();
    }

    @FXML
    private void exportXML(ActionEvent event) {
         menuVentanas.cambiarContenido("/genericdao/vista/Nuevo.fxml");
        
        
        
//        //Instanciamos la clase JDOM.
//        JDOM jdom = new JDOM();
//        jdom.exportarXml("XMLExportado.xml");
    }

    @FXML
    private void importXML(ActionEvent event) {
        
         FileChooser fichero = new FileChooser();
        File ficheroSeleccionado = fichero.showOpenDialog(null);
        fichero.setTitle("Elegir fichero XML");
        
        
        //Instanciamos la clase JDOM.
        JDOM jdom = new JDOM();

        //Probamos a importar un archivo XML, creado previamente.
        List<ArrayList> lista =jdom.importarXml(ficheroSeleccionado.getAbsolutePath());
        AlumnosDao alumnoDao = new AlumnosDao();
        alumnoDao.add(lista.get(2));
        CursosDao cursoDao = new CursosDao();
        cursoDao.add(lista.get(0));
        CursosAlumnoDao cursoAlumnoDao = new CursosAlumnoDao();
        cursoAlumnoDao.add(lista.get(1));
         menuVentanas.cambiarContenido("/genericdao/vista/List.fxml");
    }

}
