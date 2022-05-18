insert into blog_user (username, auth, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, nachname, password, profil_bild, vorname) values
('admin', 'admin', true, true, true, true, 'Werner', 'admin', null, 'Richard'),
('BlogUser111', 'user', true, true, true, true, 'Meier', 'pw123', null, 'Oliver'),
('BlogUser112', 'user', true, true, true, true, 'Schneider', 'pw123', null, 'Beate'),
('BlogUser113', 'user', true, true, true, true, 'Kramer', 'pw123', null, 'Detlev'),
('BlogUser114', 'user', true, true, true, true, 'Obama', 'pw123', null, 'Barack'),
('BlogUser115', 'user', true, true, true, true, 'Trump', 'pw123', null, 'Donald');



insert into beitrag (id, title, content, author_id) values
(1, 'Von Foos und Bars', 'Bar, foo baR. BarF oo FOO bAR!!!!!!!!!! Bar foo, FUU BAA Foo bAR! Bar fu Foo baR? bar bAR BAR foo foO fOo FoBaor!', 'admin'),
(2, 'Lorem Ipsum1', 'What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry´s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', 'BlogUser111'),
(3, 'Lorem Ipsum2', 'Where does it come from?Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32. The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.', 'BlogUser111'),
(4, 'Lorem Ipsum3', 'Why do we use it? It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using ´Content here, content here´, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for ´lorem ipsum´ will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).', 'BlogUser112'),
(5, 'Lorem Ipsum4', 'Where can I get some? There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don´t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn´t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.', 'BlogUser113'),
(6, 'Lorem Ipsum5', 'Cras dui elit, pretium sed placerat eu, scelerisque id lorem. Vivamus scelerisque fermentum tortor, et condimentum ante condimentum sed. In hac habitasse platea dictumst. Vestibulum ut leo aliquam, vulputate est ac, egestas ipsum. Quisque a bibendum eros. Nullam iaculis ultricies orci a commodo. Donec sit amet magna dui. Aliquam rutrum vitae elit ut ultrices. Nulla et aliquam massa. Ut ut risus et neque pulvinar malesuada. Aliquam tristique odio viverra massa condimentum finibus. Morbi faucibus quis ex at pellentesque. Phasellus vel metus vitae nisl lobortis ultrices. Sed sodales quam ut purus tempor, at congue tellus aliquam. Maecenas felis odio, egestas suscipit pellentesque ac, tempor sed ipsum. Vivamus sem magna, tincidunt sit amet diam vel, sodales gravida eros.', 'BlogUser114'),
(7, 'Lorem Ipsum6', 'Proin quam orci, dictum et maximus fermentum, suscipit id est. Cras id ex eros. Etiam sit amet suscipit urna. Etiam accumsan diam non condimentum accumsan. Maecenas non enim id enim dapibus iaculis. Duis id efficitur diam. Vivamus urna sapien, sagittis id tellus nec, dapibus efficitur ipsum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ac massa libero. Curabitur dapibus risus urna, ac hendrerit dolor sollicitudin vel. Donec tempor massa est, ac convallis arcu consequat at. Nunc faucibus, erat a maximus viverra, tortor ipsum tincidunt enim, eget aliquet nulla diam sed ligula. Nam venenatis nunc vitae augue tincidunt ullamcorper. Quisque venenatis mi eu lectus fermentum, eget imperdiet nisl molestie.', 'BlogUser115'),
(8, 'Lorem Ipsum7', 'Donec eget odio lobortis, varius risus sit amet, vestibulum tortor. Mauris tristique consectetur tincidunt. Nulla scelerisque tempor massa non facilisis. Pellentesque convallis massa at magna varius, volutpat mollis leo finibus. Praesent ultricies laoreet tincidunt. Duis convallis vestibulum massa id aliquam. Quisque pellentesque leo libero, vel auctor purus pharetra id. Aliquam aliquet sapien vel viverra pellentesque. Donec malesuada turpis nec feugiat fringilla.', 'BlogUser112');

insert into beitrag_view (beitrag_id, date, author_id) values
(1, '2022-05-18 10:47:00.000', 'admin'),
(1, '2022-05-18 10:53:00.000', 'BlogUser111'),
(2, '2022-05-18 18:39:00.000', 'BlogUser112'),
(2, '2022-05-18 19:31:00.000', 'BlogUser113'),
(2, '2022-05-18 19:55:00.000', 'BlogUser114'),
(3, '2022-05-18 12:02:00.000', 'BlogUser115'),
(3, '2022-05-18 13:19:00.000', 'BlogUser114'),
(3, '2022-05-18 11:23:00.000', 'BlogUser113'),
(3, '2022-05-18 17:39:00.000', 'BlogUser112'),
(3, '2022-05-18 17:54:00.000', 'BlogUser111'),
(4, '2022-05-18 22:36:00.000', 'BlogUser112'),
(5, '2022-05-18 23:47:00.000', 'BlogUser113'),
(6, '2022-05-18 02:02:00.000', 'BlogUser114'),
(7, '2022-05-18 05:16:00.000', 'BlogUser115'),
(8, '2022-05-18 08:24:00.000', 'BlogUser113');