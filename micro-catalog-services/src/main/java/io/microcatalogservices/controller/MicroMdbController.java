package io.microcatalogservices.controller;

import io.microcatalogservices.model.CatalogItem;
import io.microcatalogservices.model.ItemInfo;
import io.microcatalogservices.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MicroMdbController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        UserRating userRating = restTemplate.getForObject("http://micro-rating-service/rating/user/"+userId, UserRating.class);
        return userRating.getRatingList().stream().map(rating -> {
            ItemInfo itemInfo = restTemplate.getForObject("http://micro-info-service/item/"+rating.getItemId(), ItemInfo.class);
            return new CatalogItem(itemInfo.getName(), "Description", rating.getRating());
        }).collect(Collectors.toList());
    }
}
