package com.example.demo.services;


import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;


    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }


    @Override
    @Transactional
    public PurchaseResponse placeOrder(PurchaseData purchaseData) {
        try {
            Cart cart = purchaseData.getCart();

            //Generate tracking number
            String orderTrackingNumber = generateOrderTrackingNumber();

            //Setting tracking number to cart
            cart.setOrderTrackingNumber(orderTrackingNumber);
            Set<CartItem> cartItems = purchaseData.getCartItems();
            cartItems.forEach(item -> cart.add(item));

            //Setting the status of the cart to 'ordered'
            cart.setStatus(StatusType.ordered);

            Customer customer = purchaseData.getCustomer();

           //Saving the cart
            cartRepository.save(cart);

            //Associating cart with the customer
            customer.add(cart);
            return new PurchaseResponse(orderTrackingNumber);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Cart is empty");
        }

    }

    @Override
    public PurchaseResponse checkout(PurchaseData purchase) {
        return null;
    }


    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
