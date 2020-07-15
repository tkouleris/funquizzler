package com.tkouleris.funquizzler.service;

import com.tkouleris.funquizzler.dao.UserRepository;
import com.tkouleris.funquizzler.exceptions.InvalidEmailException;
import com.tkouleris.funquizzler.exceptions.InvalidPasswordException;
import com.tkouleris.funquizzler.exceptions.InvalidUsernameException;
import com.tkouleris.funquizzler.exceptions.UserExistsException;
import com.tkouleris.funquizzler.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {

    private final PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence rawPassword) {
            return null;
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return false;
        }
    };

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserService userService;


    @BeforeEach
    void initUseCase() {
        userService = new UserService(userRepository,passwordEncoder);
    }

    @Test
    void createUserNotValidUsername(){
        User user = new User();
        user.setUsername("$#");
        user.setEmail("user@random.com");
        user.setPassword("myrandompassword");

        Assertions.assertThrows(InvalidUsernameException.class,()->userService.createNewUser(user) );
    }

    @Test
    void createUserNotValidPassword(){
        User user = new User();
        user.setUsername("randomuser");
        user.setPassword("abc");
        user.setEmail("user@random.com");

        Assertions.assertThrows(InvalidPasswordException.class,()->userService.createNewUser(user) );

        user.setPassword("abcdefghij");
        Assertions.assertThrows(InvalidPasswordException.class,()->userService.createNewUser(user) );

        user.setPassword("123asdfVC");
        Assertions.assertThrows(InvalidPasswordException.class,()->userService.createNewUser(user) );
    }

    @Test
    void createUserNotValidEmail(){
        User user = new User();
        user.setUsername("randomuser");
        user.setPassword("@bcdefghiJ1");
        user.setEmail("user@random");

        Assertions.assertThrows(InvalidEmailException.class,()->userService.createNewUser(user) );
    }

    @Test
    void createUserNotAllowUserWithSameUsername(){
        User existedUser = new User();

        User user = new User();
        user.setUsername("randomuser");
        user.setEmail("user@random.com");
        user.setPassword("@bcdefghiJ1");

        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(existedUser);
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(existedUser);
        Assertions.assertThrows(UserExistsException.class,()->userService.createNewUser(user) );
    }

    @Test
    void createUserNewUserIsCreated() throws Exception {

        User user = new User();
        user.setUsername("randomuser");
        user.setEmail("user@random.com");
        user.setPassword("@bcdefghiJ1");

        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User registeredUser = userService.createNewUser(user);
        Assertions.assertNotNull(registeredUser);
    }
}
