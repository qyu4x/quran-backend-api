package com.qirara.alquran.service;

import com.qirara.alquran.config.QuranResource;
import com.qirara.alquran.payload.response.AyatResponse;
import com.qirara.alquran.payload.response.SurahDetailResponse;
import com.qirara.alquran.payload.response.SurahResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuranServiceImpl implements QuranService{

    private static final Logger log = LoggerFactory.getLogger(QuranServiceImpl.class);

    private final QuranResource quranResource;

    public QuranServiceImpl(QuranResource quranResource) {
        this.quranResource = quranResource;
    }

    @Override
    public SurahDetailResponse detail(String href) throws IOException {

        String quranUrl = quranResource.getQuranKu().concat(href);
        log.info("Fetching : {} ", quranUrl);

        Document document = Jsoup.connect(quranUrl).get();
        Elements surah = document.getElementsByClass("main wrap").select("article");
        String title = surah.select("h1").text();
        Elements listAyat = surah.select("li");

        SurahDetailResponse surahDetailResponse = new SurahDetailResponse();
        surahDetailResponse.setTitle(title);

        List<AyatResponse> ayatResponses = new ArrayList<>();
        listAyat.stream().forEach(ayat -> {
            String arabic = ayat.select("p[class=arabic]").text();
            String translate = ayat.select("p[class=translate]").text();
            String meaning = ayat.select("p[class=meaning]").text();

            AyatResponse ayatResponse = new AyatResponse();
            ayatResponse.setArabic(arabic);
            ayatResponse.setTranslate(translate);
            ayatResponse.setMeaning(meaning);

            ayatResponses.add(ayatResponse);

        });
        surahDetailResponse.setAyat(ayatResponses);

        return surahDetailResponse;
    }

    @Override
    public List<SurahResponse> getList() throws IOException {
        String quranUrl = quranResource.getQuranKu();
        log.info("Fetching : {} ", quranUrl);

        Document document = Jsoup.connect(quranUrl).get();
        Elements listSurah = document.getElementsByClass("list").select("li");

        List<SurahResponse> surahResponses = new ArrayList<>();
        listSurah.stream().forEach(surah -> {

            String title = surah.select("a").text();
            String  href = surah.select("a").attr("abs:href").replace(quranResource.getQuranKu(), "/");

            SurahResponse surahResponse = new SurahResponse();
            surahResponse.setTitle(title);
            surahResponse.setHref(href);

            surahResponses.add(surahResponse);
        });


        return surahResponses;
    }
}
