package com.klc.daloopintegration.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HookData {

    private String type;
    private String event;
    private Date createdDate;
    private Data data;


}
