import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Subscription } from 'rxjs';
import { Comment } from 'src/app/models/Comment';
import { JSONResponse } from 'src/app/models/JSONResponse';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-create-comment',
  templateUrl: './create-comment.component.html',
  styleUrls: ['./create-comment.component.css']
})
export class CreateCommentComponent implements OnInit, OnDestroy {

  @Input()
  feedID : number = 0;
  comment : Comment = {
    id: 0,
    content: '',
    authorResponse: {
      id: 0,
      name: '',
      profileImage: null,
      lastNotification: 0
    }
  };

  commentObs : Subscription = new Subscription();
  response : JSONResponse = {
    status: false,
    message: "",
    data: null
  };

  @Output()
  eventVal : EventEmitter<string> = new EventEmitter();

  constructor(private apiService : ApiService) { }

  ngOnInit(): void {
  }

  createComment() : void {
    if (this.comment.content.length>0) {
      this.commentObs = this.apiService.commentFeed(this.feedID, this.comment).subscribe(data => {
        if (data.status) {
          this.response = data
          this.eventVal.emit();
        }
      });
    }
  }

  ngOnDestroy() : void {
    this.commentObs.unsubscribe();
  }
}
