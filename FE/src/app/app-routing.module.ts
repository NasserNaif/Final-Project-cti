import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Pages/login/login.component';
import { RegistrationComponent } from './Pages/registration/registration.component';
import { HeaderComponent } from './Pages/header/header.component';
import { FooterComponent } from './Pages/footer/footer.component';
import { HomeComponent } from './Pages/home/home.component';
import {UploadComponent} from "./Pages/upload/upload.component";
import {HeaderAfterLoginComponent} from "./Pages/header-after-login/header-after-login.component";
import {PlaylistComponent} from "./Pages/playlist/playlist.component";
import {WatchVideoComponent} from "./Pages/watch-video/watch-video.component";
import {UpdateComponent} from "./Pages/update/update.component";
import {CommentComponent} from "./Pages/comment/comment.component";

const routes: Routes = [
  {path: 'home', component: HomeComponent, title: 'Home'},

  {path:'header', component: HeaderComponent, title: 'header'},
  {path:'add-comment/:id', component: HeaderAfterLoginComponent, title: 'HeaderAfterLoginComponent'},


  {path: 'Playlist/:id', component: PlaylistComponent, title: 'Playlist'},
  {path: 'comment/:id', component: CommentComponent, title: 'WatchVideo'},
  {path: 'add-lecture/:id', component: UpdateComponent, title: 'Add lecture'},


  {path: '', component: LoginComponent, title: 'Login'},
  {path: 'login', component: LoginComponent, title: 'Login'},
  {path: 'upload', component: UploadComponent, title: 'upload'},
  {path:'registration', component: RegistrationComponent, title: 'Registration'},
  {path:'footer', component: FooterComponent, title: 'Footer'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
