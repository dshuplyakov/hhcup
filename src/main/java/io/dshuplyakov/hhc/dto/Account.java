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
    private String sex;
    private String email;
    private String fname;
    private String sname;
    private String phone;
    private String country;
    private String city;
    private Integer birth;
    private String status;
    private Integer joined;
    private List<String> interests;
   // private List<String> premium;
}
