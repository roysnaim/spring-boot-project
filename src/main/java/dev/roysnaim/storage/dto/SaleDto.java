package dev.roysnaim.storage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.roysnaim.storage.model.Sale;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class SaleDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

    @NotNull
    private Long buyerId;
    @NotNull
    private Long sellerId;
    @NotNull
    private Long productId;
    @NotNull
    private int quantity;
    @NotNull
    private double price;

    public SaleDto(Sale sale) {
        this.id = sale.getId();
        this.creationDate = sale.getCreationDate();
        this.buyerId = sale.getBuyer().getId();
        this.sellerId = sale.getSeller().getId();
        this.productId = sale.getProduct().getId();
        this.quantity = sale.getQuantity();
        this.price = sale.getPrice();
    }

    public SaleDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SaleDto{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
