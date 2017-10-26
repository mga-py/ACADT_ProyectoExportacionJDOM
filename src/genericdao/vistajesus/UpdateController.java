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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Jesús González <jesusgonzalezmerin@gmail.com>
 */
public class UpdateController implements Initializable, IControlPantallas {

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
    private TextField tfFiltro;
    @FXML
    private Label lbMatricula;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbApellido1;
    @FXML
    private Label lbApellido2;
    @FXML
    private Label lbIdCurso;
    @FXML
    private Label lbCodigoCurso;
    @FXML
    private Label lbDescripcion;
    @FXML
    private TextField tfFiltroCurso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Menú actualizar cargado.");
        tfCurso.setVisible(false);
        tfCodigoCurso.setVisible(false);
        tfDescripcionCurso.setVisible(false);

    }

    @Override
    public void setMainApp(ACADT_ProyectoExportJDOM mainApp) {
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
        menuVentanas.cambiarContenido("/genericdao/vistajesus/List.fxml");
    }

    @FXML
    private void findAlumno(ActionEvent event) {
        alumnoDao = new AlumnosDao();
        Alumno alumno = new Alumno();
        alumno.setMatricula(Integer.valueOf(tfFiltro.getText()));
        if (alumnoDao.exist(alumno)) {
            System.out.println("EXISTE");
            tfMatricula.setVisible(true);
            tfNombre.setVisible(true);
            tfApellido01.setVisible(true);
            tfApellido02.setVisible(true);
            lbMatricula.setVisible(true);
            lbNombre.setVisible(true);
            lbApellido1.setVisible(true);
            lbApellido2.setVisible(true);
        } else {
            System.out.println("NO EXISTE");
        }

    }

    @FXML
    private void findCourse(ActionEvent event) {
        cursoDao = new CursosDao();
        Curso curso= new Curso();
        curso.setCodCurso(tfFiltroCurso.getText());
        if(cursoDao.exist(curso)){
            System.out.println("EXISTE");
            tfCodigoCurso.setVisible(true);
            tfDescripcionCurso.setVisible(true);
            lbCodigoCurso.setVisible(true);
            lbDescripcion.setVisible(true);
        }
        else{
            System.out.println("NO EXISTE");
        }
    }

}
