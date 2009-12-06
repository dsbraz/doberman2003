/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.dao;

/**
 * Exceçao para caso de falha na deleçao de dados no banco
 */
public class DeleteException extends Exception {

    public DeleteException(String msg) {
        super(msg);
    }

    public DeleteException(Throwable arg) {
        super(arg);
    }

}