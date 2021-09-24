export interface User {
    id : number;
    username : string;
    password : string;
    name : string;
    email : string;
    profileImage : string | null;
    lastNotification : number;
}