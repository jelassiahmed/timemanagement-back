package com.timemanagemenet.timemanagementapp.Service.Workflow;

import com.timemanagemenet.timemanagementapp.Entity.Leave;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WorkflowService {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    void startProcessByInstId(Leave demandeConge , String validateur1)
    {

        HashMap<String, Object> variables = new HashMap<>();
        variables.put("startedBy", "initiateurx"); // replace ny current matricule
        variables.put("demande", demandeConge); // replace ny current matricule

        ProcessInstance processInstance= runtimeService.startProcessInstanceById("proceess", variables);

        Optional<Task> task = Optional.ofNullable(
                taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("Avis N+1").singleResult()
        );
        if (task.isPresent() && validateur1 != null) {
          task.map(t -> {
                t.setAssignee(validateur1.toString());
                taskService.setAssignee(t.getId(), validateur1.toString());
                return t;
            });
        }
    }


    public List<Task> getMyTasks(String user){

    List<Task> tasks=    taskService.createTaskQuery()
                .taskAssignee(user)
                .list();

      if ("ROLE_RH".equals(user) ){ // to be replaced by if current user has ROLE_RH
            List<Task> taskListRh = taskService.createTaskQuery()
                    .taskCandidateGroup("ROLE_RH")
                    .list();

            tasks.addAll(taskListRh);
        }
        return tasks;
    }


    public void completeTask(String taskId, String decision){
       Map varibles= new HashMap<>();

       varibles.put("decision",decision);
        taskService.complete(taskId,varibles);

    }




}
