package com.igorjsantos.data_analyzer.business;

import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchService;

import com.igorjsantos.data_analyzer.dto.DataResultsDTO;
import com.igorjsantos.data_analyzer.exception.ApplicationException;

public interface FileService {

    boolean isValidPath(final Path filename);

    void saveToFile(final Path filename, final DataResultsDTO results) throws ApplicationException;

    WatchService watch(Path inputFolder);;

    boolean isValidWatchEvent(Kind<?> event);
}
