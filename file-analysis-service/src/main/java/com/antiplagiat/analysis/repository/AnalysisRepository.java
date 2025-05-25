package com.antiplagiat.analysis.repository;

import com.antiplagiat.analysis.entity.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisRepository extends JpaRepository<AnalysisResult, String> {}