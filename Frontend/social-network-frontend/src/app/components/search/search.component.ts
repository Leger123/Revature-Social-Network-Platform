import { Component, DoCheck, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { UserResponse } from 'src/app/models/UserResponse';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit, OnDestroy, DoCheck {

  users : Array<UserResponse> = [];
  displayUsers : Array<UserResponse> = [];
  userObs : Subscription = new Subscription();
  searchStr : string = "";

  constructor(private apiService : ApiService) { }

  ngOnInit(): void {
    this.userObs = this.apiService.viewAllUser().subscribe(data => {
      this.users = data.data;
    });
  }

  ngOnDestroy() : void {
    this.userObs.unsubscribe();
  }

  ngDoCheck() : void {
    this.displayUsers = this.users.filter(user => user.name.toLowerCase().includes(this.searchStr.toLowerCase()));
  }
}
