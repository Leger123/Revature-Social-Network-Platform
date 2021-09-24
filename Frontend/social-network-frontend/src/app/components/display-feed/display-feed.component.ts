import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Feed } from 'src/app/models/Feed';
import { JSONResponse } from 'src/app/models/JSONResponse';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-display-feed',
  templateUrl: './display-feed.component.html',
  styleUrls: ['./display-feed.component.css']
})
export class DisplayFeedComponent implements OnInit, OnDestroy {

  @Input()
  feed : Feed = {
    id: 0,
    content: '',
    imageUrl: null,
    videoUrl: undefined,
    comments: [],
    likesResponse: 0,
    authorResponse: {
      id: 0,
      name: '',
      profileImage: null,
      lastNotification: 0
    }
  };

  likeFeedObs : Subscription = new Subscription();
  feedObs : Subscription = new Subscription();

  response : JSONResponse = {
    status: false,
    message: "",
    data: null
  };

  constructor(private apiService : ApiService) { }

  ngOnInit(): void {
  }

  likeFeed() {
    this.likeFeedObs = this.apiService.likeFeed(this.feed.id).subscribe(data => {
      this.response = data;
      if (data.status) this.feed.likesResponse++;
    });
  }

  refresh() : void {
    this.feedObs = this.apiService.viewFeedByFeedID(this.feed.id).subscribe(data => {
      this.feed = data.data
    });
  }

  ngOnDestroy() : void {
    this.likeFeedObs.unsubscribe();
    this.feedObs.unsubscribe();
  }
}
