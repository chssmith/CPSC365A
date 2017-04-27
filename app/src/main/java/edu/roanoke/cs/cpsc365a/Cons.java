package edu.roanoke.cs.cpsc365a;

//  Constants file that contains constants used throughout the application.
public interface Cons {

    //--------------------------------------------------
    //
    //  Shared Preferences Constants
    //
    //--------------------------------------------------

    //The string to access the Shared Preferences containing the user's settings.
    public static final String USER_SETTINGS = "USER_SETTINGS";

    public static final String USER_NICKNAME = "USER_NICKNAME";

    public static final String USER_ID = "USER_ID";

    public static final String ROOM_ID = "ROOM_ID";

    //--------------------------------------------------
    //
    //  API endpoints
    //
    //--------------------------------------------------

    public interface API {

        public static final String addUser = "http://cs.roanoke.edu/~jastoro/CPSC365A-WebApp/public/api/user/create";

        public static final String joinRoom = "http://cs.roanoke.edu/~jastoro/CPSC365A-WebApp/public/api/room/show/{code}";

        public static final String submitData = "http://cs.roanoke.edu/~jastoro/CPSC365A-WebApp/public/api/response/create";

    }

}