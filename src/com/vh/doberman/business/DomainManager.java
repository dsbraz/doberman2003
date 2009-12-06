/*
 * << Doberman >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.doberman.business;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.vh.doberman.dao.DomainDAO;
import com.vh.doberman.valueobject.Domain;
import com.vh.narch.business.AbstractBO;
import com.vh.narch.dao.FindException;
import com.vh.narch.valueobject.ValueObject;

/**
 * Implementa um gerente para os dominios
 */
public class DomainManager extends AbstractBO {

    private static DomainManager instance;

    protected DomainManager() {
        super(new DomainDAO());
    }

    /**
     * @return Retorna a instancia ativa do DomainManager
     */
    public static DomainManager getInstance() {
        if (instance == null) {
            instance = new DomainManager();
        }
        return instance;
    }

    /**
     * Cria um novo dominio com o nome e as permissoes especificadas
     * @param name O nome do dominio
     * @param permissions As permissoes do dominio
     * @return Retorna um Domain contendo os dados especificados
     */
    public Domain newDomain(String name, Collection permissions) {
        Domain domain = new Domain();
        domain.setName(name);
        domain.setPermissions(permissions); 
        return domain;
    }

    /**
     * Localiza um dominio atraves de uma permissao
     * @param id Identificador da permissao
     * @param con Conexao que se usada na pesquisa
     * @return Retorna uma Collection de VOs com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
     */
    public Collection findByPermission(Integer id, Connection con) throws FindException {
        return ((DomainDAO)getDAO()).findByPermission(id, con);
    }

    /**
     * Localiza os dominios que contem todas as permissoes referenciadas
     * @param perms Permissoes que serao utilizadas na consulta
     * @param con Conexao que sera utilizada na pesquisa
     * @return Retorna uma Collection de VOs(Domains) com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
     */
    public Collection findByPermissions(Collection perms, Connection con) throws FindException {
        Collection domains = new ArrayList();
        Iterator it = find(con).iterator();
        while (it.hasNext()) {
             Domain d = (Domain)it.next();
             Collection c = d.getPermissions();
             if (perms.containsAll(c)) {
                 domains.add(d);
             }
        }
        return domains;
    }

    /**
     * Localiza um dominio pelo nome
     * @param name O nome do dominio que sera procurado
     * @param con A conexao que sera usada na pesquisa
     * @return Retorna um ValueObject contendo os dados encontrados
     * @throws FindException se a pesquisa nao puder ser efetuada
     */
    public ValueObject findByName(String name, Connection con) throws FindException {
        return ((DomainDAO)getDAO()).findByName(name, con);
    }

}