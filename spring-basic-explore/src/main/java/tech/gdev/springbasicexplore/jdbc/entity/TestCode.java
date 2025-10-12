package tech.gdev.springbasicexplore.jdbc.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author gdev
 * @date 2025/3/30 16:17
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "testCode")
public class TestCode {
    @Positive
    @NotNull
    private Integer id;

    @Positive
    private Integer code;

    private String note;

    @Override
    public String toString() {
        return "TestCode [id=" + id + ", code=" + code + ", note=" + note + "]";
    }
}
