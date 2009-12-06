/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.dao;

/**
 * Exceçao para falha na gravaçao de novos dados
 */
public class InsertException extends Exception {

    public InsertException(String msg) {
        super(msg);
    }

    public InsertException(Throwable arg) {
        super(arg);
    }

}