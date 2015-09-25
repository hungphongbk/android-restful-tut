package hcmut.cse.ttcnpm.restapitutorial;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hungphongbk on 9/25/15.
 */
public class ScheduleItem {
    @SerializedName("subjectId")
    private String subjectId;
    @SerializedName("subjectName")
    private String subjectName;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
