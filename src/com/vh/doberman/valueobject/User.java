/*
 * << Doberman >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.doberman.valueobject;

import com.vh.narch.valueobject.AbstractVO;

/**
 * Implementa um ValueObject para os usuarios
 * @see com.vh.narch.valueobject.AbstractVO
 */
public class User extends AbstractVO {
    
    private String name;
    private String password;
    
    /**
     * @return Retorna o nome do usuario
     */
    public String getName() {
        return name;
    }

    /**
     * @param name O novo nome do usuario
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Retorna o password do usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password O novo password do usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

}