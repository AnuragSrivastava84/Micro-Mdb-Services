package io.microcatalogservices.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.microcatalogservices.model.CatalogItem;
import io.microcatalogservices.model.ItemInfo;
import io.microcatalogservices.model.Rating;
import io.microcatalogservices.model.UserRating;
import io.microcatalogservices.service.ItemInfoService;
import io.microcatalogservices.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MicroCatalogController {

    @Autowired
    private UserRatingService userRatingService;

    @Autowired
    private ItemInfoService itemInfoService;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        UserRating userRating = userRatingService.getUserRating(userId);
        return userRating.getRatingList().stream().map(rating -> {
            ItemInfo itemInfo = itemInfoService.getItemInfo(rating);
            return new CatalogItem(itemInfo.getName(), "Description", rating.getRating());
        }).collect(Collectors.toList());
    }
}
