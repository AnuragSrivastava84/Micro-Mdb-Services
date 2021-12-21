package io.microratingservices.controller;

import io.microratingservices.model.Rating;
import io.microratingservices.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class MicroRatingController {

    @RequestMapping("/{itemId}")
    public Rating getRating(@PathVariable("itemId") String itemId){
        return new Rating(itemId, 3);
    }

    @RequestMapping("user/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratingList = Arrays.asList(new Rating("123", 3), new Rating("234", 5));
        return new UserRating(ratingList);
    }
}
