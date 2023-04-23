package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.entity.dto.TaskDto;
import com.cleardewy.aoki.entity.vo.lesson.TaskListMemberVo;
import com.cleardewy.aoki.entity.vo.lesson.TaskListOwnerVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class TaskEntityManager {
    @Autowired
    TaskMapper taskMapper;


    public void createTask(TaskDto taskDto) {
        if (taskMapper.createTask(taskDto)==0)
            throw AokiException.fail();
    }

    public void updateTask(TaskDto taskDto) {
        if (taskMapper.updateTask(taskDto)==0)
            throw AokiException.fail();
    }
    public void deleteTask(Integer taskId) {
        if (taskMapper.deleteTask(taskId)==0)
            throw AokiException.fail();
    }

    public List<TaskListOwnerVo> getOwnerTasks(Integer id, Integer lessonId) {
        return taskMapper.getOwnerTasks(id,lessonId);
    }

    public List<TaskListMemberVo> getMemberTasks(Integer id, Integer lessonId) {
        return taskMapper.getMemberTasks(id,lessonId);
    }

    public Boolean verifyTaskOwner(Integer taskId,Integer id){
        return taskMapper.verifyTaskOwner(taskId,id)!=0;
    }

    public void toggleTaskPublish(Integer taskId){
        if (taskMapper.toggleTaskPublish(taskId)==0)
            throw AokiException.notFound();
    }
}
