package com.aforo255.microservice.security.model.dao;

import com.aforo255.microservice.security.model.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends PagingAndSortingRepository<Role, Long> {
}
