package com.cliniconline.platform.model.dao.impl;

import com.cliniconline.platform.model.dao.UserAuthorityDao;
import com.cliniconline.platform.model.dto.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by bonallure on 2/9/22
 */
@Repository
public class UserAuthorityDaoImpl implements UserAuthorityDao {

    private static final String INSERT_USER_SQL = "insert into users (username, password, enabled) values (?, ?, ?)";

    private static final String INSERT_AUTHORITY_SQL = "insert into authorities (username, authority) values (?, ?)";

    private static final String DELETE_USER_SQL = "delete from users where username = ?";

    private static final String DELETE_AUTHORITY_SQL = "delete from authorities where username = ?";

    private static final String UPDATE_USER_SQL = "update users set password = ?, enabled = ? where username = ?";

    // jdbctemplate
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserAuthorityDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createUserAuthority(UserAuthority userAuthority) {
        jdbcTemplate.update(INSERT_USER_SQL,
                userAuthority.getUsername(),
                userAuthority.getPassword(),
                userAuthority.isEnabled());

        jdbcTemplate.update(INSERT_AUTHORITY_SQL,
                userAuthority.getUsername(),
                userAuthority.getRole());
    }

    @Override
    public void deleteUserAuthority(UserAuthority userAuthority) {
        jdbcTemplate.update(DELETE_AUTHORITY_SQL, userAuthority.getUsername());
        jdbcTemplate.update(DELETE_USER_SQL, userAuthority.getUsername());
    }

    @Override
    public void updateUserAuthority(UserAuthority userAuthority) {
        jdbcTemplate.update(UPDATE_USER_SQL,
                userAuthority.getPassword(),
                userAuthority.isEnabled(),
                userAuthority.getUsername());
    }
}
