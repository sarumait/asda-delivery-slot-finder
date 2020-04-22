package com.sarumait.asda;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeliverySlot {
    private final LocalDateTime dateTime;
    private final String price;

    public DeliverySlot(LocalDateTime dateTime, String price) {
        this.dateTime = dateTime;
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "DeliverySlot{" +
                "dateTime=" + dateTime +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliverySlot that = (DeliverySlot) o;
        return Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, price);
    }

}
