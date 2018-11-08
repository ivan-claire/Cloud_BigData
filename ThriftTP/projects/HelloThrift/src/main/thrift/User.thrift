
struct Info
{
    1: i32 age,
    2: string address
}

service UserService
{
    Info getInfo(1: string s)
}

