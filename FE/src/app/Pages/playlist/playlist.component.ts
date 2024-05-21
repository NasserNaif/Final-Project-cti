import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseModel, LectureModel} from "../../../models/user.model";

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit{

  searchText!: string;

  id!:string |null

  courses!:LectureModel[]
  constructor(private http: HttpClient ,public auth :AuthService,private router: Router,private route: ActivatedRoute) {
  }

  isAdmin:boolean = this.auth.isAdmin()
  ngOnInit(): void {

    this.route.paramMap.subscribe(param =>{
      this.id = param.get("id")

      console.log(this.id)
    })
    this.isAdmin = this.auth.isAdmin()
    this.getCourses()
    console.log(this.isAdmin)

  }
  searchCourse(search:any){
    this.searchText = (<HTMLInputElement>search.target).value

    if (this.searchText != ""){
      this.courses =  this.courses.filter((e) =>{
        return  e.name.toLowerCase().includes(this.searchText) || e.number.toString().toLowerCase().includes(this.searchText)
      })
    }else {
      this.getCourses()
    }

  }


  addCourse(){
    this.router.navigate([`/add-lecture/${this.id}`])
  }

  getCourses(){
    this.http.get<LectureModel[]>(`http://localhost:8080/api/lecture/${this.id}`).pipe().subscribe({
      next: data =>{
        console.log(data)
        this.courses = data
      },
      error:err => console.log(err)
    })
  }
}
