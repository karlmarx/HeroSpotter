package com.karlmarxindustries.herospotter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="supers")
public class Super  {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    int id;
    @Column(nullable = false)
    String name;
    @Column
    String description;
    @Column(name="is_villian")
    boolean isVillain;
    @ManyToMany
            @JoinTable(name="supers_powers",
            joinColumns = {@JoinColumn(name="super_id")},
            inverseJoinColumns = {@JoinColumn(name="power_id")})
    List<Power> powers;
    @ManyToMany
    @JoinTable(name="affiliations",
            joinColumns = {@JoinColumn(name="super_id")},
            inverseJoinColumns = {@JoinColumn(name="org_id")})
    List<Organization> organizations;
}
