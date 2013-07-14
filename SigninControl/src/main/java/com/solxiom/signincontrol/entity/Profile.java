/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.entity;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author soleikav
 */
public class Profile {
    
    private String id;
    private String facebook_id;
    private List<Comment> comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.facebook_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profile other = (Profile) obj;
        if (!Objects.equals(this.facebook_id, other.facebook_id)) {
            return false;
        }
        return true;
    }
    
    
}
