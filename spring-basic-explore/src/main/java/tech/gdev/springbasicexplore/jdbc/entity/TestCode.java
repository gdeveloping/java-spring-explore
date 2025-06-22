package tech.gdev.springbasicexplore.jdbc.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    @NotBlank
    @Size(min = 1)
    private Integer id;
    private Integer code;
    private String note;

    @Override
    public String toString() {
        return "TestCode [id=" + id + ", code=" + code + ", note=" + note + "]";
    }
}
