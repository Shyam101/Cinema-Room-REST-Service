package cinema;

import cinema.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
public class Controller {

    Cinema cinema = new Cinema(9,9);

    @GetMapping("/seats")
    public Map<String,Object> getSeats() {

        return Map.of(
                "total_rows", cinema.getTotal_rows(),
                "total_columns", cinema.getTotal_columns(),
                "available_seats", cinema.getAvailableSeats()
        );
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Map<String, Integer> request) {
        int row = request.get("row");
        int col = request.get("column");

        if (row < 1 || row > cinema.getTotal_rows()
                || col < 1 || col > cinema.getTotal_columns()) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        } else if (!cinema.canBookTicket(row, col)) {
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        } else {
            Pair<UUID, Seat> bookingDetails = cinema.bookTicket(row,col);

            return new ResponseEntity<>(Map.of(
                    "token", bookingDetails.getFirst(),
                    "ticket", bookingDetails.getSecond()
            ), HttpStatus.OK);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Map<String,UUID> request) {
        Seat returnedSeat = cinema.cancelTicket(request.get("token"));
        if (returnedSeat == null) {
            return new ResponseEntity<>(Map.of("error","Wrong token!"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Map.of("returned_ticket",returnedSeat), HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(value="password", required = false) String password) {
        if ("super_secret".equals(password)) {
            return new ResponseEntity<>(Map.of(
                    "current_income",cinema.profit(),
                    "number_of_available_seats", cinema.numberOfAvailabeSeats(),
                    "number_of_purchased_tickets", cinema.numberOfBookedSeats()
            ),HttpStatus.OK);
        }

        return new ResponseEntity<>(Map.of("error","The password is wrong!"),HttpStatus.UNAUTHORIZED);
    }
}
