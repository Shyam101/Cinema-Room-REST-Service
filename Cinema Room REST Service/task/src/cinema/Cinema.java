package cinema;

import cinema.util.Pair;

import java.util.*;

public class Cinema {
    private final int total_rows;
    private final int total_columns;
    int[][] seats;
    int total_profit;
    Map<UUID, Seat> bookedSeats;

    public Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.total_profit = 0;
        this.bookedSeats = new HashMap<>();
        this.seats = new int[total_rows][total_columns];

        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                seats[i-1][j-1] = i <= 4 ? 10 : 8;
            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> seatList = new ArrayList<>();

        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                seatList.add(new Seat(i, j, seats[i-1][j-1]));
            }
        }
        return seatList;
    }

    public boolean canBookTicket(int row, int col) {
        return seats[row-1][col-1] != -1;
    }

    public Pair<UUID,Seat> bookTicket(int row, int col) { //will return token and ticket number
        Seat seat = new Seat(row, col, seats[row-1][col-1]);
        seats[row-1][col-1] = -1;
        UUID token = UUID.randomUUID();
        total_profit += seat.getPrice();
        bookedSeats.put(token, seat);
        return new Pair<>(token, seat);
    }

    public Seat cancelTicket(UUID token) {
        Seat seat = bookedSeats.get(token);
        if (seat != null) {
            total_profit -= seat.getPrice();
            seats[seat.getRow()-1][seat.getColumn()-1] = seat.getPrice();
            bookedSeats.remove(token);
        }
        return seat;
    }

    public int numberOfAvailabeSeats() {
        return total_columns*total_rows - bookedSeats.size();
    }

    public int numberOfBookedSeats() {
        return bookedSeats.size();
    }

    public int profit() {
        return total_profit;
    }
}
