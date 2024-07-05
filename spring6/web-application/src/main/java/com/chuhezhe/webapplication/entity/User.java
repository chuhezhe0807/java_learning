package com.chuhezhe.webapplication.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

/**
 * ClassName: UserDTO
 * Package: com.chuhezhe.webapplication.domain
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/3 23:23
 * @Version 1.0
 */
public class User {
    @NotNull(groups = Save.class)
    private Long userId;

    @NotNull(groups = {Save.class, Update.class})
    @Length(min = 3, max = 5, groups = {Save.class, Update.class})
    private String userName;

    @NotNull(groups = Update.class)
    private String account;

    @NotNull
    @Valid
    private Job job;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public interface Save {

    }

    public interface Update {

    }

    public static class Job {
        @NotNull
        @Length(min = 1, max = 3)
        private String jobName;

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(account, user.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, account);
    }
}
