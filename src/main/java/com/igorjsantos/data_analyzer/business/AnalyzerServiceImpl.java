package com.igorjsantos.data_analyzer.business;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.igorjsantos.data_analyzer.config.ApplicationConfig;
import com.igorjsantos.data_analyzer.dto.DataFileDTO;
import com.igorjsantos.data_analyzer.dto.DataResultsDTO;
import com.igorjsantos.data_analyzer.exception.ApplicationException;

public final class AnalyzerServiceImpl implements AnalyzerService {

    private static final FileService fileService = new FileServiceImpl();

    private static final DataService dataService = new DataServiceImpl();

    @Override
    public void init() {

        registerAnalyzer();
    }

    public void registerAnalyzer() {

        try {
            final WatchService watcher = FileSystems.getDefault().newWatchService();

            ApplicationConfig.getInputFolder()
                    .register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

            while (true) {

                WatchKey key;

                try {
                    key = watcher.take();
                }
                catch (final InterruptedException e) {
                    return;
                }

                for (final WatchEvent<?> event : key.pollEvents()) {

                    @SuppressWarnings("unchecked")
                    final WatchEvent<Path> ev = (WatchEvent<Path>) event;

                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE &&
                            event.kind() == StandardWatchEventKinds.ENTRY_MODIFY)
                        onFileEvent(ev.context());

                }

                if (!key.reset())
                    break;
            }
        }
        catch (final IOException e) {
            throw new ApplicationException("An error occurs when trying to register a whatcher");
        }

    }

    private void onFileEvent(final Path context) {

        if (!fileService.isValid(context))
            return;

        final DataFileDTO extractedData = dataService.extractData(context);

        fileService.saveToFile(context, analyzeData(extractedData));
    }

    private DataResultsDTO analyzeData(final DataFileDTO data) {

        final DataResultsDTO results = new DataResultsDTO();

        results.setClientsAmount(data.getCustomers().count());

        results.setSalesmanAmount(data.getSalesmen().count());

        results.setMostExpensiveSale(data.getSales().iterator().next());

        data.getSales().forEach(sale -> {
            if (results.getMostExpensiveSale().getTotal() > sale.getTotal())
                results.setMostExpensiveSale(sale);

            sale.getSalesman().addTotalSales(sale.getTotal());
        });

        results.setWorstSalesmanEver(data.getSalesmen().iterator().next());

        data.getSalesmen().forEach(salesman -> {
            if (results.getWorstSalesmanEver().getTotalSales() < salesman.getTotalSales())
                results.setWorstSalesmanEver(salesman);
        });

        return results;
    }
}
