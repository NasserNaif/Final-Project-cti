import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CourseModel, ImageModel, LectureModel} from "../../../models/user.model";
import {AuthService} from "../../services/auth.service";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update',

  templateUrl: './update.component.html',
  styleUrls:[ './update.component.css','../upload/upload.component.css']
})
export class UpdateComponent implements OnInit{
  form!: FormGroup ;
  attachment!:ImageModel | null
  id!:string |null


  constructor(private auth:AuthService,private http: HttpClient,private formBuilder: FormBuilder,private router: Router,private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(param =>{
      this.id = param.get("id")

      console.log(this.id)
    })
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
      ]
    });
  }

  get f() {
    return this.form.controls;
  }


  save(){
    console.log(this.attachment)
    let course:LectureModel = {
      id:null,
      name : this.f['name'].value as string,
      number:this.f['number'].value as number,
      course:null,
      comments:null
    }

    console.log(course)

    this.http.post(`http://localhost:8080/api/lecture/${this.id}`,course).pipe().subscribe({
      next: data =>{
        alert(data)
        this.router.navigate([`/Playlist/${this.id}`])

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
