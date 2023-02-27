package api.endpoints;

/*
	Base URL: https://gorest.co.in/public/v2/
	Post Request: https://gorest.co.in/public/v2/users
	Get Request: https://gorest.co.in/public/v2/users/{user_id}
	Put Request: https://gorest.co.in/public/v2/users/{user_id}
	Delete Request: https://gorest.co.in/public/v2/users/{user_id}
 */

public class Routes 
{
	static String base_url="https://gorest.co.in/public/v2/users";
	static String post_url=base_url;
	static String get_url=base_url+"/{user_id}";
	static String put_url=base_url+"/{user_id}";
	static String delete_url=base_url+"/{user_id}";
}
