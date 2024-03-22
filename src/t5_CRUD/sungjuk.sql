show tables;

create table sungjuk(
	idx			int not null auto_increment primary key, /*성적 고유번호*/
	name		varchar(20) not null, 									/*성명, 중복 불허*/
	kor			int default 0,													/*국어 점수*/
	eng			int default 0,													/*영어 점수*/
	mat			int default 0														/*수학 점수*/
);

desc sungjuk;


insert into sungjuk values (default, '홍길동', 100, 90, 80);

select * from sungjuk;