/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.util.logging;

import java.sql.Timestamp;

import com.vh.narch.valueobject.AbstractVO;

/**
 * Implementa um ValueObject para os logs do sistema
 */
class LogVO extends AbstractVO {
    
    private String message;
    private Timestamp time;

    /**
     * @return Retorna a data do log
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * @param time A nova data do log
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }

    /**
     * @return Retorna a mensagem do log
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * @param message A nova mensagem do log
     */
    public void setMessage(String message) {
        this.message = message;
    }

}