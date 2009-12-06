/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.valueobject;

import java.util.Map;

/**
 * Interface definidora do pattern ValueObject
 */
public interface ValueObject {    

    /**
     * Obtem o identificador unico do ValueObject
     * @return Um Integer contendo o identificador
     */
	public Integer getId();
	
	/**
	 * Aplica um novo identificador ao ValueObject
	 * @param id Integer contendo o identificador
	 */
	public void setId(Integer id);
	
	/**
	 * Verifica de dois ValueObjects sao iguais (contem o mesmo id)
	 * @param obj ValueObject que sera comparado
	 * @return true caso sejam iguais, false caso contrario
	 */
	public boolean equals(Object obj);

    /**
     * Transforma um ValueObject em um Map
     * Obs: Apenas campos de tipos "simples" sao suportados, os
     * campos do tipo Collection sao ignorados e do tipo ValueObject 
     * sao utilizados como Integer(eh utilizado apenas o identificador 
     * do ValueObject e nao a referencia para ele)
     * @return Retorna um Hashtable contendo os dados do ValueObject
     * @throws Exception caso a transformaçao nao possa ser feita
     */
    public Map toMap() throws Exception;

}