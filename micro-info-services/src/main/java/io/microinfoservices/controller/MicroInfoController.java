package io.microinfoservices.controller;

import io.microinfoservices.model.ItemInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class MicroInfoController {

    @RequestMapping("/{itemId}")
    public ItemInfo getItemInfo(@PathVariable("itemId") String itemId){
        return new ItemInfo(itemId, "Movie", "Transformer");
    }
}
