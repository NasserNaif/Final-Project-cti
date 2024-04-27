import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Pages/login/login.component';
import { RegistrationComponent } from './Pages/registration/registration.component';
import { HeaderComponent } from './Pages/header/header.component';
import { FooterComponent } from './Pages/footer/footer.component';
import { HomeComponent } from './Pages/home/home.component';

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch:'full'},
  {path: 'home', component: HomeComponent, title: 'Home'},
  {path:'header', component: HeaderComponent, title: 'header'},
  {path: 'login', component: LoginComponent, title: 'Login'},

  {path:'registration', component: RegistrationComponent, title: 'Registration'},
  {path:'footer', component: FooterComponent, title: 'Footer'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
