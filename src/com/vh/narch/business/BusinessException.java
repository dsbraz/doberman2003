/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.business;

/**
 * Implementa uma exceçao para os BOs
 */
public class BusinessException extends Exception {

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable arg) {
        super(arg);
    }

}