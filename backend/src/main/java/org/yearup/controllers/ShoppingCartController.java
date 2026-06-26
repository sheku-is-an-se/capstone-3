package org.yearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Category;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;
import org.yearup.service.ShoppingCartService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
@CrossOrigin
@PreAuthorize("hasRole('ROLE_USER')")
public class ShoppingCartController {
    // a shopping cart controller depends on the service layer
    private ShoppingCartService shoppingCartService;
    private UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    // Returns the shopping cart for the logged in user
    @GetMapping
    public ShoppingCart getCart(Principal principal) {
        // get the currently logged in username
        String userName = principal.getName();
        // find database user by username
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        // use the shoppingCartService to get all items in the cart and return the cart
        return shoppingCartService.getByUserId(userId);
    }

    // Adds a product to the cart, creates a new cart item or increases quantity
    @PostMapping("/products/{productId}")
    public ResponseEntity<ShoppingCart> addToCart(Principal principal, @PathVariable int productId) {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();
        ShoppingCart cart = shoppingCartService.addProduct(userId, productId);
        return ResponseEntity.status(201).body(cart);
    }

    // Updates the quantity of a product in the cart
    @PutMapping("/products/{productId}")
    public ShoppingCart updateCart(Principal principal, @PathVariable int productId, @RequestBody ShoppingCartItem item) {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();
        ShoppingCart cart = shoppingCartService.updateProduct(userId, productId, item.getQuantity());
        return cart;
    }

    // Clears all items from the cart
    @DeleteMapping
    public ShoppingCart clearCart(Principal principal) {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();
        ShoppingCart cart = shoppingCartService.clearCart(userId);
        return cart;
    }
}
