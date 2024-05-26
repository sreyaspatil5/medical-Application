package com.smart.application.services;

import com.smart.application.models.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemService {

    List<CartItem> getAllCartItems();

    Optional<CartItem> getCartItemById(Long id);

    CartItem addProductToCart(CartItem cartItem);

    void removeProductFromCart(Long id);

    void clearCart();
}
