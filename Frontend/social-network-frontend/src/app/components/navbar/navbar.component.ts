import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JSONResponse } from 'src/app/models/JSONResponse';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnDestroy {

  logoutObs : Subscription = new Subscription();

  response : JSONResponse = {
    status: false,
    message: "",
    data: null
  };

  constructor(private apiService : ApiService, private route : Router) { }

  ngOnInit(): void {
  }

  logout() : void {
    this.logoutObs = this.apiService.logout().subscribe(data => {
      this.response = data;
      if (this.response.status) this.route.navigate(["/"]);
    });
  }

  ngOnDestroy() : void {
    this.logoutObs.unsubscribe();
  }

}
