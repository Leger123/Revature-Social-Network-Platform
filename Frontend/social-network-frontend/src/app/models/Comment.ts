import { UserResponse } from "./UserResponse";

export interface Comment {
    id : number,
    content : string,
    authorResponse : UserResponse
}