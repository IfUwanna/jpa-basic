package jpql;
import jpql.domain.Member;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JPQLMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // 고객의 요청마다 사용하고 버려야함 (쓰레드간 공유X)
        EntityTransaction tx = em.getTransaction(); // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

        tx.begin();// 트랜잭션 시작
        try{

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            // TypedQuery, Query
            TypedQuery<Member> query = em.createQuery("select m From Member m", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.username From Member m", String.class);
            Query query3 = em.createQuery("select m.username From Member m"); //반환형이 명확하지 않을때

            // getResultList(), getSingleResult()
            List<Member> resultList = query.getResultList(); // 결과가 하나 이상일 때, 리스트 반환
            Member result = query.getSingleResult();         // 결과가 정확히 하나, 단일 객체 반환

            //파라미터 바인딩 - 이름 기준, 위치 기준
            query = em.createQuery("SELECT m FROM Member m where m.username=:username",Member.class);
            query.setParameter("username", "지훈:이름기준"); //이름기준
            query = em.createQuery("SELECT m FROM Member m where m.username=?1",Member.class);
            query.setParameter(1, "지훈:위치기준");           //위치기준, 왠만하면 X

            //메서드 체이닝
            resultList = em.createQuery("SELECT m FROM Member m where m.username=:username",Member.class)
                    .setParameter("username", "지훈:이름기준")
                    .getResultList();

            tx.commit();

        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();

    }
}
