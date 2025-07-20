package tech.gdev.springbasicexplore.controller.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author gdev
 * @date 2025/6/29 10:40
 */
@Getter
@Setter
public class PersonInfo {
    @NotBlank
    @Length(min = 2)
    private String name;

    @Positive
    private int age;

    @Valid
    @Size(max = 3)
    private List<PetInfo> pets;
}
