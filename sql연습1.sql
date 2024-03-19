SELECT * FROM friend;  -- friend 테이블 전체 내용 보기

insert into friend (name, address) values ('홍길동', '서울');
insert into friend (name, age) values ('홍길동', 30);
insert into friend values ('홍길동', 25,'청주');
insert into friend values ('홍길동',default,'청주');

delete from friend;
delete from friend where address = '청주';
