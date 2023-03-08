package com.qirara.alquran.controller;

import com.qirara.alquran.payload.response.SurahDetailResponse;
import com.qirara.alquran.payload.response.SurahResponse;
import com.qirara.alquran.payload.response.WebResponse;
import com.qirara.alquran.service.QuranService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quran")
public class QuranController {

    private final QuranService quranService;

    public QuranController(QuranService quranService) {
        this.quranService = quranService;
    }

    @GetMapping("/all")
    public ResponseEntity<WebResponse<List<SurahResponse>>> all() throws IOException {
        return new ResponseEntity<>(
                new WebResponse<>(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        quranService.getList()
                ),
                HttpStatus.OK
        );

    }

    @GetMapping("/{href}")
    public ResponseEntity<WebResponse<SurahDetailResponse>> detail(@PathVariable("href") String href) throws IOException {
        return new ResponseEntity<>(
                new WebResponse<>(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        quranService.detail(href)
                ),
                HttpStatus.OK
        );

    }


}
