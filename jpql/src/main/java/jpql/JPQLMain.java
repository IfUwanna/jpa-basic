package jpql;
import jpql.domain.Address;
import jpql.domain.Member;
import jpql.domain.MemberDTO;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JPQLMain {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;
    public static void main(String[] args) {

        emf = Persistence.createEntityManagerFactory("hello");
        em = emf.createEntityManager(); // 고객의 요청마다 사용하고 버려야함 (쓰레드간 공유X)
        tx = em.getTransaction(); // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

        tx.begin();// 트랜잭션 시작
        try{

            insertMember("member",10);
            insert100Member();

            // TypedQuery, Query
            TypedQuery<Member> query = em.createQuery("select m From Member m", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.username From Member m", String.class);
            Query query3 = em.createQuery("select m.username From Member m"); //반환형이 명확하지 않을때

            // getResultList(), getSingleResult()
            List<Member> resultList = query.getResultList(); // 결과가 하나 이상일 때, 리스트 반환
//            Member result = query.getSingleResult();         // 결과가 정확히 하나, 단일 객체 반환

            //파라미터 바인딩 - 이름 기준, 위치 기준
            query = em.createQuery("SELECT m FROM Member m where m.username=:username",Member.class);
            query.setParameter("username", "지훈:이름기준"); //이름기준
//            query = em.createQuery("SELECT m FROM Member m where m.username=?1",Member.class);
//            query.setParameter(1, "지훈:위치기준");           //위치기준, 왠만하면 X

            //메서드 체이닝
            resultList = em.createQuery("SELECT m FROM Member m where m.username=:username",Member.class)
                    .setParameter("username", "지훈:이름기준")
                    .getResultList();

            //엔티티 프로젝션 -> 영속성 컨텍스트에서 다 관리 (select 절에 지정)
            // 프로젝션 - 임베디드타입
            em.createQuery("SELECT o.address FROM Order o", Address.class).getResultList();

            // 프로젝션 - Query, Object[], new
            List<Query> projectionbyQuery =  em.createQuery("SELECT m.username,m.age FROM Member m").getResultList();
            List<Object[]> projectionbyObj = em.createQuery("SELECT m.username,m.age FROM Member m").getResultList();
            Object o = projectionbyObj.get(0); // Object[]
            List<MemberDTO> projectionBynew = em.createQuery("SELECT new jpql.domain.MemberDTO(m.username,m.age) FROM Member m", MemberDTO.class).getResultList();

            //페이징
            List<Member> pagingMember = em.createQuery("SELECT m from Member m order by m.age desc", Member.class)
                    .setFirstResult(2) //조회 시작 위치 (0부터 시작)
                    .setMaxResults(10) //조회할 데이터 수
                    .getResultList();

            //조인



            tx.commit();

        }catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();

    }

    private static void insertMember(String username, int age) {
        Member member = new Member();
        member.setUsername(username);
        member.setAge(age);
        em.persist(member);
    }
    private static void insert100Member() {
        for (int i = 0; i < 100; i++) {
            Member member = new Member();
            member.setUsername("autoUser"+i);
            member.setAge(i);
            em.persist(member);
        }
    }



}
