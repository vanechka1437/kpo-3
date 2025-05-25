package com.antiplagiat.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class GatewayController {
    private final RestTemplate restTemplate;

    @Value("${storage.service.url}")
    private String storageServiceUrl;

    @Value("${analysis.service.url}")
    private String analysisServiceUrl;

    @Autowired
    public GatewayController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestBody byte[] file, @RequestParam String filename) {
        String url = storageServiceUrl + "/upload?filename=" + filename;
        return forwardRequest(url, HttpMethod.POST, file);
    }

    @GetMapping("/analyze/{fileId}")
    public ResponseEntity<?> analyzeFile(@PathVariable String fileId) {
        String url = analysisServiceUrl + "/analyze/" + fileId;
        return forwardRequest(url, HttpMethod.GET, null);
    }

    private ResponseEntity<?> forwardRequest(String url, HttpMethod method, Object body) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> entity = new HttpEntity<>(body, headers);
            return restTemplate.exchange(url, method, entity, String.class);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service unavailable");
        }
    }
}