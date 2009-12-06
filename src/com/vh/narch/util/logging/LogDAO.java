/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.util.logging;

import java.sql.ResultSet;

import com.vh.narch.dao.AbstractDAO;
import com.vh.narch.valueobject.ValueObject;

/**
 * Implementa um DAO para os logs do sistema
 */
class LogDAO extends AbstractDAO {

    public LogDAO() {
        super("Log");
    }

    /**
     * @see com.vh.narch.dao.AbstractDAO#getRowAsValueObject(java.sql.ResultSet)
     */
    protected ValueObject getRowAsValueObject(ResultSet rs) throws Exception {
        LogVO log = new LogVO();
        log.setId(new Integer(rs.getInt("id")));
        log.setMessage(rs.getString("message"));
        log.setTime(rs.getTimestamp("time"));
        return log;
    }

}
