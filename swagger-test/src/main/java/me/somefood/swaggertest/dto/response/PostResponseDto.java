package me.somefood.swaggertest.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostResponseDto {

    @ApiModelProperty(value = "제목", dataType = "string", required = true)
    private String title;

    @ApiModelProperty(value = "내용", dataType = "string")
    private String content;
}
