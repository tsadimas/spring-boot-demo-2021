package gr.hua.ds.springboot1.service;

import gr.hua.ds.springboot1.entity.User;
import gr.hua.ds.springboot1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {


    private UserRepository userRepository;
    private final CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Lazy CustomPasswordEncoder customPasswordEncoder){
        this.userRepository = userRepository;
        this.customPasswordEncoder = customPasswordEncoder;

    }

    public void registerUser(User user) {

        System.out.println("In save =================");
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(customPasswordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);

    }

    public User findUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("user not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Collections.emptyList());

    }
}