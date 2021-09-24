import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Feed } from 'src/app/models/Feed';
import { JSONResponse } from 'src/app/models/JSONResponse';
import { UserResponse } from 'src/app/models/UserResponse';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit, OnDestroy {

  user : UserResponse = {
    id: 0,
    name: '',
    profileImage: null,
    lastNotification: 0
  };
  feeds : Array<Feed> = [];
  viewUserObs : Subscription = new Subscription();
  viewFeedObs : Subscription = new Subscription();
  response : JSONResponse = {
    status: false,
    message: "",
    data: null
  };
  @Input()
  id : number = 0;
  
  constructor(private apiService : ApiService) { }

  ngOnInit(): void {
    this.viewUserObs = this.apiService.viewUser(this.id).subscribe(data => this.user = data.data);
    this.viewFeedObs = this.apiService.viewFeedByUserID(this.id).subscribe(data => this.feeds = data.data);
  }

  ngOnDestroy(): void {
    this.viewFeedObs.unsubscribe();
    this.viewUserObs.unsubscribe();
  }


}
