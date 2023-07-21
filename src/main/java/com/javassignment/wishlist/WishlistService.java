package com.javassignment.wishlist;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {
    private static List<Wishlist> wishlists = new ArrayList<>();
    private static int wishlistCount = 0;
    static{
        wishlists.add(new Wishlist(++wishlistCount,"sachin","Learn Spring",
                LocalDate.now().plusYears(1), false));
        wishlists.add(new Wishlist(++wishlistCount,"sachin","Learn AWS",
                LocalDate.now().plusYears(2), false));
        wishlists.add(new Wishlist(++wishlistCount,"sachin","Learn Fullstack",
                LocalDate.now().plusYears(4), false));
    }

    public List<Wishlist> findByUsername(String username){
        return wishlists;
    }

    public void addWishlist(String username, String description, LocalDate targetDate, Boolean done){
        Wishlist wishlist = new Wishlist(++wishlistCount,username,description,targetDate,done);
        wishlists.add(wishlist);

    }

}
