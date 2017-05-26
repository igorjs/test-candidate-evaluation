package com.igorjsantos.data_analyzer;

import com.igorjsantos.data_analyzer.business.AnalyzerService;
import com.igorjsantos.data_analyzer.business.AnalyzerServiceImpl;

public class Application {

    public static void main(final String[] args) {

        final AnalyzerService analyzerService = new AnalyzerServiceImpl();

        analyzerService.init();
    }
}
