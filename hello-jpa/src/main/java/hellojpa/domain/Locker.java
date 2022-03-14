package hellojpa.domain;

import javax.persistence.*;

/**
 * packageName    : hellojpa.domain
 * fileName       : Locker
 * author         : Jihun Park
 * date           : 2022/03/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/13        Jihun Park       최초 생성
 */
@Entity
public class Locker {
    @Id
    @GeneratedValue
    @Column(name="LOCKER_ID")
    private long id;
    private String name;

    @OneToOne(mappedBy = "locker")
    Member member;

}
