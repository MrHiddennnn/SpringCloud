package com.formacionbdi.spring.app.item.controller;

import com.formacionbdi.spring.app.item.models.dto.Item;
import com.formacionbdi.spring.app.item.models.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/listadoitems")
    public List<Item> listadoItems() {
        return itemService.findAll();
    }

    @GetMapping("/informacionitem/{id}/cantidad/{cantidad}")
    public Item informacionItem(@PathVariable("id") Long id, @PathVariable("cantidad") Integer cantidad) {
        return itemService.findById(id, cantidad);
    }

}
