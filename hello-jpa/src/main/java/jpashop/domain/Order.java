package jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

/**
 * packageName    : jpashop.domain
 * fileName       : Order
 * author         : Jihun Park
 * date           : 2022/03/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/11        Jihun Park       최초 생성
 */
@Entity
@Table(name="ORDERS") //DB듦 예약어때문에 이렇게 만
public class Order extends BaseEntity{
    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

//    @Column(name="MEMBER_ID")
//    private Long memberId; // 객체를 RDB에 맞춤! 객체지향적이지 않다!!!

    @ManyToOne (fetch = LAZY)
    @JoinColumn(name="MEMBER_ID" )
    private Member member;


    @OneToOne(fetch = LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    private LocalDateTime orderDate; //Temporal 필요없음

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //연관관계 편의 메서드!! 주인은 orderitem.order를 수정하면 되지만 둘다 바꿔주기 위해
    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem); // 가짜맵핑
        orderItem.setOrder(this);       // 진짜맵핑
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
