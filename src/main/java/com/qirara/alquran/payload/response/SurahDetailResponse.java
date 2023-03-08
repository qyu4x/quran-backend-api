package com.qirara.alquran.payload.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class SurahDetailResponse {


    private String title;

    private List<AyatResponse> ayat;


}
