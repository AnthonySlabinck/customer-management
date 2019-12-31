package com.example.customermanagement;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class CustomerInitializer {

    private final CustomerRepository customerRepository;

    CustomerInitializer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @EventListener
    void on(ApplicationReadyEvent event) {
        Customer customer = new Customer();
        customer.setFirstName("Anthony");
        customer.setLastName("Slabinck");
        customer.setEmailAddress("anthonyslabinck@hotmail.com");

        customerRepository.save(customer);

        customer = new Customer();
        customer.setFirstName("Neeltje");
        customer.setLastName("Slabbinck");
        customer.setEmailAddress("neeltje.slabbinck@hotmail.be");

        customerRepository.save(customer);

        customer = new Customer();
        customer.setFirstName("Muriel");
        customer.setLastName("Goegebeur");
        customer.setEmailAddress("murielgoegebeur@gmail.com");

        customerRepository.save(customer);
    }
}
