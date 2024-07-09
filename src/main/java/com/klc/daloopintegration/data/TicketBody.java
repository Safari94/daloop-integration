package com.klc.daloopintegration.data;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketBody {

    private int problem_id;
    private String description;
    private int priority;
    private int client_id;
    private int local_id;
    private int operator_id;
    private String report_type;
    private int report_id;
    private int supplier_id;
    private boolean include_automatic_assignees;

}
