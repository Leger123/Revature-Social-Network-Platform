import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Globals } from '../globals';
import { JSONResponse } from '../models/JSONResponse';
import { User } from '../models/User';
import { Feed } from '../models/Feed';
import { Comment } from '../models/Comment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private httpCli : HttpClient, private global : Globals) { }

  // User
  register(user : User) : Observable<JSONResponse> {
    return this.httpCli.post<JSONResponse>(`${this.global.domain}/api/user`, user, {withCredentials: true});
  }

  login(username: string, password: string) : Observable<JSONResponse> {
    return this.httpCli.post<JSONResponse>(`${this.global.domain}/api/login`, {
      username : username,
      password : password
    }, {withCredentials: true});
  }

  resetPassword(email : string) : Observable<JSONResponse>{
    return this.httpCli.patch<JSONResponse>(`${this.global.domain}/api/user/password/${email}`, null, {withCredentials: true});
  }

  update(user : User) : Observable<JSONResponse> {
    return this.httpCli.put<JSONResponse>(`${this.global.domain}/api/user`, user, {withCredentials: true});
  }

  viewAllUser() : Observable<JSONResponse> {
    return this.httpCli.get<JSONResponse>(`${this.global.domain}/api/user`, {withCredentials: true});
  }

  viewUser(id : number) : Observable<JSONResponse> {
    return this.httpCli.get<JSONResponse>(`${this.global.domain}/api/user/${id}`, {withCredentials: true});
  }

  updateProfileImage(imgUrl : string) {
    return this.httpCli.patch<JSONResponse>(`${this.global.domain}/api/user/profile-img/`, imgUrl, {withCredentials: true});
  }

  logout() : Observable<JSONResponse> {
    return this.httpCli.get<JSONResponse>(`${this.global.domain}/api/logout`, {withCredentials: true});
  }

  check() : Observable<JSONResponse> {
    return this.httpCli.get<JSONResponse>(`${this.global.domain}/api/check`, {withCredentials: true});
  }

  // Feed
  viewFeedByUserID(id : number) : Observable<JSONResponse> {
    return this.httpCli.get<JSONResponse>(`${this.global.domain}/api/feed/user/${id}`, {withCredentials: true});
  }
  
  createFeed(feed : Feed) : Observable<JSONResponse> {
    return this.httpCli.post<JSONResponse>(`${this.global.domain}/api/feed`, feed , {withCredentials: true});
  } 

  viewAllFeed(offset: number) : Observable<JSONResponse>{
    return this.httpCli.get<JSONResponse>(`${this.global.domain}/api/feed/offset/${offset}`, {withCredentials: true});
  } 
  
  likeFeed(id : number) : Observable<JSONResponse> {
    return this.httpCli.patch<JSONResponse>(`${this.global.domain}/api/feed/${id}`, null, {withCredentials: true});
  }

  viewFeedByFeedID(id : number) : Observable<JSONResponse> {
    return this.httpCli.get<JSONResponse>(`${this.global.domain}/api/feed/${id}`, {withCredentials: true});
  }

  getFeedCount() : Observable<JSONResponse> {
    return this.httpCli.get<JSONResponse>(`${this.global.domain}/api/feed/count`, {withCredentials: true});
  }

  // Comment
  commentFeed(id : number, comment : Comment) : Observable<JSONResponse> {
    return this.httpCli.post<JSONResponse>(`${this.global.domain}/api/comment/${id}`, comment , {withCredentials: true});
  }

  // notification
  getPreviewNotification() : Observable<JSONResponse> {
    return this.httpCli.get<JSONResponse>(`${this.global.domain}/api/notification-preview`, {withCredentials: true});
  }

  getAllNotification() : Observable<JSONResponse> {
    return this.httpCli.get<JSONResponse>(`${this.global.domain}/api/notification`, {withCredentials: true});
  }

  // Image
  upload(uploadData : FormData) : Observable<JSONResponse> {
    return this.httpCli.post<JSONResponse>(`${this.global.domain}/api/upload`, uploadData, {withCredentials: true});
  }
}
