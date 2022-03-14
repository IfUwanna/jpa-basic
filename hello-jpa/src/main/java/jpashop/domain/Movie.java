package jpashop.domain;

import javax.persistence.Entity;

/**
 * packageName    : jpashop.domain
 * fileName       : Movie
 * author         : Jihun Park
 * date           : 2022/03/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/14        Jihun Park       최초 생성
 */
@Entity
public class Movie extends Item{
    private String director;
    private String actor;
}
