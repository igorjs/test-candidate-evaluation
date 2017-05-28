package com.igorjsantos.data_analyzer.business;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.igorjsantos.data_analyzer.config.ApplicationConfig;
import com.igorjsantos.data_analyzer.dto.DataFileDTO;
import com.igorjsantos.data_analyzer.dto.DataResultsDTO;
import com.igorjsantos.data_analyzer.model.Sale;
import com.igorjsantos.data_analyzer.model.Salesman;

public final class AnalyzerServiceImpl implements AnalyzerService {

    private final FileService fileService = new FileServiceImpl();

    private final DataService dataService = new DataServiceImpl();

    @Override
    public void init() {

        analyzeDataFiles();
    }

    @SuppressWarnings("unchecked")
    public void analyzeDataFiles() {

        final Path inputPath = ApplicationConfig.getInputFolder();

        final WatchService watcher = fileService.watch(inputPath);

        WatchKey key;

        while (true) {

            try {
                key = watcher.take();
            }
            catch (final InterruptedException e) {
                return;
            }

            for (final WatchEvent<?> event : key.pollEvents()) {

                final WatchEvent<Path> ev = (WatchEvent<Path>) event;

                if (fileService.isValidWatchEvent(ev.kind())) {
                    final Path path = inputPath.resolve(ev.context());

                    if (fileService.isValidPath(path))
                        onFileEvent(path);
                }
            }

            if (!key.reset())
                break;
        }
    }

    private void onFileEvent(final Path filename) {

        final DataFileDTO extractedData = dataService.extractData(filename);

        fileService.saveToFile(filename, analyzeData(extractedData));
    }

    private DataResultsDTO analyzeData(final DataFileDTO data) {

        final DataResultsDTO results = new DataResultsDTO();

        results.setClientsAmount(data.getCustomers().size());
        results.setSalesmanAmount(data.getSalesmen().size());

        results.setMostExpensiveSale(analyzeMostExpensiveSale(data));
        results.setWorstSalesmanEver(analyzeWorstSalesmanEver(data));

        return results;
    }

    private static Sale analyzeMostExpensiveSale(final DataFileDTO data) {

        Sale mostExpensiveSale = data.getSales().iterator().next();

        for (final Sale sale : data.getSales())
            if (mostExpensiveSale.getTotal() < sale.getTotal())
                mostExpensiveSale = sale;

        return mostExpensiveSale;
    }

    private static Salesman analyzeWorstSalesmanEver(final DataFileDTO data) {

        Salesman worstSalesmanEver = data.getSalesmen().iterator().next();

        for (final Salesman salesman : data.getSalesmen())
            if (worstSalesmanEver.getTotalSales() > salesman.getTotalSales())
                worstSalesmanEver = salesman;

        return worstSalesmanEver;
    }
}
