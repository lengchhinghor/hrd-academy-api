package org.ksga.springboot.springsecuritydemo.model.course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Long id;
    private String name;
    private String description;
    private String thumnail;
    private boolean is_draft;
    private boolean is_private;
    private String createdAt;

    public Course(Long id) {
        this.id = id;
    }
    @JsonIgnore
    public Long getId() {
        return id;
    }
}
