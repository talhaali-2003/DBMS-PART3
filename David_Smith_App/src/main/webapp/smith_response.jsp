<!-- smith_response.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>David Smith's Response to Client's Quote Response</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<h2>David Smith's Response to Client's Quote Response</h2>
		<form action="SmithResponseServlet" method="post">
			<!-- Add form fields for David Smith's response (e.g., additional details, agreement) -->
			<div class="mb-3">
				<label for="additionalDetails" class="form-label">Additional
					Details:</label>
				<textarea class="form-control" name="additionalDetails" rows="4"></textarea>
			</div>

			<div class="mb-3 form-check">
				<input type="checkbox" class="form-check-input" name="agreement"
					value="agree"> <label class="form-check-label">Agree</label>
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
</html>
