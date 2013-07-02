package com.solxiom.signincontrol.connection;

import com.solxiom.signincontrol.config.AppProperties;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @author: Kavan Soleimanbeigi
 */
@Controller
public class ConnectionController {

    private ConnectionManager cmanager;
    //** start copy pastable
    private String login_check_path = "/check-login";
    private String facebook_connect_path = "/facebook/connect";
    private String response_redirect_to_login_path = "/tagcloud/login";

    public ConnectionController() {
        setConnectionProperties();//spring-master line only should be excluded from copy paste
        cmanager = ConnectionManager.getInstance();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String authTest(HttpServletRequest request, HttpServletResponse response) {


        if (cmanager.isUserLoggedIn(request.getSession().getId())) {
            return "redirect:" + login_check_path;
        }
        return "redirect:" + facebook_connect_path;
    }

    @RequestMapping(value = "/facebook/connect", method = RequestMethod.GET)
    public @ResponseBody
    List<String> FacebookConnect(HttpServletRequest request,
            HttpServletResponse response) {

        List<String> logList = new LinkedList<String>();
        try {
            ConnectionCredential coc = this
                    .buildDefaultConnectionCredential();
            cmanager.setConnectionCredential(coc);
        } catch (Exception ex) {
            logList.add("Connection failed >  invalid or null ConnectionCredential");
        }
        try {
            cmanager.connect(request, response);
        } catch (Exception e) {
            logList.add("connection failed >  " + e.toString());
        }
        logList.add("Facebook login session created successfully!");
        try {
            if (cmanager.isRedirectNeeded(request.getSession().getId())) {
                cmanager.redirectToOriginal(request, response, logList);
            }

        } catch (Exception e) {
            logList.add("redirect to redx failed " + e.toString());

        }

        return logList;
    }

    @RequestMapping(value = "/check-login", method = RequestMethod.GET)
    public @ResponseBody
    List<String> loginTest(HttpServletRequest req) {
        List<String> logList = new LinkedList<String>();
        if (cmanager.isUserLoggedIn(req.getSession().getId())) {
            logList.add("you're logged in");
        } else {
            logList.add("you're not logged in");
        }

        return logList;
    }

    @RequestMapping(value = "/redx", method = RequestMethod.GET)
    public @ResponseBody
    List<String> redTest(HttpServletRequest req, HttpServletResponse response) {
        List<String> logList = new LinkedList<String>();

        if (cmanager.isUserLoggedIn(req.getSession().getId())) {
            CachedRequest cache = cmanager.retrieveCachedRequest(req
                    .getSession().getId());
            logList.add("now, let's do our thing!");

            if (cache != null) {

                logList.add("your cached query string: "
                        + cache.getQuerryString());
                logList.add("your cached uri was: " + cache.getRequestURI());
                logList.add("your cached url was: " + cache.getRequestURL());

            } else {

                logList.add("your query string was: " + req.getQueryString());
                logList.add("your uri was: " + req.getRequestURI());
                logList.add("your url was: " + req.getRequestURL());
            }

        } else {
            try {
                cmanager.cacheThisRequest(req);
                response.sendRedirect(response_redirect_to_login_path);
            } catch (Exception ex) {
                logList.add("redirect to login page failed!");
            }
        }

        return logList;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String doLogout(HttpServletRequest req) {
        cmanager.logout(req.getSession().getId());
        return "redirect:/check-login";
    }

    @RequestMapping(value = "/update-status", method = RequestMethod.GET)
    public @ResponseBody
    List<String> postToWall(HttpServletRequest request,
            HttpServletResponse response) {
        String message = request.getParameter("message");
        List<String> logList = new LinkedList<String>();
        if (cmanager.isUserLoggedIn(request.getSession().getId())) {
            try {
                Facebook facebook = cmanager.getFacebook();
                if (facebook != null && message != null && !message.isEmpty()) {
                    facebook.feedOperations().updateStatus(message);

                    logList.add(" Your status update, " + message
                            + " posted to your facbook wall successfully!");
                } else {
                    logList.add("status update message should not be null or empty");
                }

            } catch (Exception e) {
                logList.add(e.toString());
            }
        } else {
            cmanager.cacheThisRequest(request);
            try {
                response.sendRedirect("/tagcloud/fb-login");
            } catch (Exception e) {
                logList.add("sorry, rquired login failed!");
            }
            // logList.add("sorry, you should login first");
        }
        return logList;
    }

    public ConnectionCredential buildDefaultConnectionCredential() {
        return new ConnectionCredentialBuilder().setClient_id(client_id)
                .setClient_secret(client_secret)
                .setApp_namespace(app_namespace)
                .setAuth_redirect_link(auth_redirect_link)
                .setAuth_scope(auth_scope).build();
    }
    //** end copy pastable
    //********************* start spring-master local
    private String client_id;
    private String client_secret;
    private String app_namespace;
    private String auth_scope;
    private String session_cookieKey;
    private String auth_redirect_link;

    private void setConnectionProperties() {
        AppProperties prop = new AppProperties();
        this.client_id = prop.getFacebook_client_id();
        this.client_secret = prop.getFacebook_client_secret();
        this.app_namespace = prop.getFacebook_app_namespace();
        this.auth_scope = prop.getFacebook_auth_scope();
        this.session_cookieKey = prop.getSessionIdKey();
        this.auth_redirect_link = prop.getAuth_response();
        this.response_redirect_to_login_path = "/spring-master/login";
    }
//************************* end spring-master local
}
