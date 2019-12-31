package com.example.customermanagement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    @GetMapping("/customers")
    public String list(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customersList";
    }

    @GetMapping("/customers/create")
    public String create(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "createCustomer";
    }

    @PostMapping("/customers/create")
    public String create(@Valid Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return "createCustomer";
        }

        customerRepository.save(customer);

        return "redirect:/customers/" + customer.getId();
    }

    @GetMapping("/customers/{customerId}")
    public String details(@PathVariable("customerId") Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "customerDetails";
    }

    @GetMapping("/customers/{customerId}/edit")
    public String edit(@PathVariable("customerId") Customer customer, Model model) {
        model.addAttribute("customer", customer);
        return "editCustomer";
    }

    @PostMapping("/customers/{customerId}/edit")
    public String edit(@PathVariable Long customerId, @Valid Customer customer, Errors errors) {
        if (errors.hasErrors()) {
            return "editCustomer";
        }

        customer.setId(customerId);
        customerRepository.save(customer);

        return "redirect:/customers/" + customer.getId();
    }
}

