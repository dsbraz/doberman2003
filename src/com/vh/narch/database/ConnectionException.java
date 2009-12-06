/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.database;

/**
 * Exceçao para as Connections
 */
public class ConnectionException extends Exception {

    public ConnectionException(String msg) {
        super(msg);
    }

    public ConnectionException(Throwable arg) {
        super(arg);
    }

}