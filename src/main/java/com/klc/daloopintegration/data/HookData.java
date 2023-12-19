package com.klc.daloopintegration.data;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HookData {

    private String type;
    private String event;
    private Date createdDate;
    private Data data;


}
