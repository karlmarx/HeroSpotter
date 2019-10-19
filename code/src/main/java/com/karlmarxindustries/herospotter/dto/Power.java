package com.karlmarxindustries.herospotter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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
    @ManyToMany(mappedBy="powers", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Super> superMembers;

    public Power(String name, String description, boolean isUnique) {
        this.name = name;
        this.description = description;
        this.isUnique = isUnique;
    }

    public Power(int id, String name, String description, boolean isUnique, List<Super> superMembers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isUnique = isUnique;
        this.superMembers = superMembers;
    }

    public Power() {
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isUnique() {
        return this.isUnique;
    }

    public List<Super> getSuperMembers() {
        return this.superMembers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnique(boolean isUnique) {
        this.isUnique = isUnique;
    }

    public void setSuperMembers(List<Super> superMembers) {
        this.superMembers = superMembers;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Power)) return false;
        final Power other = (Power) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        if (this.isUnique() != other.isUnique()) return false;
        final Object this$superMembers = this.getSuperMembers();
        final Object other$superMembers = other.getSuperMembers();
        if (this$superMembers == null ? other$superMembers != null : !this$superMembers.equals(other$superMembers))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Power;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        result = result * PRIME + (this.isUnique() ? 79 : 97);
        final Object $superMembers = this.getSuperMembers();
        result = result * PRIME + ($superMembers == null ? 43 : $superMembers.hashCode());
        return result;
    }


}
