package com.cliniconline.platform.model.dao;

import com.cliniconline.platform.model.dto.UserAuthority;

/**
 * Created by bonallure on 2/9/22
 */
public interface UserAuthorityDao {

    void createUserAuthority(UserAuthority userAuthority);

    void deleteUserAuthority(UserAuthority userAuthority);

    void updateUserAuthority(UserAuthority userAuthority);
}
