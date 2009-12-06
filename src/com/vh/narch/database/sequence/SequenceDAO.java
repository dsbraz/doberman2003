/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.database.sequence;

import java.sql.Connection;
import java.sql.ResultSet;

import com.vh.narch.dao.AbstractDAO;
import com.vh.narch.dao.FindException;
import com.vh.narch.valueobject.ValueObject;

/**
 * Implementaçao de um DAO para as sequences
 */
class SequenceDAO extends AbstractDAO {

    public SequenceDAO() {
        super("Sequence");
    }
    
    /**
     * Localiza uma sequence pelo nome
     * @param name O nome da sequence que sera procurada
     * @param con A conexao que sera usada na pesquisa
     * @return Retorna um ValueObject contendo os dados encontrados
     * @throws FindException se a pesquisa nao puder ser efetuada
     */
    public ValueObject findByName(String name, Connection con) throws FindException {
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select id, name, value");
            sql.append("  from sys_sequences");
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
        SequenceVO vo = new SequenceVO();
        vo.setId(new Integer(rs.getInt("id")));
        vo.setName(rs.getString("name"));
        vo.setValue(new Integer(rs.getInt("value")));
        return vo;
    }

}