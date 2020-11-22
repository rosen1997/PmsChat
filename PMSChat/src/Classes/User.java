package Classes;

public class User
{
    public int Id;
    public String FirstName;
    public String LastName;
    public boolean LoggedIn;
    public String Email;
    public String Password;

    public User(int id, String n, String ln, String email,boolean loggedin)
    {
        this.Id=id;
        this.FirstName=n;
        this.LastName=ln;
        this.LoggedIn=loggedin;
        this.Email=email;
    }
}
