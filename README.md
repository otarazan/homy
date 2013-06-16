# Homy Project
Solving all the **little** problems.
Please read the following notes on where to find resources and how to include them in your work.

### Todo
- [x] Create all tempaltes and master template
- [x] Create model
- [x] Create dynamic tags for template changes on clicks
- [x] Create all controller
- [ ] Create login
- [ ] Create deposit index action
- [ ] Create settings index action
- [ ] Create task index action
- [ ] Create grocery index action
- [ ] Create dashboard index action

### Teamplates and views
Views are called by the "render" method. This method calls the view with the name of its calling method
```java
{contoller name} {
    public static void {method name} {
        render(); // renders {controller}/{method name}.html
        // same as renderTemplate('{contoller name}/{method name'})
    }
}
```

Views are located in the view directory:

    views/{contoller name}/{method name}.html

feel free to edit. All templates are rendered inside the master tempaltes (except for the login):

    view/main.html    

Find *RAW* templates in:

    /public/{name of the html file}.html

### Notes on Dev process

*1* Please check all your code before you commit. If it does not run it does not help

*2* If you do not know how to _merge_, merge with somebody else together, otherwise your code might get lost


