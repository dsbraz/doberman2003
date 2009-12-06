/*
 * << Narch >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.narch.security;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

import com.vh.narch.util.SystemProperties;

/**
 * Implementa a segurança do sistema
 */
public final class Secure {

    /**
     * Aplica a criptografia hash
     * @param arg Os dados que serao criptografados
     * @return Retorna os dados criptografados
     * @throws SecurityException em caso de falha na criptografia
     */
    public static String encripty(String arg) throws SecurityException {
        try {
			MessageDigest md = null;
			BASE64Encoder enc = new BASE64Encoder();
			String algorithm = SystemProperties.getProperty("sec.algorithm");
			md = MessageDigest.getInstance(algorithm);
			md.update(arg.getBytes());
			return enc.encode(md.digest());
        } catch (Exception e) {
            throw new SecurityException(e);
        }
    }

}