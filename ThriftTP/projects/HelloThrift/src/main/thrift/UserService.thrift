
//typedef i32 MyInteger
//const string message = "unable to create user";

struct UserDetails
{
    1: string first_name,
    2: string last_name,
    3: string email_address,
    4: string city
}

struct UserInfo
{
    1: string user_name,
    2: i64 user_id,
    3: UserDetails user_details,

}

exception AlreadyExist {
    1: string message= "unable to create user";
}

exception UnknownId {
    1: string id_message= "unknown id";
}

exception UnknownName {
    1: string id_message= "unknown name";
}


service UserService
{
    i64 createUser(1: string user_name, 2: UserDetails user_details) throws (1:AlreadyExist exist); 
    void deleteUser(1: i64 user_id) throws (1:UnknownId id);
    bool checkUser(1: string user_name) throws (1:UnknownId id); //true if user exist else f 
    UserInfo getUserInfoById(1: i64 user_id) throws (1:UnknownId id); 
    UserInfo getUserInfoByName(1: string user_name) throws (1:UnknownName name);
    void updateDetails(1: i64 user_id, 2: UserDetails user_details) throws (1:UnknownId id); 
}

