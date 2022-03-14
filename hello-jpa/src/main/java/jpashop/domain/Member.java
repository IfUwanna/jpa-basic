package jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : jpashop.domain
 * fileName       : Member
 * author         : Jihun Park
 * date           : 2022/03/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/11        Jihun Park       최초 생성
 */
@Entity
public class Member extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // 비즈니스상 끊어주는게 맞음!
    List<Order> Orders = new ArrayList<>();

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
