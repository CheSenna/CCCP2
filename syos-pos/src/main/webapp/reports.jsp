<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reports</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Daily Sales Report</h2>
        <form action="generateReport" method="POST" class="mb-4">
            <div class="form-row">
                <div class="col-md-6">
                    <label for="saleDate">Select Date:</label>
                    <input type="date" class="form-control" id="saleDate" name="saleDate" required>
                </div>
                <div class="col-md-6 d-flex align-items-end">
                    <button type="submit" class="btn btn-primary btn-block">Generate Report</button>
                </div>
            </div>
        </form>

        <!-- Report table -->
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Sale ID</th>
                    <th>Sale Date</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="sale" items="${dailySales}">
                    <tr>
                        <td>${sale.saleId}</td>
                        <td>${sale.saleDate}</td>
                        <td>${sale.total}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
