import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../services/auth.service";
import {CourseModel, UserModel} from "../../../models/user.model";
import {Observable} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',

  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  searchText!: string;

  courses!:CourseModel[]

  constructor(private http: HttpClient ,public auth :AuthService,private router: Router,) {
  }

  isAdmin:boolean = this.auth.isAdmin()
  ngOnInit(): void {

    this.isAdmin = this.auth.isAdmin()
    this.getCourses()
    console.log(this.isAdmin)

  }
  searchCourse(search:any){
    this.searchText = (<HTMLInputElement>search.target).value

    if (this.searchText != ""){
      this.courses =  this.courses.filter((e) =>{
        return  e.name.toLowerCase().includes(this.searchText) || e.number.toLowerCase().includes(this.searchText)
      })
    }else {
      this.getCourses()
    }

  }


  addCourse(){
    this.router.navigate(['/upload'])
  }

  getCourses(){
     this.http.get<CourseModel[]>("http://localhost:8080/api/course/").pipe().subscribe({
       next: data =>{
         // console.log(data)
         this.courses = data
       },
       error:err => console.log(err)
    })
  }




}
