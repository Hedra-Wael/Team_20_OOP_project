import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;

class Guest{ /*dummy class اشتغلوا عليها */
    String name ;
    String reference;
}

class Room {/*dummy class نفس الكلام  */
    int roomNum ;
    double pricePerNight;
     String reference;
}

 class Reservation { /*task member 3 */
    private int Days ;
    private Guest guest =new Guest(); 
    private Room room =new Room();   
    private LocalDate checkInDay;
    private LocalDate checkOutDay;
   public int getDays(LocalDate S ,LocalDate E ) {
    int startDay = S.getDayOfMonth(); 
    int endDay = E.getDayOfMonth();
    return endDay-startDay;
    /*incomplete */
   }
   
    private ReservationStatue status;
    public Reservation(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.guest = guest;
        this.room = room;
        this.checkInDay = checkIn;
        this.checkOutDay = checkOut;
        this.status = ReservationStatue.PENDING; 
        Days = getDays(checkIn,checkOut);
    }
    private enum ReservationStatue{
        PENDING,
        CONFIRMED,
        CANCELLED,
        COMPLETED;
    }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public LocalDate getCheckInDay() { return checkInDay; }
    public LocalDate getCheckOutDay() { return checkOutDay; }
    public ReservationStatue getStatus() { return status; }
    public void setStatus(ReservationStatue status) { this.status = status; }
}
class Invoice  {
   private  enum PaymentMethod{
       cash,
       credit_card,
       online;
   }
   
   private final LocalDate paymentDate =localDate.now();
   private Reservation reservation = new Reservation() ; /*not finished*/
   Invoice (Reservation reservation)
   {
       this.reservation= reservation ;
   }
  public printInvoce (){
      System.out.println ("||=========Invoice=========||");
      System.out.println ("||guest name :" + reservation.guest.name + "   ||");
      System.out.println (" total price = " + reservation.room.pricePerNight*reservation.Days + "EGP" );
      System.out.println("Payment Method: " + paymentMethod); /*same here */
      System.out.println("Date: " + paymentDate);
  }
  
public static void Main (String asrg[])
{
  
}
