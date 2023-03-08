package com.qirara.alquran.service;

import com.qirara.alquran.payload.response.SurahDetailResponse;
import com.qirara.alquran.payload.response.SurahResponse;

import java.io.IOException;
import java.util.List;

public interface QuranService {

    SurahDetailResponse detail(String href) throws IOException;

    List<SurahResponse> getList() throws IOException;

}
