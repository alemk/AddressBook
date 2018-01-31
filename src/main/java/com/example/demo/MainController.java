package com.example.demo;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class MainController {
    @Autowired
    AddressBookRepository addressBookRepository;
    @RequestMapping("/")
    public String listAddressBook(Model model){
        model.addAttribute("addrssbooks", addressBookRepository.findAll());
        return "addressbooklist;
    }
    @GetMapping("/add")
    public String addressbookform(Model model){
        model.addAttribute("addressbook", new Addressbook());
        return "addressbookform";
    }
    @PostMapping("/process")
    public String processaddressform(@Valid Addressbook addressbook, BindingResult result)
    {
        if (result.hasErrors()){
            return "addressbookform";
        }
        addressBookRepository.save(addressbook);
        return "redirect:/";
    }
    @RequestMapping("detail/{id}")
    public String showaddressbook(@PathVariable("id") long id, Model model){
        model.addAttribute("addressbook", addressBookRepository.findOne(id));
        return "showaddressbook";
    }

    @RequestMapping("/update/{id}")
    public String updateaddressbook(@PathVariable("id") long id, Model model){
        model.addAttribute("addressbook", addressBookRepository.findOne(id));
        return "addressbookform";
    }
    @RequestMapping("/delete/{id}")
    public String deladdressbook(@PathVariable("id") long id){
        addressBookRepository.delete(id);
        return "redirect:/";
    }

}
