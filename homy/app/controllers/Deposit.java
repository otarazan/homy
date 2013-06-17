package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Deposit extends Controller {


    public static void index(){
    	List<DepositBoxItem> itemList=DepositBoxItem.findAll();
    	render(itemList);
    }
    
    public static void delete(long itemId){
    	DepositBoxItem temp=DepositBoxItem.findById(itemId);
    	temp.delete();
    	index();
    }
}    