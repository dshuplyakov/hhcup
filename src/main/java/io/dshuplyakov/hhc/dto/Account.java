package io.dshuplyakov.hhc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by Dmitry on 21.12.2018.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Account {
    private int id;
    private String fname;
    private String email;
    private List<String> interests;
    private String status;
    private String sex;
    private String phone;
    private Long birth;
    private String city;
    private String country;
    private Long joined;
}
