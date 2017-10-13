package mehani.wyanbu.com.mehani.UserOrder;

/**
 * Created by Anas on 10/6/2017.
 */

public class UserOrder_Items {
    private String UserOrder_id;
    private String UserOrder_title;
    private String UserOrder_description;
    private String UserOrder_iconURL;
    private String UserOrder_CreatData;

    public UserOrder_Items(String userOrder_id, String userOrder_title, String userOrder_description, String userOrder_iconURL, String userOrder_CreatData) {
        UserOrder_id = userOrder_id;
        UserOrder_title = userOrder_title;
        UserOrder_description = userOrder_description;
        UserOrder_iconURL = userOrder_iconURL;
        UserOrder_CreatData = userOrder_CreatData;
    }

    public String getUserOrder_id() {
        return UserOrder_id;
    }

    public void setUserOrder_id(String userOrder_id) {
        UserOrder_id = userOrder_id;
    }

    public String getUserOrder_title() {
        return UserOrder_title;
    }

    public void setUserOrder_title(String userOrder_title) {
        UserOrder_title = userOrder_title;
    }

    public String getUserOrder_description() {
        return UserOrder_description;
    }

    public void setUserOrder_description(String userOrder_description) {
        UserOrder_description = userOrder_description;
    }

    public String getUserOrder_iconURL() {
        return UserOrder_iconURL;
    }

    public void setUserOrder_iconURL(String userOrder_iconURL) {
        UserOrder_iconURL = userOrder_iconURL;
    }

    public String getUserOrder_CreatData() {
        return UserOrder_CreatData;
    }

    public void setUserOrder_CreatData(String userOrder_CreatData) {
        UserOrder_CreatData = userOrder_CreatData;
    }
}
