/*
 * << Doberman >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.doberman.valueobject;

import com.vh.narch.valueobject.AbstractVO;

/**
 * Implementa um ValueObject para as açoes
 * @see com.vh.narch.valueobject.AbstractVO
 */
public class Permission extends AbstractVO {

    private String name;
    private String module;

    /**
     * @return Retorna o nome da permissao
     */
    public String getName() {
        return name;
    }

    /**
     * @param name O novo nome da permissao
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Retorna o modulo da permissao
     */
    public String getModule() {
        return module;
    }

    /**
     * @param mod O novo modulo da permissao
     */
    public void setModule(String mod) {
        this.module = mod;
    }

}