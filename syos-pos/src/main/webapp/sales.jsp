<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sales Transaction</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Sales Transaction</h2>
        <form action="processSale" method="POST">
            <div class="form-group">
                <label for="itemCode">Item Code:</label>
                <input type="text" class="form-control" id="itemCode" name="itemCode" required placeholder="Enter Item Code">
            </div>
            <div class="form-group">
                <label for="quantity">Quantity:</label>
                <input type="number" class="form-control" id="quantity" name="quantity" required placeholder="Enter Quantity">
            </div>
            <button type="submit" class="btn btn-primary btn-block">Process Sale</button>
        </form>

        <!-- Display success or error messages -->
        <div class="mt-4">
            <% if (request.getAttribute("message") != null) { %>
                <div class="alert alert-success"><%= request.getAttribute("message") %></div>
            <% } else if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
            <% } %>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
