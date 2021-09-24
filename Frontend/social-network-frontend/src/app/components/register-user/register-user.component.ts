import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { JSONResponse } from 'src/app/models/JSONResponse';
import { User } from 'src/app/models/User';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit, OnDestroy {

  user : User = {
    id: 0,
    username: '',
    password: '',
    name: '',
    email: '',
    profileImage: null,
    lastNotification: 0
  }
  registerObs : Subscription = new Subscription();
  response : JSONResponse = {
    status: false,
    message: "",
    data: null
  };

  constructor(private apiService : ApiService) { }

  ngOnInit(): void {
  }

  onSubmit() : void {
    if (this.user.username.length>0 && this.user.password.length>0 && this.user.email.length>0 && this.user.name.length>0) {
      this.registerObs = this.apiService.register(this.user).subscribe(data => this.response = data);
    } else {
      this.response.message = "Please fill the form!"
    }
  }

  ngOnDestroy() : void {
    this.registerObs.unsubscribe();
  }

  receive(imgUrl : string) : void {
    this.user.profileImage = imgUrl;
  }
}
