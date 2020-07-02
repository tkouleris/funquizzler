package com.tkouleris.funquizzler.service;

import com.tkouleris.funquizzler.dao.UserRepository;
import com.tkouleris.funquizzler.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {

    protected UserRepository userRepository;
    protected PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createNewUser(User user) throws Exception
    {
        if(user_exists(user)) throw new Exception("User already exists!");

        String UserEmail = user.getEmail().trim();
        String UserUsername = user.getUsername().trim();
        String UserPassword = user.getPassword().trim();

        boolean UsernameIsNotValid = !UserUsername.matches("[A-Za-z0-9_]+");
        /* Email Restriction
         * ---------------------
         *This expression matches email addresses, and checks that they are of the proper form.
         *It checks to ensure the top level domain is between 2 and 4 characters long,
         *but does not check the specific domain against a list (especially since
         *there are so many of them now).
         * "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
         */
        boolean EmailIsNotValid = !UserEmail.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

        /* Password Restriction
         * ------------------------
         * At least 8 chars
         * Contains at least one digit
         * Contains at least one lower alpha char and one upper alpha char
         * Contains at least one char within a set of special chars (@#%$^ etc.)
         * Does not contain space, tab, etc.
         */
        boolean PasswordIsNotValid = !UserPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}");

        if(UsernameIsNotValid) throw new Exception("Username not set or not valid!");
        if(PasswordIsNotValid ) throw new Exception("Password not set or not valid");
        if(EmailIsNotValid) throw new Exception("Email not set or not valid!");


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private Boolean user_exists(User user)
    {
        if(userRepository.findByUsername(user.getUsername()) != null) return true;
        if(userRepository.findByEmail(user.getEmail()) != null) return true;
        return false;
    }
}
