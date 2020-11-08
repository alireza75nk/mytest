package ir.android.mytest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetroUser {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("token")
    @Expose
    private int token;
    @SerializedName("email")
    @Expose
    private String email;


    public RetroUser(Integer id, String name, String email, int token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }
    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The id
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }
    /**
     *
     * @param email
     * The id
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     *
     * @return
     * The token
     */
    public int getToken() {
        return token;
    }
    /**
     *
     * @param token
     * The id
     */
    public void setToken(int token) {
        this.token = token;
    }
}
