/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.database.sequence;

import java.sql.Connection;

import com.vh.narch.database.ConnectionManager;

/**
 * Gerencia as sequences
 */
public class Sequence {
    
    private String name;
    
    public Sequence(String name) {
        this.name = name;
    }
    
    /**
     * Obtem o proximo valor da sequence especificada no construtor
     * @return Retorna o proximo valor da sequence especificada
     * @throws Exception caso o valor nao possa ser recuperado
     */
    public Integer nextValue() throws Exception {
        Connection con = ConnectionManager.getConnection();
        SequenceDAO dao = new SequenceDAO();
        SequenceVO seq = (SequenceVO)dao.findByName(name, con);
        Integer value = new Integer(seq.getValue().intValue() + 1);
        seq.setValue(value);
        dao.update(seq, con);
        return value;
    }

}