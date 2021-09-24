import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ApiService } from './api.service';
import { User } from 'src/app/models/User';

describe('ApiService', () => {
  let service: ApiService;
  let httpMock: HttpTestingController;
  let user:User;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers:[ApiService]
    });

    service = TestBed.inject(ApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should return user when checkLike is called', () => {
    service.register(user).subscribe((result: any) => {
      expect(result).toBe(user);
    });

    const req = httpMock.expectOne('http://localhost:9000/api/user', 'call to api');
    expect(req.request.method).toBe('GET');

    req.flush(true);

    httpMock.verify();
  });

  /* it('should be created', () => {
    expect(service).toBeTruthy();
  }); */
});
