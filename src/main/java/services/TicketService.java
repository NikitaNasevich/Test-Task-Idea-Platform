package services;

import entries.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketService {

    public static long getDuration(Ticket ticket) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyH:mm");

        String departureDateString = ticket.getDepartureDate() + ticket.getDepartureTime();
        LocalDateTime dateTimeDeparture = LocalDateTime.parse(departureDateString, formatter);

        String arrivalDateString = ticket.getArrivalDate() + ticket.getArrivalTime();
        LocalDateTime dateTimeArrival = LocalDateTime.parse(arrivalDateString, formatter);

        return Duration.between(dateTimeDeparture, dateTimeArrival).toMinutes();
    }

}
