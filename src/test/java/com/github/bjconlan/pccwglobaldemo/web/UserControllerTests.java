package com.github.bjconlan.pccwglobaldemo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bjconlan.pccwglobaldemo.domain.User;
import com.github.bjconlan.pccwglobaldemo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * A number of CRUD operation tests to validate the defined swagger.json profile.
 *
 * These are generally simple tests which attempt to execute code paths defined
 * in the UserController (and ExceptionAdvice).
 *
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Validate for get users with and without data and size/page parameters
	 *
	 * - Tests without data (without parameters)
	 * - Tests without data (with size=3&page=1 parameters)
	 * - Tests with data (10 users) (without parameters)
	 * - Tests with data (10 users) (with size=3&page=1 parameters)
	 *
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void findAll() throws Exception {
		this.mockMvc.perform(get("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", empty()));

		this.mockMvc.perform(get("/users")
				.param("size", "3")
				.param("page", "1")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", empty()));

		List<User> users = userRepository.saveAll(
				Stream.generate(UserControllerTests::generateUser)
						.limit(10)
						.collect(Collectors.toList()));

		this.mockMvc.perform(get("/users")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(users.size())))
				.andExpect(jsonPath("$[0].id", equalTo(users.get(0).getId())))
				.andExpect(jsonPath("$[9].id", equalTo(users.get(9).getId())));

		this.mockMvc.perform(get("/users")
				.param("size", "3")
				.param("page", "1")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].id", equalTo(users.get(3).getId())))
				.andExpect(jsonPath("$[2].id", equalTo(users.get(5).getId())));
	}

	/**
	 * Assert that the specified 'CreateUserRequest' definition is adhered to to
	 * and if not return the appropriate '400 - Bad Request' http status code.
	 *
	 * Assert that all fields specified in the 'CreateUserRequest' are in-fact
	 * persisted and appropriate generated fields populated and returned with
	 * the provided data.
	 *
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void create() throws Exception {
		this.mockMvc.perform(put("/users").content(objectMapper.writeValueAsBytes(new User()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest());

		User user = generateUser();
		this.mockMvc.perform(put("/users").content(objectMapper.writeValueAsBytes(user))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", equalTo(user.getName())))
				.andExpect(jsonPath("username", equalTo(user.getUsername())))
				.andExpect(jsonPath("email", equalTo(user.getEmail())))
				.andExpect(jsonPath("password", equalTo(user.getPassword())))
				.andExpect(jsonPath("id", not(isEmptyString())));
	}

	/**
	 * Assert that when attempting to update an invalid ID a '404 - Not Found'
	 * http status code is returned.
	 *
	 * Assert that the specified 'UpdateUserRequest' definition is adhered to to
	 * and if not return the appropriate '400 - Bad Request' http status code.
	 *
	 * Assert that all fields specified in the 'UpdateUserRequest' are in-fact
	 * persisted.
	 *
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void update() throws Exception {
		this.mockMvc.perform(post("/users/{id}", "invalid")
				.content(objectMapper.writeValueAsBytes(generateUser()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());

		User user = userRepository.save(generateUser());
		this.mockMvc.perform(post("/users/{id}", user.getId())
				.content(objectMapper.writeValueAsBytes(new User()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isBadRequest());

		User updatedUser = generateUser();
		this.mockMvc.perform(post("/users/{id}", user.getId())
				.content(objectMapper.writeValueAsBytes(updatedUser))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", equalTo(updatedUser.getName())))
				.andExpect(jsonPath("username", equalTo(updatedUser.getUsername())))
				.andExpect(jsonPath("email", equalTo(updatedUser.getEmail())))
				.andExpect(jsonPath("password", equalTo(updatedUser.getPassword())))
				.andExpect(jsonPath("id", equalTo(user.getId())));
	}

	/**
	 * Assert that when attempting to get an invalid ID a '404 - Not Found'
	 * http status code is returned.
	 *
	 * Assert that when a valid ID is specified the correct user resource is
	 * returned with a status code of '200 - OK'
	 *
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void findById() throws Exception {
		this.mockMvc.perform(get("/users/{id}", "invalidId")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());

		User user = userRepository.save(generateUser());
		this.mockMvc.perform(get("/users/{id}", user.getId())
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", equalTo(user.getName())))
				.andExpect(jsonPath("username", equalTo(user.getUsername())))
				.andExpect(jsonPath("email", equalTo(user.getEmail())))
				.andExpect(jsonPath("password", equalTo(user.getPassword())))
				.andExpect(jsonPath("id", equalTo(user.getId())));

	}

	/**
	 * Assert that when attempting to delete an invalid ID a '404 - Not Found'
	 * http status code is returned.
	 *
	 * Assert that when a valid ID is specified the correct user resource is
	 * returned with a status code of '200 - OK' and the resource is no longer
	 * held within the collection.
	 *
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void delete() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", "invalidId")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());

		User user = userRepository.save(generateUser());
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", user.getId())
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("name", equalTo(user.getName())))
				.andExpect(jsonPath("username", equalTo(user.getUsername())))
				.andExpect(jsonPath("email", equalTo(user.getEmail())))
				.andExpect(jsonPath("password", equalTo(user.getPassword())))
				.andExpect(jsonPath("id", equalTo(user.getId())));

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", user.getId())
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());
	}

	/**
	 * Randomly generates a name in the format of "{$firstname} ${lastname} from
	 * a predefined list of names.
	 *
	 * @return A randomized pair of names separated by a space.
	 */
	private static String randomName() {
		List<String> names = Arrays.asList("John", "Liz", "Joe", "Jesse", "Kim", "Susan", "Ben", "Ian");
		int namesSize = names.size();
		Random random = new Random();

		return names.get(random.nextInt(namesSize)) + " " + names.get(random.nextInt(namesSize));
	}

	/**
	 * Creates a populated User object (sans id) using a randomly generated name.
	 *
	 * @return a populated User object
	 */
	private static User generateUser() {
		User user = new User();
		user.setName(randomName());
		user.setUsername(user.getName().replace(" ", "_").toLowerCase());
		user.setEmail(user.getUsername() + "@email.com");
		user.setPassword("password");

		return user;
	}
}
