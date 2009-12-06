/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.dao;

/**
 * Exceçao para falha na alteraçao dos dados
 */
public class UpdateException extends Exception {
    
    public UpdateException(String msg) {
        super(msg);
    }

    public UpdateException(Throwable arg) {
        super(arg);
    }

}