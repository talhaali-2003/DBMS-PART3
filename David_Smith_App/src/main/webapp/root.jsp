<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Root User Interface</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>

<body class="bg-light">

	<div class="container mt-5">
		<form action="RootUserInterfaceServlet" method="post"
			class="col-md-6 mx-auto bg-white p-4 rounded shadow">

			<h2 class="mb-4 text-center">Root User Interface</h2>

			<%
			if (request.getParameter("error") != null) {
			%>
			<p class="text-danger">Invalid credentials. Please try again.</p>
			<%
			}
			%>
<!-- 
			<div class="mb-3">
				<label for="username" class="form-label">Username:</label> <input
					type="text" id="username" name="username" class="form-control"
					required>
			</div>

			<div class="mb-3">
				<label for="password" class="form-label">Password:</label> <input
					type="password" id="password" name="password" class="form-control"
					required>
			</div> -->

			<!-- Dropdown menu for selecting the action -->
			<div class="mb-3">
				<label for="action" class="form-label">Select Action:</label> <select
					id="action" name="action" class="form-select" required>
					 
					<option value="deleteTables">Delete Tables</option>
					<option value="createTables">Create Tables</option>
					<option value="insertData">Insert Data</option>
				</select>
			</div>

			<!-- Single submit button for the form -->
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>

	<!-- Bootstrap JS and Popper.js (if needed) -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>

</html>
