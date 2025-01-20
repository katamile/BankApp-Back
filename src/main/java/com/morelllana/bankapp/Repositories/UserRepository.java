package com.morelllana.bankapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.morelllana.bankapp.Models.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
        // Puedes agregar métodos personalizados si es necesario
        Usuario findByUsername(String Username);
}
