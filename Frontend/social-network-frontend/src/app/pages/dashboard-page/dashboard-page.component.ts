import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CreateFeedComponent } from 'src/app/components/create-feed/create-feed.component'; 
import { Feed } from 'src/app/models/Feed';
import { User } from 'src/app/models/User';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-dashboard-page',
  templateUrl: './dashboard-page.component.html',
  styleUrls: ['./dashboard-page.component.css']
})
export class DashboardPageComponent implements OnInit, OnDestroy {

  user : User = {
    id: 0,
    username: '',
    password: '',
    name: '',
    email: '',
    profileImage: null,
    lastNotification: 0
  };
  feedList: Array<Feed> = [] 
  displayNotification : boolean = false;
  offset : number = 0;
  total : number = 0;
  viewFeedObs: Subscription = new Subscription();
  userObs: Subscription = new Subscription();
  feedCountObs: Subscription = new Subscription();

  constructor(private dialog:MatDialog, private apiService : ApiService, private route : Router) { }

  ngOnInit(): void {
    this.userObs = this.apiService.check().subscribe(data => {
      if (!data.status) this.route.navigate([""]);
      else {
        this.user = data.data;
        this.feedCountObs = this.apiService.getFeedCount().subscribe(data => {
          this.offset = Number(data.data) - 19;
          this.total = this.offset;
          this.fetchFeeds();
        });
      }
    });
  }


  private fetchFeeds() {
    this.viewFeedObs = this.apiService.viewAllFeed(this.offset).subscribe(data => this.feedList = data.data) 
  }

  onClickCreateFeed(){
     const dialogRef = this.dialog.open(CreateFeedComponent);
     dialogRef.afterClosed().subscribe(() => this.ngOnInit());
  }

  prev() {
    this.viewFeedObs.unsubscribe();
    this.offset += 20;
    if (this.offset > this.total) this.offset = this.total;
    this.fetchFeeds();
  }

  next() {
    this.viewFeedObs.unsubscribe();
    this.offset -= 20;
    if (this.offset < 1) this.offset = 1;
    this.fetchFeeds();
  }

  ngOnDestroy() : void {
    this.viewFeedObs.unsubscribe();
    this.userObs.unsubscribe();
  }
}
