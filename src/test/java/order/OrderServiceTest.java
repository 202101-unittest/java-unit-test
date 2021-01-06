package order;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderService orderService;
    private BookDao bookDao;

    @Before
    public void setUp() throws Exception {
//        orderService = new OrderServiceForTest();
        orderService = Mockito.spy(OrderService.class);

        bookDao = Mockito.mock(BookDao.class);
//        orderService.setBookDao(bookDao);
        when(orderService.getBookDao()).thenReturn(bookDao);
    }

    @Test
    public void syncBookOrders_3_orders_only_2_book_order() {
        givenOrders(
                createOrder("Book"),
                createOrder("CD"),
                createOrder("Book"));

        orderService.syncBookOrders();

        shouldInsertOrders("Book", 2);
    }

    private void shouldInsertOrders(String type, int times) {
        verify(bookDao, times(times))
                .insert(argThat(order -> order.getType().equals(type)));
    }

    private void givenOrders(Order... orders) {
//        orderService.setOrders(Arrays.asList(orders));
        when(orderService.getOrders()).thenReturn(Arrays.asList(orders));
    }

    private Order createOrder(final String type) {
        return new Order() {{
            setType(type);
        }};
    }

//    private class OrderServiceForTest extends OrderService {
//        private List<Order> orders;
//        private BookDao bookDao;
//
//        @Override
//        protected BookDao getBookDao() {
//            return this.bookDao;
//        }
//
//        public void setBookDao(BookDao bookDao) {
//            this.bookDao = bookDao;
//        }
//
//        @Override
//        protected List<Order> getOrders() {
//            return this.orders;
//        }
//
//        public void setOrders(List<Order> orders) {
//            this.orders = orders;
//        }
//    }
}
