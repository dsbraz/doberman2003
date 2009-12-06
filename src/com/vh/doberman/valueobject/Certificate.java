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
 * Implementa um ValueObject para os certificados
 * @see com.vh.narch.valueobject.AbstractVO
 */
public class Certificate extends AbstractVO {
    
    private String key;
    private User owner;
    private Collection permissions;

    /**
     * @return Retorna a chave do certificado
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key A nova chave do certificado
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return Retorna o proprietario do certificado
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @param owner O novo proprietario do certificado
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * @return Retorna as permissoes do certificado
     */
    public Collection getPermissions() {
        if (permissions == null) { permissions = new ArrayList(); }
        return permissions;
    }

    /**
     * @param perms As novas permissoes do certificado
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