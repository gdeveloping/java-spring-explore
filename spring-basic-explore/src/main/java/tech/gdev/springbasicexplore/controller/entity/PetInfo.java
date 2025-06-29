package tech.gdev.springbasicexplore.controller.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @author gdev
 * @date 2025/6/29 10:41
 */
@Setter
@Getter
@ToString
public
class PetInfo {
    @NotBlank
    private String petName;

    @Positive
    private int age;

    @Valid
    private List<ToyInfo> toys;
}
