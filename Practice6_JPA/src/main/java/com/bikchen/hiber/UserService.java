package com.bikchen.hiber;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final EntityManager entityManager;

    @Transactional
    public UserEntity createUser(String first_name, String last_name, String email) {
        UserEntity user = new UserEntity();
        user.setFirstName(first_name);
        user.setLastName(last_name);

        return entityManager.merge(user);
    }
}
