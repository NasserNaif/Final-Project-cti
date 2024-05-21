import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseModel, ImageModel} from "../../../models/user.model";
import {HttpClient} from "@angular/common/http";
import {log} from "@angular-devkit/build-angular/src/builders/ssr-dev-server";

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit{

  form!: FormGroup ;
  attachment!:ImageModel | null

  constructor(private auth:AuthService,private http: HttpClient,private formBuilder: FormBuilder,private router: Router,) {

  }
  id!:string |null


  ngOnInit(): void {


    this.form = this.formBuilder.group({
      name: [
        '',
        [
          Validators.required,

        ]
      ],
      number: [
        '',
        [
          Validators.required,

        ]
      ],
      level: [
        '',
        [
          Validators.required,

        ]
      ],
      teacher: [
        '',
        [
          Validators.required,

        ]
      ],
      courseImage: [
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
    let course:CourseModel = {
      id:null,
      name : this.f['name'].value as string,
      teacher : this.f['teacher'].value as string,
      level:this.f['level'].value as number,
      number:this.f['number'].value as string,
      courseImage:this.attachment != null ? this.attachment : null,
      lectures:null,
      addedBy:null
    }

    console.log(course)

    this.http.post("http://localhost:8080/api/course/",course).pipe().subscribe({
      next: data =>{
        alert(data)
        this.router.navigate(['/home'])

      },
      error:err => console.log(err)
    })

  }


  onAttachmentDoc($event: any, attachment: string) {
    if (!$event || !$event.target || !$event.target.files[0]) {
      return;
    }
    const file: File = $event.target.files[0];

    const allowedImageTypes = ["image/jpeg", "image/png"];

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
        this.attachment = {base64:btoa(reader.result as string),fileName:file.name,id:null}
    }
  }
}}
