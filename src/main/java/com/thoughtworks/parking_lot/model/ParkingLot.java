package com.thoughtworks.parking_lot.model;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true, length = 30)
    private String name;

    @Column(name = "capacity", length = 6)
    @Min(0)
    private int capacity;

    @Column(name = "position")
    private String position;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @OneToMany(mappedBy = "parkingLot")
    private List<Order> orders;


    public ParkingLot() {
    }

    public ParkingLot(String name, @Min(0) int capacity, String position) {
        this.name = name;
        this.capacity = capacity;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
