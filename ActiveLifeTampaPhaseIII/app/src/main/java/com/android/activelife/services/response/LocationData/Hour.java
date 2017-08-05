
package com.android.activelife.services.response.LocationData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hour {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("times")
    @Expose
    private Times times;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Times getTimes() {
        return times;
    }

    public void setTimes(Times times) {
        this.times = times;
    }

}
