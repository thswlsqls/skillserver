package com.ebson.skillserver.repository;

import com.ebson.skillserver.domain.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestUserJPARepository extends JpaRepository<TestUser, UUID> {

}
