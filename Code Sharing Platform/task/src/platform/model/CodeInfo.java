package platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
public class CodeInfo {

    @JsonIgnore
    @Id
    @Column
    private String id;

    @NotEmpty(message = "程式碼不能為空")
    @Column
    private String code;

    @JsonFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    @Column
    private LocalDateTime date;

    @Column
    private long time;

    @Column
    private long views;

    @JsonIgnore
    @Column
    private long originalTime;

    @JsonIgnore
    @Column
    private long originalViews;

    public CodeInfo() {
    }

    public CodeInfo(String id, String code, LocalDateTime date, long time, long views) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.time = time;
        this.views = views;
        this.originalTime = time;
        this.originalViews = views;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public long getOriginalTime() {
        return originalTime;
    }

    public void setOriginalTime(long originalTime) {
        this.originalTime = originalTime;
    }

    public long getOriginalViews() {
        return originalViews;
    }

    public void setOriginalViews(long originalViews) {
        this.originalViews = originalViews;
    }
}