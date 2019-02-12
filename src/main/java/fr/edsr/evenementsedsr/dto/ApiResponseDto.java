package fr.edsr.evenementsedsr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponseDto {

    private boolean success;
    private String message;
}
