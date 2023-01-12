package com.robo.api.Services.Impl;

import com.robo.api.Domain.DTO.UserDto;
import com.robo.api.Domain.Users;
import com.robo.api.Repositories.UserRepository;
import com.robo.api.Services.Exceptions.DataIntegratyViolationException;
import com.robo.api.Services.Exceptions.ObjectNotFoundException;
import com.robo.api.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    @Override
    public List<Users> findAll(){
        return userRepository.findAll();
    }

    @Override
    public Users create(UserDto obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj,Users.class));
    }

    @Override
    public Users update(UserDto obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj,Users.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDto obj){
        Optional<Users> user = userRepository.findByEmail(obj.getEmail());
        if(user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw new DataIntegratyViolationException("Email já cadastrado");
        }
    }
}
