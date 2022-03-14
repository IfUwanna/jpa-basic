package hellojpa.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * packageName    : hellojpa.domain
 * fileName       : BaseEntity
 * author         : Jihun Park
 * date           : 2022/03/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/14        Jihun Park       최초 생성
 */
@MappedSuperclass
public class BaseEntity {
    @Column(name="ins_dtm")
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
