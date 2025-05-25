package com.antiplagiat.analysis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AnalysisResult {
    @Id
    private String fileId;
    private int paragraphs;
    private int words;
    private int characters;
}