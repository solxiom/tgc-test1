/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.connection;


/**
 *
 * @author Kavan Soleimanbeigi
 */
import javax.servlet.http.HttpServletRequest;

import org.springframework.social.oauth2.AccessGrant;

public class ActiveConnection {

	private AccessGrant accessGrant;
	private CachedRequest cahcedRequest;

	public ActiveConnection() {
		this.accessGrant = null;
		this.cahcedRequest = null;

	}

	public AccessGrant getAccessGrant() {
		return accessGrant;
	}

	public void setAccessGrant(AccessGrant accessGrant) {
		this.accessGrant = accessGrant;
	}

	public CachedRequest getCachedRequest() {
		return cahcedRequest;
	}

	public void setCachedrequest(HttpServletRequest request) {
		if (request != null) {
			this.cahcedRequest = new CachedRequestBuilder()
					.buildCachedRequestFromRequest(request);
		} else {
			this.cahcedRequest = null;
		}
	}

}
