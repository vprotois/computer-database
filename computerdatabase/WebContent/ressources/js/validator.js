$(document).ready(function() {
	
	
	let format = new RegExp("^(?:(?:(?:0?[13578]|1[02])(\/|-|\.)31)\1|(?:(?:0?[1,3-9]|1[0-2])(\/|-|\.)(?:29|30)\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:0?2(\/|-|\.)29\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\/|-|\.)(?:0?[1-9]|1\d|2[0-8])\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$")
		
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