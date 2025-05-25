package com.antiplagiat.analysis.service;

import com.antiplagiat.analysis.entity.AnalysisResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnalysisService {
    private final RestTemplate restTemplate;

    @Value("${storage.service.url}")
    private String storageServiceUrl;

    public AnalysisService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AnalysisResult analyze(String fileId) {
        byte[] fileContent = restTemplate.getForObject(
                storageServiceUrl + "/file/" + fileId,
                byte[].class
        );

        assert fileContent != null;
        String text = new String(fileContent);
        int paragraphs = text.split("\n\n").length;
        int words = text.split("\\s+").length;
        int characters = text.length();

        return new AnalysisResult(fileId, paragraphs, words, characters);
    }
}