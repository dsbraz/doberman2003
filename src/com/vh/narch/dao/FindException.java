/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.dao;

/**
 * Exce�ao para falha na localiza�ao dos dados
 */
public class FindException extends Exception {

    public FindException(String msg) {
        super(msg);
    }
    
    public FindException(Throwable arg) {
        super(arg);
    }

}