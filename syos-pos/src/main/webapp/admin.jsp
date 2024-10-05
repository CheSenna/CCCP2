<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Admin Dashboard</h1>

        <div class="row">
            <!-- Manage Stock -->
            <div class="col-md-6 mb-3">
                <div class="card shadow">
                    <div class="card-body text-center">
                        <h4 class="card-title">Manage Stock</h4>
                        <p class="card-text">View and manage your stock inventory.</p>
                        <a href="stock.jsp" class="btn btn-primary">Go to Stock Management</a>
                    </div>
                </div>
            </div>

            <!-- View Reports -->
            <div class="col-md-6 mb-3">
                <div class="card shadow">
                    <div class="card-body text-center">
                        <h4 class="card-title">View Reports</h4>
                        <p class="card-text">Generate and view daily sales reports.</p>
                        <a href="reports.jsp" class="btn btn-primary">Go to Reports</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Add more cards for other admin functions as necessary -->
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
