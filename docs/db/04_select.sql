select auth.card, auth.password, member.priviledge from auth, member where auth.card = member.card;