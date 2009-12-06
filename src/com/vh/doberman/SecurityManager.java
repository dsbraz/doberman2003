/*
 * << Doberman >>
 * by VanHelsin 2004
 * GNU General Public License
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.vh.doberman;

import com.vh.doberman.business.CertificateManager;
import com.vh.doberman.business.DomainManager;
import com.vh.doberman.business.PermissionManager;
import com.vh.doberman.business.UserManager;

/**
 * Gerencia os serviços de segurança
 */
public final class SecurityManager {

    public static final UserManager user = UserManager.getInstance();
    public static final PermissionManager permission = PermissionManager.getInstance();
    public static final DomainManager domain = DomainManager.getInstance();
    public static final CertificateManager certificate = CertificateManager.getInstance();

}