package com.qirara.alquran.controller;

import com.qirara.alquran.payload.response.SurahDetailResponse;
import com.qirara.alquran.payload.response.SurahResponse;
import com.qirara.alquran.payload.response.WebResponse;
import com.qirara.alquran.service.QuranService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quran")
public class QuranController {

    private final QuranService quranService;

    public QuranController(QuranService quranService) {
        this.quranService = quranService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<WebResponse<List<EntityModel<SurahResponse>>>> all() throws IOException {
        List<SurahResponse> surahResponses = quranService.getList();

        List<EntityModel<SurahResponse>> entityModels = new ArrayList<>();
        surahResponses.forEach(surahResponse -> {
            try {
                Link link = WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).detail(surahResponse.getPath().replace("/", "")))
                        .withRel("href");

                EntityModel<SurahResponse> surahResponseEntityModel = EntityModel.of(surahResponse, link);
                entityModels.add(surahResponseEntityModel);
            } catch (IOException exception) {
                throw new RuntimeException(exception.getMessage());
            }
        });

        return new ResponseEntity<>(
                new WebResponse<>(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        entityModels
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
