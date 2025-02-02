package com.nathaliadv.calendataapi.helper;

import com.nathaliadv.calendataapi.core.model.HolidayResponse;

import java.time.LocalDate;
import java.util.List;

public class TestHelper {

    public static List<HolidayResponse> createHolidayResponses() {
       return List.of(
                new HolidayResponse(LocalDate.of(2025, 1, 1), "Nieuwjaarsdag", "New Year's Day"),
                new HolidayResponse(LocalDate.of(2025, 4, 18), "Goede Vrijdag", "Good Friday"),
                new HolidayResponse(LocalDate.of(2025, 4, 20), "Eerste Paasdag", "Easter Sunday"),
                new HolidayResponse(LocalDate.of(2025, 4, 21), "Tweede Paasdag", "Easter Monday"),
                new HolidayResponse(LocalDate.of(2025, 4, 26), "Koningsdag", "King's Day"),
                new HolidayResponse(LocalDate.of(2025, 5, 5), "Bevrijdingsdag", "Liberation Day"),
                new HolidayResponse(LocalDate.of(2025, 5, 29), "Hemelvaartsdag", "Ascension Day"),
                new HolidayResponse(LocalDate.of(2025, 6, 8), "Eerste Pinksterdag", "Pentecost"),
                new HolidayResponse(LocalDate.of(2025, 6, 9), "Tweede Pinksterdag", "Whit Monday"),
                new HolidayResponse(LocalDate.of(2025, 12, 25), "Eerste Kerstdag", "Christmas Day"),
                new HolidayResponse(LocalDate.of(2025, 12, 26), "Tweede Kerstdag", "St. Stephen's Day")
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
}
