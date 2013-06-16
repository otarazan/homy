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


    public static void createTask(String title,int recurrence,String assignee,Date remainingTime) {
	models.Task task=new models.Task(title, recurrence,assignee,remainingTime).save();
        renderJSON(task);
    }

    public static void changeStatus(long id, boolean status) {
	models.Task task = models.Task.findById(id);
        task.status=status;
        task.save();
        renderJSON(task);
    }
}    