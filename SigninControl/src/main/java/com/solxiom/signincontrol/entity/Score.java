/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.entity;

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
}
