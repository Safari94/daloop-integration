package com.klc.daloopintegration.data;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Response {

    private String type;
    private String id;
    private Attributes attributes;
}
