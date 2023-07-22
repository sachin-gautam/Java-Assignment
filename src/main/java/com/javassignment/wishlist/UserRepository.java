package com.javassignment.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    public List<Wishlist> findByUsername(String username);

}
