// Initialize components
function inicializar()
{
	$("#templateHeader").load("/FerGymWeb2.0/pages/template/templateHeader.html", function(){ $("#templateHeader").css("height", $(".divHeader").height() + "px"); });
	$("#templateFooter").load("/FerGymWeb2.0/pages/template/templateFooter.html");
	
	//validate if inputs is filled and show the validation
	$("input").on('input', function()
	{
		if($(this).val().length > 0)
		{
			$(this).removeClass("inputError");
			//if($(this).attr('id') == "inputEmail") 	{ $("#validationEmail").css("display", "none"); }
			//else 									{ $("#validationPassword").css("display", "none"); }
		}
		else
		{
			$(this).addClass("inputError");
			//if($(this).attr('id') == "inputEmail") 	{ $("#validationEmail").css("display", "inline-block"); }
			//else 									{ $("#validationPassword").css("display", "inline-block"); }
		}
	});
}

// validate inputs and request login to back-end
function signUpRequest()
{
	var json = {};
	json['name'] = $("#inputName").val().trim();
	json['email'] = $("#inputEmail").val().trim();
	json['password'] = $("#inputPassword").val().trim();
	
	if((json.name != undefined && json.name != ""))
	{
		if((json.email != undefined && json.email != ""))
		{
			if((json.password != undefined && json.password != ""))
			{
				$.ajax({
					method: 'POST',
					dataType: "json",
					url: 'http://localhost:8080/FerGymWeb2.0/signUpServlet',
					data: json,
					success: success
				});
			}
			else
			{
				$("#inputPassword").addClass("inputError");
				//$("#validationPassword").css("display", "inline-block");
			}
		}
		else
		{
			$("#inputEmail").addClass("inputError");
			//$("#validationEmail").css("display", "inline-block");
		}
	}
	else
	{
		$("#inputName").addClass("inputError");
		//$("#validationEmail").css("display", "inline-block");
	}
}

//Function to receive the servlet response
function success(json)
{
	if(json.msg == "ok")
	{
		alert('Cadastrado com sucesso');
	}
	else
	{
		alert(json.msg);
	}
}