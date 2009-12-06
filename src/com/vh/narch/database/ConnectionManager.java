/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.vh.narch.util.SystemProperties;

/**
 * Gerencia as conexoes com o banco de dados
 */
public class ConnectionManager {

    /**
     * Obtem um conexao JDBC
     * @return Retorna uma connection jdbc padrao
     */
    public static Connection getConnection() throws ConnectionException {
        try {
            String driver = SystemProperties.getProperty("con.driver");
            String database = SystemProperties.getProperty("con.database");
            String user = SystemProperties.getProperty("con.user");
            String password = SystemProperties.getProperty("con.password");
            Class.forName(driver);
            return DriverManager.getConnection(database, user, password);
        } catch (Exception e) {
            throw new ConnectionException(e);
        }
    }

    /**
     * Obtem um conexao JDBC
     * @param autoCommit false para conexao transacional e true caso contrario
     * @return Retorna uma connection jdbc padrao
     */
    public static Connection getConnection(boolean autoCommit) throws ConnectionException {
        try {
            Connection con = getConnection();
            con.setAutoCommit(autoCommit);
            return con;
        } catch (Exception e) {
            throw new ConnectionException(e);
        }
    }

    /**
     * Realiza o commit na transacao
     * @param con Connection que sera utilizada
     * @throws ConnectionException em caso de falha na confirmacao
     */
    public static void commit(Connection con) throws ConnectionException {
        try {
            con.commit();
        } catch (Exception e) {
            throw new ConnectionException(e);
        }
    }

    /**
     * Libera a conexao JDBC
     * @param con Conexao que sera liberada
     * @throws ConnectionException em caso de falha no fechamento
     */
    public static void release(Connection con) throws ConnectionException {
        try {
            con.close();
        } catch (Exception e) {
            throw new ConnectionException(e);
        }
    }

}