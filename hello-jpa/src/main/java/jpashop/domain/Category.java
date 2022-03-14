package jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

/**
 * packageName    : jpashop.domain
 * fileName       : Category
 * author         : Jihun Park
 * date           : 2022/03/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/13        Jihun Park       최초 생성
 */
@Entity
public class Category extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="CATEGORY_ITEM",
            joinColumns = @JoinColumn(name="CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name="ITEM_CD")
    )
    private List<Item> items = new ArrayList<>();

}
