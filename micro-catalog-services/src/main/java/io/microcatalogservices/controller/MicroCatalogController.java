package io.microcatalogservices.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.microcatalogservices.model.CatalogItem;
import io.microcatalogservices.model.ItemInfo;
import io.microcatalogservices.model.UserRating;
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
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        UserRating userRating = restTemplate.getForObject("http://micro-rating-service/rating/user/"+userId, UserRating.class);
        return userRating.getRatingList().stream().map(rating -> {
            ItemInfo itemInfo = restTemplate.getForObject("http://micro-info-service/item/"+rating.getItemId(), ItemInfo.class);
            return new CatalogItem(itemInfo.getName(), "Description", rating.getRating());
        }).collect(Collectors.toList());
    }

    private List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId){
        return Arrays.asList(new CatalogItem("No Item","No item found",0));
    }
}
