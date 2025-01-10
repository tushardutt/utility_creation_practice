package com.userManagement.user_registration.repository;
import com.userManagement.user_registration.model.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Repository
public class UserRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public UserRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public User save(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        redisTemplate.opsForHash().put("USER:" + id, "userDetails", user);
        return user;
    }

    public User findById(String id) {
        return (User) redisTemplate.opsForHash().get("USER:" + id, "userDetails");
    }
    // Delete user by ID
    public void deleteById(String id) {
        redisTemplate.opsForHash().delete("USER:" + id, "userDetails"); // Correct key format for deletion
    }

    public List<User> findAll() {
        Set<String> keys = redisTemplate.keys("USER:*");
        List<User> users = new ArrayList<>();
        for (String key : keys) {
            User user = (User) redisTemplate.opsForHash().get(key, "userDetails");
            if (user != null) {
                users.add(user);
            }
        }
        return users;
    }

    public User findByEmail(String email) {
        Set<String> keys = redisTemplate.keys("USER:*");
        for (String key : keys) {
            User user = (User) redisTemplate.opsForHash().get(key, "userDetails");
            if (user != null && user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }
}
