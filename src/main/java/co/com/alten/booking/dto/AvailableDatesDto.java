package co.com.alten.booking.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDatesDto {
    LocalDate startDate;
    LocalDate endDate;
    String documentCustomer;
}
