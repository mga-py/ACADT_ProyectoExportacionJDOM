
import genericdao.modelo.dao.AlumnosDao;
import genericdao.modelo.dao.CursosAlumnoDao;
import genericdao.modelo.dao.CursosDao;
import genericdao.modelo.exportacionJDOM.JDOM;

public class test {

    public static void main(String[] args) {
        //Instanciamos la clase JDOM.
        JDOM jdom = new JDOM();

        //Probamos a importar un archivo XML, creado previamente.
        jdom.importarXml("academiaExportado.xml");
        AlumnosDao alumnoDao = new AlumnosDao();
        alumnoDao.add(jdom.importarXml("academiaExportado.xml").get(2));
        CursosDao cursoDao = new CursosDao();
        cursoDao.add(jdom.importarXml("academiaExportado.xml").get(0));
        CursosAlumnoDao cursoAlumnoDao = new CursosAlumnoDao();
        cursoAlumnoDao.add(jdom.importarXml("academiaExportado.xml").get(1));

        //Probamos a exportar un archivo XML.
        //jdom.exportarXml("academiaExportado02.xml");
    }
}
