package jpashop;

import jpashop.domain.Book;
import jpashop.domain.Member;
import jpashop.domain.Order;
import jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * packageName    : jpashop
 * fileName       : JpaShopMain
 * author         : Jihun Park
 * date           : 2022/03/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/11        Jihun Park       최초 생성
 */
public class JpaShopMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // 고객의 요청마다 사용하고 버려야함 (쓰레드간 공유X)
        EntityTransaction tx = em.getTransaction(); // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

        tx.begin();
        try{
/*            Order order = new  Order();
            OrderItem orderItem = new OrderItem();
            order.addOrderItem(orderItem);
//            orderItem.setOrder(order); // 이렇게 단방향만해도 문제가없다.
             em.persist(orderItem);*/

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");

            em.persist(book);
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
