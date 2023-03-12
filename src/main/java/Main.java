import com.fasterxml.jackson.databind.ObjectMapper;
import entries.Ticket;
import entries.Tickets;
import services.TicketService;
import services.TimeService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Tickets tickets;
        String origin = "VVO";
        String destination = "TLV";
        String fileName = "tickets.json";
        int percentile = 90;


        String pathToFile = getFilePath(fileName);

        if (pathToFile == null) {
            return;
        }

        try {
            tickets = new ObjectMapper().readValue(new File(pathToFile + fileName), Tickets.class);
        } catch (IOException e) {
            System.out.println("Невозможно найти файл по следующему пути: " + pathToFile + fileName);
            return;
        }

        long avgDuration = getAvgDuration(tickets, origin, destination);

        long percentileDuration = getPercentileDuration(tickets, percentile, origin, destination);

        System.out.println("среднее время полета: " + TimeService.getHoursFromMinutes(avgDuration) + " часов и "
                + TimeService.getRemainingMinutes(avgDuration) + " минуты (" + avgDuration + " минуты);");

        System.out.println("90-й процентиль времени полета: " + TimeService.getHoursFromMinutes(percentileDuration)
                + " часов и "
                + TimeService.getRemainingMinutes(percentileDuration) + " минуты (" + percentileDuration + " минуты);");
    }

    private static long getAvgDuration(Tickets tickets, String origin, String destination) {
        long duration = 0;
        int flightsCount = 0;

        for (Ticket ticket : tickets.getTickets()) {
            if (ticket.getOrigin().equals(origin) && ticket.getDestination().equals(destination)) {
                duration += TicketService.getDuration(ticket);
                flightsCount++;
            }
        }

        return duration / flightsCount;
    }

    private static long getPercentileDuration(Tickets tickets, int percentile, String origin, String destination) {
        List<Ticket> ticketsToWork = new ArrayList<>(tickets.getTickets());
        double allPercent = 100;

        ticketsToWork.removeIf(ticket -> !ticket.getOrigin().equals(origin) && !ticket.getDestination().equals(destination));

        ticketsToWork.sort((o1, o2) -> (int) (TicketService.getDuration(o1) - TicketService.getDuration(o2)));

        int correctIndex = 1;
        int indexOfPercent = (int) Math.round(ticketsToWork.size() * (percentile / allPercent) - correctIndex);

        Ticket ticket = ticketsToWork.get(indexOfPercent);

        return TicketService.getDuration(ticket);
    }

    private static String getFilePath(String fileName) {
        System.out.println("Введите путь до файла " + fileName);
        String path;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            path = reader.readLine();
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время ввода");
            return null;
        }
        return path;
    }
}
