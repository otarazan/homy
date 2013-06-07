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
    
    public static void createTask(String title) {
        Task task=new Task(title).save();
        renderJSON(task);
    }
    
    public static void changeStatus(long id, boolean done) {
        Task task = Task.findById(id);
        task.done=done;
        task.save();
        renderJSON(task);
    }
}