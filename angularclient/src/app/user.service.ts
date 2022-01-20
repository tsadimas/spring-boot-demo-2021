import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user.model';
import { Observable } from 'rxjs/Observable';
import {map} from "rxjs/operators";

@Injectable()
export class UserService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:9000/api/students';
  }

  public findAll(): Observable<User[]> {
    // return this.http.get<User[]>(this.usersUrl);

    return this.http.get<User[]>(this.usersUrl).pipe(
      map((result:any)=>{
        console.log(result); //<--it's an object
        //result={"_embedded": {"categories": [..]..}
        return result._embedded.students; //just return "categories"
      }));
  }

  public save(user: User) {
    return this.http.post<User>(this.usersUrl, user);
  }
}
