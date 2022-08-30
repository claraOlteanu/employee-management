package com.employee.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document("employees")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Employee {
    @Id
    @GeneratedValue
    private String id;
    private String name;
    private String email;
    private long telephone;
    private String dateOfBirth;
    private String department;
    private double salary;

    public Employee(String id) {
        this.id = id;
    }

//
//
//
//        public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
}
