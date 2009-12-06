/*
 * << Doberman >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.doberman.valueobject;

import java.util.ArrayList;
import java.util.Collection;

import com.vh.narch.valueobject.AbstractVO;

/**
 * Implementa um ValueObject para os dominios
 * @see com.vh.narch.valueobject.AbstractVO
 */
public class Domain extends AbstractVO {

    private String name;
    private Collection permissions;
    
    /**
     * @return Retorna o nome do dominio
     */
    public String getName() {
        return name;
    }

    /**
     * @param name O novo nome do dominio
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Retorna as permissoes do dominio
     */
    public Collection getPermissions() {
        if (permissions == null) { permissions = new ArrayList(); }
        return permissions;
    }

    /**
     * @param perms As novas permissoes do dominio
     */
    public void setPermissions(Collection perms) {
        this.permissions = perms;
    }

    /**
     * @param perm Permissao que sera adicionada
     */
    public void addPermission(Permission perm) {
        this.getPermissions().add(perm);
    }

    /**
     * @param perm Permissao que sera removida
     */
    public void removePermission(Permission perm) {
        this.getPermissions().remove(perm);
    }

}