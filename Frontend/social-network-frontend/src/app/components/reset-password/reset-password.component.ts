import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { JSONResponse } from 'src/app/models/JSONResponse';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit, OnDestroy {

  email : string = "";

  resetObs : Subscription = new Subscription();

  response : JSONResponse = {
    status: false,
    message: "",
    data: null
  };

  constructor(private apiService : ApiService) { }

  ngOnInit(): void {
  }


  resetPassword() : void {
    if (this.email.length>0) {
      this.resetObs = this.apiService.resetPassword(this.email).subscribe(data => this.response = data);
     } else {
       this.response.message = "Please fill the form!"
     }
  }

  ngOnDestroy() : void {
    this.resetObs.unsubscribe();
  }

}
