package com.github.bjconlan.pccwglobaldemo.web.rest;

import com.github.bjconlan.pccwglobaldemo.domain.User;
import com.github.bjconlan.pccwglobaldemo.repository.UserRepository;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

/**
 * User resource controller using a generally common ReSTful path/method pattern
 * which follows the details specified within the swagger.json definition
 * provided.
 *
 * *NOTE* The Timed annotation provides timing details of each request used in a
 * running instance of the service. (details of the performance can be seen in
 * on the servers /actuator/metrics path)
 *
 * @since 0.0.1
 */
@RestController
public class UserController {
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Returns a collection of users that have been persisted in the data store.
	 *
	 * @param pageable an optional pageable object for specifying the size & page
	 *                 parameters (defaults to ?page=0&size=20)
	 *                 NOTE that pagables also provide a sort parameter although
	 *                      this is not tested or required by the swagger spec.
	 * @return a collection of users
	 */
	@GetMapping("/users")
	@Timed("web.rest.user.findAll")
	public List<User> findAll(Pageable pageable) {
		return this.userRepository.findAll(pageable).getContent();
	}

	/**
	 * Creates a new user resource.
	 *
	 * @param user the user resource to persist
	 * @return the persisted user data
	 */
	@PutMapping("/users")
	@Timed("web.rest.user.create")
	public User create(@RequestBody @Valid User user) {
		user.setId(null); // Small price to pay for generalising the domain/model
		return this.userRepository.save(user);
	}

	/**
	 * Returns the user resource with the specified user id or HttpStatus.NOT_FOUND
	 * status code if the id specified is not found.
	 *
	 * @param id the id of the user to return
	 * @return An user entity resource (or HttpStatus.NOT_FOUND if the id doesn't
	 *         exist)
	 */
	@GetMapping("/users/{id}")
	@Timed("web.rest.user.findById")
	public User findById(@PathVariable String id) {
		return this.userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	/**
	 * Updates the user data with the specified id. If the user id is not found
	 * a HttpStatus.NOT_FOUND status code will be returned.
	 *
	 * @param id the id of the user to update
	 * @param updatedUser the updated fields to persist to the user resource
	 * @return A user entity resource (or HttpStatus.NOT_FOUND if the id doesn't
	 *         exist)
	 */
	@PostMapping("/users/{id}")
	@Timed("web.rest.user.update")
	public User update(@PathVariable String id, @RequestBody @Valid User updatedUser) {
		User user = this.userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		BeanUtils.copyProperties(updatedUser, user, "id");
		return this.userRepository.save(user);
	}

	/**
	 * Removes the specified user id from the Users resource collection. If the
	 * user is not found a HttpStatus.NOT_FOUND status code will be returned.
	 *
	 * @param id the id of the user to delete
	 * @return The last defined instance of the user with that id before deletion
	 */
	@DeleteMapping("/users/{id}")
	@Timed("web.rest.user.delete")
	public User delete(@PathVariable String id) {
		User user = this.userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		this.userRepository.delete(user);
		return user;
	}
}
