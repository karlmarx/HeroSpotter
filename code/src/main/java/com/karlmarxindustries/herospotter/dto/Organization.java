package com.karlmarxindustries.herospotter.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name="organizations")
//@AttributeOverride(name="id", column=@Column(name="org_id"))
public class Organization {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private double latitude;
    @Column
    private double longitude;
    @Column(nullable = false)
    private String name;
    @Column
    private String email;
    @Column
    private String url;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column
    private String description;
    @ManyToMany(mappedBy="organizations")
    private List<Super> superMembers;
}
