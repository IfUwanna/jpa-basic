package hellojpa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * packageName    : hellojpa
 * fileName       : Member
 * author         : Jihun Park
 * date           : 2022/03/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/11        Jihun Park       최초 생성
 */

//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",  //식별자 생성기 이름 (필수)
//        sequenceName = "MEMBER_SEQ",    //매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1,               //처음생성하는 수 지정
//        allocationSize = 1)             //한번에 증가하는 수 (주의 기본값 50)
//@TableGenerator(
//        name = "MEMBER_SEQ_GENERATOR",  //식별자 생성기 이름 (필수)
//        table = "MY_SEQUENCES",         //키생성 테이블명
//        pkColumnName = "SEQUENCE_NAME", //시퀀스 컬럼명 (기본 : sequence_name)
//        valueColumnName = "NEXT_VAL",   //시퀀스 값 컬렴명 (기본 : next_val)
//        initialValue = 0 ,              //초기값
//        pkColumnValue = "MEMBER_SEQ",   //시퀀스 키로 사용할 값 컬렴명
//        allocationSize = 1)             //한번에 증가하는 수 (주의 기본값 50)
@Entity(name="defaultMember")
@Table(name="HELLO_MEMBER")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name")
    private String username;
    private Integer age;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID") //현재 클래스가 Many, 참조객체가 One
    private Team team;

    @OneToOne
    @JoinColumn(name="LOCKER_ID") // 주테이블에 외래키 단방향. 성능상 유리
    private Locker locker;

    @Embedded   // 2. 임베디드 타입(복합 값션 타입) 사용
    private Address address;

//    @ElementCollection// 3. 값타입 컬렉션
//    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "MEMBER_ID"))
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }


 /*    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private String temp;*/


}
