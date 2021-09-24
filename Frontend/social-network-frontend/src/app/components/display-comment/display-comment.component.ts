import { Component, Input, OnInit } from '@angular/core';
import { Comment } from 'src/app/models/Comment';

@Component({
  selector: 'app-display-comment',
  templateUrl: './display-comment.component.html',
  styleUrls: ['./display-comment.component.css']
})
export class DisplayCommentComponent implements OnInit {

  @Input()
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

  constructor() { }

  ngOnInit(): void {
  }

}
