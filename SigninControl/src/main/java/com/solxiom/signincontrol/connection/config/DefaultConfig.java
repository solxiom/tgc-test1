/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.connection.config;

import com.solxiom.signincontrol.config.AppProperties;
import com.solxiom.signincontrol.connection.ConnectionCredentialBuilder;
import com.solxiom.signincontrol.connection.entity.ConnectionCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author soleikav
 */
@Component
public class DefaultConfig implements ConnectionConfig {

    private String client_id;
    private String client_secret;
    private String app_namespace;
    private String auth_scope;
    private String session_cookieKey;
    private String auth_redirect_link;
    private String logout_redirect_link;
    private String default_redirect_path;

    public DefaultConfig() {
        init();
    }

    private void init() {
        AppProperties prop = new AppProperties();
        this.client_id = prop.getFacebook_client_id();
        this.client_secret = prop.getFacebook_client_secret();
        this.app_namespace = prop.getFacebook_app_namespace();
        this.auth_scope = prop.getFacebook_auth_scope();
        this.session_cookieKey = prop.getSessionIdKey();
        this.auth_redirect_link = prop.getAuth_response();
        this.logout_redirect_link = prop.getLogout_redirect_link();
        this.default_redirect_path = prop.getDefault_redirect_path();
    }

    @Override
    public ConnectionCredential buildDefaultConnectionCredential() {
        return new ConnectionCredentialBuilder().setClient_id(client_id)
                .setClient_secret(client_secret)
                .setApp_namespace(app_namespace)
                .setAuth_redirect_link(auth_redirect_link)
                .setAuth_scope(auth_scope)
                .setLogout_redirect_link(logout_redirect_link)
                .setDefault_redirect_path(default_redirect_path).build();
    }

    @Override
    public LoggingPolicy getDefaultLoggingPolicy() {
        return LoggingPolicy.CLEAR_ON_PUBLIC_CALL;
    }
}
