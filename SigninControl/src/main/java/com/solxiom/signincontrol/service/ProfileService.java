/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.service;

import com.solxiom.signincontrol.entity.Profile;

/**
 *
 * @author kavan
 */
public interface ProfileService extends AbstractCrudService<Profile> {

    Profile loadByFacebookId(String facebookId);
    
    Profile createByFacebookId(String facebookId);
}
