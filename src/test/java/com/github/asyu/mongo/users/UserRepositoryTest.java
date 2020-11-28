package com.github.asyu.mongo.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void test_create_and_read() {
        // given
        User user = new User();
        user.setName("asyu");
        user.setAge(33);

        // when
        userRepository.save(user);

        // then
        User savedUser = userRepository.findByName("asyu");
        assertThat(user.getName()).isEqualTo(savedUser.getName());
    }

    @Test
    public void test_get_greater_than() {
        // given
        User user = new User();
        user.setName("zio");
        user.setAge(34);

        // when
        userRepository.save(user);
        List<User> users = userRepository.findByAgeGreaterThan(32);

        // then
        assertThat(users.size()).isGreaterThan(1);
    }

    @Test
    public void test_update() {
        // given
        String city = "seoul";

        // when
        Optional<User> user = userRepository.findById("5fc25709540f01504ec02a60");
        user.orElseThrow().setCity(city);

        // then
        userRepository.save(user.orElseThrow());
        Optional<User> updatedUser = userRepository.findById("5fc25709540f01504ec02a60");
        assertThat(updatedUser.orElseThrow().getCity()).isNotEmpty();
    }

    @Test
    public void test_delete() {
        // given
        String targetId = "5fc2571299496731768b5834";

        // when
        userRepository.deleteById(targetId);

        // then
        Optional<User> user = userRepository.findById(targetId);
        assertThat(user).isEmpty();
    }
}