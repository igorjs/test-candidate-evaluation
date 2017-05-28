package com.igorjsantos.data_analyzer.business;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.igorjsantos.data_analyzer.config.ApplicationConfig;
import com.igorjsantos.data_analyzer.dto.DataResultsDTO;
import com.igorjsantos.data_analyzer.exception.ApplicationException;

public class FileServiceImpl implements FileService {

    private static final List<String> ALLOWED_EXTENSIONS = ApplicationConfig.getAllowedExtensions();

    private static final String STATUS_FILENAME_SUFFIX = ".done.";

    private static final Kind<?>[] ALLOWED_WATCH_EVENTS = {
            StandardWatchEventKinds.ENTRY_CREATE,
            StandardWatchEventKinds.ENTRY_MODIFY
    };

    private static boolean isAllowedExtension(final Path filename) {
        if (ALLOWED_EXTENSIONS.isEmpty())
            return true;

        final String fileExtension = FilenameUtils.getExtension(filename.toString());
        return ALLOWED_EXTENSIONS.stream().filter(ext -> ext.equals(fileExtension)).count() > 0;
    }

    @Override
    public boolean isValidPath(final Path filename) {

        if (!Files.exists(filename))
            return false;

        if (!Files.isReadable(filename))
            return false;

        if (!Files.isRegularFile(filename))
            return false;

        if (!isAllowedExtension(filename))
            return false;

        return true;
    }

    @Override
    public void saveToFile(final Path filename, final DataResultsDTO results) throws ApplicationException {

        final String outputFolder = ApplicationConfig.getOuputFolder().toString();

        final String name = FilenameUtils.getBaseName(filename.toString());
        final String ext = FilenameUtils.getExtension(filename.toString());
        final String newFilename = name.concat(STATUS_FILENAME_SUFFIX).concat(ext);

        final Path savePath = Paths.get(outputFolder, newFilename);

        try {
            Files.write(savePath, results.toString().getBytes());
        }
        catch (final Exception e) {
            throw new ApplicationException("Sorry, unable to save %s", newFilename);
        }
    }

    @Override
    public WatchService watch(final Path folderToWatch) {

        try {
            final FileSystem fileSystem = FileSystems.getDefault();
            final Path inputFolder = folderToWatch;

            final WatchService watchService = fileSystem.newWatchService();

            inputFolder.register(watchService, ALLOWED_WATCH_EVENTS);

            return watchService;
        }
        catch (final IOException e) {
            throw new ApplicationException("An error occurs when trying to register a whatcher");
        }
    }

    @Override
    public boolean isValidWatchEvent(final Kind<?> event) {
        return Arrays.asList(ALLOWED_WATCH_EVENTS).contains(event);
    }

}
