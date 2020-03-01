package com.bikchen.hiber;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final EntityManager entityManager;

    @Transactional
    public UserEntity createUser(String first_name, String last_name) {
        UserEntity user = new UserEntity();
        user.setFirstName(first_name);
        user.setLastName(last_name);

        return entityManager.merge(user);
    }

    @Transactional
    public List<UserEntity> getAllUsers() {
        return entityManager
                .createQuery("select u from UserEntity u", UserEntity.class)
                .getResultList();
    }

    @Transactional
    public List<UserEntity> getUsersByLastName(String lName) {
        return entityManager
                .createQuery("select u from UserEntity u where u.lastName = :lName", UserEntity.class)
                .setParameter("lName", lName)
                .getResultList();
    }

    @Transactional
    List<UserEntity> getUsersBySubstring(String substring) {
        return entityManager
                .createQuery("select u " +
                        "from UserEntity u " +
                        "where u.firstName like concat('%', :substring, '%')" +
                        " or u.lastName like concat('%', :substring, '%')", UserEntity.class)
                .setParameter("substring", substring)
                .getResultList();
    }
}
