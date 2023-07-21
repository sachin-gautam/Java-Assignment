package com.javassignment.wishlist;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

        //Check if the username matches
        Predicate<? super Wishlist> predicate = wishlist -> wishlist.getUsername().equalsIgnoreCase(username);
        //Converting the wishlist to stream and checking to return as list
        return wishlists.stream().filter(predicate).toList();
    }

    public void addWishlist(String username, String description, LocalDate targetDate, Boolean done){
        Wishlist wishlist = new Wishlist(++wishlistCount,username,description,targetDate,done);
        wishlists.add(wishlist);

    }

    public void deleteById(int id){


        //Using a predicate to cycle through all the wishlist ids to find the one we selected for deletion
        Predicate<? super Wishlist> predicate = wishlist -> wishlist.getId() == id;
        wishlists.removeIf(predicate);
    }

    public Wishlist findByID(int id) {

        //Filtering from the stream of the list based on the condition and returning the required element
        Predicate<? super Wishlist> predicate = wishlist -> wishlist.getId() == id;
        Wishlist wishlist = wishlists.stream().filter(predicate).findFirst().get();
        return wishlist;
    }

    public void updateWishlist(@Valid Wishlist wishlist){
        deleteById(wishlist.getId());
        wishlists.add(wishlist);
    }
}
