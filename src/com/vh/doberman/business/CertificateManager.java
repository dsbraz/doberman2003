/*
 * << Doberman >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.doberman.business;

import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;

import com.vh.doberman.dao.CertificateDAO;
import com.vh.doberman.valueobject.Permission;
import com.vh.doberman.valueobject.Certificate;
import com.vh.doberman.valueobject.Domain;
import com.vh.doberman.valueobject.User;
import com.vh.narch.business.AbstractBO;
import com.vh.narch.business.BusinessException;
import com.vh.narch.dao.FindException;
import com.vh.narch.security.Secure;
import com.vh.narch.security.SecurityException;
import com.vh.narch.valueobject.ValueObject;

/**
 * Implementa um gerente para os certificados
 */
public class CertificateManager extends AbstractBO {

    private static CertificateManager instance;
    private static final String CERT_KEY = "doberman";

    protected CertificateManager() {
        super(new CertificateDAO());
    }

    /**
     * @return Retorna a instancia ativa do CertificateManager
     */
    public static CertificateManager getInstance() {
        if (instance == null) {
            instance = new CertificateManager();
        }
        return instance;
    }

    /**
     * Cria um novo certificado ja ativado para o proprietario 
     * e contendo o dominio especificado
     * @param owner O proprietario do certificado
     * @param domain O dominio que sera incluido no certificado
     * @return Retorna um Certificate contendo os dados especificados
     * @throws SecurityException em caso de falha na criaçao do certificado
     */
    public Certificate newCertificate(User owner, Domain domain) throws SecurityException {
        Certificate cert = new Certificate();
        cert.setOwner(owner);
        cert.setPermissions(domain.getPermissions());
        active(cert);
        return cert;
    }

    /**
     * Localiza um certificado atraves de uma açao
     * @param id Identificador da açao
     * @param con Conexao que se usada na pesquisa
     * @return Retorna uma Collection de VOs com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
     */
    public Collection findByPermission(Integer id, Connection con) throws FindException {
        return ((CertificateDAO)getDAO()).findByPermission(id, con);
    }

    /**
     * Localiza um certificado pelo proprietario
     * @param id Idenficador do proprietario
     * @param con Conexao que sera usada na pesquisa
     * @return Retorna um Certificate com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
     */
    public ValueObject findByOwner(Integer id, Connection con) throws FindException {
        return ((CertificateDAO)getDAO()).findByOwner(id, con);
    }

    /**
     * Ativa um certificado. i.e. Torna o certificado valido para o sistema
     * @param cert Certificado que sera ativado
     * @throws SecurityException em caso de falha no processo de ativaçao
     */
    public void active(Certificate cert) throws SecurityException {
        cert.setKey(Secure.encripty(cert.getOwner().getId() + CERT_KEY));
    }

    /**
     * Carrega o certificado de acordo com o nome usuario especificado
     * @param owner Nome do usuario (proprietario do certificado)
     * @param con Conexao que sera usada na consulta
     * @return Retorna um Certificate contendo os certificados do usuario
     * @throws BusinessException em caso de falha no processo de carregamento
     */
    public ValueObject load(String owner, Connection con) throws BusinessException {
        try {
            ValueObject result = null;
            UserManager userMan = UserManager.getInstance();
            User user = (User)userMan.findByName(owner, con);
            if (user != null) { result = findByOwner(user.getId(), con); }
            return result;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * Adiciona uma permissao ao certificado especificado
     * @param cert O certificado ao qual a açao sera adicionada
     * @param perm A permissao que se adicionada
     */
    public void addPermission(Certificate cert, Permission perm) {
        cert.getPermissions().add(perm);
    }

    /**
     * Adiciona um dominio ao certificado especificado
     * @param cert Certificado ao qual o dominio sera adicionado
     * @param domain Dominio que sera adicionado
     */
    public void addDomain(Certificate cert, Domain domain) {
        cert.getPermissions().addAll(domain.getPermissions());
    }

    /**
     * Verifica a autenticidade de um certificado
     * @param cert Certificado que sera verificado
     * @return Retorna true caso o certificado sera validade e false caso contrario
     * @throws SecurityException em caso de falha no processo de autenticaçao
     */
    public boolean checkAuthenticity(Certificate cert) throws SecurityException {
        String k1 = cert.getKey();
        String k2 = Secure.encripty(cert.getOwner().getId() + CERT_KEY);
        return k1.equals(k2);
    }

    /**
     * Verifica se a permissao especificada pertence ao certificado especificado Obs:
     * A verificaçao eh feita atraves do nome e modulo da açao
     * @param cert O certificado a ser verificado
     * @param perm A permissao a ser verificada
     * @return Retorna true caso a açao pertenca ao certificado e false caso contrario
     * @throws BusinessException em caso de falha na verificaçao
     */
    public boolean checkPermission(Certificate cert, Permission perm) throws BusinessException {
        try {
            if (checkAuthenticity(cert)) {
                Iterator it = cert.getPermissions().iterator();
                while (it.hasNext()) {
                    Permission p = (Permission)it.next();
                    if (p.getModule().equals(perm.getModule())
                            || p.getName().equals(perm.getName())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Verifica se o dominio especificado pertence ao certificado especificado
     * @param cert Certificado que sera verificado
     * @param domain Dominio que sera verificado
     * @return Retorna true caso o dominio pertença ao certificado e false caso contrario
     * @throws BusinessException em caso de falha na verificaçao
     */
    public boolean checkPermission(Certificate cert, Domain domain) throws BusinessException {
        boolean result = false;
        Iterator it = domain.getPermissions().iterator();
        while (it.hasNext()) {
            result = checkPermission(cert, (Permission)it.next());
        }
        return result;
    }

}