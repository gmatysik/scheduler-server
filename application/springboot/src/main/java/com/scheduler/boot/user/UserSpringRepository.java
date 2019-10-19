package com.scheduler.boot.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserSpringRepository extends CrudRepository<UserTable, Long> {

    @Query("SELECT u FROM UserTable u WHERE u.name = :name")
    public List<UserTable> findUsersBySearchCriteria(@Param("name") String name);

}
