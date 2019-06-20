var form = $('#registerForm');
				form.submit(function (e) {
					var user = {
							"firstname":$("#firstname").val(),
							"lastname":$("#lastname").val(),
							"email":$("#email").val(),
							"password":$("#password").val(),
							"regno":$("#regno").val()
					};
					
					console.log(user);
					e.preventDefault();
					var userJSON = JSON.stringify(user);
					console.log(userJSON);
					
					
					$.ajax({
						type: form.attr('method'),
						url: "/SpringExamSystem/user/register",
						contentType : "application/json",
						data: userJSON,
						success: function(data){
							alert("Done!");
						},
						error: function(data){
							console.log(data);
						}
					});
				});