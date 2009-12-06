/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.business;

import java.sql.Connection;
import java.util.Collection;

import com.vh.narch.dao.DataAccessObject;
import com.vh.narch.dao.DeleteException;
import com.vh.narch.dao.FindException;
import com.vh.narch.dao.InsertException;
import com.vh.narch.dao.UpdateException;
import com.vh.narch.util.logging.LogManager;
import com.vh.narch.valueobject.ValueObject;

/**
 * Implementaçao generica de um BusinessObject
 */
public abstract class AbstractBO implements BusinessObject {
    
    private DataAccessObject dao;
    
    public AbstractBO(DataAccessObject dao) {
        setDAO(dao);
    }
    
    /**
     * @return Retorna o DataAccessObject
     */
    protected DataAccessObject getDAO() {
        return dao;
    }
    
    /**
     * @param dao O novo DataAccessObject
     */
    protected void setDAO(DataAccessObject dao) {
        this.dao = dao;
    }

    /**
     * @see com.vh.narch.business.BusinessObject#find(Connection)
     */
    public Collection find(Connection con) throws FindException {
        return getDAO().find(con);
    }
    
    /**
     * @see com.vh.narch.business.BusinessObject#findById(Integer, Connection)
     */
    public ValueObject findById(Integer id, Connection con) throws FindException {
        return getDAO().findById(id, con);
    }

    /**
     * @see com.vh.narch.business.BusinessObject#insert(com.vh.narch.valueobject.ValueObject,
     * 		java.sql.Connection)
     */
    public void insert(ValueObject data, Connection con) throws InsertException {
        getDAO().insert(data, con);
    }

    /**
     * @see com.vh.narch.business.BusinessObject#update(com.vh.narch.valueobject.ValueObject,
     * 		java.sql.Connection)
     */
    public void update(ValueObject data, Connection con) throws UpdateException {
        getDAO().update(data, con);
    }

    /**
     * @see com.vh.narch.business.BusinessObject#delete(com.vh.narch.valueobject.ValueObject,
     * 		java.sql.Connection)
     */
    public void delete(ValueObject data, Connection con) throws DeleteException {
        getDAO().delete(data, con);
    }

    /**
     * @see com.vh.narch.util.logging.LogManager#log(String) 
     */
    public void log(String user, String action, String value) {
        LogManager.log("user=" + user + " action=" + action + " value=" + value);        
    }

}