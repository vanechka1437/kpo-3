package com.antiplagiat.analysis.controller;

import com.antiplagiat.analysis.entity.AnalysisResult;
import com.antiplagiat.analysis.service.AnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    private final AnalysisService service;

    public AnalysisController(AnalysisService service) {
        this.service = service;
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<AnalysisResult> analyze(@PathVariable String fileId) {
        return ResponseEntity.ok(service.analyze(fileId));
    }
}