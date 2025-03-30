package tech.gdev.springbasicexplore.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gdev
 * @date 2025/3/30 16:17
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestCode {
    private int id;
    private int code;
    private String note;

    @Override
    public String toString() {
        return "TestCode [id=" + id + ", code=" + code + ", note=" + note + "]";
    }
}
