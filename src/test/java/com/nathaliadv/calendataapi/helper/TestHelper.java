package com.nathaliadv.calendataapi.helper;

import com.nathaliadv.calendataapi.core.model.HolidayResponse;

import java.time.LocalDate;
import java.util.List;

public class TestHelper {

    public static List<HolidayResponse> createHolidayResponses() {
       return List.of(
                new HolidayResponse(LocalDate.of(2025, 1, 1), "Nieuwjaarsdag", "New Year's Day"),
               new HolidayResponse(LocalDate.of(2025, 5, 5), "Bevrijdingsdag", "Liberation Day"),
               new HolidayResponse(LocalDate.of(2025, 5, 29), "Hemelvaartsdag", "Ascension Day"),
               new HolidayResponse(LocalDate.of(2025, 6, 8), "Eerste Pinksterdag", "Pentecost"),
               new HolidayResponse(LocalDate.of(2025, 6, 9), "Tweede Pinksterdag", "Whit Monday"),
                new HolidayResponse(LocalDate.of(2025, 4, 20), "Eerste Paasdag", "Easter Sunday"),
                new HolidayResponse(LocalDate.of(2025, 4, 21), "Tweede Paasdag", "Easter Monday"),
                new HolidayResponse(LocalDate.of(2025, 4, 26), "Koningsdag", "King's Day"),
                new HolidayResponse(LocalDate.of(2025, 12, 25), "Eerste Kerstdag", "Christmas Day"),
                new HolidayResponse(LocalDate.of(2025, 12, 26), "Tweede Kerstdag", "St. Stephen's Day"),
               new HolidayResponse(LocalDate.of(2025, 4, 18), "Goede Vrijdag", "Good Friday")
        );
    }

    public static List<HolidayResponse> createPreviousYearHolidayResponses() {
        return List.of(
                new HolidayResponse(LocalDate.of(2024, 1, 1), "Nieuwjaarsdag", "New Year's Day"),
                new HolidayResponse(LocalDate.of(2024, 3, 29), "Goede Vrijdag", "Good Friday"),
                new HolidayResponse(LocalDate.of(2024, 3, 31), "Eerste Paasdag", "Easter Sunday"),
                new HolidayResponse(LocalDate.of(2024, 4, 1), "Tweede Paasdag", "Easter Monday"),
                new HolidayResponse(LocalDate.of(2024, 4, 27), "Koningsdag", "King's Day"),
                new HolidayResponse(LocalDate.of(2024, 5, 5), "Bevrijdingsdag", "Liberation Day"),
                new HolidayResponse(LocalDate.of(2024, 5, 9), "Hemelvaartsdag", "Ascension Day"),
                new HolidayResponse(LocalDate.of(2024, 5, 19), "Eerste Pinksterdag", "Pentecost"),
                new HolidayResponse(LocalDate.of(2024, 5, 20), "Tweede Pinksterdag", "Whit Monday"),
                new HolidayResponse(LocalDate.of(2024, 12, 25), "Eerste Kerstdag", "Christmas Day"),
                new HolidayResponse(LocalDate.of(2024, 12, 26), "Tweede Kerstdag", "St. Stephen's Day")
        );
    }

    public static List<HolidayResponse> createHolidayResponsesMX() {
        return List.of(
                new HolidayResponse(LocalDate.of(2025, 1, 1), "Año Nuevo", "New Year's Day"),
                new HolidayResponse(LocalDate.of(2025, 2, 3), "Día de la Constitución", "Constitution Day"),
                new HolidayResponse(LocalDate.of(2025, 3, 17), "Natalicio de Benito Juárez", "Benito Juárez's birthday"),
                new HolidayResponse(LocalDate.of(2025, 4, 17), "Jueves Santo", "Maundy Thursday"),
                new HolidayResponse(LocalDate.of(2025, 4, 18), "Viernes Santo", "Good Friday"),
                new HolidayResponse(LocalDate.of(2025, 5, 1), "Día del Trabajo", "Labor Day"),
                new HolidayResponse(LocalDate.of(2025, 9, 16), "Día de la Independencia", "Independence Day"),
                new HolidayResponse(LocalDate.of(2025, 11, 17), "Día de la Revolución", "Revolution Day"),
                new HolidayResponse(LocalDate.of(2025, 12, 25), "Navidad", "Christmas Day")
        );
    }

    public static List<HolidayResponse> createHolidayResponsesFakeCountry() {
        return List.of(
                new HolidayResponse(LocalDate.of(2025, 1, 10), "Fake Local Name 1", "Fake Name 1"),
                new HolidayResponse(LocalDate.of(2025, 6, 10), "Fake Local Name 2", "Fake Name 1"),
                new HolidayResponse(LocalDate.of(2025, 12, 10), "Fake Local Name 3", "Fake Name 3")
        );
    }
}
