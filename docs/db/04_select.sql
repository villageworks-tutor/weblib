-- select auth.card, auth.password, member.priviledge from auth, member where auth.card = member.card;
-- select member.card, member.name, member.zipcode, member.address, member.phone, member.email, member.birthday, member.priviledge, priviledge.name from member, priviledge where member.priviledge = priviledge.code;
select member.card, member.name, member.zipcode, member.address, member.phone, member.email, member.birthday, member.priviledge as priciledgeCode, priviledge.name as priviledgeName from member join priviledge on member.priviledge = priviledge.code where member.email = 'yoshiko_shimada@ztvzw.frig.fhbl';