import { UserResponse } from "./UserResponse";

export interface Notifaication {
    type : string;
    timestamp : number;
    userResponse : UserResponse;
    feedID : number;
}