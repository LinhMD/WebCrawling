package com.swd391.assi2.team2.model.job123;

import com.swd391.assi2.team2.model.DataModel;

public class company  extends DataModel {
    public String id;
    public String name;
    public String district;
    public String hotline;
    public String description;

    public company(String id, String name, String district, String hotline, String description) {
        this.id = id;
        this.name = name;
        this.district = district;
        this.hotline = hotline;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
