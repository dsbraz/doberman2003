/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.dao;

import java.sql.Connection;
import java.util.Collection;

import com.vh.narch.valueobject.ValueObject;

/**
 * Interface definidora do pattern DataAccessObject
 */
public interface DataAccessObject {

    /**
     * Localiza todos os objetos da tabela referenciada
     * @param con A conexao que sera usada na pesquisa
     * @return Retorna uma Collection com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
     */
    public Collection find(Connection con) throws FindException;

    /**
     * Localiza um objeto pelo identificador
     * @param id O identificador do objeto
     * @param con A conexao que sera usada na pesquisa
     * @return Retorna um ValueObject com os dados da pesquisa
     * @throws FindException caso a pesquisa nao possa ser feita
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