package mehani.wyanbu.com.mehani.CareersGridView;

/**
 * Created by Anas on 10/6/2017.
 */

public class GridViewItems {
    private String Careers_id;
    private String Careers_name;
    private String Careers_iconURL;

    public String getCareers_id() {
        return Careers_id;
    }

    public void setCareers_id(String careers_id) {
        Careers_id = careers_id;
    }

    public String getCareers_name() {
        return Careers_name;
    }

    public void setCareers_name(String careers_name) {
        Careers_name = careers_name;
    }

    public String getCareers_iconURL() {
        return Careers_iconURL;
    }

    public void setCareers_iconURL(String careers_iconURL) {
        Careers_iconURL = careers_iconURL;
    }

    public GridViewItems(String careers_id, String careers_name, String careers_iconURL) {
        Careers_id = careers_id;
        Careers_name = careers_name;
        Careers_iconURL = careers_iconURL;
    }
}
