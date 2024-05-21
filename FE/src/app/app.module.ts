import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './Pages/header/header.component';
import { FooterComponent } from './Pages/footer/footer.component';
import { LoginComponent } from './Pages/login/login.component';
import { RegistrationComponent } from './Pages/registration/registration.component';
import { UpdateComponent } from './Pages/update/update.component';
import { HomeComponent } from './Pages/home/home.component';
import { UploadComponent } from './Pages/upload/upload.component';
import { HeaderAfterLoginComponent } from './Pages/header-after-login/header-after-login.component';
import { PlaylistComponent } from './Pages/playlist/playlist.component';
import { WatchVideoComponent } from './Pages/watch-video/watch-video.component';
import {ReactiveFormsModule} from "@angular/forms";
import { HttpClientModule} from '@angular/common/http';
import { AuthInterceptorProvider} from "./Pages/interceptors/auth.interceptor";
import {CommentComponent} from "./Pages/comment/comment.component";


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RegistrationComponent,
    UpdateComponent,
    HomeComponent,
    UploadComponent,
    HeaderAfterLoginComponent,
    PlaylistComponent,
    WatchVideoComponent,
    CommentComponent


  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        RouterOutlet,
        ReactiveFormsModule,
      HttpClientModule


    ],
  providers: [
    AuthInterceptorProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
