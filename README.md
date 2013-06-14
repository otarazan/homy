# Homy Project
Solving all the _little_ problems.
Please read the following notes on where to find resources and how to include them in your work.

### Teamplates and views

Views are called by the "render" method. This method calls the view with the name of its calling method

    {contoller name} {
        public static void {method name} {
            render(); // renders {controller}/{method name}.html
            // same as renderTemplate({method name})
        }
    }

Views are located in the view directory:
    
    views/Application/

When writing your own controller move the view in the corresponding sub directory:

    views/{controller}/{view name}

All templates are rendered inside the master tempaltes (except for the login):

    view/main.html    

Find *RAW* templates in:

    /public/{name of the html file}.html

When generating your own views use this file. Reference the stylesheets and javascripts via:

    <script src="@{'public/javascripts/YOUR_JS'}"... or
    @{script 'public/javascripts/YOUR_JS'} or
    <script src="@{'public/stylesheets/YOUR_JS'}"...

### Notes on Dev process

*1* Please check all your code before you commit. If it does not run it does not help

*2* If you do not know how to _merge_, merge with somebody else together, otherwise your code might get lost


