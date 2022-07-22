package com.example.recipe.service.implementation;

import com.example.recipe.entity.User;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.service.interfaces.UserServices;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;

    public UserServicesImpl(UserRepository userRepository) {

		super();
		this.userRepository = userRepository;
	}

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id){

        return userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id.toString()));
    }

    @Override
    public User addUser(User user){

        userRepository.save(user);

        return user;
    }

    @Override
    public User updateUser(Long id, User userData){

        User user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(id.toString()));
		
		user.setUserName(userData.getUserName());
		user.setRecipes(userData.getRecipes());
		user.setComments(userData.getComments());

		return userRepository.save(user);

    }

    @Override
    public void deleteUser(Long id){
        
        boolean userExists = userRepository.existsById(id);
        if(!userExists){
            throw new IllegalStateException(
                    "User with id " + id + " does not exist"
            );
        }
        userRepository.deleteById(id);
    }
}
