package com.agh.firstproject.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoUpdateRequest {

    @Schema(description = "완료 여부", example = "true")
    private Boolean completed;
}
