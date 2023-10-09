package dev.roysnaim.storage.controller;

import dev.roysnaim.storage.dto.SaleDto;
import dev.roysnaim.storage.service.SaleService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleDto>> getSales() {
        List<SaleDto> saleList = saleService.getSales();
        if(saleList == null || saleList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(saleList, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<SaleDto> getSaleById(@PathVariable Long id) {
        Optional<SaleDto> sale = saleService.getSaleById(id);
        return sale.isPresent() ? new ResponseEntity<>(sale.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<SaleDto> addSale(@RequestBody @Valid SaleDto saleDto) {
        return new ResponseEntity<>(saleService.addSale(saleDto), HttpStatus.CREATED);
    }

    //TODO: @PutMapping

    @ExceptionHandler(value = IllegalArgumentException.class)
    public void illegalArgumentExceptionHandler(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
