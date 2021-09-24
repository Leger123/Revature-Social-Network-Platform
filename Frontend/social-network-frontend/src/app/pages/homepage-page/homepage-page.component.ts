import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-homepage-page',
  templateUrl: './homepage-page.component.html',
  styleUrls: ['./homepage-page.component.css']
})
export class HomepagePageComponent implements OnInit, OnDestroy {

  userObs: Subscription = new Subscription();
  pageType : number = 1;

  constructor(private apiService : ApiService, private route : Router) { }

  ngOnInit(): void {
    this.userObs = this.apiService.check().subscribe(data => {
      if (data.status) this.route.navigate(["/dashboard"]);
    });
  }

  ngOnDestroy() : void {
    this.userObs.unsubscribe();
  }
}
