/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Gerencia as propriedades do sistema
 */
public class SystemProperties {

    /**
     * Recupera e retorna os properties do sistema
     * @return Retorna um Properties com os dados do arquivo
     * @throws IOException em caso de falha na leitura
     */
    public static Properties getSysProperties(String name) throws IOException {
        Properties prop = new Properties();
        prop.load(getResourceAsStream(name));
        return prop;
    }

    /**
     * Recupera o valor de uma propriedade contida no Properties do sistema
     * Obs: A verificacao nos properties do sistema acontece na seguinte ordem:
     * Primeiro eh verificado o narch-map.properties e caso a propriedade 
     * procurada nao seja encontrada eh verificado o narch.properties, ou seja
     * caso a propriedade procurada seja encontrada no narch-map sera esta que
     * sera retornada, mesmo que existe uma com o mesmo nome em narch
     * @param name Nome da propriedade a ser lida
     * @return Retorna uma String com o valor da propriedade
     * @throws IOException Em caso de falha na leitura da propriedade
     */
    public static String getProperty(String name) throws IOException {
        String p = getSysProperties("narch-map.properties").getProperty("narch-map." + name);
        if (p == null) { p = getSysProperties("narch.properties").getProperty("narch." + name); }
        return p;
    }

    /**
     * Localiza o arquivo de properties
     * @return Retorna um InputStream para o arquivo
     */
    private static InputStream getResourceAsStream(String name) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);        
    }

}