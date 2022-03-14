package hellojpa.domain;

import org.h2.engine.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : hellojpa
 * fileName       : Team
 * author         : Jihun Park
 * date           : 2022/03/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/11        Jihun Park       최초 생성
 */
@Entity
public class Team extends BaseEntity{
    @Id
    @GeneratedValue()
    @Column(name="TEAM_ID")
    private Long id;
    private String name;
//  Memeber Team 다대일
//    @OneToMany(mappedBy = "team")  // team이 주인임! mappedBy 기재된건 읽기만 가능!
//    private List<Member> members = new ArrayList<>();

    // Team Member 일대다 (보통 N쪽 오래키가 있는곳에 거는데 PK가 있는 TEAM에 일대다. 잘안쓴다.)
    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();

    public Team() {
    }

    public void addMember(Member member){  // 어느쪽에 양방향으로 넣을지 정하면된다.
        member.setTeam(this);
        members.add(member);
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
