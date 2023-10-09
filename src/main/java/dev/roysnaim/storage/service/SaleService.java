package dev.roysnaim.storage.service;

import dev.roysnaim.storage.dto.SaleDto;
import dev.roysnaim.storage.model.Sale;
import dev.roysnaim.storage.repository.ClientRepository;
import dev.roysnaim.storage.repository.ProductRepository;
import dev.roysnaim.storage.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ClientService clientService;
    private final ProductService productService;

    @Autowired
    public SaleService(SaleRepository saleRepository, ClientRepository clientRepository, ProductRepository productRepository, ClientService clientService, ProductService productService) {
        this.saleRepository = saleRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.clientService = clientService;
        this.productService = productService;
    }

    public List<SaleDto> getSales() {
        return saleRepository.findAll().stream()
                .map(SaleDto::new)
                .collect(Collectors.toList());
    }

    public Optional<SaleDto> getSaleById(Long id) {
        return saleRepository.findById(id)
                .map(SaleDto::new);
    }

    //TODO: Refine method
    public SaleDto addSale(SaleDto saleDto) throws IllegalArgumentException {
        if(!clientService.existsClient(saleDto.getBuyerId()))
            throw new IllegalArgumentException("Client with id " + saleDto.getBuyerId() + " does not exist");
        if(!clientService.existsClient(saleDto.getSellerId()))
            throw new IllegalArgumentException("Client with id " + saleDto.getSellerId() + " does not exist");
        if(!productService.existsProduct(saleDto.getProductId()))
            throw new IllegalArgumentException("Product with id " + saleDto.getProductId() + " does not exist");


        Sale sale = new Sale();
        sale.setBuyer(clientRepository.getReferenceById(saleDto.getBuyerId()));
        sale.setSeller(clientRepository.getReferenceById(saleDto.getSellerId()));
        sale.setProduct(productRepository.getReferenceById(saleDto.getProductId()));
        sale.setPrice(saleDto.getPrice());
        sale.setQuantity(saleDto.getQuantity());

        //Map SaleDto to Sale

        return new SaleDto(saleRepository.save(sale));
    }
}
