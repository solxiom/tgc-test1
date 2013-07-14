/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.entity;

import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

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
}
