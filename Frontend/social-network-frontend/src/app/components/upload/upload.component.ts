import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { Subscription } from 'rxjs';
import { JSONResponse } from 'src/app/models/JSONResponse';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit, OnDestroy {

  selectedFile : any;
  uploadObs : Subscription = new Subscription();
  response : JSONResponse = {
    status: false,
    message: "",
    data: null
  };

  @Output()
  eventVal : EventEmitter<string> = new EventEmitter();

  constructor(private apiService : ApiService) { }

  ngOnInit(): void {
  }


  onFileChanged(event : any) {
    this.selectedFile = event.target.files[0];
  }


  upload() {
    const uploadData = new FormData();
    uploadData.append('file', this.selectedFile, this.selectedFile.name);
    this.uploadObs = this.apiService.upload(uploadData).subscribe(data => {
      this.response = data;
      this.eventVal.emit(this.response.data);
      });
  }

  ngOnDestroy(): void {
    this.uploadObs.unsubscribe();
  }
}
