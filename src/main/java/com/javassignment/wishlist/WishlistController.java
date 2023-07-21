package com.javassignment.wishlist;

import jakarta.validation.Valid;
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
public class WishlistController {

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    private WishlistService wishlistService;

    //list-wishlist
    @RequestMapping("list-wishlist")
    public String listAllWishlist(ModelMap model){

        List<Wishlist> wishlists = wishlistService.findByUsername("sachin");
        model.addAttribute("wishlists",wishlists);

        return "listWishlist";
    }

    @RequestMapping(value = "add-wishlist", method = RequestMethod.GET)
    public String showNewWishlist(ModelMap model){
        String username = (String)model.get("name");
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

        String username = (String)model.get("name");
        wishlistService.addWishlist(username,wishlist.getDescription(),
                LocalDate.now().plusYears(1),false);
        return "redirect:list-wishlist";
    }

    @RequestMapping("delete-wishlist")
    public String deleteWishlist(@RequestParam int id){

        wishlistService.deleteById(id);
        return "redirect:list-wishlist";
    }

}
