package io.microratingservices.controller;

import io.microratingservices.model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class MicroRatingController {

    @RequestMapping("/{itemId}")
    public Rating getRating(@PathVariable("itemId") String itemId){
        return new Rating(itemId, 3);
    }
}
