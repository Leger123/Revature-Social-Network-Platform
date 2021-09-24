import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JSONResponse } from 'src/app/models/JSONResponse';
import { User } from 'src/app/models/User';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  user : User = {
    id: 0,
    username: '',
    password: '',
    name: '',
    email: '',
    profileImage: null,
    lastNotification: 0
  }
  getUserObs : Subscription = new Subscription();
  
  updateObs : Subscription = new Subscription();
  response : JSONResponse = {
    status: false,
    message: "",
    data: null
  };

  constructor(private apiService : ApiService, private route : Router) { }

  ngOnInit(): void {
      let resp : JSONResponse = {
        status: false,
        message: "",
        data: null
      };
      this.getUserObs = this.apiService.check().subscribe(data => {
        resp = data;
        if (resp.status) this.user = resp.data;
        else this.route.navigate([""]);
        this.user.password = '';
      });
  }

  onSubmit() : void {
    if (this.user.password.length>0 && this.user.name.length>0) {
      this.updateObs = this.apiService.update(this.user).subscribe(data => this.response = data);
    } else {
      this.response.message = "Please fill the form!"
    }
  }

  ngOnDestroy() : void {
    this.updateObs.unsubscribe();
  }

  receive(imgUrl : string) : void {
    this.user.profileImage = imgUrl;
  }
}

