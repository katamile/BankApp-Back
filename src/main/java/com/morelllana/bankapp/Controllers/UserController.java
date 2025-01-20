package com.morelllana.bankapp.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morelllana.bankapp.DTOs.UserDTO;
import com.morelllana.bankapp.Models.Usuario;
import com.morelllana.bankapp.Services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserServices userService;

    // Obtener todos los users
    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<Usuario> users = userService.getAllUsers();
        return users.stream()
                .map(user -> new UserDTO(user.getUsername(), user.getPassword()))
                .toList();
    }

    // Obtener user por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<Usuario> user = userService.getUserById(id);
        return user.map(u -> ResponseEntity.ok(new UserDTO(u.getUsername(), u.getPassword())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear nuevo user
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody Usuario user) {
        Usuario createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(createdUser.getUsername(), createdUser.getPassword()));
    }

    // Actualizar user
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody Usuario userDetails) {
        Usuario updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(new UserDTO(updatedUser.getUsername(), updatedUser.getPassword()));
    }

    // Eliminar user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
