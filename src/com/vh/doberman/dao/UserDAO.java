/*
 * << Doberman >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.doberman.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.vh.doberman.valueobject.User;
import com.vh.narch.dao.AbstractDAO;
import com.vh.narch.dao.FindException;
import com.vh.narch.valueobject.ValueObject;

/**
 * Implementa um DAO para os usuarios
 */
public class UserDAO extends AbstractDAO {

    public UserDAO() {
        super("User");
    }

    /**
     * Localiza um usuario pelo nome
     * @param name O nome do usuario que sera procurado
     * @param con A conexao que sera usada na pesquisa
     * @return Retorna um ValueObject contendo os dados encontrados
     * @throws FindException se a pesquisa nao puder ser efetuada
     */
    public ValueObject findByName(String name, Connection con) throws FindException {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select id, name, password");
            sql.append("  from tb_users");
            sql.append(" where name = '" + name + "'");
	        ResultSet rs = executeQuery(sql.toString(), con);
	        return resultSetToValueObject(rs);
        } catch (Exception e) {
            throw new FindException(e);
        }
    }

    /**
     * @see com.vh.narch.dao.AbstractDAO#getRowAsValueObject(ResultSet)
     */
    protected ValueObject getRowAsValueObject(ResultSet rs) throws Exception {
        User vo = new User();
        vo.setId(new Integer(rs.getInt("id")));
        vo.setName(rs.getString("name"));
        vo.setPassword(rs.getString("password"));
        return vo;
    }

}