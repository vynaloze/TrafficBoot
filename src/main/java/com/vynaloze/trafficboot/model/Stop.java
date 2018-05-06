package com.vynaloze.trafficboot.model;

import com.vynaloze.trafficboot.dao.orm.entity.annotation.Entity;
import com.vynaloze.trafficboot.dao.orm.entity.annotation.EntityField;

@Entity
public class Stop {
    @EntityField
    private Integer id;
    @EntityField
    private String address;

    public Stop(Integer id, String address) {
        this.id = id;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stop)) {
            return false;
        }

        Stop stop = (Stop) o;

        if (!id.equals(stop.id)) {
            return false;
        }
        return address.equals(stop.address);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
