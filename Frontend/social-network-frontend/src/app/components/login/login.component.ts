import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JSONResponse } from 'src/app/models/JSONResponse';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  username : string = "";
  password : string = "";

  loginObs : Subscription = new Subscription();

  response : JSONResponse = {
    status: false,
    message: "",
    data: null
  };

  constructor(private apiService : ApiService, private route : Router) { }

  ngOnInit(): void {
  }

  login() : void {
    if (this.username.length>0 && this.password.length>0) {
      this.loginObs = this.apiService.login(this.username, this.password).subscribe(data => {
        this.response = data;
        if (this.response.status) this.route.navigate(["/dashboard"]);
      });
    } else {
      this.response.message = "Please fill the form!"
    }
  }

  ngOnDestroy() : void {
    this.loginObs.unsubscribe();
  }

}
