/*
 * << Doberman >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.doberman.business;

import java.sql.Connection;

import com.vh.doberman.dao.UserDAO;
import com.vh.doberman.valueobject.User;
import com.vh.narch.business.AbstractBO;
import com.vh.narch.dao.FindException;
import com.vh.narch.security.Secure;
import com.vh.narch.security.SecurityException;
import com.vh.narch.valueobject.ValueObject;

/**
 * Implementaçao de um gerente (BO) para os usuarios
 */
public class UserManager extends AbstractBO {

    private static UserManager instance;

    protected UserManager() {
        super(new UserDAO());
    }

    /**
     * @return Retorna a intancia ativa de UserManager
     */
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    /**
     * Cria um novo usuario com o nome e senha especificados
     * @param name O nome do usuario
     * @param passwd A senha do usuario
     * @return Retorna um User com os dados especificados
     */
    public User newUser(String name, String passwd) throws SecurityException {
        User user = new User();
        user.setName(name);
        user.setPassword(Secure.encripty(passwd));
        return user;
    }

    /**
     * Consulta um usuario pelo seu nome
     * @param name O nome do usuario que sera consultado
     * @param con A conexao que se usada na consulta
     * @return Retorna um User com os dados pesquisados
     * @throws FindException em caso de falha na pesquisa
     */
    public ValueObject findByName(String name, Connection con) throws FindException {
        return ((UserDAO)getDAO()).findByName(name, con);
    }

    /**
     * Verifica a autenticidade de um usuario
     * @param user Usuario a ser verificado
     * @param con Conexao que sera utilizada na verificaçao
     * @return Retorna true caso o usuario seja autentico e false caso contrario
     * @throws SecurityException em caso de falha no processo de validaçao
     */
    public boolean checkAuthenticity(User user, Connection con) throws SecurityException {
        try {
            User _usr = (User)findByName(user.getName(), con);
            if (_usr != null) {
    		    String name = (user.getName());
    		    String password = Secure.encripty(user.getPassword());
    		    String _name = _usr.getName();
    		    String _password = _usr.getPassword();
                return name.equals(_name) && password.equals(_password);
            }
            return false;
        }
        catch (Exception e) {
            throw new SecurityException(e);
        }
    }

}