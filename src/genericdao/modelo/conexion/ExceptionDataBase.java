/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericdao.modelo.conexion;

/**
 *
 * @author JAVIER
 */
public class ExceptionDataBase extends Exception {

    final static int DB_VERSION_EXCEPTION = 1;

    public ExceptionDataBase(String msg) {
        super(msg);
    }
}
