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
    @NotBlank(message = "课程明不能为空")
    private String name;
    private String introduction;
    @Min(value = 0,message = "团队人数异常")
    private Integer teamMemberLimit;
    private boolean topicMod;
    private String avatarURL;
}
