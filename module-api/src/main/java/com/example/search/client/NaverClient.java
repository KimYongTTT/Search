package com.example.search.client;

import com.example.search.client.config.NaverFeignConfig;
import com.example.search.model.NaverBlogSearchResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naver", url = "${app.api.naver.url}", configuration = NaverFeignConfig.class)
public interface NaverClient {
    @GetMapping(path = "/v1/search/blog.json")
    NaverBlogSearchResponseDTO searchBlogs(
            @RequestParam String query,
            @RequestParam String sort,
            @RequestParam(value = "start") Integer pageNumber,
            @RequestParam(value = "display") Integer pageSize);
}
