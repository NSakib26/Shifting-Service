import java.lang.*;
class User {
    private String name;
    private String email;
    private String password;
    private String gender;

    public User(String name, String email, String password, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
	public void setName(String name)
	{
		this.name = name;
	}
    public String getName() {
        return name;
    }
	public void setEmail(String email)
	{
		this.email = email;
	}
    public String getEmail() {
        return email;
    }
	public void setPassword(String password)
	{
		this.password = password;
	}
    public String getPassword() {
        return password;
    }
	public void setGender(String gender)
	{
		this.gender = gender;
	}
    public String getGender() {
        return gender;
    }
}