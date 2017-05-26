package com.igorjsantos.data_analyzer.business;

import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;

import com.igorjsantos.data_analyzer.config.ApplicationConfig;
import com.igorjsantos.data_analyzer.dto.DataResultsDTO;
import com.igorjsantos.data_analyzer.exception.ApplicationException;

public class FileServiceImpl implements FileService {

    @Override
    public boolean isValid(final Path filename) {

        if (!Files.exists(filename))
            return false;

        if (!Files.isReadable(filename))
            return false;

        if (!Files.isRegularFile(filename))
            return false;

        if (!ApplicationConfig.getAllowedExtensions().isEmpty()) {
            final String fileName = filename.getFileName().toString();
            final String fileExtension = FilenameUtils.getExtension(fileName);

            return !ApplicationConfig
                    .getAllowedExtensions()
                    .stream()
                    .anyMatch(ext -> ext.equals(fileExtension));
        }

        return true;
    }

    @Override
    public void saveToFile(final Path filename, final DataResultsDTO results) throws ApplicationException {

        try {
            Files.write(ApplicationConfig.getOuputFolder(), results.toString().getBytes());
        }
        catch (final Exception e) {
            throw new ApplicationException("Sorry, unable to save %s", filename.getFileName());
        }
    }
}
