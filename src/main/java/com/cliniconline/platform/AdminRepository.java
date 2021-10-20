package com.cliniconline.platform;

import com.cliniconline.platform.model.dto.Admin;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bonallure on 10/8/21
 */
public interface AdminRepository extends CrudRepository<Admin, Long> {
}
