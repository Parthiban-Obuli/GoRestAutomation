package api.payload;

public class PostComments 
{
	int User_ID;
	String Title;
	String Body;
	public int getUser_ID() 
	{
		return User_ID;
	}
	public void setUser_ID(int user_ID) 
	{
		User_ID = user_ID;
	}
	public String getTitle() 
	{
		return Title;
	}
	public void setTitle(String title) 
	{
		Title = title;
	}
	public String getBody() 
	{
		return Body;
	}
	public void setBody(String body) 
	{
		Body = body;
	}
}
