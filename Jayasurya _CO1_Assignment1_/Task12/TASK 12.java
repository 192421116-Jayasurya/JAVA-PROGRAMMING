
import java.time.LocalDate;
import java.time.LocalTime;

abstract class Vehicle {
    String vehicleNo;

    Vehicle(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    abstract double calculateFee(LocalTime entryTime);
}

class Car extends Vehicle {
    Car(String vehicleNo) {
        super(vehicleNo);
    }

    @Override
    double calculateFee(LocalTime entryTime) {
        return 90.0;
    }
}

class ElectricBike extends Vehicle {
    ElectricBike(String vehicleNo) {
        super(vehicleNo);
    }

    @Override
    double calculateFee(LocalTime entryTime) {
        return 25.0; // Fixed fee
    }
}

class DeliveryVan extends Vehicle {
    DeliveryVan(String vehicleNo) {
        super(vehicleNo);
    }

    @Override
    double calculateFee(LocalTime entryTime) {
        double carRate = 90.0;

        if (!entryTime.isBefore(LocalTime.of(9, 0))
                && entryTime.isBefore(LocalTime.of(17, 0))) {
            return carRate * 2;
        }

        return carRate;
    }
}

class VisitorPass {
    private final String visitorName;
    private final LocalDate issueDate;

    VisitorPass(String visitorName, LocalDate issueDate) {
        this.visitorName = visitorName;
        this.issueDate = issueDate;
    }

    boolean isValid(LocalDate date) {
        return date.equals(issueDate);
    }

    void display() {
        System.out.println("Visitor: " + visitorName);
        System.out.println("Pass Valid for Issue Date: "
                + isValid(issueDate));
    }
}

class ParkingFacility {
    private final String[][] slots;

    ParkingFacility(int[] slotsPerFloor) {
        slots = new String[slotsPerFloor.length][];

        for (int i = 0; i < slotsPerFloor.length; i++) {
            slots[i] = new String[slotsPerFloor[i]];
        }
    }

    int getFloorCount() {
        return slots.length;
    }

    int getSlotCount(int floor) {
        if (floor < 0 || floor >= slots.length) {
            return -1;
        }
        return slots[floor].length;
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle bike = new ElectricBike("EB101");
        Vehicle van = new DeliveryVan("DV202");
        Vehicle car = new Car("CR303");

        LocalTime peakTime = LocalTime.of(10, 30);
        LocalTime offPeakTime = LocalTime.of(18, 0);

        System.out.println("Electric Bike Fee: Rs "
                + bike.calculateFee(peakTime));

        System.out.println("Delivery Van Fee (10:30 AM): Rs "
                + van.calculateFee(peakTime));

        System.out.println("Delivery Van Fee (6:00 PM): Rs "
                + van.calculateFee(offPeakTime));

        VisitorPass pass = new VisitorPass(
                "Campus Visitor", LocalDate.of(2026, 10, 26));

        pass.display();

        ParkingFacility parking = new ParkingFacility(
                new int[]{10, 15, 12, 8, 20});

        System.out.println("Number of Floors: "
                + parking.getFloorCount());

        for (int i = 0; i < parking.getFloorCount(); i++) {
            System.out.println("Floor " + (i + 1)
                    + " Slots: " + parking.getSlotCount(i));
        }
    }
}
