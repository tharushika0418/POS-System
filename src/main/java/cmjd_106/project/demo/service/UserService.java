package cmjd_106.project.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cmjd_106.project.demo.entity.User;

@Service
public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}