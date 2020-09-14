select * from EMPLOYEES;

-- 회원가입 --> VO와 DAO
create table tblMem(
 num number primary key, -- 일련번호 : 회원의 번호
 name varchar2(20) not null, -- 이름 : 필수입력 컬럼
 phone varchar2(20) not null, -- 번호 : 필수입력 컬럼
 addr varchar2(50), -- 주소는 null 값을 주지 않음 : 우편번호 조회컬럼 위도경도찾기(주소의다른개념-프로그램상구하기)
 lat number(16, 12), -- (위도,소수점) : 소수점으로 나올 수 있도록
 lng number(16, 12)  -- (경도,소수점) : 소수점으로 나올 수 있도록
)
-- 잘못했을 때, drop table tblMem; 한 후 다시 시도하면됨
create sequence seq_num; -- 자동으로 증가하는 일련번호
-- 샘플로 하나 저장해볼까요?
insert into tblMem
values ( seq_num.nextval, -- seq_num.nextval : 자동으로 계속 num에 있는 값이 증가하게 하는 것
'나길동',
'010-11111-1111',
'광주광역시 서구 풍암동',
35.1257699845615,
126.852047602507);

select * from tblMem;