import java.util.ArrayList;
import java.time.LocalDate;

abstract class Staff {
    protected String username;
    protected String password;
    protected LocalDate dateOfBirth;
    protected Role role;
    protected int workingHours;

    public Staff(String username, String password, LocalDate dob, Role role, int hours) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dob;
        this.role = role;
        this.workingHours = hours;
    }
}

class Admin extends Staff {
    public Admin(String username, String password, LocalDate dob, int hours) {
        super(username, password, dob, Role.ADMIN, hours);
    }

    // CRUD for Room Types [cite: 63]
    public void addRoomType(RoomType rt) { HotelDatabase.roomTypes.add(rt); }
    public void deleteRoomType(RoomType rt) { HotelDatabase.roomTypes.remove(rt); }

    // CRUD for Amenities [cite: 63]
    public void addAmenity(Amenity a) { HotelDatabase.amenities.add(a); }

    // CRUD for Rooms [cite: 57]
    public void addRoom(Room r) { HotelDatabase.rooms.add(r); }
    public void updateRoom(int index, Room newRoom) {
        if(index >= 0 && index < HotelDatabase.rooms.size()) {
            HotelDatabase.rooms.set(index, newRoom);
        }
    }
}

public class HotelDatabase {
    public static ArrayList<Guest> guests = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Reservation> reservations = new ArrayList<>();
    public static ArrayList<Invoice> invoices = new ArrayList<>();

    // Added for Milestone 1 [cite: 63]
    public static ArrayList<RoomType> roomTypes = new ArrayList<>();
    public static ArrayList<Amenity> amenities = new ArrayList<>();

    public static void loaddata() {


        roomTypes.add(new RoomType("Single", 100.0));
        amenities.add(new Amenity("WiFi"));
    }
}
