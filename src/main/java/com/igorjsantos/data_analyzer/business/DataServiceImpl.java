package com.igorjsantos.data_analyzer.business;

import java.nio.file.Path;
import java.util.List;

import com.igorjsantos.data_analyzer.dto.DataFileDTO;
import com.igorjsantos.data_analyzer.model.Customer;
import com.igorjsantos.data_analyzer.model.Sale;
import com.igorjsantos.data_analyzer.model.Salesman;
import com.igorjsantos.data_analyzer.utils.DataFileParser;

public class DataServiceImpl implements DataService {

    @Override
    public DataFileDTO extractData(final Path filename) {

        DataFileParser.setup(filename);

        final List<Customer> customers = DataFileParser.parseCustomers();
        final List<Salesman> salesmen = DataFileParser.parseSalesman();
        final List<Sale> sales = DataFileParser.parseSales();

        updateSalesmanForSales(sales, salesmen);

        return new DataFileDTO(customers, salesmen, sales);
    }

    private static void updateSalesmanForSales(final List<Sale> sales, final List<Salesman> salesmen) {

        for (final Sale sale : sales)
            for (final Salesman salesman : salesmen)
                if (sale.getSalesman().getName().equalsIgnoreCase(salesman.getName())) {
                    salesman.addTotalSales(sale.getTotal());
                    sale.setSalesman(salesman);
                }
    }

}
