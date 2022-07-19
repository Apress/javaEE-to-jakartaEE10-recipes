package org.jakartaeerecipe.chapter15.recipe15_03;

import jakarta.persistence.*;
import jakarta.ws.rs.core.Context;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;

@Entity
@Table(name="USER")
public class User implements Serializable {
    @Id
    @Column(name = "USER_ID", nullable = false)
    private BigInteger userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "SECURITY_ROLE")
    private String securityRole;

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecurityRole() {
        return securityRole;
    }

    public void setSecurityRole(String securityRole) {
        this.securityRole = securityRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (securityRole != null ? !securityRole.equals(user.securityRole) : user.securityRole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (securityRole != null ? securityRole.hashCode() : 0);
        return result;
    }
}
