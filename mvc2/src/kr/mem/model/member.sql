select * from EMPLOYEES;

-- ȸ������ --> VO�� DAO
create table tblMem(
 num number primary key, -- �Ϸù�ȣ : ȸ���� ��ȣ
 name varchar2(20) not null, -- �̸� : �ʼ��Է� �÷�
 phone varchar2(20) not null, -- ��ȣ : �ʼ��Է� �÷�
 addr varchar2(50), -- �ּҴ� null ���� ���� ���� : �����ȣ ��ȸ�÷� �����浵ã��(�ּ��Ǵٸ�����-���α׷����ϱ�)
 lat number(16, 12), -- (����,�Ҽ���) : �Ҽ������� ���� �� �ֵ���
 lng number(16, 12)  -- (�浵,�Ҽ���) : �Ҽ������� ���� �� �ֵ���
)
-- �߸����� ��, drop table tblMem; �� �� �ٽ� �õ��ϸ��
create sequence seq_num; -- �ڵ����� �����ϴ� �Ϸù�ȣ
-- ���÷� �ϳ� �����غ����?
insert into tblMem
values ( seq_num.nextval, -- seq_num.nextval : �ڵ����� ��� num�� �ִ� ���� �����ϰ� �ϴ� ��
'���浿',
'010-11111-1111',
'���ֱ����� ���� ǳ�ϵ�',
35.1257699845615,
126.852047602507);

select * from tblMem;