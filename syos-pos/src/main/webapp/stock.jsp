<!DOCTYPE html>
<html>
<head>
    <title>Stock Management</title>
</head>
<body>
    <h1>Stock Management</h1>
    <table>
        <tr>
            <th>Item Code</th>
            <th>Item Name</th>
            <th>Stock</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        <!-- Display stock items dynamically here -->
    </table>

    <!-- Form to add new stock -->
    <h2>Add New Stock</h2>
    <form action="addStock" method="POST">
        <label for="itemCode">Item Code:</label>
        <input type="text" id="itemCode" name="itemCode" required>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required>

        <button type="submit">Add Stock</button>
    </form>
</body>
</html>
