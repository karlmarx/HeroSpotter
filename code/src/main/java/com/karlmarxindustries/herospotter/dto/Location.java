package com.karlmarxindustries.herospotter.dto;

import javax.persistence.*;


@Entity(name="locations")
public class Location {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    int id;
    @Column
    double latitude;
    @Column
    double longitude;
    @Column
    String name;
    @Column
    String address;
    @Column(name="place_id")
    String placeId;

    public Location(String name, String address, String placeId) {
        this.name = name;
        this.address = address;
        this.placeId = placeId;
    }

    public Location(int id, double latitude, double longitude, String name, String address, String placeId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.address = address;
        this.placeId = placeId;
    }

    public Location() {
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Location)) return false;
        final Location other = (Location) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        if (Double.compare(this.getLatitude(), other.getLatitude()) != 0) return false;
        if (Double.compare(this.getLongitude(), other.getLongitude()) != 0) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
        final Object this$placeId = this.getPlaceId();
        final Object other$placeId = other.getPlaceId();
        if (this$placeId == null ? other$placeId != null : !this$placeId.equals(other$placeId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Location;
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
        final Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final Object $placeId = this.getPlaceId();
        result = result * PRIME + ($placeId == null ? 43 : $placeId.hashCode());
        return result;
    }

    public String toString() {
        return "Location(id=" + this.getId() + ", latitude=" + this.getLatitude() + ", longitude=" + this.getLongitude() + ", name=" + this.getName() + ", address=" + this.getAddress() + ", placeId=" + this.getPlaceId() + ")";
    }
}
