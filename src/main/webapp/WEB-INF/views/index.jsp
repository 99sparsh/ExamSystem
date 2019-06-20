<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
	<head>
		<title>Register</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link href="<c:url value="resources/assets/css/main.css" />" rel="stylesheet">
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">

							
							<!-- Content -->
								<section>
									<header class="main">
										<h1>Register</h1>
									</header>

									

						<!-- Form -->

							<form method="post" id="registerForm">
								<div class="row gtr-uniform">
									<div class="col-6 col-12-xsmall">
										<input type="text" name="firstname" id="firstname" placeholder="FirstName" />
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="text" name="lastname" id="lastname" placeholder="LastName" />
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="email" name="email" id="email" placeholder="Email" />
									</div>
									<!-- Break -->
									<div class="col-6 col-12-xsmall">
										<input type="text" name="regno" id="regno" placeholder="Registration Number"/>
									</div>
									<br>
									<br>

									<div class="col-6 col-12-xsmall">
										<input type="password" name="passsword" id="password" value="" placeholder="Password" />
									</div>
									
								
									<div class="col-12">
										<ul class="actions">
											<li><input type="submit" value="Submit" onclick="submit()" class="primary" /></li>
											<li><input type="reset" value="Reset" /></li>
										</ul>
									</div>
								</div>
							</form>
							<div>
								Already Registered? <br>
								<a href="/user/login"> Proceed to Login </a>
							</div>

												
													

												

											</div>
										</div>

								</section>

						</div>
					</div>

				
		<!-- Scripts -->
			<script src="<c:url value="resources/assets/js/jquery.min.js" />"></script>
			<script src="<c:url value="resources/assets/js/browser.min.js"/>"</script>
			<script src="<c:url value="resources/assets/js/breakpoints.min.js"/>"</script>
			<script src="<c:url value="resources/assets/js/util.js"/>"</script>
			<script src="<c:url value="resources/assets/js/main.js"/>"</script>
			<script src="<c:url value="resources/assets/js/test.js"/>"</script>
			<script type="text/javascript">
				
			</script>

	</body>
</html>