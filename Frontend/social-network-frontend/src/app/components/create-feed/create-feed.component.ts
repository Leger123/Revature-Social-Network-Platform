import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { JSONResponse } from 'src/app/models/JSONResponse';
import { Feed } from 'src/app/models/Feed';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-create-feed',
  templateUrl: './create-feed.component.html',
  styleUrls: ['./create-feed.component.css']
})

export class CreateFeedComponent implements OnInit {

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

  createFeedObs : Subscription = new Subscription();

  response : JSONResponse = {
    status: false,
    message: "",
    data: null
  };

  constructor(private apiService : ApiService, private router: Router) { }

  ngOnInit(): void {
  }

  createFeed(){
    if (this.feed.content.length > 0)
      this.createFeedObs = this.apiService.createFeed(this.feed).subscribe(data => this.response = data);
  }  

  ngOnDestroy() : void {
    this.createFeedObs.unsubscribe();
  }

  receive(imgUrl : string) : void {
    this.feed.imageUrl = imgUrl;
  }

}
