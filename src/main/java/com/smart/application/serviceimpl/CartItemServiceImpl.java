package com.smart.application.services;

import com.smart.application.models.CartItem;
import com.smart.application.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public Optional<CartItem> getCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public CartItem addProductToCart(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeProductFromCart(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void clearCart() {
        cartItemRepository.deleteAll(); // Implement the logic to delete all cart items
    }
}
