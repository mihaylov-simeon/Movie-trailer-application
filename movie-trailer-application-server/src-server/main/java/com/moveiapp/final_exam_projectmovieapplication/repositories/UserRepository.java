package com.moveiapp.final_exam_projectmovieapplication.repositories;

import com.moveiapp.final_exam_projectmovieapplication.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNameAndEmail(String name, String email);
    User findByEmail(String email);
}
