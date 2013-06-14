package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        renderTemplate("Application/login.html");
    }

    public static void tasks() {
        //List tasks= Task.find("order by id desc").fetch();
        render();
    }

    public static void dashboard(){
        render();}
    
    public static void deposit(){
        render();}
    
    public static void grocery(){
        render();}
    
    public static void login(){
        render();}
    
    public static void settings(){
        render();}


    public static void createTask(String title) {
        Task task=new Task(title).save();
        renderJSON(task);
    }
    
    public static void changeStatus(long id, boolean done) {
        Task task = Task.findById(id);
        task.status=true;
        task.save();
        renderJSON(task);
    }
}