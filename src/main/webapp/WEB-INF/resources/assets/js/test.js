var form = $('#registerForm');
				form.submit(function (e) {
					console.log("Test data");
					console.log(form.serialize());
					e.preventDefault();
					
					
					$.ajax({
						type: form.attr('method'),
						url: "/SpringExamSystem/user/register",
						data: form.serialize(),
						success: function(data){
							alert(data);
						},
						error: function(data){
							console.log(data)
						}
					});
				});