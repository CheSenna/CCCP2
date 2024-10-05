import com.syospos.yourapp.dao.SalesDetailsDAO;
import com.syospos.yourapp.model.SalesDetail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class SalesDetailsDAOTest {

    private SalesDetailsDAO salesDetailsDAO;
    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        // Assuming a local database for testing
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/syosdb", "root", "password");
        salesDetailsDAO = new SalesDetailsDAO(connection);
    }

    @Test
    public void testCreateSalesDetail() throws SQLException {
        SalesDetail salesDetail = new SalesDetail();
        salesDetail.setSaleId(1);
        salesDetail.setItemCode("ITEM123");
        salesDetail.setQuantity(2);
        salesDetail.setPricePerItem(50.00);
        salesDetail.setTotalPrice(100.00);

        // Call create method
        salesDetailsDAO.create(salesDetail);

        // Validate that the sales detail was added to the database
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM sales_details WHERE sale_id = ?");
        stmt.setInt(1, salesDetail.getSaleId());
        ResultSet rs = stmt.executeQuery();

        assertTrue(rs.next());  // Ensure a result is returned
    }

    @After
    public void tearDown() throws SQLException {
        // Clean up test data
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM sales_details WHERE sale_id = ?");
        stmt.setInt(1, 1);
        stmt.executeUpdate();
    }
}
