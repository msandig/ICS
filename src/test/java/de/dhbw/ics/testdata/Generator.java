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
        generatePresentations();
    }

    private static void generatePresentations() {
        String[] movies = {"06669d87-9d98-4c7b-9b64-18367e9f8f36",
                "feace4d4-d3e5-4334-9870-5bf209fc881f",
                "e6e0f02a-dcad-4316-bad0-7099035f2c6e",
                "93afc908-09e4-47ba-803c-b2eb2b86a7ae",
                "9d496b2f-91d1-4498-86aa-27ef6824423e",
                "3f090002-688e-4969-bda6-d906f8defbbf",
                "9681fb89-70f7-43a3-9d79-deccb54aa9d9",
                "9b7bb5bd-6757-4b55-9af5-6d3ff1580042"};

        String[] rooms = {"deac0c4b-edc9-41e6-8987-790b3e7baf99",
                "7ec72af5-aa90-4a3e-83c4-26da188d627a",
                "7680ffb5-aa1c-4c1e-8e99-20396abed920",
                "51f058c0-5e32-42d8-a4a9-428f4cee42d4",
                "a39ebb2d-03fe-4c79-83dc-85bb0d33ce48",
                "e8804702-9168-4c76-bbd4-fd7f7912bf1b",
                "7d31bebc-b906-4d1b-9743-5773f6449a00",
                "7b4fe9df-b6be-48ca-9db9-da4f6e40c687"};

        String[] presentationCat = {"7ec80e73-2e86-41cf-b5e9-f25b2975ead8", "7ec80e73-2e86-41cf-b5e9-f25b2975ead3"};


        List<Long> times = generateTimes();
        for (int i = 0; i < movies.length; i++) {
            for (int j = 0; j <times.size(); j++) {
                if (j % 2 == 1) {
                    LOG.info("INSERT INTO PRESENTATION (pres_uuid, movie_uuid, prescat_uuid, room_uuid, date) VALUES ('{}', '{}', '{}', '{}', {});", UUID.randomUUID().toString(), movies[i], presentationCat[1], rooms[i], times.get(j));
                } else {
                    LOG.info("INSERT INTO PRESENTATION (pres_uuid, movie_uuid, prescat_uuid, room_uuid, date) VALUES ('{}', '{}', '{}', '{}', {});", UUID.randomUUID().toString(), movies[i], presentationCat[0], rooms[i], times.get(j) );
                }

            }
        }
    }

    private static void generatePriceCategorys() {

        String[] presentationCat = {"7ec80e73-2e86-41cf-b5e9-f25b2975ead8", "7ec80e73-2e86-41cf-b5e9-f25b2975ead3"};
        String[] seatCat = {"ef688fe2-af15-481f-a8d5-0208a5c5564e", "ef688fe2-af15-481f-a8d5-0208a5c55624", "ef688fe2-af15-481f-a8d5-0208a5c55vfe", "ef688fe2-af15-481f-a8d5-0208a5c55wqe"};
        Map<String, String> priceMap = new HashMap<>();
        priceMap.put("Erwachsener", "");
        priceMap.put("Kind", "unter 12 Jahren");
        priceMap.put("Ermäßigt", "Schüler, körperlich oder geistig eingeschränkte Menschen");

        for (String key : priceMap.keySet()) {

            for (String pres : presentationCat) {
                for (String seatc : seatCat) {
                    LOG.info("INSERT INTO PRICE_CATEGORY (pricecat_uuid, prescat_uuid, seatcat_uuid, pricecat_title, pricecat_description, price) VALUES ('{}', '{}', '{}', '{}', '{}', {});",
                            UUID.randomUUID().toString(), pres, seatc, key, priceMap.get(key), "''"
                    );
                }
            }
        }


    }

    private static List<Long> generateTimes() {
        int[] h = {2, 4, 7, 9};
        int[] m = {0, 30};
        List<Long> times = new ArrayList<>();
        for (int i = 11; i < 22; i++) {
            for (int j = 0; j < h.length; j++) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, 2019);
                calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
                calendar.set(Calendar.DAY_OF_MONTH, i);
                calendar.set(Calendar.HOUR, h[j]);
                if (j % 2 == 1) {
                    calendar.set(Calendar.MINUTE, m[1]);
                } else {
                    calendar.set(Calendar.MINUTE, m[0]);
                }

                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.AM_PM, 1);
                times.add(calendar.getTimeInMillis());
                System.out.println(calendar.getTime().toString() + ": " + calendar.getTimeInMillis());

            }
        }
        return times;
    }

    private static void generateRooms() {
        int s = 1;
        List<Room> roomList = new ArrayList<>();
        while (s <= 8) {
            Room room = new Room("3D Raum", true, true, s);
            LOG.info("INSERT INTO ROOM(room_uuid, room_type, clean, vip_seats, room_number) VALUES ('{}', '{}', {}, {}, {});", room.getUuid(), room.getRoomType(), room.isClean(), room.isVIP(), room.getNumber());
            roomList.add(room);
            s++;
        }

        for (Room room : roomList) {
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
                    if (i + 1 == x && j + 2 > y) {
                        LOG.info("INSERT INTO SEAT(seat_uuid, row, number, seatcat_uuid, room_uuid) VALUES ('{}', {}, {}, '{}', '{}');", seat.getUuid(), seat.getRow(), seat.getNumber(), wheelchair, seat.getRoom().getUuid());
                    } else if (i == x / 2) {
                        LOG.info("INSERT INTO SEAT(seat_uuid, row, number, seatcat_uuid, room_uuid) VALUES ('{}', {}, {}, '{}', '{}');", seat.getUuid(), seat.getRow(), seat.getNumber(), comfort, seat.getRoom().getUuid());
                    } else if (i == x / 2 + 1) {
                        LOG.info("INSERT INTO SEAT(seat_uuid, row, number, seatcat_uuid, room_uuid) VALUES ('{}', {}, {}, '{}', '{}');", seat.getUuid(), seat.getRow(), seat.getNumber(), premium, seat.getRoom().getUuid());
                    } else {
                        LOG.info("INSERT INTO SEAT(seat_uuid, row, number, seatcat_uuid, room_uuid) VALUES ('{}', {}, {}, '{}', '{}');", seat.getUuid(), seat.getRow(), seat.getNumber(), normal, seat.getRoom().getUuid());
                    }
                }
            }
        }
    }

}
