package net.voznjuk.fp.controller;

import net.voznjuk.fp.domain.Invoice;
import net.voznjuk.fp.domain.User;
import net.voznjuk.fp.repos.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private InvoiceRepo invoiceRepo;

    @GetMapping("/")
    public String greeting( Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main( Map<String, Object> model){
        Iterable<Invoice> invoices = invoiceRepo.findAll();
        model.put("invoices", invoices);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String status,
            @RequestParam String comment, Map<String, Object> model
    ){
        Invoice invoice = new Invoice(comment, status, user);
        invoiceRepo.save(invoice);

        Iterable<Invoice> invoices = invoiceRepo.findAll();
        model.put("invoices", invoices);

        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String searchkey, Map<String, Object> model){
        Iterable<Invoice> invoices;
        if(searchkey != null && !searchkey.isEmpty()){
            invoices = invoiceRepo.findByStatus(searchkey);
        } else {
            invoices = invoiceRepo.findAll();
        }

        model.put("invoices", invoices);

        return "main";
    }

}