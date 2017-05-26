package com.igorjsantos.data_analyzer.business;

import java.nio.file.Path;

import com.igorjsantos.data_analyzer.dto.DataFileDTO;

public interface DataService {

    DataFileDTO extractData(final Path filename);
}
