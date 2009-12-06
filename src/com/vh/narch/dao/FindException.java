/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.dao;

/**
 * Exceçao para falha na localizaçao dos dados
 */
public class FindException extends Exception {

    public FindException(String msg) {
        super(msg);
    }
    
    public FindException(Throwable arg) {
        super(arg);
    }

}