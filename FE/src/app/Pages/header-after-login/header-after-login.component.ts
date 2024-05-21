import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AttachmentModel, CommentModel, CourseModel, ImageModel} from "../../../models/user.model";
import {AuthService} from "../../services/auth.service";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-header-after-login',
  templateUrl: './header-after-login.component.html',
  styleUrls: ['./header-after-login.component.css','../upload/upload.component.css']
})
export class HeaderAfterLoginComponent implements OnInit{
  form!: FormGroup ;
  attachment!:AttachmentModel | null

  constructor(private auth:AuthService,private http: HttpClient,private formBuilder: FormBuilder,private router: Router,private route: ActivatedRoute) {

  }
  id!:string |null


  ngOnInit(): void {
    this.route.paramMap.subscribe(param =>{
      this.id = param.get("id")

      console.log(this.id)
    })


    this.form = this.formBuilder.group({
      title: [
        '',
        [
          Validators.required,

        ]
      ],
      url: [
        '',
        [

        ]
      ],
      attachmant: [
        '',
        [

        ]
      ],
    });
  }

  get f() {
    return this.form.controls;
  }


  save(){
    console.log(this.attachment)
    let course:CommentModel = {
      id:null,
      createdDate:null,
      lecture:null,
      title : this.f['title'].value as string,
      url:this.f['url'].value as string,
      attachmant:this.attachment != null ? this.attachment : null,
      user:null,
    }

    console.log(course)

    this.http.post(`http://localhost:8080/api/comment/${this.id}`,course).pipe().subscribe({
      next: data =>{
        alert(data)
        this.router.navigate([`/comment/${this.id}`])

      },
      error:err => console.log(err)
    })

  }


  onAttachmentDoc($event: any, attachment: string) {
    if (!$event || !$event.target || !$event.target.files[0]) {
      return;
    }
    const file: File = $event.target.files[0];

    const allowedImageTypes = ["image/jpeg", "image/png","application/pdf"];

    if (file.size > 1048576) {
      alert("file size shouldn't exceed 1 MB")
      this.f[`${attachment}`].setValue('')
      return;
    }
    if (!allowedImageTypes.includes(file.type)) {
      alert("file type must be image (JPEG , PNG)")

      this.f[`${attachment}`].setValue('')
      return;
    }
    const reader = new FileReader();
    reader.readAsBinaryString(file);
    reader.onload = () => {
      if (reader.result) {
        console.log("_________ inside")
        this.attachment = {base64:btoa(reader.result as string),fileName:file.name,id:null,comment:null}
      }
    }
  }
}
