package dishesApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api")
public class DishController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private DishRepository dishRepository;

    @GetMapping("/dishes")
    public ResponseEntity<List<Dish>> getAllDishes() {
        List<Dish> dishes = dishRepository.findAll();
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/reserve/{tableNumber}/dishes")
    public ResponseEntity<List<Dish>> getDishesForTable(@PathVariable int tableNumber) {
        Optional<Reservation> optionalReservation = reservationRepository.findByTableNumber(tableNumber);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            List<Dish> dishes = reservation.getSelectedDishes();
            return ResponseEntity.ok(dishes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/reserve/{tableNumber}/dishes")
    public ResponseEntity<String> updateDishesForTable(
            @PathVariable int tableNumber,
            @RequestBody List<Long> dishIds) {

        Optional<Reservation> optionalReservation = reservationRepository.findByTableNumber(tableNumber);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            List<Dish> selectedDishes = dishRepository.findAllById(dishIds);
            reservation.setSelectedDishes(selectedDishes);
            reservationRepository.save(reservation);
            return ResponseEntity.ok("Dishes updated for table " + tableNumber);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}