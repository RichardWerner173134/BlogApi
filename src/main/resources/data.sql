insert into beitrag (id, title, content, author_id)
values (
1, 'Foo', 'Bar, foo baR. BarF oo FOO bAR!!!!!!!!!! \nBar foo, FUU BAA Foo bAR! Bar fu Foo baR?\n\nbar bAR BAR foo foO fOo FoBaor!', 'hans'
)

insert into "user" (username, auth, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, nachname, password, profil_bild, vorname)
values(
'hans', 'user', true, true, true, true, 'werner', 'hans', null, 'hans'
)