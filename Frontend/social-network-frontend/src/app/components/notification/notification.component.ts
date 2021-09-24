import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Notifaication } from 'src/app/models/Notification';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit, OnDestroy {

  notifications : Array<Notifaication> = [];
  @Input()
  lastNotification : number = 0;
  notificationObs : Subscription = new Subscription();
  
  constructor(private apiService : ApiService) { }

  ngOnInit(): void {
    this.notificationObs = this.apiService.getPreviewNotification().subscribe(data => {
      this.notifications = data.data;
    });
  }

  getAll() {
    this.notificationObs.unsubscribe();
    this.notificationObs = this.apiService.getAllNotification().subscribe(data => {
      this.notifications = data.data;
    });
  }

  ngOnDestroy() : void {
    this.notificationObs.unsubscribe();
  }
}
