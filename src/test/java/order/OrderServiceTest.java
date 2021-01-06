package order;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OrderServiceTest {

    @Test
    public void syncBookOrders_3_orders_only_2_book_order() {
        OrderServiceForTest orderService = new OrderServiceForTest();
        orderService.setOrders(asList(
                new Order() {{
                    setType("Book");
                }},
                new Order() {{
                    setType("CD");
                }},
                new Order() {{
                    setType("Book");
                }}
        ));

        BookDao bookDao = Mockito.mock(BookDao.class);
        orderService.setBookDao(bookDao);
        orderService.syncBookOrders();

        verify(bookDao, times(2))
                .insert(argThat(order -> order.getType().equals("Book")));
    }

    private class OrderServiceForTest extends OrderService {
        private List<Order> orders;
        private BookDao bookDao;

        @Override
        protected BookDao getBookDao() {
            return this.bookDao;
        }

        public void setBookDao(BookDao bookDao) {
            this.bookDao = bookDao;
        }

        @Override
        protected List<Order> getOrders() {
            return this.orders;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
        }
    }
}
