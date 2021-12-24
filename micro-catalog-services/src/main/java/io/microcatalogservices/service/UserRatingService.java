package io.microcatalogservices.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.microcatalogservices.model.Rating;
import io.microcatalogservices.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod = "getFallbackUserRating"
            /*,
            threadPoolKey = "getUserRating",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            }*/
    )
    public UserRating getUserRating(String userId) {
        return restTemplate.getForObject("http://micro-rating-service/rating/user/"+ userId, UserRating.class);
    }

    private UserRating getFallbackUserRating(String userId) {
        return new UserRating(Arrays.asList(new Rating("0",0)));
    }
}
