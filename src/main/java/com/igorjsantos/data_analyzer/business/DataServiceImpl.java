package com.igorjsantos.data_analyzer.business;

import java.nio.file.Path;
import java.util.stream.Stream;

import com.igorjsantos.data_analyzer.dto.DataFileDTO;
import com.igorjsantos.data_analyzer.model.Customer;
import com.igorjsantos.data_analyzer.model.Sale;
import com.igorjsantos.data_analyzer.model.Salesman;
import com.igorjsantos.data_analyzer.utils.DataFileParser;

public class DataServiceImpl implements DataService {

    @Override
    public DataFileDTO extractData(final Path filename) {

        DataFileParser.setup(filename);

        final Stream<Customer> customers = DataFileParser.parseCustomers();

        final Stream<Salesman> salesmen = DataFileParser.parseSalesman();

        final Stream<Sale> sales = DataFileParser.parseSales();

        sales.forEach(sale -> {
            final String salemanName = sale.getSalesman().getName();

            final Salesman salesman = salesmen.filter(s -> s.getName().equalsIgnoreCase(salemanName))
                    .findFirst().get();

            salesman.addTotalSales(sale.getTotal());

            sale.setSalesman(salesman);
        });

        return new DataFileDTO(customers, salesmen, sales);
    }

}
