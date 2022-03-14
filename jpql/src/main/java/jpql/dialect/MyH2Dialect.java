package jpql.dialect;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

/**
 * packageName    : jpql.domain
 * fileName       : MyH2Dialect
 * author         : Jihun Park
 * date           : 2022/03/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/15        Jihun Park       최초 생성
 */
public class MyH2Dialect extends H2Dialect {
    public MyH2Dialect(){
        this.registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
