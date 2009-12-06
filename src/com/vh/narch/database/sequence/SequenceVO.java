/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.database.sequence;

import com.vh.narch.valueobject.AbstractVO;

/**
 * Implementaão de um VO para as sequences
 */
class SequenceVO extends AbstractVO {

    private String name;
    private Integer value;

    /**
     * @return Retorna o nome da sequence
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name O novo nome da sequence
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return Retorna o valor atual da sequence
     */
    public Integer getValue() {
        return value;
    }
    
    /**
     * @param value O novo valor da sequence
     */
    public void setValue(Integer value) {
        this.value = value;
    }

}