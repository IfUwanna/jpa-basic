package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Team;
import org.h2.engine.User;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * packageName    : hellojpa
 * fileName       : JpaMain
 * author         : Jihun Park
 * date           : 2022/03/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/03/11        Jihun Park       최초 생성
 */
public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // 고객의 요청마다 사용하고 버려야함 (쓰레드간 공유X)
        EntityTransaction tx = em.getTransaction(); // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

        tx.begin();// 트랜잭션 시작
        try{

            /* insert */
/*
            Member member = new Member();
            member.setId(1L);
            member.setName("helloA");
            em.persist(member);
*/

            /* select */
/*
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());
*/

            /* update */
 //           findMember.setName("HelloJPA"); //별도로 저장안해도됨 컬렉션처럼 다룰수있음

            /* 전체 조회 */
/*
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(2)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }
*/

            /* 영속성 컨텍스트 예제 */
            //비영속
/*            Member member = new Member();
            member.setId(101L);
            member.setName("helloJPA");
            //영속 (영속성컨텍스트에 저장 = DB에 저장은 안됨)
            System.out.println("=== BEFORE ===");
            em.persist(member);
            가;

            Member findMember = em.find(Member.class, 101L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());
            /* 영속 엔티티의 동일성 보장 */
           /* Member findMember1 = em.find(Member.class, 101L); //첫 조회때 영속성컨텍스트 1차캐시에 올림
            Member findMember2 = em.find(Member.class, 101L); //1차캐시에 올라간 데이터를 영속성컨텍스트에서 가져
            System.out.println("result = " + (findMember1 == findMember2));
           */
            /* 쓰기지연 SQL 저장소속 */
/*            Member member1 = new Member(150L,"A");
            Member member2 = new Member(160L,"B");
            em.persist(member1);
            em.persist(member2);
            감*/
            // 변경 감지
/*            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");*/

            /* 플러시 */
/*            Member member = em.find(Member.class, 200L);
            em.persist(member);
            em.flush(); //   쓰기지연SQL저장소의 쿼리가 DB에 나
            System.out.println("================");
            */

            /* 준영 */
            /*Member member = em.find(Member.class, 150L);
            //member.setName("AAAAA");
            //em.detach(member);
            em.clear(); // 영속성컨텍스트 전체 초기화

            Member member2 = em.find(Member.class, 150L); // 다시 DB조회해서 올림
            System.out.println("================");
            tx.commit();*/

            /* Enumerated */
/*            Member member = new Member();
            member.setId(2L);
            member.setUsername("B");
            member.setRoleType(RoleType.ADMIN);
            em.persist(member);
            */
/*

            Member member = new Member();
            member.setUsername("B");
            member.setRoleType(RoleType.ADMIN);
            em.persist(member);
*/

            /* 5-1 단방향 연관관계 */
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("member1");
//            member.setTeam(team); //단방향 연관관계 설정, 참조 저장
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            Member findMember = em.find(Member.class, member.getId());
//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam.getName() = " + findTeam.getName());

            //Team 변경- 연관관계 수정
//            Team newTeam = new Team();
//            team.setName("TeamB");
//            em.persist(newTeam);
//            findMember.setTeam(newTeam);

            /* 5-2 양방향 연관관계 */
            //저장
/*            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.changeTeam(team);           // 이게 중요함!! 연관관계 편으 ㅣ메소드를 생성하자
            team.getMembers().add(member);  //가짜 맵핑이지만 순수 객체상태 고려해서 세팅은 해주자! 아래것만으로도 데이터는 들어감
            em.persist(member);

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시!영속성 컨텍스트에는 members가 없어
            List<Member> members = findTeam.getMembers(); //지연로딩! 사용하는 시점에 날림
            for (Member m : members) {
                System.out.println("m.getName() = " + m.getName());
            }*/

            /* 6. 일대다 */
/*            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            Team team = new Team();
            team.setName("TeamA");
            team.getMembers().add(member);
            em.persist(team);*/


            // JPQL
//            List<Member> resultList = em.createQuery("select m From defaultMember m where m.username like '%park%'", Member.class)
//                    .getResultList();

            // Criteria
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq= query.select(m).where(cb.equal(m.get("username"),"kim"));
            List<Member> resultList = em.createQuery(cq).getResultList();

            for (Member member : resultList) {
                System.out.println("member.getName() = " + member.getUsername());
            }
            //native query
            em.createNativeQuery("select member_id from member").getResultList();



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
