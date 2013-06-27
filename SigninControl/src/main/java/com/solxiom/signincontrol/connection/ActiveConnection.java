/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.connection;

/**
 *
 * @author Kavan
 */
import javax.servlet.http.HttpServletRequest;

import org.springframework.social.oauth2.AccessGrant;

public class ActiveConnection {

    private AccessGrant accessGrant;
    private HttpServletRequest redirect_cache;

    public ActiveConnection() {
        this.accessGrant = null;
        this.redirect_cache = null;
    }

    public AccessGrant getAccessGrant() {
        return accessGrant;
    }

    public void setAccessGrant(AccessGrant accessGrant) {
        this.accessGrant = accessGrant;
    }

    public HttpServletRequest getRedirect_cache() {
        return redirect_cache;
    }

    public void setRedirect_cache(HttpServletRequest redirect_cache) {
        this.redirect_cache = redirect_cache;
    }
}
