import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {AttachmentModel, CommentModel, ImageModel, LectureModel} from "../../../models/user.model";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {flush} from "@angular/core/testing";

@Component({
  selector: 'app-comment',
  standalone: false,

  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css','../playlist/playlist.component.css']
})
export class CommentComponent implements OnInit{


  searchText!: string;

  id!:string |null

  courses!:CommentModel[]
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
        return  e.title.toLowerCase().includes(this.searchText) || e.user?.firstname.toString().toLowerCase().includes(this.searchText)
          || e.user?.lastName.toString().toLowerCase().includes(this.searchText)
      })
    }else {
      this.getCourses()
    }

  }


  addCourse(){
    this.router.navigate([`/add-comment/${this.id}`])
  }

  getCourses(){
    this.http.get<CommentModel[]>(`http://localhost:8080/api/comment/${this.id}`).pipe().subscribe({
      next: data =>{
        console.log(data)
        this.courses = data
      },
      error:err => console.log(err)
    })
  }

  download(file: AttachmentModel ) {
    if (file == null || file.base64 == null || file.fileName == null)
      return
    const linkSource = 'data:application/pdf;base64,'+file.base64;
    const downloadLink = document.createElement('a');
    const fileName = file.fileName ;

    downloadLink.href = linkSource;
    downloadLink.download = fileName;
    downloadLink.click();
  }
}
