# Homy Project
Solving all the **little** problems.

## Notifications

### Logging
When you execute an action that should be listed in the notifications bar inform the notification controller of it
```java
Notification.logGenericActivity(yourRoom, yourRoomy, ActionCode.YOUR-ACTION-CODE, "your controller name");
```
Refere to the ntofication model for details on the actioncodes

### Showing notifications
If you want to show the notificationbar (populate) with the generic notifications retrieve them via
```java
        List<NotificationMessage> genericAc = currentRoom.notifications.getCurrentNotifications();
```
The notificationsbar is populated with the contents of the _genericAc_ parameter (sibmited to the render method).


## Teamplates and views
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

## Notes on Dev process

*1* Please check all your code before you commit. If it does not run it does not help

*2* If you do not know how to _merge_, merge with somebody else together, otherwise your code might get lost


