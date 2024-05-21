import {Injectable} from "@angular/core";
import {UserLogin, UserRegister} from "../../models/UserLogin.model";
import {Observable} from "rxjs";
import {UserModel} from "../../models/user.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

export class AuthService{
  constructor(private http: HttpClient ) {
  }


  get token(): string {
    return window.localStorage.getItem("token") as string;
  }


  login(logIn:UserLogin):Observable<UserModel>{
      return this.http.post<UserModel>("http://localhost:8080/api/auth/login",logIn)
  }


  register(logIn:UserRegister):Observable<void>{
    return this.http.post<void>("http://localhost:8080/api/auth/register",logIn)
  }
  setToken(token:string){
    return window.localStorage.setItem("token",token)
  }

  isAdmin():boolean{
    return this.getRole() == "ADMIN"
  }

  setUserData(user:UserModel){

    return window.localStorage.setItem("user",JSON.stringify(user))

  }

  getUserData(){
    if ( window.localStorage.getItem("user") != null &&  window.localStorage.getItem("user") != undefined) {
      const user =  localStorage.getItem("user") as string

      return JSON.parse(user) as UserModel
    }
    return null
  }


  setRole(role:string){
    localStorage.setItem("role",role)
  }

  getRole(){
    return localStorage.getItem("role")
  }
}
