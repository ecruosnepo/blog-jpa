package com.estsoft.blogjpa.external;

import com.estsoft.blogjpa.dto.AddArticleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class ExternalAPIParser {

    //외부 API 호출 -> json 받아오기
    RestTemplate restTemplate = new RestTemplate();

    public List<AddArticleRequest> parser(String url){
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        if(response.getStatusCode().is2xxSuccessful()){
            List<LinkedHashMap<String, Object>> responseBody = response.getBody();
            List<AddArticleRequest> bodyParseEntity = new ArrayList<>();
            for (LinkedHashMap<String, Object> item : responseBody){
                bodyParseEntity.add(new AddArticleRequest(item.get("title").toString(), item.get("body").toString()));
            }

            return bodyParseEntity;
        }else {
            return null;
        }
    }


    public void parseOM(String url){
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
    }
}
