package com.example.search.client;

import com.example.search.client.config.KakaoFeignConfig;
import com.example.search.model.KakaoBlogSearchResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakao", url = "${app.api.kakao.url}", configuration = KakaoFeignConfig.class)
public interface KakaoClient {
    @GetMapping(path = "/v2/search/blog")
    KakaoBlogSearchResponseDTO searchBlogs(
            @RequestParam String query,
            @RequestParam String sort,
            @RequestParam(value = "page") Integer pageNumber,
            @RequestParam(value = "size") Integer pageSize);
}
