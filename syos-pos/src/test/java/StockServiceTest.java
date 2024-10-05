import com.syospos.yourapp.model.Item;
import com.syospos.yourapp.service.StockService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class StockServiceTest {

    private StockService stockService;
    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        // Assuming a local database for testing
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/syosdb", "root", "password");
        stockService = new StockService(connection);
    }

    @Test
    public void testUpdateStock() throws SQLException {
        Item item = new Item();
        item.setItemId(1);
        item.setStock(50);

        stockService.updateStock(item, 10);
        assertTrue(item.getStock() == 40);
    }

    @After
    public void tearDown() throws SQLException {
        // Clean up test data if necessary, assuming a reset of the item's stock is needed
        // This would depend on how your StockService is implemented
        Item item = new Item();
        item.setItemId(1);
        item.setStock(50);  // Reset stock to original value for testing purposes
        stockService.updateStock(item, -10); // Resetting the stock back
    }
}
