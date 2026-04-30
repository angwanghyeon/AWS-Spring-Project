package com.agh.firstproject.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "Todo 생성 요청")
public class TodoCreateRequest {

    @NotBlank(message = "내용은 필수입니다.")
    @Schema(description = "할 일 내용", example = "운동하기")
    private String content;

}
