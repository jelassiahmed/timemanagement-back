package com.timemanagemenet.timemanagementapp.Entity.dto;

import com.timemanagemenet.timemanagementapp.Entity.Leave;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification implements Serializable {

    private String taskId;

    private String taskName;

    private String processInstId;

    private Date date;

    private Leave leave;

    private String decision;

    private String comment ;
}
