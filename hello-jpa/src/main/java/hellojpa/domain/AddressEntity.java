package hellojpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * packageName    : hellojpa.domain
 * fileName       : AddressEntity
 * author         : Jihun Park
 * date           : 2022/03/14
 * description    : 값타임 래퍼 엔티
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/14        Jihun Park       최초 생성
 */
@Entity
public class AddressEntity {

    @Id @GeneratedValue
    private Long id;
    private Address address;
    public AddressEntity(String city, String street, String zipcode){
        this.address = new Address(city,street,zipcode);
    }

    public AddressEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
