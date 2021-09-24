import { Comment } from "./Comment";
import { UserResponse } from "./UserResponse";

export interface Feed {
    id : number;
    content : string;
    imageUrl : string | null;
    videoUrl : string | undefined;
    comments : Array<Comment>;
    likesResponse : number;
    authorResponse : UserResponse;
}