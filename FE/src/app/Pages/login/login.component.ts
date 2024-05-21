import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserLogin} from "../../../models/UserLogin.model";
import {log} from "@angular-devkit/build-angular/src/builders/ssr-dev-server";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',

  templateUrl: './login.component.html',
  styleUrls:[ './login.component.css']
})
export class LoginComponent implements OnInit{
  constructor(private auth:AuthService,private formBuilder: FormBuilder,private router: Router,) {

  }

   form!: FormGroup ;



  get f() {
    return this.form.controls;
  }

  ngOnInit(): void {
    localStorage.clear()
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
    });
  }


  logIn() {

    let userLogIn = {

    password : this.f['password'].value as string,
    username : this.f['username'].value as string
  }
    this.auth.login(userLogIn).pipe().subscribe({
      next: data =>{

        this.auth.setToken(data.token)
        this.auth.setUserData(data)
        this.auth.setRole(data.roles)
        this.router.navigate(['/home'])


      },
      error:err => console.log(err)
    })
  }


}
