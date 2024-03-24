show tables;

-- 직급별 본봉 테이블
create table salary( /*성  적  표*/
	jikkub 			char(8) not null primary key,		/*직급*/
	bonbong 		int not null 										/*본봉*/
);

desc salary;

insert into salary values ('부장', 5000000);
insert into salary values ('과장', 4000000);
insert into salary values ('대리', 3000000);
insert into salary values ('사원', 2000000);

select * from salary;


-- 인사관리 테이블
create table insa ( /*인사 관리 프로그램 1.전체조회 2.개별조회 3.입력 4. 수정 5. 삭제 0.종료*/
	idx 			int not null auto_increment primary key, /*인사관리 고유 번호*/
	sabun 		char(8) not null, 											/*직급코드 년2월2일2 일련번호2 중복불가*/ /*사번 : 오늘 날짜 api date 2024-03-23 substring 23 56 89 + 같은 날짜 찾아서 비교 - 있으면 count 함수 사용*/
	buseo			varchar(10) not null, 									/*인사과, 총무과, 생산과, 영업과 -- 입력*/ 
	name			varchar(20) not null,										/*성명 --입력*/
	jikkub 		char(8) not null,												/*부장, 과장, 대리, 사원 --salary 테이블에서 가져오기*/
	age				int default 25,													/*나이, 기본값 25 -- 입력*/
	ipsail 		datetime default now(),									/*입사일 -- 입력*/
	gender		char(2) default '여자',										/*성별 -- 입력*/
	address 	varchar (30), 													/*주소 -- 입력*/
	unique key (sabun),																/*중복불가키 : 사번*/
	foreign key (jikkub) references salary (jikkub) 	/*외래키*/
);
desc insa;
--drop table insa;

insert into insa values (default, '24032101', '인사과', '홍길동', '과장', 35, '2000-1-5', '남자', '서울');
insert into insa values (default, '24032102', '영업과', '김말숙', '대리', 31, '2007-11-25', default, '청주');
insert into insa values (default, '24032201', '총무과', '이기자', '사원', 25, '2022-8-22', '남자', '서울');

select * from insa;

--이기자의 급여 계산
--사원의 본봉 검색
select * from salary where jikkub = '사원';

--이기자 의 본봉 insa + salary join
select * from salary s, insa i where s.jikkub = i.jikkub; /*join의 기본 조건*/
select i.name, i.jikkub, s.bonbong from salary s, insa i where s.jikkub = i.jikkub;




