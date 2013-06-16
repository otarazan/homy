package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Task extends Controller {


    public static void index(){
        render();
    }

    public static void createTask(String title) {
        models.Task task=new models.Task(title).save();
        //renderJSON(task);
        render();
    }
    
    public static void changeStatus(long id, boolean done) {
        models.Task task = models.Task.findById(id);
        task.status=true;
        task.save();
        //renderJSON(task);
        render();
    }

}    