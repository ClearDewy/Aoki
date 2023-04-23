package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.TaskDto;
import com.cleardewy.aoki.entity.vo.lesson.TaskListMemberVo;
import com.cleardewy.aoki.entity.vo.lesson.TaskListOwnerVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Mapper
public interface TaskMapper {
    Integer createTask(TaskDto taskDto);
    Integer updateTask(TaskDto taskDto);
    Integer deleteTask(Integer id);

    List<TaskListOwnerVo> getOwnerTasks(Integer id, Integer lessonId);

    List<TaskListMemberVo> getMemberTasks(Integer id, Integer lessonId);

    Integer verifyTaskOwner(Integer taskId,Integer id);
    Integer toggleTaskPublish(Integer taskId);
}
