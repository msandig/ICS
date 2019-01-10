package de.dhbw.ics.testdata;

import de.dhbw.ics.vo.Room;
import de.dhbw.ics.vo.Seat;
import de.dhbw.ics.vo.SeatCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@SuppressWarnings("Duplicates")
public class Generator {

    private static final Logger LOG = LoggerFactory.getLogger(Generator.class);

    public static void main(String[] args) {
        generatePriceCategorys();
    }

    private static void generatePriceCategorys(){

        String[] presentationCat = {"7ec80e73-2e86-41cf-b5e9-f25b2975ead8", "7ec80e73-2e86-41cf-b5e9-f25b2975ead3"};
        String[] seatCat = {"ef688fe2-af15-481f-a8d5-0208a5c5564e", "ef688fe2-af15-481f-a8d5-0208a5c55624", "ef688fe2-af15-481f-a8d5-0208a5c55vfe", "ef688fe2-af15-481f-a8d5-0208a5c55wqe"};
        Map<String, String> priceMap = new HashMap<>();
        priceMap.put("Erwachsener", "");
        priceMap.put("Kind", "unter 12 Jahren");
        priceMap.put("Ermäßigt", "Schüler, körperlich oder geistig eingeschränkte Menschen");


    }

    private static void generateTimes() {
        int[] h = {2, 5, 8, 9, 10};
        int[] m = {0, 30};
        for (int i = 11; i < 20; i++) {
            for (int j = 0; j < h.length; j++) {
                for (int z = 0; z < m.length; z++) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, 2019);
                    calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
                    calendar.set(Calendar.DAY_OF_MONTH, i);
                    calendar.set(Calendar.HOUR, h[j]);
                    calendar.set(Calendar.MINUTE, m[z]);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.AM_PM, 1);
                    System.out.println(calendar.getTime().toString() + ": " + calendar.getTimeInMillis());
                }
            }
        }
    }

    private static void generateRooms() {
        int s = 1;
        List<Room> roomList = new ArrayList<>();
        while (s <=8) {
            Room room = new Room("3D Raum", true, true, s);
            LOG.info("INSERT INTO ROOM(room_uuid, room_type, clean, vip_seats, room_number) VALUES ('{}', '{}', {}, {}, {});", room.getUuid(), room.getRoomType(), room.isClean(), room.isVIP(), room.getNumber());
            roomList.add(room);
            s++;
        }

        for(Room room : roomList) {
            LOG.info("");
            Random ran = new Random();
            SeatCategory seatCategory = new SeatCategory("", "");
            String comfort = "ef688fe2-af15-481f-a8d5-0208a5c5564e";
            String premium = "ef688fe2-af15-481f-a8d5-0208a5c55vfe";
            String normal = "ef688fe2-af15-481f-a8d5-0208a5c55624";
            String wheelchair = "ef688fe2-af15-481f-a8d5-0208a5c55wqe";
            int x = ran.nextInt((5) + 1) + 10;
            int y = ran.nextInt((2) + 1) + 20;

            for (int i = 1; i < x; i++) {
                for (int j = 1; j <= y; j++) {
                    Seat seat = new Seat(room, seatCategory, j, i);
                    room.getSeats().put(seat.getUuid(), seat);
                    if(i+1 == x && j + 2 > y) {
                        LOG.info("INSERT INTO SEAT(seat_uuid, row, number, seatcat_uuid, room_uuid) VALUES ('{}', {}, {}, '{}', '{}');", seat.getUuid(), seat.getRow(), seat.getNumber(), wheelchair , seat.getRoom().getUuid());
                    } else if (i == x/2){
                        LOG.info("INSERT INTO SEAT(seat_uuid, row, number, seatcat_uuid, room_uuid) VALUES ('{}', {}, {}, '{}', '{}');", seat.getUuid(), seat.getRow(), seat.getNumber(), comfort , seat.getRoom().getUuid());
                    } else if (i == x/2 +1) {
                        LOG.info("INSERT INTO SEAT(seat_uuid, row, number, seatcat_uuid, room_uuid) VALUES ('{}', {}, {}, '{}', '{}');", seat.getUuid(), seat.getRow(), seat.getNumber(), premium , seat.getRoom().getUuid());
                    } else {
                        LOG.info("INSERT INTO SEAT(seat_uuid, row, number, seatcat_uuid, room_uuid) VALUES ('{}', {}, {}, '{}', '{}');", seat.getUuid(), seat.getRow(), seat.getNumber(), normal , seat.getRoom().getUuid());
                    }
                }
            }
        }
    }

}
