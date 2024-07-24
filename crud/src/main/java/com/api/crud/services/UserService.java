package com.api.crud.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.crud.exceptions.ResourceNotFoundException;
import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRespository;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	IUserRespository userRepository;
	
	
	public ArrayList<UserModel> getUsers(){
		return (ArrayList<UserModel>) userRepository.findAll();
	}
	
	public UserModel saveUser(UserModel user) {
		user.setFechaCreacion(LocalDateTime.now());
		logger.info("Saving user: {}", user);
		return userRepository.save(user);
	}	
	
	public Optional<UserModel> getById(Long id){
		return userRepository.findById(id);
	}
	
	
	public UserModel updateById(UserModel request, Long id) {
		 logger.info("Updating user with ID: {}", id);
	        Optional<UserModel> optionalUser = userRepository.findById(id);
	        
	        if (optionalUser.isEmpty()) {
	            logger.error("User not found with ID: {}", id);
	            throw new ResourceNotFoundException("User not found with id " + id);
	        }
	        
	        UserModel user = optionalUser.get();
	        logger.info("Found user: {}", user);
	        
	        user.setTipoIdentificacion(request.getTipoIdentificacion());
	        user.setNumeroIdentificacion(request.getNumeroIdentificacion());
	        user.setNombres(request.getNombres());
	        user.setApellidos(request.getApellidos());
	        
	        UserModel updatedUser = userRepository.save(user);
	        logger.info("User updated: {}", updatedUser);
	        
	        return updatedUser;       
        
    }
	
	public Boolean deleteUser(Long id) {
		try {			
			Optional<UserModel> optionalUser = userRepository.findById(id);

	        if (optionalUser.isEmpty()) {
	            logger.error("User not found with ID: {}", id);
	            throw new ResourceNotFoundException("User not found with id " + id);
	        }

	        UserModel user = optionalUser.get();
	        logger.info("User deleted: {}", user);
	        userRepository.deleteById(id);
	        logger.info("User deleted with ID: {}", id);
	        return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
}
