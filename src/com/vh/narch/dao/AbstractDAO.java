/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.vh.narch.database.sequence.Sequence;
import com.vh.narch.util.SystemProperties;
import com.vh.narch.valueobject.ValueObject;

/**
 * Implementaçao generica de um DAO
 * @see com.vh.narch.dao.DataAccessObject
 */
public abstract class AbstractDAO implements DataAccessObject {

    private String tableName;
    private String sequenceName;
    
    public AbstractDAO(String voName) {
        try {
	        tableName = SystemProperties.getProperty("vo." + voName + ".tableName");
	        sequenceName = SystemProperties.getProperty("vo." + voName + ".sequenceName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Retorna a sequence associada
     */
    protected Sequence getSequence() {
        return new Sequence(sequenceName);
    }

    /**
     * Executa o sql de pesquisa informado
     * @param sql O sql que sera executado
     * @param con A conexao sera utilizada na pesquisa
     * @return Retorna um ResultSet contendo os dados pesquisados
     */
    protected ResultSet executeQuery(String sql, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }

    /**
     * Executa o sql de alteraçao informado
     * @param sql O sql que sera executado
     * @param con A conexao sera utilizada na pesquisa
     * @return Retorna um int informando qtas linha foram modificadas
     */
    protected int excuteUpdate(String sql, Connection con) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeUpdate();
    }

    /**
     * @see com.vh.narch.dao.DataAccessObject#find(Connection)
     */
    public Collection find(Connection con) throws FindException {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select * from " + tableName);
	        ResultSet rs = executeQuery(sql.toString(), con);
	        return resultSetToCollection(rs);
        } catch (Exception e) {
            throw new FindException(e);
        }
    }

    /**
     * @see com.vh.narch.dao.DataAccessObject#findById(Integer, Connection)
     */
    public ValueObject findById(Integer id, Connection con) throws FindException {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select * from " + tableName);
            sql.append(" where id = '" + id + "'");
	        ResultSet rs = executeQuery(sql.toString(), con);
	        return resultSetToValueObject(rs);
        } catch (Exception e) {
            throw new FindException(e);
        }
    }

    /**
     * Evento executado antes da inserçao dos dados 
     * @param data Os dados que serao incluidos
     * @param con A conexao que sera utilizada na inclusao
     * @throws Exception em caso de falha na inclusao
     * @see com.vh.narch.dao.AbstractDAO#insert(ValueObject, Connection)
     */
    protected void beforeInsert(ValueObject data, Connection con) throws Exception {
        //
    }

    /**
     * @see com.vh.narch.dao.DataAccessObject#insert(com.vh.narch.valueobject.ValueObject,
     *      java.sql.Connection)
     * @see com.vh.narch.dao.AbstractDAO#beforeInsert(ValueObject, Connection) 
     * @see com.vh.narch.dao.AbstractDAO#afterInsert(ValueObject, Connection)
     */
    public void insert(ValueObject data, Connection con) throws InsertException {
        try {
            beforeInsert(data, con);
            Sequence seq = getSequence();
            data.setId(seq.nextValue());
            Map row = data.toMap();
            StringBuffer sql = new StringBuffer();
            sql.append("insert into " + tableName + "(");
            Iterator it_keys = row.keySet().iterator();
            while (it_keys.hasNext()) { sql.append(it_keys.next() + ","); }
            sql.deleteCharAt(sql.lastIndexOf(","));
            sql.append(") values (");
            Iterator it_vals = row.values().iterator();
            while (it_vals.hasNext()) { sql.append("'" + it_vals.next() + "',"); }
            sql.deleteCharAt(sql.lastIndexOf(","));
            sql.append(")");
            excuteUpdate(sql.toString(), con);
            afterInsert(data, con);
        } catch (Exception e) {
            throw new InsertException(e);
        }
    }

    /**
     * Evento executado apos a inserçao dos dados 
     * @param data Os dados que serao incluidos
     * @param con A conexao que sera utilizada na inclusao
     * @throws Exception em caso de falha na inclusao
     * @see com.vh.narch.dao.AbstractDAO#insert(ValueObject, Connection)
     */
    protected void afterInsert(ValueObject data, Connection con) throws Exception {
        //
    }

    /**
     * Evento executado antes a alteraçao dos dados
     * @param data Os dados que serao alterados
     * @param con A conexao que sera utilizada na alteraçao
     * @throws Exception em caso de falha na alteraçao
     * @see com.vh.narch.dao.AbstractDAO#update(ValueObject, Connection)
     */
    protected void beforeUpdate(ValueObject data, Connection con) throws Exception {
        //
    }

    /**
     * @see com.vh.narch.dao.DataAccessObject#update(com.vh.narch.valueobject.ValueObject,
     *      java.sql.Connection)
     * @see com.vh.narch.dao.AbstractDAO#beforeUpdate(ValueObject, Connection)
     * @see com.vh.narch.dao.AbstractDAO#afterUpdate(ValueObject, Connection)
     */
    public void update(ValueObject data, Connection con) throws UpdateException {
        try {
            beforeUpdate(data, con);
            Map row = data.toMap();
            StringBuffer sql = new StringBuffer();
            sql.append("update " + tableName + " set ");
            Iterator it_keys = row.keySet().iterator();
            Iterator it_vals = row.values().iterator();
            while (it_keys.hasNext() && it_vals.hasNext()) {
                sql.append(it_keys.next() + "= '" + it_vals.next() + "',");
            }
            sql.deleteCharAt(sql.lastIndexOf(","));
            sql.append(" where id = '" + data.getId() + "'");
            excuteUpdate(sql.toString(), con);
            afterUpdate(data, con);
        } catch (Exception e) {
            throw new UpdateException(e);
        }
    }

    /**
     * Evento executado apos a alteraçao dos dados
     * @param data Os dados que serao alterados
     * @param con A conexao que sera utilizada na alteraçao
     * @throws Exception em caso de falha na alteraçao
     * @see com.vh.narch.dao.AbstractDAO#update(ValueObject, Connection)
     */
    protected void afterUpdate(ValueObject data, Connection con) throws Exception {
        //
    }

    /**
     * Evento executado antes da exclusao dos dados
     * @param data Os dados que serao excluidos
     * @param con A conexao que sera utilizada na exclusao
     * @throws Exception em caso de falha na exclusao
     * @see com.vh.narch.dao.AbstractDAO#delete(ValueObject, Connection)
     */
    protected void beforeDelete(ValueObject data, Connection con) throws Exception {
        //
    }

    /**
     * @see com.vh.narch.dao.DataAccessObject#delete(com.vh.narch.valueobject.ValueObject,
     *      java.sql.Connection)
     * @see com.vh.narch.dao.AbstractDAO#beforeDelete(ValueObject, Connection)
     * @see com.vh.narch.dao.AbstractDAO#afterDelete(ValueObject, Connection) 
     */
    public void delete(ValueObject data, Connection con) throws DeleteException {
        try {
            beforeDelete(data, con);
            StringBuffer sql = new StringBuffer();
            sql.append("delete from " + tableName);
            sql.append(" where id = '" + data.getId() + "'");
            excuteUpdate(sql.toString(), con);
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    /**
     * Evento executado apos da exclusao dos dados
     * @param data Os dados que serao excluidos
     * @param con A conexao que sera utilizada na exclusao
     * @throws Exception em caso de falha na exclusao
     * @see com.vh.narch.dao.AbstractDAO#delete(ValueObject, Connection)
     */
    protected void afterDelete(ValueObject data, Connection con) throws Exception {
        //
    }

	//==========================================================================
	// UTIL -- Output ("Formatador")
	//==========================================================================

    /**
     * Transforma uma linha de um ResultSet em um ValueObject
     * @param rs O ResultSet que sera transformado
     * @return Retorna um ValueObject contendo os dados do ResultSet
     * @throws Exception caso a transformacao nao possa ser feita
     */
    protected abstract ValueObject getRowAsValueObject(ResultSet rs) throws Exception;

    /**
     * Transforma a primeira linha do ResultSet no ValueObject 
     * especificado no metodo getRowAsValueObject(ResultSet)
     * @param rs O ResultSet que sera transformado
     * @return Retorna um ValueObject contendo os dados do ResultSet
     * @throws Exception caso a transformacao nao possa ser feita
     * @see com.vh.narch.dao.AbstractDAO#getRowAsValueObject(ResultSet)
     */
    protected ValueObject resultSetToValueObject(ResultSet rs) throws Exception {
        ValueObject vo = null;
        if (rs.next()) { vo = getRowAsValueObject(rs); }
        return vo;
    }

    /**
     * Transforma um ResultSet em uma Collection de VOs 
     * especificado no metodo getRowAsValueObject(ResultSet)
     * @param rs O ResultSet que sera transformado
     * @return Retorna uma Collection de VOs contendo os dados do ResultSet
     * @throws Exception caso a transformacao nao possa ser feita
     * @see com.vh.narch.dao.AbstractDAO#getRowAsValueObject(ResultSet) 
     */
    protected Collection resultSetToCollection(ResultSet rs) throws Exception {
        Collection result = new ArrayList();
        while (rs.next()) { result.add(getRowAsValueObject(rs)); }
        return result;
    }

}