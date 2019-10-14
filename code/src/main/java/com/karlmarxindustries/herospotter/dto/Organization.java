package com.karlmarxindustries.herospotter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="organizations")
//@AttributeOverride(name="id", column=@Column(name="org_id"))
public class Organization {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
     int id;
//    @Column
//     double latitude;
//    @Column
//     double longitude;
    @Column(nullable = false)
     String name;
    @Column
     String email;
    @Column
     String url;
    @Column(name="phone_number")
     String phoneNumber;
    @Column
     String description;
    @ManyToMany(mappedBy="organizations")
     List<Super> superMembers;
    @Column
    String address;
}
