-- 创建数据库
CREATE DATABASE IF NOT EXISTS `aoki`;

USE `aoki`;

CREATE TABLE if not exists `major`(
        `id` int primary key auto_increment,
        `majorName` varchar(31) NOT NULL
);

CREATE TABLE if not exists `user`(
       `id` int primary key auto_increment,
       `username` varchar(15) NOT NULL UNIQUE,
       `password` varchar(31) NOT NULL,
       `name` varchar(31),
       `email` varchar(31),
       `majorId` int,
       `role` tinyint NOT NULL ,
       `avatarURL` varchar(127),
       CONSTRAINT `userMajorId` FOREIGN KEY (`majorId`) REFERENCES `major` (`id`) ON DELETE CASCADE ON UPDATE SET NULL
);



CREATE TABLE if not exists `file`(
        `id` int primary key auto_increment,
        `name` char(32) NOT NULL UNIQUE,
        `suffix` varchar(31),
        `md5` char(32) NOT NULL UNIQUE,
        `size` long NOT NULL,
        `type` varchar(31) NOT NULL,
        `isDelete` tinyint NOT NULL default false
);

CREATE TABLE if not exists `lesson`(
       `id` int primary key auto_increment,
       `name` varchar(127) NOT NULL,
       `introduction` text,
       `ownerId` int,
       `teamMode` int,
       `topicMode` tinyint NOT NULL DEFAULT 0,
       `avatarURL` varchar(127),
       CONSTRAINT `lessonOwnerId` FOREIGN KEY (`ownerId`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
);

CREATE TABLE if not exists `lessonMember`(
     `id` int primary key auto_increment,
     `memberId` int NOT NULL ,
     `lessonId` int NOT NULL ,
     CONSTRAINT `lessonMemberMemberId` FOREIGN KEY (`memberId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT `lessonMemberLessonId` FOREIGN KEY (`lessonId`) REFERENCES `lesson` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists `topic`(
          `id` int primary key auto_increment,
          `name` varchar(127) NOT NULL,
          `number` int default 0,
          `limit` int,
          `difficult` double(2,1) default 0,
          `ownerId` int NOT NULL ,
          `lessonId` int NOT NULL ,
          CONSTRAINT `chk_number` CHECK (`number` <= `limit`),
          CONSTRAINT `topicOwnerId` FOREIGN KEY (`ownerId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
          CONSTRAINT `topicLessonId` FOREIGN KEY (`lessonId`) REFERENCES `lesson` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists `topicTime`(
      `id` int primary key auto_increment,
      `beginTime` datetime,
      `endTime` datetime,
      `lessonId` int NOT NULL ,
      CONSTRAINT `topicTimeLessonId` FOREIGN KEY (`lessonId`) REFERENCES `lesson` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE if not exists `topicMember`(
        `id` int primary key auto_increment,
        `topicId` int NOT NULL ,
        `memberId` int NOT NULL ,
        CONSTRAINT `topicMemberTopicId` FOREIGN KEY (`topicId`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists `task`(
          `id` int primary key auto_increment,
          `name` varchar(127) NOT NULL,
          `publish` tinyint NOT NULL default false,
          `topicId` int NOT NULL ,
          `beginTime` datetime NOT NULL ,
          `endTime` datetime NOT NULL ,
          CONSTRAINT `taskTopicId` FOREIGN KEY (`topicId`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 有记录但是submitted为false为保存
CREATE TABLE if not exists `taskSubmitted`(
           `id` int primary key auto_increment,
           `taskId` int not null ,
           `memberId` int not null ,
           `submitted` tinyint default false,
           CONSTRAINT `taskSubmittedTaskId` FOREIGN KEY (`taskId`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
            UNIQUE KEY `taskId_submitted` (`taskId`,`memberId`)
);

CREATE TABLE if not exists `scoreRule`(
           `id` int primary key auto_increment,
           `name` varchar(127) NOT NULL,
           `taskId` int NOT NULL ,
           `limit` double(4,1),
           CONSTRAINT `scoreRuleTaskId` FOREIGN KEY (`taskId`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists `question`(
            `id` int primary key auto_increment,
            `taskId` int NOT NULL ,
            `content` text,
            CONSTRAINT `questionTaskId` FOREIGN KEY (`taskId`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists `answer`(
           `id` int primary key auto_increment,
           `questionId` int NOT NULL ,
           `content` text,
           `ownerId` int NOT NULL ,
           CONSTRAINT `answerQuestionId` FOREIGN KEY (`questionId`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
           UNIQUE KEY `questionId_ownerId` (`questionId`,`ownerId`)
);

CREATE TABLE if not exists `scoreRecord`(
             `id` int primary key auto_increment,
             `scoreRuleId` int NOT NULL ,
             `taskId` int NOT NULL ,
             `memberId` int NOT NULL ,
             `score` double(4,1) NOT NULL default 0,
             CONSTRAINT `scoreRecordScoreRuleId` FOREIGN KEY (`scoreRuleId`) REFERENCES `scoreRule` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
             CONSTRAINT `scoreRecordTaskId` FOREIGN KEY (`taskId`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
             UNIQUE KEY `scoreRuleId_taskId_memberId` (`scoreRuleId`,`taskId`,`memberId`)
);

CREATE TABLE if not exists `milestones`(
            `id` int primary key auto_increment,
            `name` varchar(127) not null ,
            `beginTime` date not null ,
            `endTime` date not null ,
            `introduction` text,
            `lessonId` int not null ,
            CONSTRAINT `milestonesLessonId` FOREIGN KEY (`lessonId`) REFERENCES `lesson` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists `team`(
           `id` int primary key auto_increment,
           `name` varchar(127) not null ,
           `lessonId` int not null ,
           `ownerId` int not null ,
           CONSTRAINT `teamLessonId` FOREIGN KEY (`lessonId`) REFERENCES `lesson` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
           CONSTRAINT `teamUserId` FOREIGN KEY (`ownerId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE if not exists `teamMember`(
             `id` int primary key auto_increment,
             `teamId` int not null ,
             `memberId` int not null ,
             CONSTRAINT `teamMemberTeamId` FOREIGN KEY (`teamId`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
             CONSTRAINT `teamMemberUserId` FOREIGN KEY (`memberId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
             UNIQUE KEY `teamId_memberId` (`teamId`,`memberId`)
);

-- 自定义异常

CREATE FUNCTION `topic_limit_exceeded` ()
    RETURNS INT
    DETERMINISTIC
    NO SQL
BEGIN
    DECLARE e INT DEFAULT 1642;     -- 异常状态码1642
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Topic limit exceeded', MYSQL_ERRNO = e;
    RETURN 1;
END;



-- 触发器

CREATE TRIGGER `update_topic_number_trigger`
    AFTER INSERT ON `topicmember`
    FOR EACH ROW
BEGIN
    UPDATE topic SET `number`=`number`+1 WHERE `id`=NEW.`topicId`;
END;
CREATE TRIGGER `topicmember_delete_trigger`
    AFTER DELETE ON topicmember
    FOR EACH ROW
BEGIN
    UPDATE topic SET `number` = `number` - 1 WHERE `id` = OLD.`topicId`;
END;

CREATE TRIGGER `check_topic_limit`
    BEFORE INSERT ON `topicmember`
    FOR EACH ROW
BEGIN
    DECLARE cur_number INT DEFAULT 0;
    DECLARE cur_limit INT DEFAULT 0;

    SELECT `number`, `limit` INTO cur_number, cur_limit FROM topic WHERE id = NEW.topicId;

    IF (cur_number >= cur_limit) THEN
        CALL topic_limit_exceeded();
    END IF;
END;



-- 数据
INSERT INTO `major`(`majorName`) VALUES("计算机科学技术","软件工程","人工智能")