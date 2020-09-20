package user;

public class User {
    private int userIden = 1;
    private String userName;
    private int userId;
    public int getUserIden(){
        return userIden;
    }
    public String getUserName(){
        return userName;
    }
    public int getUserId(){
        return userId;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public void setUserIden(int userIden){
        this.userIden = userIden;
    }

    public static class LogUser{
        public static int userId;
        public LogUser(int id){
            userId = id;
        }
    }
}

