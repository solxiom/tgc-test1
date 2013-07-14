/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solxiom.signincontrol.entity.repository;

import com.solxiom.signincontrol.entity.Profile;

/**
 *
 * @author soleikav
 */
public interface ProfileRepository extends AbstractCrudRepositoryInterface<Profile> {
    public Profile loadByFacebookId(String facebookId);

}
