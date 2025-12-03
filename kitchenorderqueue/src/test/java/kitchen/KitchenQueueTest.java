public class KitchenQueueTest {
    
    private KitchenOrderQueue queue; 

    @BeforeEach
    void setUp() {
        queue = new KitchenOrderQueue(); // fresh queue before each test
    } 
    @Test
     void  AddNewOrderToQueue(){
        Order newOrder = new Order("101");
        queue.receiveNewOrder(newOrder);

        assertTrue(queue.getActiveOrders().contains(newOrder));
     }
    @Test
    void orderStatusShouldUpdateToInProgress() {
        Order order = new Order("102");
        queue.receiveNewOrder(order);

        queue.markOrderInProgress(order.getId()); 

        assertEquals(OrderStatus.IN_PROGRESS, order.getStatus(),
                "Order status should be IN_PROGRESS after starting");
    }
    @Test
    void completedOrdersShouldBeRemovedFromActiveList() {
        Order order = new Order("103");
        queue.receiveNewOrder(order);

        queue.completeOrder(order.getId());  

        List<Order> activeOrders = queue.getActiveOrders();

        assertFalse(activeOrders.contains(order),
                "Completed order should not be in active list");
    }
}

