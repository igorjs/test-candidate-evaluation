package com.igorjsantos.data_analyzer.business;

import java.nio.file.Path;

import com.igorjsantos.data_analyzer.dto.DataResultsDTO;
import com.igorjsantos.data_analyzer.exception.ApplicationException;

public interface FileService {

    boolean isValid(final Path filename);

    void saveToFile(final Path filename, final DataResultsDTO results) throws ApplicationException;

}
