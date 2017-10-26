package genericdao.vistajesus;

import acadt_proyectoexportjdom.ACADT_ProyectoExportJDOM;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


/**
 * FXML Controller class
 *
 * @author Jesús González <jesusgonzalezmerin@gmail.com>
 */
public class PortadaController implements Initializable, IControlPantallas {

    //Atributos
    private ACADT_ProyectoExportJDOM menuVentanas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void setMainApp(ACADT_ProyectoExportJDOM mainApp) {
        menuVentanas = mainApp;
    }

}
