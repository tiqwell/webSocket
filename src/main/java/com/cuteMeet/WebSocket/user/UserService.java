package com.cuteMeet.WebSocket.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user){
        user.set_status(Status.ONLINE);
        repository.save(user);
    }
    public void disconnect(User user){
        var storedUser = repository.findById(user.get_nickname()).orElse(null);
        if(storedUser != null){
            storedUser.set_status(Status.ONLINE);
            repository.save(storedUser);

        }
    }
    public List<User> findConnectedUsers()
    {
        return repository.findAllByStatus(Status.ONLINE);
    }

}
