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
public class UserControllerJpa {

    public UserControllerJpa(UserRepository userRepository ) {

        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    @RequestMapping(value = "add-user", method = RequestMethod.GET)
    public String showNewUser(ModelMap model){
        String username = getLoggedInUsername(model);
        User user = new User(0,"","");
        model.put("user",user);
        return "registration";
    }
    @RequestMapping(value = "add-user", method = RequestMethod.POST)
    public String addNewUser(ModelMap model, @Valid User user, BindingResult result){

        if(result.hasErrors()){
            return "registration";
        }
        String username = getLoggedInUsername(model);
        user.setUsername(username);
        userRepository.save(user);
        return "/";
    }
//
//    @RequestMapping("delete-wishlist")
//    public String deleteWishlist(@RequestParam int id){
//
//        wishlistRepository.deleteById(id);
//        return "redirect:list-wishlist";
//    }
//
//    @RequestMapping(value = "update-wishlist", method = RequestMethod.GET)
//    public String updateWishlistPage(@RequestParam int id,ModelMap model){
//        Wishlist wishlist = wishlistRepository.findById(id).get();
//        model.addAttribute(wishlist);
//        return "wishlist";
//    }
//
//    @RequestMapping(value = "update-wishlist", method = RequestMethod.POST)
//    public String updateWishlist(ModelMap model, @Valid Wishlist wishlist, BindingResult result){
//
//        if(result.hasErrors()){
//            return "wishlist";
//        }
//
//        String username = getLoggedInUsername(model);
//        wishlist.setUsername(username);
//        wishlistRepository.save(wishlist);
//        return "redirect:list-wishlist";
//    }

    private static String getLoggedInUsername(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }




}
