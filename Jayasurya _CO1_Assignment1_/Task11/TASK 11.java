
import java.util.Arrays;

class ParkingFacility {
    private final String[][] slots;

    ParkingFacility(int[] slotsPerFloor) {
        slots = new String[slotsPerFloor.length][];

        for (int f = 0; f < slotsPerFloor.length; f++) {
            slots[f] = new String[slotsPerFloor[f]];
        }
    }

    int[] allocate(String vehicleNo) {
        for (int f = 0; f < slots.length; f++) {
            for (int s = 0; s < slots[f].length; s++) {
                if (slots[f][s] == null) {
                    slots[f][s] = vehicleNo;
                    return new int[]{f, s};
                }
            }
        }
        return null;
    }

    boolean release(int floor, int slot) {
        if (floor < 0 || floor >= slots.length)
            return false;

        if (slot < 0 || slot >= slots[floor].length)
            return false;

        if (slots[floor][slot] == null)
            return false;

        slots[floor][slot] = null;
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingFacility p =
            new ParkingFacility(new int[]{1, 1});

        System.out.println("Test 1 - Allocate: " +
            Arrays.toString(p.allocate("TN01AB1234")));

        System.out.println("Test 2 - Allocate: " +
            Arrays.toString(p.allocate("TN02CD5678")));

        System.out.println("Test 3 - Full Facility: " +
            p.allocate("TN03EF9012"));

        System.out.println("Test 4 - Release: " +
            p.release(0, 0));

        System.out.println("Test 5 - Repeated Release: " +
            p.release(0, 0));

        System.out.println("Test 6 - Invalid Position: " +
            p.release(5, 0));

        System.out.println("Test 7 - Reallocate: " +
            Arrays.toString(p.allocate("TN03EF9012")));
    }
}
