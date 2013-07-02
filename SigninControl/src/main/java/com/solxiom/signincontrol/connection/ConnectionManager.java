/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.connection;

/**
 *
 * @author Kavan Soleimanbeigi
 */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;

public class ConnectionManager {

    private static ConnectionManager instance = null;
    private HashMap<String, ActiveConnection> connection_map;
    private Facebook facebook;
    private List<String> connectionLog;
    private ConnectionCredential credential;

    private ConnectionManager() {
        this.connection_map = new HashMap<String, ActiveConnection>();
        facebook = null;
        credential = null;
        connectionLog = new LinkedList<String>();
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {

            instance = new ConnectionManager();

        }

        return instance;
    }

    public void connect(HttpServletRequest request, HttpServletResponse response)
            throws InvalidConnectionCredentialException {
        if (!this.isConnectionCredentialValid()) {
                throw new InvalidConnectionCredentialException("empty or invalid credential object!");
            }
        
        String auth_code = this.getFacebookAuthorizationCode(request);
        if (auth_code == null || auth_code.isEmpty()) {
            String authurl = this.createAuthUrl();
            try {
                response.sendRedirect(authurl);
            } catch (Exception e) {

                connectionLog.add("ConnectionManager> " + e.toString());
            }
        } else {
            
            AccessGrant accessGrant = this.createFacebookAccessGrant(auth_code);
            this.login(request.getSession().getId(), accessGrant);
        }
    }

    public void setConnectionCredential(ConnectionCredential credential) throws InvalidConnectionCredentialException {
        this.credential = credential;
        if (!this.isConnectionCredentialValid()) {
            throw new InvalidConnectionCredentialException("empty or invalid credential object! after set the parameter client_id" + credential.getClient_id());
        }

    }

    public ConnectionCredential getConnectionCredential() {

        return this.credential;
    }

    public List<String> getConnectionLog() {
        if (this.connectionLog == null) {
            return new LinkedList<String>();
        }
        return this.connectionLog;
    }

    public Facebook getFacebook() {
        return this.facebook;
    }

    public AccessGrant getSessionFacebookAccessGrant(String session_id) {
        ActiveConnection conn = this.connection_map.get(session_id);
        if (conn != null && conn.getAccessGrant() != null) {
            return conn.getAccessGrant();
        }
        return null;
    }

    public void cacheThisRequest(HttpServletRequest request) {
        String session_id = request.getSession().getId();
        ActiveConnection conn = this.connection_map.get(session_id);
        if (conn == null) {
            conn = new ActiveConnection();
        }
        conn.setCachedrequest(request);
        this.connection_map.put(session_id, conn);
    }

    public boolean isRedirectNeeded(String session_id) {
        ActiveConnection conn = this.connection_map.get(session_id);
        return this.redirectElementsAvalaible(conn);
    }

    public CachedRequest retrieveCachedRequest(String session_id) {
        ActiveConnection conn = this.connection_map.get(session_id);
        if (conn != null) {
            CachedRequest cache = conn.getCachedRequest();
            conn.setCachedrequest(null);
            return cache;
        }
        return null;
    }

    /**
     * demo
     *
     */
    public void redirectToOriginal(HttpServletRequest req,
            HttpServletResponse response, List<String> logList) {

        ActiveConnection conn = this.connection_map.get(req.getSession()
                .getId());

        if (this.redirectElementsAvalaible(conn)) {
            CachedRequest cache = conn.getCachedRequest();

            try {
                response.sendRedirect(cache.getRequestURL() + "?"
                        + cache.getQuerryString());
            } catch (Exception e) {
                logList.add("redirect to original page failed, cause: "
                        + e.toString());
            }
        }
    }

    public void logout(String session_id) {
        if (session_id == null) {
            return;
        }
        ActiveConnection conn = this.connection_map.get(session_id);
        if (conn != null) {
            conn.setAccessGrant(null);
        }

    }

    public boolean isUserLoggedIn(String session_id) {
        if (session_id == null || this.facebook == null
                || !this.facebook.isAuthorized()) {
            return false;
        }
        ActiveConnection conn = this.connection_map.get(session_id);
        if (conn != null && conn.getAccessGrant() != null) {
            return true;
        }
        return false;
    }

    private AccessGrant createFacebookAccessGrant(String auth_code) {
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(
                this.credential.getClient_id(),
                this.credential.getClient_secret());
        OAuth2Operations oauth2 = connectionFactory.getOAuthOperations();
        AccessGrant accessGrant = oauth2.exchangeForAccess(auth_code,
                this.credential.getAuth_redirect_link(), null);
        return accessGrant;
    }

    private void login(String session_id, AccessGrant accessGrant) {

        ActiveConnection conn = this.connection_map.get(session_id);
        if (conn == null) {
            conn = new ActiveConnection();
        }

        conn.setAccessGrant(accessGrant);
        this.connection_map.put(session_id, conn);
        setUpFacebookApi(session_id, this.credential.getClient_id(),
                this.credential.getClient_secret());
    }

    private boolean redirectElementsAvalaible(ActiveConnection conn) {
        if (conn != null && conn.getCachedRequest() != null) {
            return true;
        }
        return false;
    }

    private void setUpFacebookApi(String session_id, String client_id,
            String client_secret) {
        if (session_id == null || (client_id == null || client_id.isEmpty())
                || (client_secret.isEmpty() || client_secret.isEmpty())) {
            return;
        }
        ActiveConnection conn = this.connection_map.get(session_id);
        if (conn == null || conn.getAccessGrant() == null) {
            return;
        }

        AccessGrant accessGrant = conn.getAccessGrant();

        Connection<Facebook> connection = new FacebookConnectionFactory(
                client_id, client_secret).createConnection(accessGrant);
        this.facebook = connection.getApi();
    }

    private String getFacebookAuthorizationCode(HttpServletRequest req) {

        if (req.getParameter("error") == null
                && req.getParameter("code") != null) {

            this.connectionLog.add("login ok!");

            return req.getParameter("code");

        } else {
            this.connectionLog.add("error: " + req.getParameter("error"));
            this.connectionLog.add("error_code: " + req.getParameter("error_code"));
            this.connectionLog.add("error_description: "
                    + req.getParameter("error_description"));
            this.connectionLog.add("error_reason: " + req.getParameter("error_reason"));
            return null;
        }
    }

    private String createAuthUrl() {
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(
                this.credential.getClient_id(),
                this.credential.getClient_secret());

        OAuth2Operations oauthOperations = connectionFactory
                .getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.add("scope", this.credential.getAuth_scope());
        params.setRedirectUri(this.credential.getAuth_redirect_link());
        String authorizeUrl = oauthOperations.buildAuthorizeUrl(
                GrantType.AUTHORIZATION_CODE, params);

        return authorizeUrl;
    }

    private boolean isConnectionCredentialValid() {
        if (this.credential == null) {
            return false;
        }
        if (this.credential.getClient_id() == null || this.credential.getClient_id().isEmpty()) {
            return false;
        }
        if (this.credential.getClient_secret() == null || this.credential.getClient_secret().isEmpty()) {
            return false;
        }
        if (this.credential.getApp_namespace() == null || this.credential.getApp_namespace().isEmpty()) {
            return false;
        }
        if (this.credential.getAuth_redirect_link() == null || this.credential.getAuth_redirect_link().isEmpty()) {
            return false;
        }
        if (this.credential.getAuth_scope() == null || this.credential.getAuth_scope().isEmpty()) {
            return false;
        }

        return true;
    }
}
