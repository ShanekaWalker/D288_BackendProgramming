package com.example.demo.bootstrap;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionsRepository;
import com.example.demo.dao.ExcursionRepository;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BootstrapData implements CommandLineRunner {


    private final CustomerRepository customerRepository;
    private final DivisionsRepository divisionRepository;
    private final ExcursionRepository excursionRepository;
    private final CartItemRepository cartItemRepository;


    public BootstrapData(CustomerRepository customerRepository, DivisionsRepository divisionRepository, CartItemRepository cartItemRepository, ExcursionRepository excursionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
        this.excursionRepository = excursionRepository;
        this.cartItemRepository = cartItemRepository;


    }

    @Override
    public void run(String... args) throws Exception {

        //Get all customers
        List<Customer> customerList = customerRepository.findAll();

        //Delete all customers who are not John Doe
        customerList.stream()
                .filter(customer -> !(customer.getFirstName().equalsIgnoreCase("John") &&
                        customer.getLastName().equalsIgnoreCase("Doe")))
                .forEach(customerRepository::delete);


        // Creating customers
        Customer janet = new Customer("Janet", "Smithson", "499 Elm St", "67890", "555-5695", divisionRepository.getReferenceById(2L));
        Customer mike = new Customer("Mike", "Adams", "223 Jackson St", "44064", "555-9147", divisionRepository.getReferenceById(2L));
        Customer emanuel = new Customer("Emanuel", "Moore", "633 MLK Blvd", "12586", "555-7418", divisionRepository.getReferenceById(2L));
        Customer william = new Customer("William", "Johnson", "321 Wobble Rd", "45312", "555-1462", divisionRepository.getReferenceById(2L));
        Customer jason  = new Customer("Jason", "Rouge", "888 Guston St", "55321", "555-8526", divisionRepository.getReferenceById(2L));



            // Save customers to repo
            customerRepository.save(janet);
            customerRepository.save(mike);
            customerRepository.save(emanuel);
            customerRepository.save(william);
            customerRepository.save(jason);

            // Print all customers to verify
            customerRepository.findAll().forEach(System.out::println);
        }
    }

