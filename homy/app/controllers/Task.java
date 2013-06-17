package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Task extends Controller {


    public static void index(){
        List tasks= models.Task.find("order by id desc").fetch();
        render(tasks);
    }
    
    public static void tasks() {
    }


    public static void createTask(String task,int recurrence,String assignee,String remainingDate) {
	models.Task newtask=new models.Task(task, recurrence,assignee,remainingDate).save();
        renderJSON(newtask);
    }

    public static void changeStatus(long id, boolean status) {
	models.Task task = models.Task.findById(id);
        task.status=status;
        task.save();
        // TaskTable tt = new TaskTable();
        // tt.addTaskItem(task);
        renderJSON(task);
    }
    
    public static void delete(long id) {
	models.Task task = models.Task.findById(id);
        task.delete();
        renderJSON(task);
    }
}    