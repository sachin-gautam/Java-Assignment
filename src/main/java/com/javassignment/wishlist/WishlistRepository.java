package com.javassignment.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {

    public List<Wishlist> findByUsername(String username);

}
