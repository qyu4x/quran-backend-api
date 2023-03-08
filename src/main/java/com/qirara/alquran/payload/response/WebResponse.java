package com.qirara.alquran.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WebResponse <T>{

    private Integer code;

    private String status;

    private T data;

}
