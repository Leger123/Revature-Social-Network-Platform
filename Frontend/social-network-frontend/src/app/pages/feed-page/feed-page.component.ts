import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Feed } from 'src/app/models/Feed';
import { JSONResponse } from 'src/app/models/JSONResponse';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-feed-page',
  templateUrl: './feed-page.component.html',
  styleUrls: ['./feed-page.component.css']
})
export class FeedPageComponent implements OnInit, OnDestroy {

  feedObs : Subscription = new Subscription();
  response : JSONResponse = {
    status: false,
    message: "",
    data: null
  };

  constructor(private apiService : ApiService, private route : ActivatedRoute) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.params["id"]);
    this.feedObs = this.apiService.viewFeedByFeedID(id).subscribe(data => this.response = data);
  }

  ngOnDestroy() : void {
    this.feedObs.unsubscribe();
  }

}
