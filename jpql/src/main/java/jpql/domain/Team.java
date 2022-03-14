package jpql.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : jpql.domain
 * fileName       : Team
 * author         : Jihun Park
 * date           : 2022/03/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/14        Jihun Park       최초 생성
 */
@Entity
public class Team {

    @Id @GeneratedValue
    private Long id;

    private String name;

    public Team() {
    }

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();


}
