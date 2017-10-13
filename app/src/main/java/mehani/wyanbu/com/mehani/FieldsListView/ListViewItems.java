package mehani.wyanbu.com.mehani.FieldsListView;

/**
 * Created by Anas on 10/6/2017.
 */

public class ListViewItems {
    private String Fields_id;
    private String Fields_title;
    private String Fields_description;
    private String Fields_iconURL;

    public ListViewItems(String fields_id, String fields_title, String fields_description, String fields_iconURL) {
        Fields_id = fields_id;
        Fields_title = fields_title;
        Fields_description = fields_description;
        Fields_iconURL = fields_iconURL;
    }

    public String getFields_id() {
        return Fields_id;
    }

    public void setFields_id(String fields_id) {
        Fields_id = fields_id;
    }

    public String getFields_title() {
        return Fields_title;
    }

    public void setFields_title(String fields_title) {
        Fields_title = fields_title;
    }

    public String getFields_description() {
        return Fields_description;
    }

    public void setFields_description(String fields_description) {
        Fields_description = fields_description;
    }

    public String getFields_iconURL() {
        return Fields_iconURL;
    }

    public void setFields_iconURL(String fields_iconURL) {
        Fields_iconURL = fields_iconURL;
    }
}
