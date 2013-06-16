package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void tasks() {
        List tasks= Task.find("order by id desc").fetch();
        render(tasks);
    }
    
    public static void createTask(String title,int recurrence,String assignee,Date remainingTime) {
        Task task=new Task(title, recurrence,assignee,remainingTime).save();
        renderJSON(task);
    }
    
    public static void changeStatus(long id, boolean status) {
        Task task = Task.findById(id);
        task.status=status;
        task.save();
        renderJSON(task);
    }
}