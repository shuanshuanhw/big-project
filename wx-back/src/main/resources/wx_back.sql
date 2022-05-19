create database wx_back DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;
create user 'wx_back'@'localhost' identified by '651392qQ';
grant all privileges on `wx_back`.* to wx_back@'localhost';
flush privileges;
# 登陆 mysql -u wx_back -p
-- ----------------------------
-- 1、作品信息表
-- ----------------------------
drop table if exists sys_work;
create table sys_work
(
    work_id       bigint(20)      not null auto_increment    comment '作品ID',
    work_name     varchar(50)     not null                   comment '作品名称',
#     worker_id     bigint(20)      not null                   comment '作者id',
    work_introduce text           not null                   comment '简介',
    work_sort     int(4)          not null                   comment '显示顺序',
    work_clicks   bigint          default 0                 comment '被点击数',
    work_likes    bigint          default 0                  comment '被赞数',
    work_favorites bigint         default 0                  comment '被收藏数',

    recommend_status char(1)      default '0'                comment '推荐状态，0为不受推荐，1为受推荐',
    status        char(1)         default '0'                comment '状态（0正常 1停用）',
    create_by     varchar(64)     default 'admin'            comment '创建者',
    create_time   timestamp   DEFAULT CURRENT_TIMESTAMP      comment '创建时间',
    update_by     varchar(64)     default ''			     comment '更新者',
    update_time   datetime                                   comment '更新时间',
    remark        varchar(500)    default null               comment '备注',
    primary key (work_id)
) engine=innodb comment = '作品信息表';
insert into sys_work(work_id,work_name,work_introduce,work_sort) values (1,'青花瓷','青花瓷（blue and white porcelain），又称白地青花瓷，常简称青花，是中国瓷器的主流品种之一，属釉下彩瓷。青花瓷是用含氧化钴的钴矿为原料，在陶瓷坯体上描绘纹饰，再罩上一层透明釉，经高温还原焰一次烧成。',1);
insert into sys_work(work_id,work_name,work_introduce,work_sort) values (2,'鸟嘴医生','他们身穿黑色长袍，手持一根长木棍，头戴一顶黑色宽檐帽，将整个头藏进去。长嘴的猛禽造型，覆盖着佩戴者的口鼻，鸟嘴长度可达到30厘米，长嘴两侧靠近鼻子的位置有两个孔，这样阴森恐怖的外型让人不寒而栗，很多人认为他是瘟疫和死神的象征。',2);
insert into sys_work(work_id,work_name,work_introduce,work_sort) values (3,'救世主','达芬奇如何天才一生自是不必多说，作为这位天才画家唯一可以出售的作品，《救世主》于纽约时间2017年11月15日晚间，以4亿美元落槌，最终成交价为4.50312亿美元(约人民币29.577亿元)价格在纽约佳士得夜拍上成交，整个拍卖仅持续了20分钟，它成为史上最贵的艺术品。',3);
insert into sys_work(work_id,work_name,work_introduce,work_sort) values (4,'阿尔及尔的女人','被誉为“现代艺术创始人”的毕加索，以超现实主义、超立体主义的抽象画作风格而闻名，而这幅《阿尔及尔的女人》可谓是其艺术生涯中里程碑式的作品，也是毕加索本人最钟情的一幅画。',4);
insert into sys_work(work_id,work_name,work_introduce,work_sort) values (5,'侧卧的裸女','20世纪意大利超现实主义画家莫迪利亚尼擅长以颜色浓烈的色块和夸张的形体塑造人物，而这幅《侧卧的裸女》是他的代表作之一。',5);
insert into sys_work(work_id,work_name,work_introduce,work_sort) values (6,'卢西恩·弗洛伊德肖像画习作','《卢西恩·弗洛伊德肖像画习作》是英国表现主义画家弗朗西斯·培根与1969年为他的好友兼对手卢西恩·弗洛伊德所画，被认为是培根最伟大的作品。2013年11月12日在纽约佳士得拍卖会上以1.424美元的价格成交。',6);
insert into sys_work(work_id,work_name,work_introduce,work_sort) values (7,'指示者','《指示者》是瑞典超表现主义画家、雕塑家贾科梅蒂的代表作之一，这个雕塑造型目前仍在很多现代风格装饰品中所见。2015年5月11日，由佳士得拍卖会以1.41亿美元的价格拍售成功。',7);
insert into sys_work(work_id,work_name,work_introduce,work_sort) values (8,'呐喊','对于早已火成表情包的《呐喊》与蒙克的知名度，甚至不用多做介绍。同为表现主义画家的蒙克，擅长用流动的线条与强烈的色彩表现画作的情绪，比如呐喊所表现的恐惧，透过画作扑面而来。',8);
insert into sys_work(work_id,work_name,work_introduce,work_sort) values (9,'无题','二战时期，作为一个生活在白人统治国家中的美国黑人涂鸦艺术家，巴斯奎特的涂鸦作品看似毫无章法，其实都代表了他对当时的政治、社会、宗教的理性思考，被誉为“新艺术代表人物”，其代表作《无题》于2017年5月18日，在纽约苏富比拍出约1.1亿美元。',9);


-- ----------------------------
-- 2、作品-图片关联表
-- ----------------------------
drop table if exists sys_work_photo;
create table sys_work_photo
(
    work_id       bigint(20)      not null                   comment '作品ID',
    photo_id      bigint(20)      not null                   comment '图片ID',
    first_status  int(4)          default 0                  comment '是否首要图片，1为首要图片，0为非首要图片，一个作品只有一个首要图片',
    primary key(work_id, photo_id)
) engine=innodb comment = '作品-图片关联表';
insert into sys_work_photo(work_id, photo_id, first_status) VALUES (1,1,1);
insert into sys_work_photo(work_id, photo_id, first_status) VALUES (2,2,1);
insert into sys_work_photo(work_id, photo_id, first_status) VALUES (3,3,1);
insert into sys_work_photo(work_id, photo_id, first_status) VALUES (4,4,1);
insert into sys_work_photo(work_id, photo_id, first_status) VALUES (5,5,1);
insert into sys_work_photo(work_id, photo_id, first_status) VALUES (6,6,1);
insert into sys_work_photo(work_id, photo_id, first_status) VALUES (7,7,1);
insert into sys_work_photo(work_id, photo_id, first_status) VALUES (8,8,1);
insert into sys_work_photo(work_id, photo_id, first_status) VALUES (9,9,1);


-- ----------------------------
-- 3、图片信息表
-- ----------------------------
drop table if exists sys_photo;
create table sys_photo
(
    photo_id       bigint(20)      not null auto_increment    comment '图片ID',
    photo_name     varchar(50)                                comment '图片名称',
    photo_url      text    not null                   comment '图片地址',
    primary key (photo_id)
) engine=innodb comment = '图片信息表';
insert into sys_photo(photo_id,photo_name, photo_url) values (1,'青花瓷','https://tse1-mm.cn.bing.net/th/id/R-C.07b3ee64cbfb31de28880981210c050a?rik=RCiY6GML%2bbavcw&riu=http%3a%2f%2fimg04.taobaocdn.com%2fbao%2fuploaded%2fi4%2fT1g1IqXfVcXXa3aHo1_040308.jpg&ehk=5V%2fIFYLjzF%2fRq6lVkCFnwjUAT78jlHSn9JQfbPbkSfQ%3d&risl=&pid=ImgRaw&r=0');
insert into sys_photo(photo_id,photo_name, photo_url) values (2,'鸟嘴医生','https://img.zcool.cn/community/01ec7e5e636e32a801216518c0623e.jpg@1280w_1l_2o_100sh.jpg');
insert into sys_photo(photo_id,photo_name, photo_url) values (3,'救世主','https://pic1.zhimg.com/80/v2-433193bb97f8447a9f6b616374bd3ebc_1440w.jpg');
insert into sys_photo(photo_id,photo_name, photo_url) values (4,'阿尔及尔的女人','https://pic1.zhimg.com/80/v2-9b64aa5897cc017b1cadcbf8daa3a178_1440w.jpg');
insert into sys_photo(photo_id,photo_name, photo_url) values (5,'侧卧的裸女','https://pic4.zhimg.com/80/v2-50eaac00af4ef700e6e8d9b00b19754b_1440w.jpg');
insert into sys_photo(photo_id,photo_name, photo_url) values (6,'卢西恩·弗洛伊德肖像画习作','https://pic3.zhimg.com/80/v2-33053088fb5c734e01f248428f607b5e_1440w.jpg');
insert into sys_photo(photo_id,photo_name, photo_url) values (7,'指示者','https://pic1.zhimg.com/80/v2-46e6f043eab1682938424095bab711dc_1440w.jpg');
insert into sys_photo(photo_id,photo_name, photo_url) values (8,'呐喊','https://pic1.zhimg.com/80/v2-bd7d22b858faba2eae420e40dba019d4_1440w.jpg');
insert into sys_photo(photo_id,photo_name, photo_url) values (9,'无题','https://pic3.zhimg.com/80/v2-8526bf41dfe73c782c0c4dc1aef2ccce_1440w.jpg');


-- ----------------------------
-- 4、作者信息表
-- ----------------------------
drop table if exists sys_worker;
create table sys_worker
(
    worker_id       bigint(20)      not null auto_increment    comment '作者ID',
    worker_name     varchar(50)     not null                   comment '作者名字',
    worker_sex      varchar(10)     not null                   comment '作者性别，0为女 1为男',
    worker_age      int                                        comment '作者年龄',
    worker_phone    varchar(15)                                comment '作者手机号码',
    worker_nickname varchar(50)                                comment '作者昵称',
    worker_account  varchar(50)     not null                   comment '作者帐号',
    worker_password varbinary(50)   not null                   comment '作者密码',

    primary key (worker_id)
) engine=innodb comment = '作者信息表';
insert into sys_worker(worker_id, worker_name, worker_sex, worker_age, worker_phone, worker_nickname, worker_account, worker_password) values (1,'算算','1',35,'15007572525','牛人','shuanshuan',HEX(AES_ENCRYPT('651392qQ','shuanshuan')));
insert into sys_worker(worker_id, worker_name, worker_sex, worker_age, worker_phone, worker_nickname, worker_account, worker_password) values (2,'黄维','1',35,'15007572525','牛人','shuanshuan',HEX(AES_ENCRYPT('651392qQ','shuanshuan')));
insert into sys_worker(worker_id, worker_name, worker_sex, worker_age, worker_phone, worker_nickname, worker_account, worker_password) values (3,'黄算算','1',35,'15007572525','牛人','shuanshuan',HEX(AES_ENCRYPT('651392qQ','shuanshuan')));

-- ----------------------------
-- 5、音视频信息表
-- ----------------------------
drop table if exists sys_audio_video;
create table sys_audio_video
(
    sys_audio_video_id       bigint(20)      not null auto_increment    comment '音视频ID',
    sys_audio_video_name     varchar(50)     not null                   comment '音视频名字',
    sys_audio_video_url      varchar(50)     not null                   comment '音视频地址',

    type          char(1)                                    comment '0为音频，1为视频',
    status        char(1)         default '0'                comment '状态（0正常 1停用）',
    create_by     varchar(64)     default 'admin'            comment '创建者',
    create_time   timestamp   DEFAULT CURRENT_TIMESTAMP      comment '创建时间',
    update_by     varchar(64)     default ''			     comment '更新者',
    update_time   datetime                                   comment '更新时间',
    remark        varchar(500)    default ''                 comment '备注',
    primary key (sys_audio_video_id)
) engine=innodb comment = '音视频信息表';

insert into sys_audio_video(sys_audio_video_id, sys_audio_video_name, sys_audio_video_url,type,status) VALUES (1,'后来','https://y.qq.com/n/ryqq/songDetail/261482231?songtype=0','0','0');
insert into sys_audio_video(sys_audio_video_id, sys_audio_video_name, sys_audio_video_url,type,status) VALUES (2,'当爱再靠近','https://c.y.qq.com/base/fcgi-bin/u?__=Nzv7l','0','0');
insert into sys_audio_video(sys_audio_video_id, sys_audio_video_name, sys_audio_video_url,type,status) VALUES (3,'为爱痴狂','https://c.y.qq.com/base/fcgi-bin/u?__=viBI8','0','0');
insert into sys_audio_video(sys_audio_video_id, sys_audio_video_name, sys_audio_video_url,type,status) VALUES (4,'成全','https://c.y.qq.com/base/fcgi-bin/u?__=Klhq1','0','0');
insert into sys_audio_video(sys_audio_video_id, sys_audio_video_name, sys_audio_video_url,type,status) VALUES (5,'原来你也在这里','https://c.y.qq.com/base/fcgi-bin/u?__=NMYRq','0','0');
insert into sys_audio_video(sys_audio_video_id, sys_audio_video_name, sys_audio_video_url,type,status) VALUES (6,'很爱很爱你','https://c.y.qq.com/base/fcgi-bin/u?__=Ptw7G','0','0');
insert into sys_audio_video(sys_audio_video_id, sys_audio_video_name, sys_audio_video_url,type,status) VALUES (7,'我们没有在一起','https://c.y.qq.com/base/fcgi-bin/u?__=OVvyOH','0','0');
insert into sys_audio_video(sys_audio_video_id, sys_audio_video_name, sys_audio_video_url,type,status) VALUES (8,'一辈子的孤单','https://c.y.qq.com/base/fcgi-bin/u?__=sQA9l','0','0');
insert into sys_audio_video(sys_audio_video_id, sys_audio_video_name, sys_audio_video_url,type,status) VALUES (9,'爱的代价','https://c.y.qq.com/base/fcgi-bin/u?__=VOjAF3W','0','0');


-- ----------------------------
-- 6、作者-作品关联表
-- ----------------------------
drop table if exists sys_worker_work;
create table sys_worker_work
(
    worker_id       bigint(20)      not null                   comment '作品ID',
    work_id      bigint(20)      not null                   comment '图片ID',
    first_status  int(4)          default 0                  comment '是否首要图片，1为首要图片，0为非首要图片，一个作品只有一个首要图片',
    primary key(worker_id, work_id)
) engine=innodb comment = '作者-作品关联表';
insert into sys_worker_work(worker_id, work_id,first_status) VALUES (1,1,1);
insert into sys_worker_work(worker_id, work_id,first_status) VALUES (1,2,1);
insert into sys_worker_work(worker_id, work_id,first_status) VALUES (1,3,1);
insert into sys_worker_work(worker_id, work_id,first_status) VALUES (2,4,1);
insert into sys_worker_work(worker_id, work_id,first_status) VALUES (2,5,1);
insert into sys_worker_work(worker_id, work_id,first_status) VALUES (2,6,1);
insert into sys_worker_work(worker_id, work_id,first_status) VALUES (3,7,1);
insert into sys_worker_work(worker_id, work_id,first_status) VALUES (3,8,1);
insert into sys_worker_work(worker_id, work_id,first_status) VALUES (3,9,1);

-- ----------------------------
-- 7、作品-音频视频关联表
-- ----------------------------
drop table if exists sys_work_audio_video;
create table sys_work_audio_video
(
    work_id       bigint(20)      not null                   comment '作品ID',
    audio_video_id      bigint(20)      not null                   comment '图片ID',
    first_status  int(4)          default 0                  comment '是否首要音视频，1为首要音视频，0为非首要音视频，一个作品只有一个首要音视频',
    primary key(work_id, audio_video_id)
) engine=innodb comment = '作品-音频视频关联表';
insert into sys_work_audio_video(work_id, audio_video_id, first_status) VALUES (1,1,1);
insert into sys_work_audio_video(work_id, audio_video_id, first_status) VALUES (2,2,1);
insert into sys_work_audio_video(work_id, audio_video_id, first_status) VALUES (3,3,1);
insert into sys_work_audio_video(work_id, audio_video_id, first_status) VALUES (4,4,1);
insert into sys_work_audio_video(work_id, audio_video_id, first_status) VALUES (5,5,1);
insert into sys_work_audio_video(work_id, audio_video_id, first_status) VALUES (6,6,1);
insert into sys_work_audio_video(work_id, audio_video_id, first_status) VALUES (7,7,1);
insert into sys_work_audio_video(work_id, audio_video_id, first_status) VALUES (8,8,1);
insert into sys_work_audio_video(work_id, audio_video_id, first_status) VALUES (9,9,1);