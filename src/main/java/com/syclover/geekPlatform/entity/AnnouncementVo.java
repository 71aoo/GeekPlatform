package com.syclover.geekPlatform.entity;

import java.sql.Timestamp;

/**
 * @author vvings
 * @version 2020/10/13 19:05
 */
public class AnnouncementVo {



        private int id;

        private String content;

        private String createdTime;

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Announcement{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    ", createdTime=" + createdTime +
                    '}';
        }
    }


