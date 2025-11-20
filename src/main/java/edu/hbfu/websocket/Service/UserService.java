package edu.hbfu.websocket.Service;
import edu.hbfu.websocket.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class UserService {
    private final Map<String ,User> users = new HashMap<>();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public boolean exists(String username) {
        return users.containsKey(username);
    }
    public User register(String username,String displayName,String password){
        String hashedPassword = encoder.encode(password);
        User user = new User(username,displayName,hashedPassword);
        users.put(username,user);
        return user;
    }
}
