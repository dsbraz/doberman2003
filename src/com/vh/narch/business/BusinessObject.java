/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.business;

import java.sql.Connection;
import java.util.Collection;

import com.vh.narch.dao.DeleteException;
import com.vh.narch.dao.FindException;
import com.vh.narch.dao.InsertException;
import com.vh.narch.dao.UpdateException;
import com.vh.narch.valueobject.ValueObject;

/**
 * Interface declarativa para os objetos de negocio
 */
public interface BusinessObject {
    
    /**
     * Localiza todos os objetos da tabela referenciada
     * @param con Conexao que sera usada na consulta
     * @return Retorna uma Collection contendo os dados da pesquisa
     * @throws FindException em caso de falha na consulta
     */
    public Collection find(Connection con) throws FindException;
    
    /**
     * Localiza um objeto atraves de seu identificador
     * @param id Idenficador do objeto
     * @param con Conexao que sera usada na consulta
     * @return Retorna um ValueObject contendo os dados encontrados
     * @throws FindException caso a procura nao possa ser efetuada
     */
    public ValueObject findById(Integer id, Connection con) throws FindException;
    
    /**
     * Grava os dados do ValueObject no banco de dados
     * @param data ValueObject contendo os dados a serem gravados
     * @param con Conexao que sera usada na operaçao
     * @throws InsertException em caso de falha na gravaçao
     */
    public void insert(ValueObject data, Connection con) throws InsertException;

    /**
     * Altera os dados gravadosno bando de dados 
     * @param data Os dados antigos (antes da alteraçao)
     * @param con Conexao que sera usada na operaçao 
     * @throws UpdateException em caso de falha na gravaçao
     */
    public void update(ValueObject data, Connection con) throws UpdateException;

    /**
     * Exclui os dados do banco de dados
     * @param data Os dados a serem excluidos
     * @param con Conexao que sera usada na operaçao
     * @throws DeleteException em caso de falha na deleçao
     */
    public void delete(ValueObject data, Connection con) throws DeleteException;

}