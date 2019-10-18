package com.karlmarxindustries.herospotter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name="organizations")
//@AttributeOverride(name="id", column=@Column(name="org_id"))
public class Organization {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
     int id;
    @Column
     double latitude;
    @Column
     double longitude;
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
            @JsonIgnore
     List<Super> superMembers;
    @Column
    String address;
    @Column(name="place_id")
    String placeId;

    public Organization(String name, String email, String url, String phoneNumber, String description, String address, String placeId) {
        this.name = name;
        this.email = email;
        this.url = url;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.address = address;
        this.placeId = placeId;
    }

    public Organization(int id, double latitude, double longitude, String name, String email, String url, String phoneNumber, String description, List<Super> superMembers, String address, String placeId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.email = email;
        this.url = url;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.superMembers = superMembers;
        this.address = address;
        this.placeId = placeId;
    }

    public Organization() {
    }

    public int getId() {
        return this.id;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUrl() {
        return this.url;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Super> getSuperMembers() {
        return this.superMembers;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPlaceId() {
        return this.placeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSuperMembers(List<Super> superMembers) {
        this.superMembers = superMembers;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Organization)) return false;
        final Organization other = (Organization) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        if (Double.compare(this.getLatitude(), other.getLatitude()) != 0) return false;
        if (Double.compare(this.getLongitude(), other.getLongitude()) != 0) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$url = this.getUrl();
        final Object other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
        final Object this$phoneNumber = this.getPhoneNumber();
        final Object other$phoneNumber = other.getPhoneNumber();
        if (this$phoneNumber == null ? other$phoneNumber != null : !this$phoneNumber.equals(other$phoneNumber))
            return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$superMembers = this.getSuperMembers();
        final Object other$superMembers = other.getSuperMembers();
        if (this$superMembers == null ? other$superMembers != null : !this$superMembers.equals(other$superMembers))
            return false;
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
        final Object this$placeId = this.getPlaceId();
        final Object other$placeId = other.getPlaceId();
        if (this$placeId == null ? other$placeId != null : !this$placeId.equals(other$placeId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Organization;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final long $latitude = Double.doubleToLongBits(this.getLatitude());
        result = result * PRIME + (int) ($latitude >>> 32 ^ $latitude);
        final long $longitude = Double.doubleToLongBits(this.getLongitude());
        result = result * PRIME + (int) ($longitude >>> 32 ^ $longitude);
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $url = this.getUrl();
        result = result * PRIME + ($url == null ? 43 : $url.hashCode());
        final Object $phoneNumber = this.getPhoneNumber();
        result = result * PRIME + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $superMembers = this.getSuperMembers();
        result = result * PRIME + ($superMembers == null ? 43 : $superMembers.hashCode());
        final Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final Object $placeId = this.getPlaceId();
        result = result * PRIME + ($placeId == null ? 43 : $placeId.hashCode());
        return result;
    }


}
