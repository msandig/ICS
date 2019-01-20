package de.dhbw.ics.vo;


import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import de.dhbw.ics.vo.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class voTest {

    private static BusySeat busySeat;
    private static Genre genre;
    private static Movie movie;
    private static PaymentMethod paymentMethod;
    private static Presentation presentation;
    private static PresentationCategory presentationCategory;
    private static PriceCategory priceCategory;
    private static Reservation reservation;
    private static Role role;
    private static Room room;
    private static Seat seat;
    private static SeatCategory seatCategory;
    private static Ticket ticket;
    private static User user;

    @Test
    public void test1Genre(){
        String uuid = UUID.randomUUID().toString();
        String name = "Test Genre";
        genre = new Genre(uuid,name);
        assertEquals(uuid,genre.getUuid());
        assertEquals(name,genre.getName());
        Genre genreCopy = new Genre(genre);
        assertTrue(genre.equals(genre));
        assertTrue(genre.equals(genreCopy));
    }

    @Test
    public void test2Movie(){
        String uuid = UUID.randomUUID().toString();
        int productionYear = 2018;
        String title = "Test Movie";
        String description = "This is a movie for unite testing!";
        int fsk = 12;
        int runTime = 145;
        movie = new Movie(uuid,productionYear,title,description,fsk,runTime);
        movie.setGenre(genre);
        assertEquals(uuid,movie.getUuid());
        assertEquals(title,movie.getTitle());
        assertTrue(movie.getTitle().equals(title));
        assertTrue(movie.getDescription().equals(description));
        assertTrue(fsk==movie.getFsk());
        assertTrue(runTime == movie.getRunTime());
        assertTrue(productionYear == movie.getProductionYear());
        Movie movieCopy = new Movie(movie);
        assertTrue(movie.equals(movie));
        assertTrue(movie.equals(movieCopy));
    }

    @Test
    public void test3Room(){
        String uuid = UUID.randomUUID().toString();
        String roomType = "3D Saal";
        boolean isClean = false;
        boolean isVip = true;
        int number = 12;
        room = new Room(uuid,roomType,isClean,isVip,number);
        assertTrue(uuid == room.getUuid() && roomType == room.getRoomType() && isClean == room.isClean()
                && isVip == room.isVIP() && number == room.getNumber());
        Room roomCopy = new Room(room);
        assertTrue(room.equals(room) && room.equals(roomCopy));
    }

    @Test
    public void test4SeatCategory(){
        String uuid = UUID.randomUUID().toString();
        String title = "Paarsitz";
        String description = "Doppelsitz";
        seatCategory = new SeatCategory(uuid,title,description);
        assertTrue(uuid == seatCategory.getUuid() && title == seatCategory.getTitle() &&
                description == seatCategory.getDescription());
        SeatCategory seatCategoryCopy = new SeatCategory(seatCategory);
        assertTrue(seatCategory.equals(seatCategory) && seatCategory.equals(seatCategoryCopy));
    }

    @Test
    public void test5Seat(){
        String uuid = UUID.randomUUID().toString();
        Integer number = 12;
        Integer row = 23;
        seat = new Seat(uuid,room,seatCategory,number,row);
        assertTrue(uuid == seat.getUuid() && number == seat.getNumber() && row == seat.getRow());
        assertTrue(room.equals(seat.getRoom()) && seatCategory.equals(seat.getSeatCategory()));
        Seat seatCopy = new Seat(seat);
        assertTrue(seat.equals(seat) && seat.equals(seatCopy));
    }

    @Test
    public void test6Role(){
        String uuid = UUID.randomUUID().toString();
        String title = "Benutzer";
        role = new Role(uuid,title);
        assertTrue(role.getUuid().equals(uuid) && role.getTitle().equals(title));
        Role roleCopy = new Role(role);
        assertTrue(role.equals(role) && role.equals(roleCopy));
    }

    @Test
    public void test7PaymentMethod(){
        String uuid = UUID.randomUUID().toString();
        String description = "PayPal for Germany";
        String provider = "PayPal";
        paymentMethod = new PaymentMethod(uuid,description,provider);
        assertTrue(uuid.equals(paymentMethod.getUuid()) && description.equals(paymentMethod.getDescription())
                && provider.equals(paymentMethod.getProvider()));
        PaymentMethod paymentMethodCopy = new PaymentMethod(paymentMethod);
        assertTrue(paymentMethod.equals(paymentMethod) && paymentMethod.equals(paymentMethodCopy));
    }

    @Test
    public void test8User(){
        String uuid = UUID.randomUUID().toString();
        String email = "nutzer@provider.com";
        String password = "megaGeheim__123!";
        user = new User(uuid,email,role,password);
        assertTrue(uuid.equals(user.getUuid()) && email.equals(user.getEmail()) && password.equals(user.getPassword()));
        User userCopy = new User(user);
        assertTrue(user.equals(user) && user.equals(userCopy));
    }

    @Test
    public void test9PresentationCategory(){
        String uuid = UUID.randomUUID().toString();
        String title = "Avatar in 3D";
        String description = "Ein Film in 3D!";
        presentationCategory = new PresentationCategory(uuid,title,description);
        assertTrue(presentationCategory.getUuid().equals(uuid) && presentationCategory.getDescription().equals(description)
                && presentationCategory.getTitle().equals(description));
        PresentationCategory presentationCategoryCopy = new PresentationCategory(presentationCategory);
        assertTrue(presentationCategory.equals(presentationCategory) && presentationCategory.equals(presentationCategoryCopy));
    }

    @Test
    public void test__PriceCategory(){
        String uuid = UUID.randomUUID().toString();
        String title = "Vormittags Avatar in 3D";
        String description = "Ein 3D Film";
        BigDecimal price = BigDecimal.valueOf(12.34);
        priceCategory = new PriceCategory(uuid,presentationCategory,seatCategory,title,description,price);
    }
}
