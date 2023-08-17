package dishesApp;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tableNumber;

    @ManyToMany
    private List<Dish> selectedDishes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<Dish> getSelectedDishes() {
        return selectedDishes;
    }

    public void setSelectedDishes(List<Dish> selectedDishes) {
        this.selectedDishes = selectedDishes;
    }
}