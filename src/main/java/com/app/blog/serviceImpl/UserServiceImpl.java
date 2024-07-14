package com.app.blog.serviceImpl;

import com.app.blog.entity.User;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.payload.UserDTO;
import com.app.blog.repository.UserRepo;
import com.app.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.dtoToUser(userDTO);
        User savedUser = this.userRepo.save(user);
        return this.userToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        user.setPassword(userDTO.getPassword());
        this.userRepo.save(user);
        return this.userToDTO(user);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        return this.userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTOS = users.stream().map(this::userToDTO).collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDTO userDTO){
        return this.modelMapper.map(userDTO,User.class);
    }

    private UserDTO userToDTO(User user){
        return this.modelMapper.map(user,UserDTO.class);
    }

}
