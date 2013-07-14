package org.openinfinity.tagcloud.domain.service;

import org.openinfinity.tagcloud.domain.entity.Profile;

public interface ProfileService extends AbstractCrudServiceInterface<Profile> {

    Profile loadByFacebookId(String facebookId);
    
    Profile createByFacebookId(String facebookId);
}