/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.dao;

/**
 * Exce�ao para falha na grava�ao de novos dados
 */
public class InsertException extends Exception {

    public InsertException(String msg) {
        super(msg);
    }

    public InsertException(Throwable arg) {
        super(arg);
    }

}