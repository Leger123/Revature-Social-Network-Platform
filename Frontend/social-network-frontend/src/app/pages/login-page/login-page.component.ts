import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  isLogin : boolean = true;

  constructor() { }

  ngOnInit(): void {

  }

  

  toggle() : void {
    this.isLogin = !this.isLogin;
  }
}
