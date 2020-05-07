# TODO LIST 

* 개인 학습용 할일 정리 어플리케이션 서버 만들기.
* 학습 목표
    * Sprint 1 (3.25 ~ 5.30)  
    기본적인 할일 정리 어플리케이션의 기능을 구현하는것이 목표  
    테스트 코드 작성, 문서화 등을 주된 목표로 설정.   
    DDD로 구현 할 예정. 설계는 구축과 함께 변경 되며 계속하여 기록 할 예정.
    * 기술스택
        * Spring Boot
            * Spring MVC의 적절한 기능들을 탐색하며 적용할 예정.
        * Spring Data JPA
            * JPA, Querydsl을 사용한 ORM 구축.
        * Spring HATEOAS
            * RestAPI 스펙을 최대한 지키는것이 목표.        
* 기능정리
    * [Trello](https://trello.com)기능 구현
    * Board
        * 새로운 Board 생성할 수 있다. 
        * Board는 이름, 생성자를 가진다.
        * Board List를 조회 할 수 있다.
    * Subject
        * Subject List를 조회 할 수 있다.
        * Board 내부에 Subject를 생성 할 수 있다.
            * Subject는 이름을 가진다.
        * Subject 내부에 Card를 등록 할수 있어야 한다.
        * Subject 간 Card는 이동 가능해야 한다.
    * Card
        * Card는 Subject내부에 포함되는 일정의 단위 이다.
        * Card 생성
            * Label, Contents, Deciription, Due Date를 가진다.
        * Card 수정
        * 조회
        * 삭제
## 용어 정리
### Board
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 할일 정리판 | Board | 큰 카테고리로 정의할 수 있는 할일 정리 판. |
| 할일 목록 | Board List | 등록한 할일 들의 목록. |

### Subject
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 주제 | Subject | 할일 정리판에 작은 단위로 나누어 그룹화 할 수 있는 단위. |

### Card
| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 할일 | Card | 가장 작은 단위의 할일. |        
       
        
