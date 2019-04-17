package session;

import lombok.Data;

@Data
public class Session {
    // 用户唯一性标识
    private String userId;
    private String userName;

    public Session() {
    }

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}