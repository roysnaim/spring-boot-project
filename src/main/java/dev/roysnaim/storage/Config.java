package dev.roysnaim.storage;

import dev.roysnaim.storage.model.Client;
import dev.roysnaim.storage.model.Product;
import dev.roysnaim.storage.model.Sale;
import dev.roysnaim.storage.repository.ClientRepository;
import dev.roysnaim.storage.repository.ProductRepository;
import dev.roysnaim.storage.repository.SaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository, ProductRepository productRepository, SaleRepository saleRepository) {
        return args -> {
            Client roy = new Client(
                    "Roy",
                    "Naeim",
                    "+961 81 037 097"
            );

            Client ralph = new Client(
                    "Ralph",
                    "Adams",
                    "+1-541-754-3010"
            );

            clientRepository.saveAll(List.of(roy, ralph));

            Product drone = new Product(
                    "Drone",
                    "Videography drone for enthusiasts",
                    "Technology"
            );

            Product apple = new Product(
                    "Apple",
                    "Fresh red apples",
                    "Groceries"
            );

            productRepository.saveAll(List.of(drone, apple));

            Sale sale = new Sale(
               clientRepository.findById(1L).get(),
               clientRepository.findById(2L).get(),
               productRepository.findById(1L).get(),
               1,
               2
            );

            saleRepository.save(sale);
        };
    }
}
