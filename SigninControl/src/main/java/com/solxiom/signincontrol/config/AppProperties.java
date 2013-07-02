/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.config;

/**
 *
 * @author soleikav
 */
public class AppProperties {

    private String facebook_client_id = "383805068398211";
    private String facebook_client_secret = "9ec7c4f69f7fcd8abfef4d20dfefb85b";
    private String facebook_app_namespace = "spring_master";
    private String facebook_auth_scope = "friends_likes,user_likes,friends_interests,user_interests,"
            + "user_groups,friends_checkins,user_checkins,"
            + "read_friendlists,publish_stream,email,"
            + "user_about_me,user_website,user_photos,"
            + "friends_photos,user_status,friends_status";
    private String sessionIdKey = "facebook_session_id";
    private String auth_response = "http://www.solxiom.com/spring-master/facebook/connect";

    public String getAuth_response() {
        return auth_response;
    }

    public void setAuth_response(String auth_response) {
        this.auth_response = auth_response;
    }

    public String getSessionIdKey() {
        return sessionIdKey;
    }

    public void setSessionIdKey(String sessionIdKey) {
        this.sessionIdKey = sessionIdKey;
    }

    public String getFacebook_client_id() {
        return facebook_client_id;
    }

    public void setFacebook_client_id(String facebook_client_id) {
        this.facebook_client_id = facebook_client_id;
    }

    public String getFacebook_client_secret() {
        return facebook_client_secret;
    }

    public void setFacebook_client_secret(String facebook_client_secret) {
        this.facebook_client_secret = facebook_client_secret;
    }

    public String getFacebook_app_namespace() {
        return facebook_app_namespace;
    }

    public void setFacebook_app_namespace(String facebook_app_namespace) {
        this.facebook_app_namespace = facebook_app_namespace;
    }

    public String getFacebook_auth_scope() {
        return facebook_auth_scope;
    }

    public void setFacebook_auth_scope(String facebook_auth_scope) {
        this.facebook_auth_scope = facebook_auth_scope;
    }
}
