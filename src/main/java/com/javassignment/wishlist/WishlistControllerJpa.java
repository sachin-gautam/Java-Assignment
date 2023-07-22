package com.javassignment.wishlist;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;


@Controller
@SessionAttributes("name")
public class WishlistControllerJpa {

    public WishlistControllerJpa( WishlistRepository wishlistRepository ) {

        this.wishlistRepository = wishlistRepository;
    }

    private WishlistRepository wishlistRepository;

    //list-wishlist
    @RequestMapping("list-wishlist")
    public String listAllWishlist(ModelMap model){
        String username = getLoggedInUsername(model);

        List<Wishlist> wishlists = wishlistRepository.findByUsername(username);
        model.addAttribute("wishlists",wishlists);

        return "listWishlist";
    }

    @RequestMapping(value = "add-wishlist", method = RequestMethod.GET)
    public String showNewWishlist(ModelMap model){
        String username = getLoggedInUsername(model);
        Wishlist wishlist = new Wishlist(0,username,"",
                LocalDate.now().plusYears(1),false);
        model.put("wishlist",wishlist);
        return "wishlist";
    }

    @RequestMapping(value = "add-wishlist", method = RequestMethod.POST)
    public String addNewWishlist(ModelMap model, @Valid Wishlist wishlist, BindingResult result){

        if(result.hasErrors()){
            return "wishlist";
        }

        String username = getLoggedInUsername(model);
        wishlist.setUsername(username);
        wishlistRepository.save(wishlist);
        return "redirect:list-wishlist";
    }

    @RequestMapping("delete-wishlist")
    public String deleteWishlist(@RequestParam int id){

        wishlistRepository.deleteById(id);
        return "redirect:list-wishlist";
    }

    @RequestMapping(value = "update-wishlist", method = RequestMethod.GET)
    public String updateWishlistPage(@RequestParam int id,ModelMap model){
        Wishlist wishlist = wishlistRepository.findById(id).get();
        model.addAttribute(wishlist);
        return "wishlist";
    }

    @RequestMapping(value = "update-wishlist", method = RequestMethod.POST)
    public String updateWishlist(ModelMap model, @Valid Wishlist wishlist, BindingResult result){

        if(result.hasErrors()){
            return "wishlist";
        }

        String username = getLoggedInUsername(model);
        wishlist.setUsername(username);
        wishlistRepository.save(wishlist);
        return "redirect:list-wishlist";
    }

    private static String getLoggedInUsername(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }




}