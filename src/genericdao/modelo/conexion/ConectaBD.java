package genericdao.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ConectaBD {

    private static ConectaBD conectaBD;

    //Version de la base de datos
    final int DB_VERSION = 1;
    final String DB_DESCRIPCION_VERSION = "Creación inicial";
    //Nombre de la base de datos
    final String DB_NOMBRE = "academia.db";
    //Conexion privada porque solo pertenece a esta clase
    private Connection connection;
    //Controla si se produce error y devuelve true en tal caso
    private boolean error;
    private Statement stmtUpdate;
    private Statement stmtResultSet;
    
    
    
    /**
     * Se crea la base de datos si no esta creada y si no obtiene la que ya esta
     * creada
     * @return (ConectaBD)devuelve esta clase en caso de que ya este creada 
     * @throws ExceptionDataBase 
     */
    public static ConectaBD getConectaBD() throws ExceptionDataBase {
        if (conectaBD == null) {
            conectaBD = new ConectaBD();
        }

        return conectaBD;
    }

    /**
     * Se crea la base de datos el constructor es privado porque solo se 
     * accede desde esta clase
     * @throws ExceptionDataBase 
     */
    private ConectaBD() throws ExceptionDataBase {
        error = false;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NOMBRE);
            stmtUpdate = connection.createStatement();
            stmtResultSet = connection.createStatement();
            connection.setAutoCommit(false);
            update();
            connection.commit();
        } catch (ClassNotFoundException ex) {
            error = true;
            System.err.println("Driver sqlite no encontrado");
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
            }
            error = true;
            System.err.println("Se ha producido un error en la sentencia SQL");
        }
    }

    

    /**
     * Metodo para obtener la conexion
     * @return conexion(Connection)
     */
    public Connection getConnection() {
        return connection;
    }

    private void update() throws SQLException, ExceptionDataBase {
        error = false;
        int versionBD = lastVersion();
        if (versionBD == 0) {
            createTables();
        } else if (versionBD != DB_VERSION) {
            throw new ExceptionDataBase("Base de datos almacenada no coincide con la de la aplicacion. Ejecute el proceso de actualización");
        }

    }

    /**
     * Crea las tablas en la base de datos
     * @throws SQLException 
     */
    private void createTables() throws SQLException {
        error = false;
        String sql;
        sql = "CREATE TABLE IF NOT EXISTS cursos( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "codCurso VARCHAR(15) UNIQUE NOT NULL, "
                + "descripcion VARCHAR (50), "
                + "CONSTRAINT codCurso UNIQUE (codCurso));";
        stmtUpdate.execute(sql);
        sql = "CREATE TABLE IF NOT EXISTS alumnos( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "matricula INTEGER UNIQUE NOT NULL, "
                + "nombre VARCHAR(15), "
                + "apellido1 VARCHAR (15), "
                + "apellido2 VARCHAR (15), "
                + "CONSTRAINT matricula UNIQUE (matricula));";
        stmtUpdate.execute(sql);
        sql = "CREATE TABLE IF NOT EXISTS version( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "version INTEGER NOT NULL, "
                + "descripcion VARCHAR (50), "
                + "fecha DATE, "
                + "CONSTRAINT version UNIQUE (version));";
        stmtUpdate.execute(sql);
        sql = "CREATE TABLE IF NOT EXISTS cursos_alumno( "
                + "idCurso INTEGER,  "
                + "idAlumno INTEGER, "
                + "FOREIGN KEY(idAlumno) REFERENCES alumnos(id), "
                + "FOREIGN KEY(idCurso) REFERENCES cursos(id), "
                + "CONSTRAINT unico UNIQUE (idAlumno, idCurso));";
        stmtUpdate.execute(sql);
        addVersion();
    }

    /**
     * Inserta la version de la base de datos
     * @throws SQLException 
     */
    private void addVersion() throws SQLException {
        error = false;
        String sql = "INSERT INTO version "
                + "(id, version, descripcion, fecha) VALUES "
                + "(null, ?,?,?);";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, DB_VERSION);
        ps.setString(2, DB_DESCRIPCION_VERSION);
        Date fechaActual = new Date();
        ps.setDate(3, new java.sql.Date(fechaActual.getTime()));
        ps.executeUpdate();
    }

    /**
     * Devuelve las versiones antiguas de la base de datos
     * @return (Integer) versioines antiguas DB
     */
    private Integer lastVersion() {
        error = false;
        int version = 0;
        String sql = "SELECT version "
                + "FROM version "
                + "ORDER BY version DESC LIMIT 1";
        try {
            ResultSet rs = stmtResultSet.executeQuery(sql);
            if (rs.next()) {
                version = rs.getInt("version");
            }
            rs.close();
        } catch (SQLException ex) {
            error = true;
        }
        return version;
    }

    /**
     * Devuelve si es error o no
     * @return (Boolean)
     */
    public boolean isError() {
        return error;
    }

}
