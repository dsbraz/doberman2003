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
import com.vh.doberman.valueobject.Certificate;
import com.vh.doberman.valueobject.User;
import com.vh.narch.dao.AbstractDAO;
import com.vh.narch.dao.FindException;
import com.vh.narch.database.ConnectionManager;
import com.vh.narch.database.sequence.Sequence;
import com.vh.narch.valueobject.ValueObject;

/**
 * Implementa um DAO para os certificados
 */
public class CertificateDAO extends AbstractDAO {

    public CertificateDAO() {
        super("Certificate");
    }

    /**
     * Localiza um certificado atraves de uma permissao
     * @param id Identificador do certificado
     * @param con Conexao que se usada na pesquisa
     * @return Retorna uma Collection de VOs com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
     */
    public Collection findByPermission(Integer id, Connection con) throws FindException {
        try {
	        StringBuffer sql = new StringBuffer();
	        sql.append("select ce.id, ce.key, ce.owner");
	        sql.append("  from tb_certificates as ce,");
	        sql.append("       tb_permissions_certificates as ca");
	        sql.append(" where ce.id = ca.certificate");
	        sql.append("       and ca.permission = '" + id + "'");
	        ResultSet rs = executeQuery(sql.toString(), con);
	        return resultSetToCollection(rs);
        } catch (Exception e) {
            throw new FindException(e);
        }
    }

    /**
     * Localiza um certificado pelo proprietario
     * @param id Idenficador do proprietario
     * @param con Conexao que sera usada na pesquisa
     * @return Retorna um Certificate com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
     */
    public ValueObject findByOwner(Integer id, Connection con) throws FindException {
        try {
	        StringBuffer sql = new StringBuffer();
	        sql.append("select id, key, owner");
	        sql.append("  from tb_certificates");
	        sql.append(" where owner = '" + id + "'");
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
        Sequence seq = new Sequence("seq_permissions_certificates");
        Iterator it_deps = ((Certificate)data).getPermissions().iterator();
        while (it_deps.hasNext()) {
            Permission perm = (Permission)it_deps.next();
            StringBuffer sql = new StringBuffer();
	        sql.append("insert into tb_permissions_certificates");
	        sql.append("(id,certificate,permission)");
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
        sql.append("delete from tb_permissions_certificates");
        sql.append(" where certificate = '" + data.getId() + "'");
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
        Certificate vo = new Certificate();
        vo.setId(new Integer(rs.getInt("id")));
        vo.setKey(rs.getString("key"));
        vo.setOwner((User)new UserDAO().findById(new Integer(rs.getInt("owner")), con));
        vo.setPermissions(new PermissionDAO().findByCertificate(vo.getId(), con));
        return vo;
    }

}