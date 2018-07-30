package com.github.bjconlan.pccwglobaldemo.repository;

import com.github.bjconlan.pccwglobaldemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The user repository providing data access functionality to our underlying
 * data store.
 *
 * @since 0.0.1
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
