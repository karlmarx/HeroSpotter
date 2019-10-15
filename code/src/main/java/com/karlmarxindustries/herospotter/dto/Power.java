package com.karlmarxindustries.herospotter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="powers")
public class Power {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    int id;
    @Column(name="power_name", nullable = false)
    String name;
    @Column
    String description;
    @Column(name="is_unique")
    boolean isUnique;
    @ManyToMany(mappedBy="powers")
    private List<Super> superMembers;

    public Power(String name, String description, boolean isUnique) {
        this.name = name;
        this.description = description;
        this.isUnique = isUnique;
    }
}
