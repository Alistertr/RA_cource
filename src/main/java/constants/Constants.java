package constants;
import static constants.Constants.Servers.REQRES_URL;
import static constants.Constants.Path.REQRES_PATH;
import static constants.Constants.Servers.JSON_PLACE_HOLDER_URL;

public class Constants {
    public static class RunVeriable{
        public static String server = Servers.JSON_PLACE_HOLDER_URL;
        public static String path = REQRES_PATH;
    }
    public static class Servers{
        public static String REQRES_URL= "https://reqres.in/";
        public static String GOOGLE_PLACES_URL;
        public static String JSON_PLACE_HOLDER_URL = "https://jsonplaceholder.typicode.com/";
        public static String REQUESTBIN_URL = "https://6a4baba94cd63f0256b5652c684664a3.m.pipedream.net";
    }

    public static class Path{
        public static String REQRES_PATH = "api/";
        public static String GOOGLE_PLACES_PATH;
    }
    public static class Actions {
        public static String REQRES_GET_PEOPLE = "users/";
        public static String REQRES_GET_UNKNOWN_USERS = "unknown/";


        //jsonplaceholder
        public static String JSON_PLACE_HOLDER_GET_POSTS = "posts/";
        public static String JSON_PLACE_HOLDER_GET_COMMENTS = "comments/";
        public static String JSON_PLACE_HOLDER_PUT_POSTS = "posts/1/";
        public static String JSON_PLACE_HOLDER_DELETE_POSTS = "posts/1/";
        public static String JSON_PLACE_HOLDER_POST_POSTS = "posts/";
    }
}
