INSERT INTO VENUE (ID, LINK_MORE_INFO, VENUE_NAME, CAPACITY,
                   FOOD_PROVIDED, INDOOR, OUTDOOR, FREE_PARKING_AVAILABLE, CITY,
                   DISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM)
values (1, 'https://www.sportpaleis.be/en/calendar?venue=AS',
        'sportpaleis', 20, true, true,
        false, false, 'Antwerpen',
        15);
INSERT INTO CLIENT (ID, NAME, NR_OF_ORDERS, TOTAL_AMOUNT,
                    DISCOUNT_TAKEN)
VALUES (1, 'Ilya', 80, 10,
        2.1);