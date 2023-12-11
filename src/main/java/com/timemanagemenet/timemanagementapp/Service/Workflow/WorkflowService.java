package com.timemanagemenet.timemanagementapp.Service.Workflow;

import com.timemanagemenet.timemanagementapp.Entity.Leave;
import com.timemanagemenet.timemanagementapp.Entity.dto.Notification;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private RepositoryService repositoryService;


    public void startProcessByInstId(Leave demandeConge , String validateur1)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal<?> keycloakPrincipal) {

            String username = keycloakPrincipal.getName();
            HashMap<String, Object> variables = new HashMap<>();
            variables.put("startedBy", username); // replace ny current matricule
            variables.put("demande", demandeConge); // replace ny current matricule


            ProcessInstance processInstance= runtimeService.startProcessInstanceById(

                    this.getProcessId("Process_0a69oud")
                    , variables);

            Optional<Task> task = Optional.ofNullable(
                    taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("Avis N+1").singleResult()
            );
            if (task.isPresent() && validateur1 != null) {
                task.map(t -> {
                    taskService.setAssignee(t.getId(), validateur1);
                    return t;
                });

            }

        }
    }


    public List<Task> getMyTasks(String user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal<?> keycloakPrincipal) {
            //get connected user role
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
            List<Task> tasks=    taskService.createTaskQuery()
                    .taskAssignee(user)
                    .list();

            if (isAdmin) { // to be replaced by if current user has ROLE_RH
                List<Task> taskListRh = taskService.createTaskQuery()
                        .taskCandidateGroup("ROLE_RH")
                        .list();

                tasks.addAll(taskListRh);
            }
            return tasks;
        }
return null;
    }


    /**
     * load notification for current user
     * Design pattern : DTO,singleton, factory,DI,builder
     *
     * @return
     */

    public List<Notification> loadNotification(String username){

        try {
          List<Task> myTasks=  this.getMyTasks(username);

          if (myTasks != null && !myTasks.isEmpty()){
              return myTasks.stream().map(this::extractInfoFromTask).toList();
          }
        }
        catch (Exception e){

        }
        return null;
    }

    Notification extractInfoFromTask(Task task){
        Notification notification = new Notification();
        try {

            Leave leave=(Leave) ( runtimeService.getVariable(task.getProcessInstanceId(),"demande"));

          notification=  Notification
                    .builder()
                    .date(task.getCreateTime())
                    .processInstId(task.getProcessInstanceId())
                    .taskId(task.getId())
                    .taskName(task.getName())
                    .leave(leave)
                    .build();

        }
        catch (Exception e){

        }
        return notification;
    }

    public void completeTask(String taskId, String decision) {
        Map<String, Object> variables = new HashMap<>();
        System.out.println("Decision Variable Value: " + decision);

        variables.put("decision", decision);
        System.out.println("variables Variable Value: " + variables);
        taskService.complete(taskId, variables);
    }

      public String getProcessId(String name){

        return repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(name)
                .latestVersion()
                .singleResult()
                .getId();
      }



}
