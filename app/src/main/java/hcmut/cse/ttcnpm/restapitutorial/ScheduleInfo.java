package hcmut.cse.ttcnpm.restapitutorial;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungphongbk on 9/25/15.
 */
public class ScheduleInfo {
    @SerializedName("name")
    private String name;
    @SerializedName("timetable")
    private List<ScheduleItem> timetable=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScheduleItem> getTimetable() {
        return timetable;
    }

    public void setTimetable(List<ScheduleItem> timetable) {
        this.timetable = timetable;
    }
}
