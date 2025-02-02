package com.nathaliadv.calendataapi.core.ports.outbound;

import com.nathaliadv.calendataapi.core.model.HolidayResponse;
import com.nathaliadv.calendataapi.core.model.HolidayAnalysisRequest;

import java.util.List;

public interface HolidayDataOutboundPort {

    List<HolidayResponse> getAllHolidaysByYearAndCountry(HolidayAnalysisRequest request);
}
