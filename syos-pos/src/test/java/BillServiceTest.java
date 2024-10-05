import com.syospos.yourapp.model.Bill;
import com.syospos.yourapp.service.BillService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BillServiceTest {

    private BillService billService;
    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        // Assuming a local database for testing
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/syosdb", "root", "password");
        billService = new BillService(connection);
    }

    @Test
    public void testCreateBill() throws SQLException {
        Bill bill = new Bill();
        bill.setBillId(1);
        bill.setTotal(100.00);
        bill.setSaleDate("2024-10-05");
        bill.setItemId(123);

        // Call createBill method
        billService.createBill(bill);

        // Validate that the bill was added to the database
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM bill WHERE bill_id = ?");
        stmt.setInt(1, bill.getBillId());
        ResultSet rs = stmt.executeQuery();

        assertTrue(rs.next());  // Ensure a result is returned
    }

    @After
    public void tearDown() throws SQLException {
        // Clean up test data
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM bill WHERE bill_id = ?");
        stmt.setInt(1, 1);
        stmt.executeUpdate();
    }
}
