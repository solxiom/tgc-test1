/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.entity;

import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author kavan
 */
public class Score {

    private String id;
    @Min(0)
    @Max(10)
    private Integer stars;
    private Profile profile;

    @Override
    public String toString() {
        return "Score, id=" + id + ", stars=" + stars;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.stars);
        hash = 71 * hash + Objects.hashCode(this.profile);
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
        final Score other = (Score) obj;
        if (!Objects.equals(this.stars, other.stars)) {
            return false;
        }
        if (!Objects.equals(this.profile, other.profile)) {
            return false;
        }
        return true;
    }
    
}
