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
import java.util.Iterator;

import com.vh.doberman.valueobject.Permission;
import com.vh.doberman.valueobject.Domain;
import com.vh.narch.dao.AbstractDAO;
import com.vh.narch.dao.FindException;
import com.vh.narch.database.ConnectionManager;
import com.vh.narch.database.sequence.Sequence;
import com.vh.narch.valueobject.ValueObject;

/**
 * Implementa um DAO para os domains
 */
public class DomainDAO extends AbstractDAO {

    public DomainDAO() {
        super("Domain");
    }

    /**
     * Localiza um dominio atraves de uma permissao
     * @param id Identificador da permissao
     * @param con Conexao que se usada na pesquisa
     * @return Retorna uma Collection de VOs com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
     */
    public Collection findByPermission(Integer id, Connection con) throws FindException {
        try {
	        StringBuffer sql = new StringBuffer();
	        sql.append("select dm.id, dm.name");
	        sql.append("  from tb_domains as dm, tb_permissions_domains as da");
	        sql.append(" where dm.id = da.domain");
	        sql.append("       and da.permission = '" + id + "'");
	        ResultSet rs = executeQuery(sql.toString(), con);
	        return resultSetToCollection(rs);
        } catch (Exception e) {
            throw new FindException(e);
        }
    }

    /**
     * Localiza um dominio pelo nome
     * @param name O nome do dominio que sera procurado
     * @param con A conexao que sera usada na pesquisa
     * @return Retorna um ValueObject contendo os dados encontrados
     * @throws FindException se a pesquisa nao puder ser efetuada
     */
    public ValueObject findByName(String name, Connection con) throws FindException {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select id, name");
            sql.append("  from tb_domains");
            sql.append(" where name = '" + name + "'");
	        ResultSet rs = executeQuery(sql.toString(), con);
	        return resultSetToValueObject(rs);
        } catch (Exception e) {
            throw new FindException(e);
        }
    }

    /**
     * @see com.vh.narch.dao.AbstractDAO#afterInsert(ValueObject, Connection)
     */
    protected void afterInsert(ValueObject data, Connection con) throws Exception {
        Sequence seq = new Sequence("seq_permissions_domains");
        Iterator it_deps = ((Domain)data).getPermissions().iterator();
        while (it_deps.hasNext()) {
            Permission perm = (Permission)it_deps.next();
            StringBuffer sql = new StringBuffer();
	        sql.append("insert into tb_permissions_domains");
	        sql.append("(id,domain,permission)");
	        sql.append(" values('" + seq.nextValue() + "',");
	        sql.append("'" + data.getId() + "',");
	        sql.append("'" + perm.getId() + "')");
	        excuteUpdate(sql.toString(), con);
        }
    }

    /**
     * @see com.vh.narch.dao.AbstractDAO#beforeDelete(ValueObject, Connection)
     */
    protected void beforeDelete(ValueObject data, Connection con) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from tb_permissions_domains");
        sql.append(" where domain = '" + data.getId() + "'");
        excuteUpdate(sql.toString(), con);
    }

    /**
     * @see com.vh.narch.dao.AbstractDAO#afterUpdate(ValueObject, Connection)
     */
    protected void afterUpdate(ValueObject data, Connection con) throws Exception {
        beforeDelete(data, con);
        afterInsert(data, con);
    }

    /**
     * @see com.vh.narch.dao.AbstractDAO#getRowAsValueObject(ResultSet)
     */
    protected ValueObject getRowAsValueObject(ResultSet rs) throws Exception {
        Connection con = ConnectionManager.getConnection();
        Domain vo = new Domain();
        vo.setId(new Integer(rs.getInt("id")));
        vo.setName(rs.getString("name"));
        vo.setPermissions(new PermissionDAO().findByDomain(vo.getId(), con));
        return vo;
    }

}