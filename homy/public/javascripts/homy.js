
$(document).ready(function() 
    { 
		//sorts all the table on the page which has tablesorter class
        $(".tablesorter").tablesorter(); 
        $( "#datepicker" ).datepicker({ dateFormat: 'dd-mm-yy'}).datepicker('setDate', new Date());
        //When textbox is clicked 'add news task' string is removed
        $("#txtTask").focusin(function() {$("#txtTask").val("")});
        //jquery tab view is created as mytasks and alltasks
        $( "#tabs" ).tabs();
        
        
        $("#createTask").click(
        		function() {
        			$.post('../task/createTask', {
        				task : $("#txtTask").val(),
        				recurrence:$('#selectRecurrence :selected').val(),
        				assignee:$('#selectUser :selected').val(),
        				remainingDate:$('#datepicker').val()
        			}, function(task) {
        				$('#allTasks tbody').append('<tr><td>'+task.title+'</td><td>'+task.roomy.firstName+'</td><td><input type="checkbox" id="'+task.id+'"></td><td>'+task.recurrence+'</td><td>'+task.remainingDate+'</td></tr>');
        			}, 'json')
        })
      //change status
        $("#allTasks input[type='checkbox']").click(function() {
        	$.post('../task/changeStatus', {
        		id : $(this).attr('id'),
        		status : $(this).is(":checked")
        	})
        });
    } 
); 

//when done button on task is clicked set as done and remove the box
function setTaskAsDone(boxid,taskID) {
	$.post('../task/changeStatus', {
		id : taskID,
		status : true
	})
	$(boxid).fadeOut("slow");
}

function prepareBox(boxid,end,level){
	countDown(boxid,end);

	$("#importance_"+boxid).attr("class", "badge badge-important");
}

function countDown(boxid,end){
	boxid="countdown_"+boxid;
	end=new Date(end);
	end=$.format.date(end, 'dd/MM/yyyy');
	var end = new Date('06/25/2013');

    var _second = 1000;
    var _minute = _second * 60;
    var _hour = _minute * 60;
    var _day = _hour * 24;
    var timer;

    function showRemaining() {
        var now = new Date();
        var distance = end - now;
        if (distance < 0) {

            clearInterval(timer);
            document.getElementById(boxid).innerHTML = 'EXPIRED!';

            return;
        }
        var days = Math.floor(distance / _day);
        var hours = Math.floor((distance % _day) / _hour);
        var minutes = Math.floor((distance % _hour) / _minute);
        var seconds = Math.floor((distance % _minute) / _second);

        document.getElementById(boxid).innerHTML = days + 'days ';
        document.getElementById(boxid).innerHTML += hours + 'hrs ';
        document.getElementById(boxid).innerHTML += minutes + 'mins ';
        document.getElementById(boxid).innerHTML += seconds + 'secs';
    }

    timer = setInterval(showRemaining, 1000);
}


function countDown(boxid,end){
	boxid="all_countdown_"+boxid;
	end=new Date(end);
	end=$.format.date(end, 'dd/MM/yyyy');
	var end = new Date('06/25/2013');

    var _second = 1000;
    var _minute = _second * 60;
    var _hour = _minute * 60;
    var _day = _hour * 24;
    var timer;

    function showRemaining() {
        var now = new Date();
        var distance = end - now;
        if (distance < 0) {

            clearInterval(timer);
            document.getElementById(boxid).innerHTML = 'EXPIRED!';

            return;
        }
        var days = Math.floor(distance / _day);
        var hours = Math.floor((distance % _day) / _hour);
        var minutes = Math.floor((distance % _hour) / _minute);
        var seconds = Math.floor((distance % _minute) / _second);

        document.getElementById(boxid).innerHTML = days + 'days ';
        document.getElementById(boxid).innerHTML += hours + 'hrs ';
        document.getElementById(boxid).innerHTML += minutes + 'mins ';
        document.getElementById(boxid).innerHTML += seconds + 'secs';
    }

    timer = setInterval(showRemaining, 1000);
}

//when pass button on task is clicked assign task to next person
function passTask(boxid,taskID) {
	$.post('../task/passTask', {
		id : taskID
	})
	$(boxid).fadeOut("slow");
}

