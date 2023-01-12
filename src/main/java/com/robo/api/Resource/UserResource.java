package com.robo.api.Resource;


import com.robo.api.Domain.DTO.UserDto;
import com.robo.api.Domain.Users;
import com.robo.api.Services.UserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserResource {

    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    UserService userService;
    @GetMapping(ID)
    public ResponseEntity<UserDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDto.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok()
                .body(userService.findAll().stream().map(x-> mapper.map(x, UserDto.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto obj){
        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(userService.create(obj).getId()).toUri();
        return ResponseEntity.created(novaUri).build();
    }

    @PutMapping(ID)
    public ResponseEntity<UserDto> update(@PathVariable Integer id,
                                          @RequestBody UserDto obj){
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(userService.update(obj),UserDto.class));

    }

    @DeleteMapping(ID)
    public ResponseEntity<UserDto> delete(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
