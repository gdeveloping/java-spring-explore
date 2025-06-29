package tech.gdev.springbasicexplore.controller.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * @author gdev
 * @date 2025/6/29 10:41
 */
@Setter
@Getter
@ToString
public class ToyInfo {
    @NotBlank
    private String toyName;

    @Positive
    private int weight;
}
