package com.cleardewy.aoki.entity.vo.lesson;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class EditLessonVo {
    Integer id;
    @NotBlank(message = "课程名不能为空")
    private String name;
    private String introduction;
    private String avatarURL;
}
