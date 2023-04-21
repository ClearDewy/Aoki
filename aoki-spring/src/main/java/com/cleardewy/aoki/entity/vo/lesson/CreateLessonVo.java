package com.cleardewy.aoki.entity.vo.lesson;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class CreateLessonVo {
    @NotBlank(message = "课程名不能为空")
    private String name;
    private String introduction;
    private boolean teamMode;
    private boolean topicMode;
    private String avatarURL;
}
