## Coding Guidelines 

### Code

1. Follow the Java coding convention [http://www.oracle.com/technetwork/java/codeconv-138413.html] Especially follow sections:  
    7 Statements  
    9 Naming Conventions  
* * *
3. Additionally follow YAML parsing conventions:  
    *1* no \t  
    *2* no numeric IDs  
    *3* organize corresponding sections underneath each other  
* * *
4. Additionally follow play conventions:  
    *1* URI resources correspond with the controller name  
    *2* The parts of URI the uri path, identifiying a variable correspond with the method parameters  
    *3* Collect corresponding routes underneath in their definition block  
* * *

### Architectural style

1. Following in parts the REST Style  
    *1* Don't keep state information  
    *2* Use distinctive URIs with clear names  
    *3* Each URI represents one resource  
        *3.1* Breaking this rule is only allowed for decorators  
    *4* Each Controller implements the 4 basic http commadns GET, POST, PUT, DELETE  
* * *
2. Follow the MVP pattern instead of MVC  
    *1* View-Presenter (named controller)-Model  
    *2* HTTP actions are pipes to the basic CRUD actions  

### Data-Layer conventions

The datalayer is strictly cut from the application logic. We use a layerd system based on MVP, meaning: Only controllers acces the model data (no data-reads by views).

1. Only models call the database mapping layer
2. Controllers interact with models as if they were normaly objects
3. Data fetching is done lazy
4. Manipulating data is only allowed from the controller layer