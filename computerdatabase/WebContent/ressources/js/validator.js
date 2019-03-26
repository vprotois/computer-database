$(document).ready(function() {
	
	
	let format = /(^$|[0-9]{2}[/][0-9]{2}[/][0-9]{4}$)/;	
	$("#discontinued").attr("disabled",true)
	
	$("#introduced").change(function() {
		let introduced = $("#introduced").val();
		let discontinued = $("#discontinued").val();
		if( introduced != ""){
			if(!introduced.match(format)){
				$("#discontinued").attr("disabled",true)
					$("#add_button").attr("disabled",true)
			}else{
				$("#discontinued").attr("disabled",false)
				if(discontinued == ""|| discontinued.match(format)){
					$("#add_button").attr("disabled",false)
				}
			}
		}else{
			console.log("no introduced!");
			$("#discontinued").attr("disable",true)
		}
	});

	$("#discontinued").change(function() {
		let introduced = $("#introduced").val();
		let discontinued = $("#discontinued").val();
		if( discontinued != ""){
			if(!discontinued.match(format)){
				$("#add_button").attr("disabled",true)
			}else{
				if(introduced.match(format)){
					$("#add_button").attr("disabled",false)
				}
			}
		}else{
			if(introduced.match(format)){
				$("#add_button").attr("disabled",false)
			}
		}
	});
});