package com.restful.social.socialmediarestapi.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO_Service {
    private static final List<User> users = new ArrayList<>();
    private static int idx = 0;

    static {
        users.add(new User(++idx, "Rami", LocalDate.of(2002, 9, 21)));
        users.add(new User(++idx, "Dibya", LocalDate.of(2000, 12, 23)));
        users.add(new User(++idx, "Jyoti", LocalDate.of(1989, 12, 2)));
        users.add(new User(++idx, "Smruti", LocalDate.of(1991, 7, 27)));
    }

    public List<User> getAll() {
        return users;
    }

    public User findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }
    public void deleteById(int id) {
        users.remove(users.stream()
                .filter(user -> user.getId() == id)
                .findFirst().orElse(null));
    }

    public User createUser(User user) {
        user.setId(++idx);
        users.add(user);
        return user;
    }
}
