/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.entity;

import java.util.Objects;
import javax.validation.constraints.Size;

/**
 *
 * @author soleikav
 */
public class Comment {

    private String id;
    @Size(min = 1, max = 800)
    private String text;
    private Profile profile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        String string = "Comment, id=" + id + ", profile-id=" + profile.getId();


        return string;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.text);
        hash = 97 * hash + Objects.hashCode(this.profile);
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
        final Comment other = (Comment) obj;
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.profile, other.profile)) {
            return false;
        }
        return true;
    }
    
}
