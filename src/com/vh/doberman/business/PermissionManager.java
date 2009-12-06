/*
 * << Doberman >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.doberman.business;

import java.sql.Connection;
import java.util.Collection;

import com.vh.doberman.dao.PermissionDAO;
import com.vh.doberman.valueobject.Permission;
import com.vh.narch.business.AbstractBO;
import com.vh.narch.dao.FindException;
import com.vh.narch.valueobject.ValueObject;

/**
 * Implementa um gerente para as açoes
 */
public class PermissionManager extends AbstractBO {

    private static PermissionManager instance;

    protected PermissionManager() {
        super(new PermissionDAO());
    }

    /**
     * @return Retorna a instancia ativa do ActionManager
     */
    public static PermissionManager getInstance() {
        if (instance == null) {
            instance = new PermissionManager();
        }
        return instance;
    }

    /**
     * Cria uma nova açao com o nome e para o modulo especificados
     * @param module O modulo da açao
     * @param name O nome da permissao
     * @return Retorna uma Action contendo os dados especificados
     */
    public Permission newPermission(String name, String module) {
        Permission perm = new Permission();
        perm.setName(name);
        perm.setModule(module);
        return perm;
    }

    /**
     * Localiza uma permissao atraves de um dominio
     * @param id Identificador do dominio
     * @param con Conexao que se usada na pesquisa
     * @return Retorna uma Collection de VOs com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
     */
    public Collection findByDomain(Integer id, Connection con) throws FindException {
        return ((PermissionDAO)getDAO()).findByDomain(id, con);
    }

    /**
     * Localiza uma permissao atraves de um certificado
     * @param id Identificador do certificado
     * @param con Conexao que se usada na pesquisa
     * @return Retorna uma Collection de VOs com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
     */
    public Collection findByCertificate(Integer id, Connection con) throws FindException {
        return ((PermissionDAO)getDAO()).findByCertificate(id, con);
    }

    /**
     * Localiza uma permissao pelo nome
     * @param name O nome da permissao que sera procurada
     * @param con A conexao que sera usada na pesquisa
     * @return Retorna um ValueObject contendo os dados encontrados
     * @throws FindException se a pesquisa nao puder ser efetuada
     */
    public ValueObject findByName(String name, Connection con) throws FindException {
        return ((PermissionDAO)getDAO()).findByName(name, con);
    }

    /**
     * Localiza uma permissao pelo modulo
     * @param module O modulo que sera procurado
     * @param con A conexao que sera usada na pesquisa
     * @return Retorna uma Collection contendo os dados encontrados
     * @throws FindException se a pesquisa nao puder ser efetuada
     */
    public Collection findByModule(String module, Connection con) throws FindException {
        return ((PermissionDAO)getDAO()).findByModule(module, con);
    }

}