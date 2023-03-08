package com.qirara.alquran.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class QuranResource {

    @Value("${quran.ku}")
    private String quranKu;

}
