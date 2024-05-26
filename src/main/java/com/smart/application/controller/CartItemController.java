package com.smart.application.controller;

import com.smart.application.models.CartItem;
import com.smart.application.models.Customer;
import com.smart.application.models.Order;
import com.smart.application.models.Product;
import com.smart.application.services.CartItemService;
import com.smart.application.services.CustomerService;
import com.smart.application.services.OrderService;
import com.smart.application.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartitems")
public class CartItemController {

    private final CartItemService cartItemService;
    private final OrderService orderService;
    private final ProductService productService;
    private final CustomerService customerService;

    @Autowired
    public CartItemController(CartItemService cartItemService, OrderService orderService, CustomerService customerService, ProductService productService) {
        this.cartItemService = cartItemService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable("id") Long id) {
        Optional<CartItem> cartItem = cartItemService.getCartItemById(id);
        return cartItem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CartItem> addProductToCart(@RequestBody CartItem cartItem) {
        Product product = cartItem.getProduct();
        Customer customer = cartItem.getCustomer();

        // Check if either product name or ID is provided
        if (product.getName() != null || product.getId() != null) {
            Optional<Product> optionalProduct = productService.getProductByName(product.getName());
            if (optionalProduct.isPresent()) {
                product = productService.save(optionalProduct.get());
                cartItem.setProduct(product);
                cartItem.setProductName(product.getName()); // Set product name
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Product not found
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Neither product name nor ID provided
        }

        // Check if either customer name or ID is provided
        if (customer.getName() != null || customer.getId() != null) {
            Optional<Customer> optionalCustomer = customerService.getCustomerByName(customer.getName());
            if (optionalCustomer.isPresent()) {
                customer = customerService.save(optionalCustomer.get());
                cartItem.setCustomer(customer);
                cartItem.setCustomerName(customer.getName()); // Set customer name
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Customer not found
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Neither customer name nor ID provided
        }

        // Save the cart item after associating it with the product and customer
        CartItem savedCartItem = cartItemService.addProductToCart(cartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCartItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable("id") Long id) {
        cartItemService.removeProductFromCart(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkoutCartItems(@RequestBody List<CartItem> cartItems, @RequestParam Long customerId) {
        // Initialize order
        Order order = new Order();

        // Populate order details from cart items
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            // Populate product details

            order.setProductName(product.getName());
            // Populate customer details
            Customer customer = new Customer(); // Assuming customer details are fetched from database based on customerId
            order.setCustomerId(customer.getId());
            order.setCustomerName(customer.getName());
            order.setCustomerAddress(customer.getAddress());
            // Populate cart item details
            order.setQuantity(cartItem.getQuantity());
            order.setPrice(product.getPrice());

            // Add logic to save the order or accumulate multiple order items
            orderService.createOrder(order);
        }

        // Optionally, clear the cart items after checkout
        cartItemService.clearCart();

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
