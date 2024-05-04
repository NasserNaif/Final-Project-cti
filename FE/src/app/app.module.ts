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

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterOutlet,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
