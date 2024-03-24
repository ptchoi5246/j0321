/*이것이 바로 Create Read Update Delete CRUD*/
/*명령어 대문자 사용하기*/
--영역O : Alt+X; 영역X : Alt+S
show tables;

create table test(
	idx        int not null auto_increment primary key, /*고유번호 추가*/
	name       varchar(20) not null,			/*성명*/
	age        int default 20,						/*나이*/
	gender     varchar(2) default '남자',		/*성별*/
	job				 varchar(20) default '기타', /*직업 추가*/
	address    varchar(50)								/*주소*/
);
drop table test; /*구조 지움*/
delete from test; /*내용만 지움*/
desc test; /*test 테이블의 구조 보기*/

insert into test values(default, '홍길동', default, default, default,'서울');
insert into test values(default, '김말숙', 34, '여자', default, '청주');
insert into test values(default, '이기자', 29, '남자', default, '부산');
insert into test values(default, '김연아', default, '여자', default, '제주');
insert into test values(default, '손흥민', 33, default, default, '서울');
insert into test values(default, '소나무', 55, default, default, '제주');
insert into test values(default, '대나무', 11, '여자', '학생', '제주');
insert into test values(default, '감나무', 22, '남자', default, '서울');

select * from test;
/*영역O : Alt+X; 영역X : Alt+S*/

DELETE FROM test WHERE name='손흥민';

/*주석, 웬만하면 이걸 사용하기 / 무슨 용도로 사용하는지 주석 자세하게 적어두기*/
/*레코드 수정하기 : update 테이블명 set 필드명 = '수정내용' where '조건(필드명=값)';*/
update test set age=25 where name='홍길동';

/*남자들의 나이를 1살씩 모두 더해주세요.*/
update test set age = age +1;
update test set age = age -1;
update test set age = age +1 where gender ='남자';

/*'서울'에 사는 사람들만 보여주세요.*/
select * from test where address = '서울';

/*'서울'과 '부산'에 사는 사람들을 보여주세요.*/
select * from test where address = '서울' or address = '부산';

/*나이가 30살 미만인 회원을 보여주세요.*/
select * from test where age < 30;

/*나이가 30살 미만인 여자회원을 보여주세요.*/
select * from test where age < 30 and gender = '여자';

/*'청주'에 사는 회원을 보여주세요*/
select * from test where address = '청주';

/*'청주'에 사는 회원을 삭제주세요*/
delete from test where address = '청주';

/*'청주/남자/19/강감찬 인 회원을 등록하세요.*/
insert into test values ('강감찬',19,default,'청주');

/*'서울'에 사는 '여자'회원의 나이를 2살씩 빼주세요.*/
update test set age = age-2 where gender = '여자' and address '서울';

/*test 테이블의 구조 보기*/
desc test;

/*필드 구조 변경 : alter table 테이블명 ~ */
/*test 테이블에서 job 필드(직업명은 5글자 이내, 기본값 :기타) (필드= 컬럼)를 추가(add colunm)하시오*/
alter table test add column job varchar(6) default '기타';

/*test 테이블에서 job 필드 삭제하기(drop column)*/
alter table test drop column job;

/*test 테이블의 job 필드의 길이를 20자로 수정하시오 (modify column:속성을 바꿈)*/
alter table test modify column job varchar(20);

/*test 테이블의 name 필드명을 irum 필드로 변경하세요.(change column : 이름을 바꿈)*/
alter table test change column name irum varchar(20);
alter table test change column irum name varchar(20);

/*test 테이블에 고유번호(idx)필드를 추가하시오. -기본키(구분이 될 수 있는 중복을 배제한 필드) 추가*/
alter table test add column idx int not null auto_increment primary key;
/*auto_increment 지정은 무조건 primary key여야 한다.*/
/*회원가입 - primary key : 아이디, 주민등록번호 중복 불가*/
/*MySQL에는 primary key 하나만 가능 / 다른 primary key를 사용하고 싶으면 unique key 사용 //여러개 사용 가능???*/
/*지역, 나이 같이 primary key로 묶을 수 있다. (address, age) primary key*/

/*idx = 5번을 삭제하시오*/
delete from test where idx = 10;

/*고유번호(idx)값을 5번부터 시작하도록 설정하시오*/
alter table test auto_increment = 5;




