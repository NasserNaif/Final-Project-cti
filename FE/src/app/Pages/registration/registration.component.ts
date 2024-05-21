import { Component } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UserRegister} from "../../../models/UserLogin.model";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  constructor(private auth:AuthService,private formBuilder: FormBuilder,private router: Router,) {

  }

  form!: FormGroup ;



  get f() {
    return this.form.controls;
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: [
        '',
        [
          Validators.required,

        ]
      ],
      password: [
        '',
        [
          Validators.required,

        ]
      ],
      firstName: [
        '',
        [
          Validators.required,

        ]
      ],
      lastName: [
        '',
        [
          Validators.required,

        ]
      ],
      email: [
        '',
        [
          Validators.required,

        ]
      ],

    });
  }


  logIn() {
    if (!this.form.valid) {
      this.form.markAllAsTouched()
        alert("All fields is required ")
      return;
    }
    let userLogIn:UserRegister = {


      password : this.f['password'].value as string,
      firstName : this.f['firstName'].value as string,
      lastName : this.f['lastName'].value as string,
      email : this.f['email'].value as string,
      username : this.f['username'].value as string
    }
    this.auth.register(userLogIn).pipe().subscribe({
      next: data =>{

        this.router.navigate(['/login'])


      },
      error:err => console.log(err)
    })
  }



}
