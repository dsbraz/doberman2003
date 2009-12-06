/*
 * << Doberman >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.doberman.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;

import com.vh.doberman.valueobject.Permission;
import com.vh.narch.dao.AbstractDAO;
import com.vh.narch.dao.FindException;
import com.vh.narch.valueobject.ValueObject;

/**
 * Implementa um DAO para as Permissions
 */
public class PermissionDAO extends AbstractDAO {

    public PermissionDAO() {
        super("Permission");
    }

    /**
     * Localiza uma permissao atraves de um dominio
     * @param id Identificador do dominio
     * @param con Conexao que se usada na pesquisa
     * @return Retorna uma Collection de VOs com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
     */
    public Collection findByDomain(Integer id, Connection con) throws FindException {
        try {
	        StringBuffer sql = new StringBuffer();
	        sql.append("select ac.id, ac.name, ac.module");
	        sql.append("  from tb_permissions as ac, tb_permissions_domains as da");
	        sql.append(" where ac.id = da.permission");
	        sql.append("       and da.domain = '" + id + "'");
	        ResultSet rs = executeQuery(sql.toString(), con);
	        return resultSetToCollection(rs);
        } catch (Exception e) {
            throw new FindException(e);
        }
    }

    /**
     * Localiza uma permissao atraves de um certificado
     * @param id Identificador do certificado
     * @param con Conexao que se usada na pesquisa
     * @return Retorna uma Collection de VOs com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
     */
    public Collection findByCertificate(Integer id, Connection con) throws FindException {
        try {
	        StringBuffer sql = new StringBuffer();
	        sql.append("select ac.id, ac.name, ac.module");
	        sql.append("  from tb_permissions as ac, tb_permissions_certificates ca");
	        sql.append(" where ac.id = ca.permission");
	        sql.append("       and ca.certificate = '" + id + "'");
	        ResultSet rs = executeQuery(sql.toString(), con);
	        return resultSetToCollection(rs);
        } catch (Exception e) {
            throw new FindException(e);
        }
    }

    /**
     * Localiza uma permissao pelo nome
     * @param name O nome da açao que sera procurada
     * @param con A conexao que sera usada na pesquisa
     * @return Retorna um ValueObject contendo os dados encontrados
     * @throws FindException se a pesquisa nao puder ser efetuada
     */
    public ValueObject findByName(String name, Connection con) throws FindException {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select id, name, module");
            sql.append("  from tb_permissions");
            sql.append(" where name = '" + name + "'");
	        ResultSet rs = executeQuery(sql.toString(), con);
	        return resultSetToValueObject(rs);
        } catch (Exception e) {
            throw new FindException(e);
        }
    }

    /**
     * Localiza uma permissao pelo modulo
     * @param module O modulo que sera procurado
     * @param con A conexao que sera usada na pesquisa
     * @return Retorna uma Collection contendo os dados encontrados
     * @throws FindException se a pesquisa nao puder ser efetuada
     */
    public Collection findByModule(String module, Connection con) throws FindException {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select id, name, module");
            sql.append("  from tb_permissions");
            sql.append(" where module = '" + module + "'");
	        ResultSet rs = executeQuery(sql.toString(), con);
	        return resultSetToCollection(rs);
        } catch (Exception e) {
            throw new FindException(e);
        }
    }

    /**
     * @see com.vh.narch.dao.AbstractDAO#getRowAsValueObject(ResultSet)
     */
    protected ValueObject getRowAsValueObject(ResultSet rs) throws Exception {
        Permission vo = new Permission();
        vo.setId(new Integer(rs.getInt("id")));
        vo.setModule(rs.getString("module"));
        vo.setName(rs.getString("name"));
        return vo;
    }

}