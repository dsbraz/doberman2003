/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.security;

/**
 * Exce�ao para falhas na seguran�a
 */
public class SecurityException extends Exception {

    public SecurityException(String msg) {
        super(msg);
    }

    public SecurityException(Throwable arg) {
        super(arg);
    }

}