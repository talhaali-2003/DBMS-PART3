<!-- client_response.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Client's Response to Initial Quote</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2>Client's Response to Initial Quote</h2>
		<form action="ClientResponseServlet" method="post">
			<!-- Add form fields for client's response (e.g., acceptance, comments) -->
			<div class="mb-3">
				<label for="acceptance" class="form-label">Acceptance:</label> <select
					class="form-select" name="acceptance" required>
					<option value="accept">Accept</option>
					<option value="reject">Reject</option>
				</select>
			</div>

			<div class="mb-3">
				<label for="comments" class="form-label">Comments:</label>
				<textarea class="form-control" name="comments" rows="4"></textarea>
			</div>

			<!-- Add more form fields as needed -->

			<button type="submit" class="btn btn-primary">Submit
				Response</button>
		</form>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>