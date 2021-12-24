package io.microcatalogservices.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.microcatalogservices.model.ItemInfo;
import io.microcatalogservices.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackItemInfo")
    public ItemInfo getItemInfo(Rating rating) {
        return restTemplate.getForObject("http://micro-info-service/item/"+ rating.getItemId(), ItemInfo.class);
    }

    private ItemInfo getFallbackItemInfo(Rating rating) {
        return new ItemInfo(rating.getItemId(),"", "Item not found");
    }
}
